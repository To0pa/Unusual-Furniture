package net.toopa.unusual_furniture.common.block.entity;

import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;

public class DrawerBlockEntity extends BaseContainerBlockEntity {

	private static final int EVENT_SET_OPEN_COUNT = 1;

	private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);

	private final DrawerController drawerController = new DrawerController();

	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {

		@Override
		protected void onOpen(Level level, BlockPos pos, BlockState state) {
			// play drawer opening sound if desired
		}

		@Override
		protected void onClose(Level level, BlockPos pos, BlockState state) {
			// play drawer closing sound if desired
		}

		@Override
		protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int oldCount, int newCount) {
			DrawerBlockEntity.this.signalOpenCount(level, pos, state, oldCount, newCount);
		}

		@Override
		protected boolean isOwnContainer(Player player) {
			if (!(player.containerMenu instanceof ChestMenu menu)) {
				return false;
			}

			return menu.getContainer() == DrawerBlockEntity.this;
		}
	};

	public DrawerBlockEntity(BlockPos pos, BlockState state) {
		super(UFBlockEntityTypes.DRAWER_BLOCK_ENTITY, pos, state);
	}

	@Override
	public int getContainerSize() {
		return 27;
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("gui.unusual_furniture.drawer_gui.label_inventory");
	}

	@Override
	public Component getDisplayName() {
		return getDefaultName();
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);

		this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(tag, items, registries);
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		ContainerHelper.saveAllItems(tag, items, registries);
	}

	@Override
	public void startOpen(Player player) {
		if (!remove && !player.isSpectator()) {
			openersCounter.incrementOpeners(player, level, worldPosition, getBlockState());
		}
	}

	@Override
	public void stopOpen(Player player) {
		if (!remove && !player.isSpectator()) {
			openersCounter.decrementOpeners(player, level, worldPosition, getBlockState());
		}
	}

	@Override
	public boolean triggerEvent(int id, int data) {
		if (id == EVENT_SET_OPEN_COUNT) {
			drawerController.shouldBeOpen(data > 0);
			return true;
		}

		return super.triggerEvent(id, data);
	}

	protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int oldCount, int newCount) {
		level.blockEvent(pos, state.getBlock(), EVENT_SET_OPEN_COUNT, newCount);
	}

	public void recheckOpen() {
		if (!remove) {
			openersCounter.recheckOpeners(level, worldPosition, getBlockState());
		}
	}

	public float getOpenNess(float partialTicks) {
		return drawerController.getOpenness(partialTicks);
	}

	public static void drawerAnimateTick(Level level, BlockPos pos, BlockState state, DrawerBlockEntity drawer) {
		drawer.drawerController.tickDrawer();
	}

//	public static int getOpenCount(BlockGetter getter, BlockPos pos) {
//		BlockEntity be = getter.getBlockEntity(pos);
//
//		if (be instanceof DrawerBlockEntity drawer) {
//			return drawer.openersCounter.getOpenerCount();
//		}
//
//		return 0;
//	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return ChestMenu.threeRows(id, inventory, this);
	}
}
