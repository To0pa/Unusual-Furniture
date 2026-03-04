package net.toopa.unusual_furniture.fabric;

//? fabric {
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;

import net.toopa.unusual_furniture.Platform;

//? < 26.1 {
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.RenderType;
//?} else {
/*import net.minecraft.client.renderer.rendertype.RenderType;
*///?}
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformImpl implements Platform {

	@Override
	public boolean isModLoaded(String modid) {
		return FabricLoader.getInstance().isModLoaded(modid);
	}

	@Override
	public String loader() {
		return "fabric";
	}

	@Override
	public Path getConfigDir() {
		return FabricLoader.getInstance().getConfigDir();
	}

	@Override
	public boolean isDevelopment() {
		return FabricLoader.getInstance().isDevelopmentEnvironment();
	}

	@Override
	public <T extends ParticleOptions> void registerClientParticleType(ParticleType<T> type, CommonSpriteParticleRegistration<T> factory) {
		ParticleFactoryRegistry.getInstance().register(type, factory::create);
	}

	@Override
	public void registerRenderType(RenderType renderType, Block... blocks) {
		BlockRenderLayerMap.INSTANCE.putBlocks(renderType, blocks);
	}

	@Override
	public <T extends Entity> void registerEntityRenderer(EntityType<? extends T> type, EntityRendererProvider<T> provider) {
		EntityRendererRegistry.register(type, provider);
	}

	//? < 26.1 {
	public void registerItemColors(ItemColor color, ItemLike... items) {
		Supplier<ItemLike>[] array = new Supplier[items.length];
		for (var i = 0; i < items.length; i++) {
			var item = Objects.requireNonNull(items[i], "items[i] is null!");
			array[i] = () -> item;
		}
		registerItemColors(color, array);
	}

	public void registerBlockColors(BlockColor color, Block... blocks) {
		Supplier<Block>[] array = new Supplier[blocks.length];
		for (var i = 0; i < blocks.length; i++) {
			var block = Objects.requireNonNull(blocks[i], "blocks[i] is null!");
			array[i] = () -> block;
		}
		registerBlockColors(color, array);
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

	@SafeVarargs
	@Override
	public final void registerItemColors(ItemColor color, Supplier<? extends ItemLike>... items) {
		Objects.requireNonNull(color, "color is null!");
		ColorProviderRegistry.ITEM.register(color, unpackItems(items));
	}

	@SafeVarargs
	@Override
	public final void registerBlockColors(BlockColor color, Supplier<? extends Block>... blocks) {
		Objects.requireNonNull(color, "color is null!");
		ColorProviderRegistry.BLOCK.register(color, unpackBlocks(blocks));
	}
	//?}
}
//?}
