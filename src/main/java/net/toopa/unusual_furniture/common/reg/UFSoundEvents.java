package net.toopa.unusual_furniture.common.reg;

import net.toopa.unusual_furniture.common.UnusualFurniture;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public final class UFSoundEvents {

	private UFSoundEvents() {}

	public static final SoundEvent SQUEAK = register("squeak");

	private static SoundEvent register(String string) {
		return register(UnusualFurniture.id(string));
	}

	private static SoundEvent register(ResourceLocation resourceLocation) {
		return register(resourceLocation, resourceLocation);
	}

	private static SoundEvent register(ResourceLocation resourceLocation, ResourceLocation resourceLocation2) {
		return Registry.register(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createVariableRangeEvent(resourceLocation2));
	}

	public static void init() {
	}
}
