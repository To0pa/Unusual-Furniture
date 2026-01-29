package net.toopa.unusual_furniture.common.block;

import com.mojang.serialization.MapCodec;
import net.toopa.unusual_furniture.common.UnusualFurniture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WaterPlantsBlock extends AbstractBagBlock {

	public static final IntegerProperty PLANT_TYPE = IntegerProperty.create("plant_type", 0, 1);
	private static final MapCodec<WaterPlantsBlock> CODEC = simpleCodec(WaterPlantsBlock::new);
	private static final VoxelShape SHAPE = box(1.0F, 0.0F, 1.0F, 15.0F, 1.0F, 15.0F);

	public WaterPlantsBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends BushBlock> codec() {
		return CODEC;
	}

	@Override
	protected IntegerProperty getPlantTypeProperty() {
		return PLANT_TYPE;
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.below()).getFluidState().is(Fluids.WATER)
				&& level.getBlockState(pos.below()).getFluidState().isSource();
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
		FluidState fluidState = level.getFluidState(pos);
		FluidState fluidState2 = level.getFluidState(pos.above());
		return (fluidState.getType() == Fluids.WATER || state.getBlock() instanceof IceBlock) && fluidState2.getType() == Fluids.EMPTY;
	}

	@Override
	protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		super.entityInside(state, level, pos, entity);
		if (level instanceof ServerLevel && entity instanceof Boat) {
			level.destroyBlock(new BlockPos(pos), true, entity);
		}
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(BuiltInRegistries.ITEM.get(UnusualFurniture.id("water_plants")));
	}
}
