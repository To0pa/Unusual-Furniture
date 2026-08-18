package net.toopa.unusual_furniture.common.reg;

import net.minecraft.world.item.CreativeModeTab;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.entity.SeatEntity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import net.toopa.unusual_furniture.platform.UFRegistries;
import net.toopa.unusual_furniture.platform.UFRegistry;
import net.toopa.unusual_furniture.platform.UFRegistryEntry;

public final class UFEntityTypes {

	public static final UFRegistry<EntityType<?>> ENTITY_TYPES = UFRegistries.create(BuiltInRegistries.ENTITY_TYPE, UnusualFurniture.MOD_ID);

	private UFEntityTypes() {}

	public static final UFRegistryEntry<EntityType<SeatEntity>> SEAT = ENTITY_TYPES.register("seat",
			() -> EntityType.Builder.<SeatEntity>of(SeatEntity::new, MobCategory.MISC)
					.sized(1, 1)
					.noSave()
					.fireImmune()
					.noSummon()
					.build("seat"));

	public static void init() {
		ENTITY_TYPES.init();
	}
}
