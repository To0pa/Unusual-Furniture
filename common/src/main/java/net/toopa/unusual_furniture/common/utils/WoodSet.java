package net.toopa.unusual_furniture.common.utils;

import java.util.Objects;
import java.util.stream.Stream;

import net.toopa.unusual_furniture.common.block.BenchBlock;
import net.toopa.unusual_furniture.common.block.CarvedPlanksBlock;
import net.toopa.unusual_furniture.common.block.CeilingLampBlock;
import net.toopa.unusual_furniture.common.block.ChairBlock;
import net.toopa.unusual_furniture.common.block.CoffeeTableBlock;
import net.toopa.unusual_furniture.common.block.DrawerBlock;
import net.toopa.unusual_furniture.common.block.OpenRiserStairBlock;
import net.toopa.unusual_furniture.common.block.ShelfBlock;
import net.toopa.unusual_furniture.common.block.StoolBlock;
import net.toopa.unusual_furniture.common.block.TableBlock;
import org.jspecify.annotations.Nullable;

import net.minecraft.world.level.block.Block;

public record WoodSet(
		Block base,
		TableBlock table,
		CoffeeTableBlock coffee_table,
		ChairBlock chair,
		StoolBlock stool,
		CeilingLampBlock ceiling_lamp,
		DrawerBlock drawer,
		BenchBlock bench,
		ShelfBlock shelf,
		CarvedPlanksBlock carved_planks,
		OpenRiserStairBlock open_riser_stairs,
		Block railing,
		@Nullable Block beam //TODO: make them not nullable
) {
	public Stream<? extends Block> stream() {
		return Stream.of(
				base,
				table,
				coffee_table,
				chair,
				stool,
				ceiling_lamp,
				drawer,
				bench,
				shelf,
				carved_planks,
				open_riser_stairs,
				railing,
				beam
		).filter(Objects::nonNull);
	}
}
