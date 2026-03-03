package net.toopa.unusual_furniture.common.reg;

//? fabric {
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
//?}
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.toopa.unusual_furniture.common.UnusualFurniture;

public class UFParticleTypes {

	public static final SimpleParticleType FURNITURE_SMOKE =
			//? fabric {
			FabricParticleTypes.simple(false);
			//?} neoforge {
			/*new SimpleParticleType(false);
			*///?}

	public static void init() {
		Registry.register(BuiltInRegistries.PARTICLE_TYPE, UnusualFurniture.id("furniture_smoke"), FURNITURE_SMOKE);
	}
}
