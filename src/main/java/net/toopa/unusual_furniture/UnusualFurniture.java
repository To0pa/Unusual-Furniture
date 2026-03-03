package net.toopa.unusual_furniture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnusualFurniture {

    public static final String MOD_ID = "unusual_furniture";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        LOG.info("Initializing {} on {}", MOD_ID, Platform.INSTANCE.loader());
    }

}
