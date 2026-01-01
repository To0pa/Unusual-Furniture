package net.toopa.unusual_furniture.common.utils;

import net.minecraft.world.level.block.Block;
import net.toopa.unusual_furniture.common.block.*;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

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
