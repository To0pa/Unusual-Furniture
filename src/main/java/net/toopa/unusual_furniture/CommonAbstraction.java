package net.toopa.unusual_furniture;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;

//? < 26.1 {
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.RenderType;
//?} else {
/*import net.minecraft.client.renderer.rendertype.RenderType;
*///?}
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

//? fabric {
import net.toopa.unusual_furniture.fabric.FabricCommonAbstraction;
//?}
//? neoforge {
/*import java.util.ArrayList;

import net.toopa.unusual_furniture.neoforge.NeoCommonAbstraction;
 *///?}

public interface CommonAbstraction {

	//? fabric {
	CommonAbstraction INSTANCE = new FabricCommonAbstraction();
	//?}
	//? neoforge {
	/*CommonAbstraction INSTANCE = new NeoCommonAbstraction(new ArrayList<>());
	 *///?}

	boolean isModLoaded(String modid);

	String loader();

	Path getConfigDir();

	boolean isDevelopment();

	<T extends ParticleOptions> void registerClientParticleType(ParticleType<T> type, CommonSpriteParticleRegistration<T> factory);

	void registerRenderType(RenderType renderType, Block... blocks);

	<T extends Entity> void registerEntityRenderer(EntityType<? extends T> type, EntityRendererProvider<T> provider);

	void registerLayerDefinition(ModelLayerLocation modelLayer, CommonTexturedModelDataProvider provider);

	void registerItemColors(ItemColor color, Supplier<? extends ItemLike>... items);

	void registerBlockColors(BlockColor color, Supplier<? extends Block>... blocks);

	default void registerItemColors(ItemColor color, ItemLike... items) {
		Supplier<ItemLike>[] array = new Supplier[items.length];
		for (var i = 0; i < items.length; i++) {
			var item = Objects.requireNonNull(items[i], "items[i] is null!");
			array[i] = () -> item;
		}
		registerItemColors(color, array);
	}

	default void registerBlockColors(BlockColor color, Block... blocks) {
		Supplier<Block>[] array = new Supplier[blocks.length];
		for (var i = 0; i < blocks.length; i++) {
			var block = Objects.requireNonNull(blocks[i], "blocks[i] is null!");
			array[i] = () -> block;
		}
		registerBlockColors(color, array);
	}

	@FunctionalInterface
	interface CommonSpriteParticleRegistration<T extends ParticleOptions> {
		ParticleProvider<T> create(SpriteSet sprites);
	}

	@FunctionalInterface
	interface CommonTexturedModelDataProvider {
		LayerDefinition createModelData();
	}
}
