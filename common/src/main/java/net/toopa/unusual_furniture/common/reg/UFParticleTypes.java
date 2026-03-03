package net.toopa.unusual_furniture.common.reg;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;

import net.minecraft.core.registries.BuiltInRegistries;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.utils.PlatformUtils;

public class UFParticleTypes {

	public static final SimpleParticleType FURNITURE_SMOKE = PlatformUtils.createSimpleParticleType();

	public static void init() {
		Registry.register(BuiltInRegistries.PARTICLE_TYPE, UnusualFurniture.id("furniture_smoke"), FURNITURE_SMOKE);
	}
}
