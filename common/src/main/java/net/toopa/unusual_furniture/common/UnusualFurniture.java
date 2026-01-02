package net.toopa.unusual_furniture.common;

import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFCreativeTabs;
import net.toopa.unusual_furniture.common.reg.UFEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.resources.ResourceLocation;

public class UnusualFurniture {
	public static final String MOD_ID = "unusual_furniture";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ResourceLocation id(String name) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
	}

	public static void initRegistries() {
		UFObjects.init();
		UFBlockEntityTypes.init();
		UFCreativeTabs.init();
		UFEntityTypes.init();
	}

	private void initEvents() {
	}
}
