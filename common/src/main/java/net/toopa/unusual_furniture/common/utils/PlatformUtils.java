package net.toopa.unusual_furniture.common.utils;

import java.util.Objects;
import java.util.function.Supplier;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.targets.ArchitecturyTarget;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
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

	public static void registerItemColors(ItemColor color, ItemLike... items) {
		Supplier<ItemLike>[] array = new Supplier[items.length];
		for (var i = 0; i < items.length; i++) {
			var item = Objects.requireNonNull(items[i], "items[i] is null!");
			array[i] = () -> item;
		}
		registerItemColors(color, array);
	}

	public static void registerBlockColors(BlockColor color, Block... blocks) {
		Supplier<Block>[] array = new Supplier[blocks.length];
		for (var i = 0; i < blocks.length; i++) {
			var block = Objects.requireNonNull(blocks[i], "blocks[i] is null!");
			array[i] = () -> block;
		}
		registerBlockColors(color, array);
	}

	@SafeVarargs
	@ExpectPlatform
	public static void registerItemColors(ItemColor color, Supplier<? extends ItemLike>... items) {
		throw new AssertionError("This should not happen");
	}

	@SafeVarargs
	@ExpectPlatform
	public static void registerBlockColors(BlockColor color, Supplier<? extends Block>... blocks) {
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
