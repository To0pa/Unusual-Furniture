package net.toopa.unusual_furniture.neoforge;

//? neoforge {

/*import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.toopa.unusual_furniture.CommonAbstraction;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

public record NeoCommonAbstraction(List<Consumer<IEventBus>> lateActions) implements CommonAbstraction {
	public static @Nullable IEventBus EVENT_BUS = null;

	public static NeoCommonAbstraction instance() {
		return (NeoCommonAbstraction) CommonAbstraction.INSTANCE;
	}

	@Override
	public boolean isModLoaded(String modid) {
		return ModList.get().isLoaded(modid);
	}

	@Override
	public String loader() {
		return "neoforge";
	}

	@Override
	public Path getConfigDir() {
		return FMLPaths.CONFIGDIR.get();
	}

	@Override
	public boolean isDevelopment() {
		return !FMLEnvironment.production;
	}

	//TODO: move to client
	@Override
	public <T extends ParticleOptions> void registerClientParticleType(ParticleType<T> type, CommonSpriteParticleRegistration<T> factory) {
		addLateAction(bus -> {
			bus.addListener(RegisterParticleProvidersEvent.class, e -> {
				e.registerSpriteSet(type, factory::create);
			});
		});
	}

	@Override
	public void registerRenderType(RenderType renderType, Block... blocks) {
		for (Block block : blocks) {
			ItemBlockRenderTypes.setRenderLayer(block, renderType);
		}
	}

	@Override
	public <T extends Entity> void registerEntityRenderer(EntityType<? extends T> type, EntityRendererProvider<T> provider) {
		EntityRenderers.register(type, provider);
	}

	//TODO: move to client
	@Override
	public void registerLayerDefinition(ModelLayerLocation modelLayer, CommonTexturedModelDataProvider provider) {
		addLateAction(bus -> {
			bus.addListener(EntityRenderersEvent.RegisterLayerDefinitions.class, e -> {
				e.registerLayerDefinition(modelLayer, provider::createModelData);
			});
		});
	}

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
		ItemLike[] array = new ItemLike[items.length];
		for (int i = 0; i < items.length; i++) {
			array[i] = Objects.requireNonNull(items[i].get());
		}
		return array;
	}

	private static Block[] unpackBlocks(Supplier<? extends Block>[] blocks) {
		Block[] array = new Block[blocks.length];
		for (int i = 0; i < blocks.length; i++) {
			array[i] = Objects.requireNonNull(blocks[i].get());
		}
		return array;
	}

	@SafeVarargs
	@Override
	public final void registerItemColors(ItemColor itemColor, Supplier<? extends ItemLike>... items) {
		Objects.requireNonNull(itemColor, "color is null!");
		if (Minecraft.getInstance().getItemColors() == null) {
			addLateAction(bus -> {
				bus.addListener(RegisterColorHandlersEvent.Item.class, e -> {
					e.register(itemColor, unpackItems(items));
				});
			});
		} else {
			Minecraft.getInstance().getItemColors().register(itemColor, unpackItems(items));
		}
	}

	@SafeVarargs
	@Override
	public final void registerBlockColors(BlockColor blockColor, Supplier<? extends Block>... blocks) {
		Objects.requireNonNull(blockColor, "color is null!");
		if (Minecraft.getInstance().getBlockColors() == null) {
			addLateAction(bus -> {
				bus.addListener(RegisterColorHandlersEvent.Block.class, e -> {
					e.register(blockColor, unpackBlocks(blocks));
				});
			});
		} else {
			Minecraft.getInstance().getBlockColors().register(blockColor, unpackBlocks(blocks));
		}
	}

	public void addLateAction(Consumer<IEventBus> consumer) {
		if (EVENT_BUS != null) {
			consumer.accept(EVENT_BUS);
		} else {
			this.lateActions.add(consumer);
		}
	}
}
*///?}
