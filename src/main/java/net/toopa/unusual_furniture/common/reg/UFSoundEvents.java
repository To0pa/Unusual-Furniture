package net.toopa.unusual_furniture.common.reg;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.platform.UFRegistries;
import net.toopa.unusual_furniture.platform.UFRegistry;
import net.toopa.unusual_furniture.platform.UFRegistryEntry;

public final class UFSoundEvents {

	public static final UFRegistry<SoundEvent> SOUND_EVENTS = UFRegistries.create(BuiltInRegistries.SOUND_EVENT, UnusualFurniture.MOD_ID);

	private UFSoundEvents() {}

	public static final UFRegistryEntry<SoundEvent> SQUEAK = register("squeak");

//	private static Holder<SoundEvent> register(ResourceLocation resourceLocation, ResourceLocation resourceLocation2, float f) {
//		return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createFixedRangeEvent(resourceLocation2, f));
//	}

	private static UFRegistryEntry<SoundEvent> register(String name) {
		return register(name, name);
	}

//	private static Holder.Reference<SoundEvent> registerForHolder(String string) {
//		return registerForHolder(ResourceLocation.withDefaultNamespace(string));
//	}

//	private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation resourceLocation) {
//		return registerForHolder(resourceLocation, resourceLocation);
//	}

	private static UFRegistryEntry<SoundEvent> register(String name, String name2) {
		return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(UnusualFurniture.id(name2)));
	}

//	private static Holder.Reference<SoundEvent> registerForHolder(ResourceLocation resourceLocation, ResourceLocation resourceLocation2) {
//		return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, resourceLocation, SoundEvent.createVariableRangeEvent(resourceLocation2));
//	}

	public static void init() {
	}
}
