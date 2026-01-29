package net.toopa.unusual_furniture.fabric.client;

import net.toopa.unusual_furniture.client.UnusualFurnitureClient;

import net.fabricmc.api.ClientModInitializer;

public class UnusualFurnitureClientFabric implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		UnusualFurnitureClient.init();
	}
}
