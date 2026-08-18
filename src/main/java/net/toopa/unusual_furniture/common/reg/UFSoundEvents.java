package net.toopa.unusual_furniture.common.reg;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

import net.toopa.unusual_furniture.common.UnusualFurniture;

public final class UFSoundEvents {

	private UFSoundEvents() {}

	public static final SoundEvent SQUEAK = register("squeak");

//	private static Holder<SoundEvent> register(ResourceLocation resourceLocation, ResourceLocation resourceLocation2, float f) {
//		return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createFixedRangeEvent(resourceLocation2, f));
//	}

	private static SoundEvent register(String string) {
		return register(UnusualFurniture.id(string));
	}

	private static SoundEvent register(ResourceLocation resourceLocation) {
		return register(resourceLocation, resourceLocation);
	}

//	private static Holder.Reference<SoundEvent> registerForHolder(String string) {
//		return registerForHolder(ResourceLocation.withDefaultNamespace(string));
//	}

//	private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation resourceLocation) {
//		return registerForHolder(resourceLocation, resourceLocation);
//	}

	private static SoundEvent register(ResourceLocation resourceLocation, ResourceLocation resourceLocation2) {
		return Registry.register(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createVariableRangeEvent(resourceLocation2));
	}

//	private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation resourceLocation, ResourceLocation resourceLocation2) {
//		return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createVariableRangeEvent(resourceLocation2));
//	}

	public static void init() {
	}
}
