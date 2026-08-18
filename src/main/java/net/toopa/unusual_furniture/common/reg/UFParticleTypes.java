package net.toopa.unusual_furniture.common.reg;

import net.minecraft.core.particles.ParticleType;

import net.toopa.unusual_furniture.common.UnusualFurniture;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

import net.toopa.unusual_furniture.platform.UFRegistries;
import net.toopa.unusual_furniture.platform.UFRegistry;
import net.toopa.unusual_furniture.platform.UFRegistryEntry;

//? fabric {
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
//?}

public final class UFParticleTypes {

	public static final UFRegistry<ParticleType<?>> PARTICLE_TYPES = UFRegistries.create(BuiltInRegistries.PARTICLE_TYPE, UnusualFurniture.MOD_ID);

	private UFParticleTypes() {}

	public static final UFRegistryEntry<SimpleParticleType> FURNITURE_SMOKE = PARTICLE_TYPES.register("furniture_smoke" , () ->
			//? fabric {
			FabricParticleTypes.simple(false)
	        //?} neoforge {
	        /*new SimpleParticleType(false)
	         *///?}
	);

	public static void init() {
		PARTICLE_TYPES.init();
	}
}
