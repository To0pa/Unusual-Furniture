package net.toopa.unusual_furniture.common.reg;

import net.toopa.unusual_furniture.common.entity.SeatEntity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public interface UFEntityTypes {

	EntityType<SeatEntity> SEAT = Registry.register(BuiltInRegistries.ENTITY_TYPE, "seat",
			EntityType.Builder.<SeatEntity>of(SeatEntity::new, MobCategory.MISC)
					.sized(1, 1)
					.noSave()
					.fireImmune()
					.noSummon()
					.build("seat"));

	static void init() {
	}
}
