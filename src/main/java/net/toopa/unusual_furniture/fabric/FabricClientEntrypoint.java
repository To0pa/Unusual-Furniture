package net.toopa.unusual_furniture.fabric;

//? fabric {
import net.toopa.unusual_furniture.client.UnusualFurnitureClient;

import net.fabricmc.api.ClientModInitializer;

public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		UnusualFurnitureClient.init();
	}

}
//?}
