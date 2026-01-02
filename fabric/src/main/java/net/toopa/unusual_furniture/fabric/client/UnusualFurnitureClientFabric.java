package net.toopa.unusual_furniture.fabric.client;

import net.toopa.unusual_furniture.client.UnusualFurnitureClient;
import net.toopa.unusual_furniture.client.renderer.DrawerRenderer;
import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

import net.fabricmc.api.ClientModInitializer;

public class UnusualFurnitureClientFabric implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		UnusualFurnitureClient.init();
	}
}
