package net.toopa.unusual_furniture.common.reg;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.toopa.unusual_furniture.common.UnusualFurniture;

public interface UFCreativeTabs {

    ResourceKey<CreativeModeTab> FURNITURE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), UnusualFurniture.id("01_furniture"));
    CreativeModeTab FURNITURE_TAB = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("item_group.unusual_furniture.unusual_furniture"))
            .icon(() -> new ItemStack(UFObjects.WOOD_SETS.get("oak").chair()/*TODO: should be oak chair*/))
            .displayItems((itemDisplayParameters, output) -> {
                UFObjects.ALL_ITEMS.forEach((map) -> map.forEach((item, resourceLocation) -> output.accept(item)));
                UFObjects.FURNITURE_ITEMS.forEach((map) -> map.forEach((item, resourceLocation) -> output.accept(item)));
            })
            .build();

    ResourceKey<CreativeModeTab> PROPS_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), UnusualFurniture.id("02_props"));
    CreativeModeTab PROPS_TAB = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 1)
            .title(Component.translatable("item_group.unusual_furniture.unusual_furniture_props"))
            .icon(() -> new ItemStack(UFObjects.WOOD_SETS.get("oak").table()/*TODO: should be huge pot*/))
            .displayItems((itemDisplayParameters, output) -> {
                UFObjects.ALL_ITEMS.forEach((map) -> map.forEach((item, resourceLocation) -> output.accept(item)));
                UFObjects.PROPS_ITEMS.forEach((map) -> map.forEach((item, resourceLocation) -> output.accept(item)));
            })
            .build();

    ResourceKey<CreativeModeTab> BUILDING_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), UnusualFurniture.id("03_building"));
    CreativeModeTab BUILDING_TAB = CreativeModeTab.builder(CreativeModeTab.Row.TOP, 2)
            .title(Component.translatable("item_group.unusual_furniture.unusual_tab_2"))
            .icon(() -> new ItemStack(UFObjects.WOOD_SETS.get("acacia").open_riser_stairs()/*TODO: should be acacia open raiser stairs*/))
            .displayItems((itemDisplayParameters, output) -> {
                UFObjects.ALL_ITEMS.forEach((map) -> map.forEach((item, resourceLocation) -> output.accept(item)));
                UFObjects.BUILDING_ITEMS.forEach((map) -> map.forEach((item, resourceLocation) -> output.accept(item)));
            })
            .build();

    static void init() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FURNITURE_TAB_KEY, FURNITURE_TAB);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, PROPS_TAB_KEY, PROPS_TAB);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BUILDING_TAB_KEY, BUILDING_TAB);
    }
}
