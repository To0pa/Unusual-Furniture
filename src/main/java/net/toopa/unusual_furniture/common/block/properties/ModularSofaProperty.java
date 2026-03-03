package net.toopa.unusual_furniture.common.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum ModularSofaProperty implements StringRepresentable {
	SINGLE,
	LEFT,
	MIDDLE,
	RIGHT,
	INNER_LEFT,
	INNER_RIGHT,
	OUTER_LEFT,
	OUTER_RIGHT,
	;

	@Override
	public String getSerializedName() {
		return name().toLowerCase(Locale.ROOT);
	}

	@Override
	public String toString() {
		return getSerializedName();
	}

	public String getLocation() {
		return switch (this) {
			case INNER_LEFT, INNER_RIGHT -> "corner";
			case OUTER_LEFT, OUTER_RIGHT -> "corner_inverted";
			default -> getSerializedName();
		};
	}
}
