package net.toopa.unusual_furniture.fabric;

//? fabric {
import net.fabricmc.api.ClientModInitializer;
import net.toopa.unusual_furniture.client.UnusualFurnitureClient;

public class FabricClientEntrypoint implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        UnusualFurnitureClient.init();
    }

}
//?}