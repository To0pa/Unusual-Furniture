package net.toopa.unusual_furniture.common.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum BroomProperty implements StringRepresentable {
	STANDING,
	LEANING,
	LAYING,
	;

	public BroomProperty cycle() {
		return switch (this) {
			case STANDING -> LEANING;
			case LEANING -> LAYING;
			case LAYING -> STANDING;
		};
	}

	@Override
	public String getSerializedName() {
		return name().toLowerCase(Locale.ROOT);
	}

	@Override
	public String toString() {
		return getSerializedName();
	}
}
