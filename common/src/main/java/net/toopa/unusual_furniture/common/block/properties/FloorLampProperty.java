package net.toopa.unusual_furniture.common.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum FloorLampProperty implements StringRepresentable {
	SINGLE,
	TOP,
	BOTTOM,
	MIDDLE,
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
