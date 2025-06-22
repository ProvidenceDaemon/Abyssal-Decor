package net.starrysock.abyssaldecor;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.starrysock.abyssaldecor.client.AbyssalDecorClient;
import net.starrysock.abyssaldecor.datagen.Datagen;

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
