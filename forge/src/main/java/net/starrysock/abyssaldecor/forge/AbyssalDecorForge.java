package net.starrysock.abyssaldecor.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.starrysock.abyssaldecor.client.AbyssalDecorClient;
import net.starrysock.abyssaldecor.registry.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

@Mod(AbyssalDecor.MOD_ID)
public class AbyssalDecorForge {
    public AbyssalDecorForge() {
        // Submit our event bus to let architectury register our content on the right time
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(AbyssalDecor.MOD_ID, bus);
        AbyssalDecor.init();
        bus.addListener(Datagen::gather);
        if (FMLEnvironment.dist.isClient()) {
            Client.init(bus);
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

    static class Datagen {
        static void gather(GatherDataEvent event) {
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            var lookupProvider = event.getLookupProvider();
            ExistingFileHelper helper = event.getExistingFileHelper();
            if (event.includeServer()) {
                BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(output,lookupProvider,helper);
                generator.addProvider(true,blockTagsProvider);
            }
        }

        public static class ModBlockTagProvider extends BlockTagsProvider {

            public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
                super(output, lookupProvider, AbyssalDecor.MOD_ID, existingFileHelper);
            }

            @Override
            protected void addTags(HolderLookup.Provider arg) {
                tag(ModTags.Blocks.MUCKROOT_GROWABLE).add(Blocks.FARMLAND);
                tag(ModTags.Blocks.AMARANTH_GROWABLE).addTag(BlockTags.DIRT);
            }
        }
    }
}
