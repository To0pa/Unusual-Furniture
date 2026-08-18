package net.toopa.unusual_furniture;

import net.minecraft.core.registries.Registries;

import net.toopa.unusual_furniture.common.reg.UFBlockEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFCreativeTabs;
import net.toopa.unusual_furniture.common.reg.UFEntityTypes;
import net.toopa.unusual_furniture.common.reg.UFObjects;
import net.toopa.unusual_furniture.common.reg.UFParticleTypes;
import net.toopa.unusual_furniture.common.reg.UFSoundEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnusualFurniture {

    public static final String MOD_ID = "unusual_furniture";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        LOG.info("Initializing {} on {}", MOD_ID, CommonAbstraction.INSTANCE.loader());
		UFObjects.init();
		UFBlockEntityTypes.init();
		UFCreativeTabs.init();
		UFEntityTypes.init();
		UFParticleTypes.init();
		UFSoundEvents.init();
    }

}
