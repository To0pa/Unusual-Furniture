package net.toopa.unusual_furniture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.toopa.unusual_furniture.common.block.properties.BroomProperty;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;

import java.util.Map;

public class RakeBlock extends BroomBlock {

	private static final VoxelShape DEFAULT_STANDING_SHAPE = Shapes.or(
			box(7.2, 6.85, 8.75F, 9.2, 24.85, 10.75F),
			box(7.1, 19.75F, 8.65, 9.3, 23.95, 10.85),
			box(7.1, 4.75F, 8.65, 9.3, 8.95, 10.85)
	);
	private static final Map<Direction, VoxelShape> STANDING_SHAPE_MAP = VoxelShapeUtils.createHorizontalShapeMap(DEFAULT_STANDING_SHAPE);
	private static final VoxelShape DEFAULT_LAYING_SHAPE = Shapes.or(
			box(7.2, 0.2, 3.2, 9.2, 2.2, 21.2),
			box(7.1, 0.1, 16.1, 9.3, 2.3, 20.3),
			box(7.1, 0.1, 1.1, 9.3, 2.3, 5.3)
	);
	private static final Map<Direction, VoxelShape> LAYING_SHAPE_MAP = VoxelShapeUtils.createHorizontalShapeMap(DEFAULT_LAYING_SHAPE);

	public RakeBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		inYourFace(state, level, pos, entity);
		super.entityInside(state, level, pos, entity);
	}

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
		inYourFace(state, level, pos, entity);
		super.stepOn(level, pos, state, entity);
	}

	public static Vec3 directionToUnitVector(Direction dir) {
		return switch (dir) {
			case NORTH -> new Vec3(0, 0, -1);
			case SOUTH -> new Vec3(0, 0, 1);
			case EAST  -> new Vec3(1, 0, 0);
			case WEST  -> new Vec3(-1, 0, 0);
			case UP    -> new Vec3(0, 1, 0);
			case DOWN  -> new Vec3(0, -1, 0);
		};
	}

	private void inYourFace(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (state.getValue(PLACEMENT) == BroomProperty.LAYING
				&& entity instanceof LivingEntity livingEntity
				&& level instanceof ServerLevel serverLevel) {
			livingEntity.hurt(livingEntity.damageSources().generic(), 4.0F);
			livingEntity.setDeltaMovement(directionToUnitVector(state.getValue(FACING)).scale(0.5F));
			livingEntity.hurtMarked = true;
			serverLevel.playSound(null, pos, SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1.0F, 1.5F);
			serverLevel.setBlock(pos, state.setValue(PLACEMENT, BroomProperty.STANDING), RakeBlock.UPDATE_ALL);
		}
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		Direction dir = state.getValue(FACING);
		return state.getValue(PLACEMENT) == BroomProperty.LAYING ? LAYING_SHAPE_MAP.get(dir) : STANDING_SHAPE_MAP.get(dir);
	}
}
