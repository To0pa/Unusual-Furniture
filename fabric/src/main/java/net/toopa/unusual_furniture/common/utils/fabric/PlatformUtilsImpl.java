package net.toopa.unusual_furniture.common.utils.fabric;

import java.util.Objects;
import java.util.function.Supplier;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import net.toopa.unusual_furniture.common.utils.PlatformUtils;

public class PlatformUtilsImpl {

	public static CreativeModeTab.Builder creativeModeTabBuilder() {
		return CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0);
	}

	public static void registerRenderType(RenderType renderType, Block... blocks) {
		BlockRenderLayerMap.INSTANCE.putBlocks(renderType, blocks);
	}

	public static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> type, EntityRendererProvider<T> provider) {
		EntityRendererRegistry.register(type, provider);
	}

	@SafeVarargs
	public static void registerItemColors(ItemColor itemColor, Supplier<? extends ItemLike>... items) {
		Objects.requireNonNull(itemColor, "color is null!");
		ColorProviderRegistry.ITEM.register(itemColor, unpackItems(items));
	}

	@SafeVarargs
	public static void registerBlockColors(BlockColor blockColor, Supplier<? extends Block>... blocks) {
		Objects.requireNonNull(blockColor, "color is null!");
		ColorProviderRegistry.BLOCK.register(blockColor, unpackBlocks(blocks));
	}

	private static ItemLike[] unpackItems(Supplier<? extends ItemLike>[] items) {
		var array = new ItemLike[items.length];
		for (var i = 0; i < items.length; i++) {
			array[i] = Objects.requireNonNull(items[i].get());
		}
		return array;
	}

	private static Block[] unpackBlocks(Supplier<? extends Block>[] blocks) {
		var array = new Block[blocks.length];
		for (var i = 0; i < blocks.length; i++) {
			array[i] = Objects.requireNonNull(blocks[i].get());
		}
		return array;
	}

	public static SimpleParticleType createSimpleParticleType() {
		return FabricParticleTypes.simple(false);
	}

	public static <T extends ParticleOptions> void registerClientParticleType(ParticleType<T> type, PlatformUtils.CommonSpriteParticleRegistration<T> factory) {
		ParticleFactoryRegistry.getInstance().register(type, factory::create);
	}
}
