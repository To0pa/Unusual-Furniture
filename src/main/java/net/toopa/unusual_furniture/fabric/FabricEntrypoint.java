package net.toopa.unusual_furniture.fabric;

//? fabric {
import net.toopa.unusual_furniture.UFEventHandler;
import net.toopa.unusual_furniture.UnusualFurniture;
import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFCreativeTabs;
import net.toopa.unusual_furniture.common.reg.UFEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFObjects;
import net.toopa.unusual_furniture.common.reg.UFParticleTypes;
import net.toopa.unusual_furniture.common.reg.UFSoundEvents;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		UnusualFurniture.init();

		initRegistries();

		UseBlockCallback.EVENT.register(UFEventHandler::onBlockClick);
	}

	public static void initRegistries() {
		UFObjects.init();
		UFBlockEntityTypes.init();
		UFCreativeTabs.init();
		UFEntityTypes.init();
		UFParticleTypes.init();
		UFSoundEvents.init();
	}
}
//?}
