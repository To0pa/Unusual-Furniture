package net.toopa.unusual_furniture.neoforge;

//? neoforge {
/*import net.minecraft.core.registries.Registries;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import net.toopa.unusual_furniture.UFEventHandler;
import net.toopa.unusual_furniture.UnusualFurniture;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import net.toopa.unusual_furniture.client.UnusualFurnitureClient;
import net.toopa.unusual_furniture.common.reg.*;

@Mod(UnusualFurniture.MOD_ID)
@EventBusSubscriber(modid = net.toopa.unusual_furniture.common.UnusualFurniture.MOD_ID)
public class NeoforgeEntrypoint {

    public NeoforgeEntrypoint() {
        UnusualFurniture.init();
    }

    @EventBusSubscriber(modid = UnusualFurniture.MOD_ID, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientSetup(final FMLClientSetupEvent event) {
            event.enqueueWork(UnusualFurnitureClient::init);
        }
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
		if (event.getRegistryKey().equals(Registries.SOUND_EVENT)) {
			UFSoundEvents.init();
		}
    }

	@SubscribeEvent
	static void onUsBlock(PlayerInteractEvent.RightClickBlock event) {
		UFEventHandler.onBlockClick(event.getEntity(), event.getLevel(), event.getHand(), event.getHitVec());
	}

}
*///?}
