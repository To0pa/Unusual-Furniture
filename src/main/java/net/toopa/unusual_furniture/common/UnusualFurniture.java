package net.toopa.unusual_furniture.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.resources.ResourceLocation;

public class UnusualFurniture {
	public static final String MOD_ID = "unusual_furniture";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ResourceLocation id(String name) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
	}

	private void initEvents() {
	}
}
