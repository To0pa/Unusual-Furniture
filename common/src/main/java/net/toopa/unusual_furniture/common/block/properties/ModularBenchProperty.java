package net.toopa.unusual_furniture.common.block.properties;

import java.util.Locale;

import net.minecraft.util.StringRepresentable;

public enum ModularBenchProperty implements StringRepresentable {
	SINGLE,
	LEFT,
	MIDDLE,
	RIGHT,
	;

	@Override
	public String getSerializedName() {
		return name().toLowerCase(Locale.ROOT);
	}

	@Override
	public String toString() {
		return getSerializedName();
	}
}
