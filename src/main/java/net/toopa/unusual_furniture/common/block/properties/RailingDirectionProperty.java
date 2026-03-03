package net.toopa.unusual_furniture.common.block.properties;

import java.util.Locale;

import net.minecraft.util.StringRepresentable;

public enum RailingDirectionProperty implements StringRepresentable {
	UP,
	DOWN,
	NONE,
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
