package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.utils.VoxelShapeUtils;
import net.toopa.unusualfurniture.init.UnusualFurnitureModBlocks;
import org.jspecify.annotations.Nullable;

public class WallTropicalPlantBlock extends HorizontalDirectionalBlock {

	private static final MapCodec<WallTropicalPlantBlock> CODEC = simpleCodec(WallTropicalPlantBlock::new);
	private static final VoxelShape SHAPE = box( 0.0F,  0.0F,  14.0F,  16.0F,  16.0F,  16.0F);

	public WallTropicalPlantBlock(Properties properties) {
		super(properties.noCollission());
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Direction facing = state.getValue(FACING);
		BlockPos attachedPos = pos.relative(facing.getOpposite());
		BlockState attachedState = level.getBlockState(attachedPos);
		return attachedState.isFaceSturdy(level, attachedPos, facing);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		return direction == state.getValue(FACING).getOpposite() && !state.canSurvive(level, pos)
				? Blocks.AIR.defaultBlockState()
				: super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = this.defaultBlockState()
				.setValue(FACING, context.getClickedFace());
		return state.canSurvive(context.getLevel(), context.getClickedPos()) ? state : null;
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(BuiltInRegistries.ITEM.get(UnusualFurniture.id("tropical_plant")));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case NORTH -> SHAPE;
			case SOUTH -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Y, 180);
			case WEST -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Y, 270);
			case EAST -> VoxelShapeUtils.rotateVoxelShape(SHAPE, Direction.Axis.Y, 90);
			default -> Shapes.block();
		};
	}
}
