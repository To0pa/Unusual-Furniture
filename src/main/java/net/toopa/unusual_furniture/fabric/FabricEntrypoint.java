package net.toopa.unusual_furniture.fabric;

//? fabric {
import net.toopa.unusual_furniture.ExampleEventHandler; // sample_content
import net.toopa.unusual_furniture.UnusualFurniture;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents; // sample_content
import net.minecraft.server.level.ServerPlayer; // sample_content
import net.toopa.unusual_furniture.common.reg.*;

public class FabricEntrypoint implements ModInitializer {

    @Override
    public void onInitialize() {
        UnusualFurniture.init();

        initRegistries();
    }

    public static void initRegistries() {
        UFObjects.init();
        UFBlockEntityTypes.init();
        UFCreativeTabs.init();
        UFEntityTypes.init();
        UFParticleTypes.init();
    }
}
//?}