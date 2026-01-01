package net.toopa.unusual_furniture.neoforge;

import net.neoforged.fml.common.Mod;
import net.toopa.unusual_furniture.common.UnusualFurniture;

@Mod(UnusualFurniture.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge() {
        // Run our common setup.
        UnusualFurniture.init();
    }
}
