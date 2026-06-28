package net.toopa.unusual_furniture.common.block.entity;

import net.minecraft.core.NonNullList;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;

import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;
import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class DrawerBlockEntity extends BaseContainerBlockEntity {

	private static final int EVENT_SET_OPEN_COUNT = 1;
	private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
		@Override
		protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {
//			ChestBlockEntity.playSound(level, blockPos, blockState, SoundEvents.CHEST_OPEN);
		}

		@Override
		protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {
//			ChestBlockEntity.playSound(level, blockPos, blockState, SoundEvents.CHEST_CLOSE);
		}

		@Override
		protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int i, int j) {
			DrawerBlockEntity.this.signalOpenCount(level, blockPos, blockState, i, j);
		}

		protected boolean isOwnContainer(Player player) {
			if (!(player.containerMenu instanceof ChestMenu)) {
				return false;
			} else {
				Container container = ((ChestMenu)player.containerMenu).getContainer();
				return container == DrawerBlockEntity.this || container instanceof CompoundContainer && ((CompoundContainer)container).contains(DrawerBlockEntity.this);
			}
		}
	};
	private final DrawerController drawerController = new DrawerController();

	public DrawerBlockEntity(BlockPos pos, BlockState state) {
		super(UFBlockEntityTypes.DRAWER_BLOCK_ENTITY, pos, state);
	}

	private static final String NUM_PLAYER_OPEN_TAG = "NumPlayersOpen";

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		ContainerHelper.saveAllItems(tag, this.items, registries);
		super.saveAdditional(tag, registries);
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		ContainerHelper.loadAllItems(tag, this.items, registries);
	}

	public static void drawerAnimateTick(Level level, BlockPos blockPos, BlockState blockState, DrawerBlockEntity chestBlockEntity) {
		chestBlockEntity.drawerController.tickDrawer();
	}

	@Override
	public boolean triggerEvent(int i, int j) {
		if (i == 1) {
			this.drawerController.shouldBeOpen(j > 0);
			return true;
		} else {
			return super.triggerEvent(i, j);
		}
	}

	@Override
	public int getContainerSize() {
		return 0;
	}

	@Override
	public void startOpen(Player player) {
		if (!this.remove && !player.isSpectator()) {
			this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	@Override
	public void stopOpen(Player player) {
		if (!this.remove && !player.isSpectator()) {
			this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> nonNullList) {
		this.items = nonNullList;
	}

	public float getOpenNess(float f) {
		return this.drawerController.getOpenness(f);
	}

	public static int getOpenCount(BlockGetter blockGetter, BlockPos blockPos) {
		BlockState blockState = blockGetter.getBlockState(blockPos);
		if (blockState.hasBlockEntity()) {
			BlockEntity blockEntity = blockGetter.getBlockEntity(blockPos);
			if (blockEntity instanceof DrawerBlockEntity) {
				return ((DrawerBlockEntity)blockEntity).openersCounter.getOpenerCount();
			}
		}

		return 0;
	}

	@Override
	protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
		return ChestMenu.threeRows(i, inventory, this);
	}

	public void recheckOpen() {
		if (!this.remove) {
			this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	protected void signalOpenCount(Level level, BlockPos blockPos, BlockState blockState, int i, int j) {
		Block block = blockState.getBlock();
		level.blockEvent(blockPos, block, 1, j);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("gui.unusual_furniture.drawer_gui.label_inventory");
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("gui.unusual_furniture.drawer_gui.label_inventory");
	}
}
