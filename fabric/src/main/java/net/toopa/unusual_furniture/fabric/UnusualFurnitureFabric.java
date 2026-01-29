package net.toopa.unusual_furniture.fabric;

import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFCreativeTabs;
import net.toopa.unusual_furniture.common.reg.UFEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFObjects;

import net.fabricmc.api.ModInitializer;

public final class UnusualFurnitureFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// Run our common setup.
		initRegistries();
	}

	public static void initRegistries() {
		UFObjects.init();
		UFBlockEntityTypes.init();
		UFCreativeTabs.init();
		UFEntityTypes.init();
	}
}
