package net.toopa.unusual_furniture.common.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum ModularCurtainProperty implements StringRepresentable {
	SINGLE_CLOSED,
	TOP_CLOSED,
	MIDDLE_CLOSED,
	BOTTOM_CLOSED,
	MIDDLE_OPEN,
	TOP_OPEN,
	LEFT_MIDDLE_OPEN,
	RIGHT_MIDDLE_OPEN,
	LEFT_BOTTOM_OPEN,
	RIGHT_BOTTOM_OPEN,
	LEFT_TOP_OPEN,
	RIGHT_TOP_OPEN,
	;

	@Override
	public String getSerializedName() {
		return name().toLowerCase(Locale.ROOT);
	}

	@Override
	public String toString() {
		return getSerializedName();
	}

	public boolean isClosed() {
		return switch (this) {
			case SINGLE_CLOSED, TOP_CLOSED, MIDDLE_CLOSED, BOTTOM_CLOSED -> true;
			case MIDDLE_OPEN, TOP_OPEN, LEFT_MIDDLE_OPEN, RIGHT_MIDDLE_OPEN, LEFT_BOTTOM_OPEN, RIGHT_BOTTOM_OPEN,
				 LEFT_TOP_OPEN, RIGHT_TOP_OPEN -> false;
		};
	}

	public boolean isOpen() {
		return !this.isClosed();
	}
}
