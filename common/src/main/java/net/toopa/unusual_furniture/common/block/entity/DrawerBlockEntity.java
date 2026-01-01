package net.toopa.unusual_furniture.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.toopa.unusual_furniture.common.block.DrawerBlock;
import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;
import org.jspecify.annotations.Nullable;

public class DrawerBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {

	public final AnimationState animationState0;
	public final AnimationState animationState1;
	private final ContainerOpenersCounter openersCounter;
	private NonNullList<ItemStack> items;

	public DrawerBlockEntity(BlockPos pos, BlockState state) {
		super(UFBlockEntityTypes.DRAWER_BLOCK_ENTITY, pos, state);
		this.items = NonNullList.withSize(27, ItemStack.EMPTY);
		this.animationState0 = new AnimationState();
		this.animationState1 = new AnimationState();

		this.openersCounter = new ContainerOpenersCounter() {
			@Override
			protected void onOpen(Level level, BlockPos pos, BlockState state) {
				playSound(state, SoundEvents.BARREL_OPEN); //TODO: change to custom modded one
				updateBlockState(state, true);
				int gameTime = (int) level.getGameTime();
				animationState0.start(gameTime);
				animationState1.start(gameTime);
			}

			@Override
			protected void onClose(Level level, BlockPos pos, BlockState state) {
				playSound(state, SoundEvents.BARREL_CLOSE); //TODO: change to custom modded one
				updateBlockState(state, false);
				animationState0.stop();
				animationState1.stop();
			}

			@Override
			protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int count, int openCount) {
			}

			@Override
			protected boolean isOwnContainer(Player player) {
				return player.containerMenu instanceof ChestMenu chest && chest.getContainer() == DrawerBlockEntity.this;
			}
		};
	}

	@Override
	protected void saveAdditional(CompoundTag tag, net.minecraft.core.HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		if (!this.trySaveLootTable(tag)) {
			ContainerHelper.saveAllItems(tag, this.items, registries);
		}
	}

	@Override
	protected void loadAdditional(CompoundTag tag, net.minecraft.core.HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(tag)) {
			ContainerHelper.loadAllItems(tag, this.items, registries);
		}
	}

	@Override
	public int getContainerSize() {
		return 27;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.unusual_furniture.drawer");
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
		return ChestMenu.threeRows(id, playerInventory, this);
	}

	public void startOpen(Player player) {
		if (!this.remove && !player.isSpectator()) {
			this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	public void stopOpen(Player player) {
		if (!this.remove && !player.isSpectator()) {
			this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	public void recheckOpen() {
		if (!this.remove) {
			this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	private void updateBlockState(BlockState state, boolean open) {
		this.level.setBlock(this.getBlockPos(), state.setValue(DrawerBlock.OPEN, open), 3);
	}

	private void playSound(BlockState state, SoundEvent sound) {
		Direction facing = state.getValue(DrawerBlock.FACING);
		double x = this.worldPosition.getX() + 0.5 + facing.getStepX() / 2.0;
		double y = this.worldPosition.getY() + 0.5 + facing.getStepY() / 2.0;
		double z = this.worldPosition.getZ() + 0.5 + facing.getStepZ() / 2.0;
		this.level.playSound(null, x, y, z, sound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		int size = getContainerSize();
		int[] slots = new int[size];
		for (int i = 0; i < size; i++) slots[i] = i;
		return slots;
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
		return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
	}
}
