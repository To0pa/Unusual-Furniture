package net.toopa.unusual_furniture.common.utils.neoforge;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.collect.Lists;

import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import net.toopa.unusual_furniture.common.UnusualFurniture;
import net.toopa.unusual_furniture.common.utils.PlatformUtils;
import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

public class PlatformUtilsImpl {
	private static final List<Pair<ItemColor, Supplier<? extends ItemLike>[]>> ITEM_COLORS = Lists.newArrayList();
	private static final List<Pair<BlockColor, Supplier<? extends Block>[]>> BLOCK_COLORS = Lists.newArrayList();

	private static final List<ParticleEntry<?>> PARTICLE_TYPES = Lists.newArrayList();

	static {
		whenAvailable(UnusualFurniture.MOD_ID, bus -> {
			bus.register(PlatformUtilsImpl.class);
		});
	}

	@SubscribeEvent
	public static void onItemColorEvent(RegisterColorHandlersEvent.Item event) {
		for (Pair<ItemColor, Supplier<? extends ItemLike>[]> pair : ITEM_COLORS) {
			event.register(pair.getLeft(), unpackItems(pair.getRight()));
		}
	}

	@SubscribeEvent
	public static void onBlockColorEvent(RegisterColorHandlersEvent.Block event) {
		for (Pair<BlockColor, Supplier<? extends Block>[]> pair : BLOCK_COLORS) {
			event.register(pair.getLeft(), unpackBlocks(pair.getRight()));
		}
	}

	@SubscribeEvent
	public static void onRegisterParticles(RegisterParticleProvidersEvent event) {
		for (ParticleEntry<?> entry : PARTICLE_TYPES) {
			entry.register(event);
		}
	}

	public static CreativeModeTab.Builder creativeModeTabBuilder() {
		return CreativeModeTab.builder();
	}

	public static void registerRenderType(RenderType renderType, Block... blocks) {
		for (Block block : blocks) {
			ItemBlockRenderTypes.setRenderLayer(block, renderType);
		}
	}

	public static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> type, EntityRendererProvider<T> provider) {
		EntityRenderers.register(type, provider);
	}

	@SafeVarargs
	public static void registerItemColors(ItemColor itemColor, Supplier<? extends ItemLike>... items) {
		Objects.requireNonNull(itemColor, "color is null!");
		if (Minecraft.getInstance().getItemColors() == null) {
			ITEM_COLORS.add(Pair.of(itemColor, items));
		} else {
			Minecraft.getInstance().getItemColors().register(itemColor, unpackItems(items));
		}
	}

	@SafeVarargs
	public static void registerBlockColors(BlockColor blockColor, Supplier<? extends Block>... blocks) {
		Objects.requireNonNull(blockColor, "color is null!");
		if (Minecraft.getInstance().getBlockColors() == null) {
			BLOCK_COLORS.add(Pair.of(blockColor, blocks));
		} else {
			Minecraft.getInstance().getBlockColors().register(blockColor, unpackBlocks(blocks));
		}
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

	public static SimpleParticleType createSimpleParticleType() {
		return new SimpleParticleType(false);
	}

	public static <T extends ParticleOptions> void registerClientParticleType(ParticleType<T> type, PlatformUtils.CommonSpriteParticleRegistration<T> factory) {
		PARTICLE_TYPES.add(new ParticleEntry<>(type, factory::create));
	}

	private record ParticleEntry<T extends ParticleOptions>(ParticleType<T> type, ParticleEngine.SpriteParticleRegistration<T> factory) {
		public void register(RegisterParticleProvidersEvent event) {
			event.registerSpriteSet(type, factory);
		}
	}

	public static void whenAvailable(String modId, Consumer<IEventBus> busConsumer) {
		IEventBus bus = getModEventBus(modId).orElseThrow(() -> new IllegalStateException("Mod '" + modId + "' is not available!"));
		busConsumer.accept(bus);
	}

	public static Optional<IEventBus> getModEventBus(String modId) {
		return ModList.get().getModContainerById(modId)
				.map(ModContainer::getEventBus);
	}
}
