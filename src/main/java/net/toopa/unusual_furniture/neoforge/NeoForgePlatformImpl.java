package net.toopa.unusual_furniture.neoforge;

//? neoforge {
/*import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.particle.ParticleEngine;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.toopa.unusual_furniture.Platform;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.toopa.unusual_furniture.UnusualFurniture;
import org.apache.commons.lang3.tuple.Pair;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class NeoForgePlatformImpl implements Platform {
    private static final List<Pair<ItemColor, Supplier<? extends ItemLike>[]>> ITEM_COLORS = Lists.newArrayList();
    private static final List<Pair<BlockColor, Supplier<? extends Block>[]>> BLOCK_COLORS = Lists.newArrayList();

    private static final List<ParticleEntry<?>> PARTICLE_TYPES = Lists.newArrayList();

    @EventBusSubscriber(
            modid = UnusualFurniture.MOD_ID,
            value = Dist.CLIENT
    )
    private final static class ClientEvents {

        private ClientEvents() {}

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

    @Override
    public <T extends ParticleOptions> void registerClientParticleType(ParticleType<T> type, CommonSpriteParticleRegistration<T> factory) {
        PARTICLE_TYPES.add(new ParticleEntry<>(type, factory::create));
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
            ITEM_COLORS.add(Pair.of(itemColor, items));
        } else {
            Minecraft.getInstance().getItemColors().register(itemColor, unpackItems(items));
        }
    }

    @SafeVarargs
    @Override
    public final void registerBlockColors(BlockColor blockColor, Supplier<? extends Block>... blocks) {
        Objects.requireNonNull(blockColor, "color is null!");
        if (Minecraft.getInstance().getBlockColors() == null) {
            BLOCK_COLORS.add(Pair.of(blockColor, blocks));
        } else {
            Minecraft.getInstance().getBlockColors().register(blockColor, unpackBlocks(blocks));
        }
    }

    private record ParticleEntry<T extends ParticleOptions>(ParticleType<T> type, ParticleEngine.SpriteParticleRegistration<T> factory) {
        public void register(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(type, factory);
        }
    }
}
*///?}