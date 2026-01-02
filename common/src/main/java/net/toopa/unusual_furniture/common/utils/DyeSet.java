package net.toopa.unusual_furniture.common.utils;

import java.util.stream.Stream;

import net.toopa.unusual_furniture.common.block.CurtainBlock;
import net.toopa.unusual_furniture.common.block.SofaBlock;

import net.minecraft.world.level.block.Block;

public record DyeSet(
		Block base,
		SofaBlock sofa,
		CurtainBlock curtain
) {
	public Stream<? extends Block> stream() {
		return Stream.of(
				base,
				sofa,
				curtain
		);
	}
}
