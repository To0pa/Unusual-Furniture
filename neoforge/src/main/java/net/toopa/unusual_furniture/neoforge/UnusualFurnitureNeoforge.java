package net.toopa.unusual_furniture.neoforge;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFCreativeTabs;
import net.toopa.unusual_furniture.common.reg.UFEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFObjects;

import net.minecraft.core.registries.Registries;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

import net.toopa.unusual_furniture.common.reg.UFParticleTypes;

@Mod(UnusualFurniture.MOD_ID)
@EventBusSubscriber(modid = UnusualFurniture.MOD_ID)
public final class UnusualFurnitureNeoforge {
	public UnusualFurnitureNeoforge() {
		// Run our common setup.
	}

	@SubscribeEvent
	static void register(RegisterEvent event) {
		if (event.getRegistryKey().equals(Registries.BLOCK)) {
			UFObjects.init();
		}
		if (event.getRegistryKey().equals(Registries.BLOCK_ENTITY_TYPE)) {
			UFBlockEntityTypes.init();
		}
		if (event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) {
			UFCreativeTabs.init();
		}
		if (event.getRegistryKey().equals(Registries.ENTITY_TYPE)) {
			UFEntityTypes.init();
		}
		if (event.getRegistryKey().equals(Registries.PARTICLE_TYPE)) {
			UFParticleTypes.init();
		}
	}
}
