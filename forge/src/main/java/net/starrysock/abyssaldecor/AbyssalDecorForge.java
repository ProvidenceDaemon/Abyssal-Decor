package net.starrysock.abyssaldecor;

import dev.architectury.platform.forge.EventBuses;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.starrysock.abyssaldecor.client.AbyssalDecorClient;
import net.starrysock.abyssaldecor.datagen.Datagen;
import net.starrysock.abyssaldecor.platform.Services;

@Mod(AbyssalDecor.MOD_ID)
public class AbyssalDecorForge {
    public AbyssalDecorForge() {
        // Submit our event bus to let architectury register our content on the right time
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(AbyssalDecor.MOD_ID, bus);
        AbyssalDecor.init();
        bus.addListener(Datagen::gather);
        bus.addListener(this::setup);
        if (FMLEnvironment.dist.isClient()) {
            Client.init(bus);
        }
    }

    void setup(FMLCommonSetupEvent event) {
        AbyssalDecor.setup();
        if (Services.PLATFORM.isDevelopmentEnvironment()) {
            for (RegistrySupplier<Block> block : AbyssalDecor.BLOCKS) {
                Block block1 = block.get();
                if (block1 instanceof SimpleWaterloggedBlock) {
                    BlockState example = block1.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,true);
                    if (example.getFluidState().is(Fluids.EMPTY)) {
                        AbyssalDecor.LOGGER.error("Block: {} has improper waterlogging!", BuiltInRegistries.BLOCK.getKey(block1));
                    }
                }
            }
        }
    }

    public static class Client {
        static void init(IEventBus bus) {
            bus.addListener(Client::setup);
        }

        static void setup(FMLClientSetupEvent event) {
            AbyssalDecorClient.setup(ItemBlockRenderTypes::setRenderLayer);
        }
    }

}
