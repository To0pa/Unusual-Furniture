package net.toopa.unusual_furniture.common.utils;

import java.util.Objects;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.targets.ArchitecturyTarget;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;

public class PlatformUtils {

	@ExpectPlatform
	public static CreativeModeTab.Builder creativeModeTabBuilder() {
		throw new AssertionError("This should not happen");
	}

	@ExpectPlatform
	public static void registerRenderType(RenderType renderType, Block... blocks) {
		throw new AssertionError("This should not happen");
	}

	@ExpectPlatform
	public static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> type, EntityRendererProvider<T> provider) {
		throw new AssertionError("This should not happen");
	}

	public static boolean isFabric() {
		return Objects.equals(ArchitecturyTarget.getCurrentTarget(), "fabric");
	}

	public static boolean isNeoForge() {
		return Objects.equals(ArchitecturyTarget.getCurrentTarget(), "neoforge");
	}

	public static boolean isForge() {
		return Objects.equals(ArchitecturyTarget.getCurrentTarget(), "forge");
	}
}
