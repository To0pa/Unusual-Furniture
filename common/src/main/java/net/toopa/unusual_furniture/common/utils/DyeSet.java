package net.toopa.unusual_furniture.common.utils;

import net.minecraft.world.level.block.Block;
import net.toopa.unusual_furniture.common.block.CurtainBlock;
import net.toopa.unusual_furniture.common.block.SofaBlock;

import java.util.stream.Stream;

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
