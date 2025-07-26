package net.starrysock.abyssaldecor;

import dev.architectury.platform.forge.EventBuses;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
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
        MinecraftForge.EVENT_BUS.addListener(AbyssalDecorForge::rightClickBlock);
    }

    static void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        InteractionResult interactionResult = AbyssalDecor.rightClickBlock(event.getEntity(), event.getHand(), event.getPos(), event.getFace());
        if (interactionResult.consumesAction()) {
            event.setCanceled(true);
            event.setUseBlock(Event.Result.DENY);
            event.setUseItem(Event.Result.DENY);
        }
    }

    void setup(FMLCommonSetupEvent event) {
        AbyssalDecor.setup();
    }

    public static class Client {
        static void init(IEventBus bus) {
            bus.addListener(Client::setup);
            if (Services.PLATFORM.isDevelopmentEnvironment()) {
                MinecraftForge.EVENT_BUS.addListener(Client::loadComplete);
            }
            MinecraftForge.EVENT_BUS.addListener(Client::tooltips);
        }

        static void tooltips(ItemTooltipEvent event) {
            AbyssalDecorClient.itemTooltips(event.getItemStack(), event.getToolTip(), event.getFlags());
        }

        static void setup(FMLClientSetupEvent event) {
            AbyssalDecorClient.setup(ItemBlockRenderTypes::setRenderLayer);
        }

        static void loadComplete(ScreenEvent.Init event) {
            for (RegistrySupplier<Block> block : AbyssalDecor.BLOCKS) {
                Block block1 = block.get();
                if (block1 instanceof SimpleWaterloggedBlock) {
                    BlockState example = block1.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);
                    FluidState fluidState = example.getFluidState();
                    if (fluidState.is(Fluids.EMPTY)) {
                        AbyssalDecor.LOGGER.error("Block: {} has improper waterlogging!", BuiltInRegistries.BLOCK.getKey(block1));
                    }
                }
            }
        }
    }
}
