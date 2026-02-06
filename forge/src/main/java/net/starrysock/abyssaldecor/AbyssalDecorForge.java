package net.starrysock.abyssaldecor;

import dev.architectury.platform.forge.EventBuses;
import dev.architectury.registry.registries.RegistrySupplier;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
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
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
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
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;

import java.util.List;

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
        MinecraftForge.EVENT_BUS.addListener(this::rightClickBlock);
        MinecraftForge.EVENT_BUS.addListener(this::trades);
        MinecraftForge.EVENT_BUS.addListener(this::wanderingTrades);
    }

    void trades(VillagerTradesEvent event) {
        VillagerProfession type = event.getType();
        Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
        if (type == VillagerProfession.FISHERMAN) {
            trades.get(2).add(new BasicItemListing(new ItemStack(AbyssalDecorBlocks.WHITE_PEARL.get()), new ItemStack(Items.EMERALD), 10, 2, 0.05F));
        }

        if (type == VillagerProfession.FISHERMAN) {
            trades.get(1).add(new BasicItemListing(new ItemStack(AbyssalDecorItems.SHELL.get(), 4), new ItemStack(Items.EMERALD), 12, 8, 0.05F));
        }

        if (type == VillagerProfession.FISHERMAN) {
            trades.get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 2), new ItemStack(AbyssalDecorBlocks.BLOOD_CORAL_BUD.get()), 10, 5, 0.05F));
            trades.get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.CLAM.get(), 2), 10, 5, 0.05F));
            trades.get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.STARFISH.get(), 2), 10, 5, 0.05F));
        }

        if (type == VillagerProfession.FARMER) {
            trades.get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.AMARANTH.get()), 10, 5, 0.05F));
            trades.get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorItems.MUCKROOT.get(), 4), 10, 5, 0.05F));
            trades.get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.BOG_APPLE_LEAVES.get()), 10, 5, 0.05F));
            trades.get(3).add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.SPIDERCORN.get()), 10, 5, 0.05F));
            trades.get(3).add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.WISTERIA.get()), 10, 5, 0.05F));
            trades.get(4).add(new BasicItemListing(new ItemStack(Items.EMERALD, 2), new ItemStack(AbyssalDecorBlocks.CINNAMON_BUSH.get()), 10, 5, 0.05F));
        }

        if (type == VillagerProfession.CARTOGRAPHER) {
            trades.get(3).add(new BasicItemListing(new ItemStack(Items.EMERALD, 4), new ItemStack(AbyssalDecorItems.RAW_SEABRASS.get()), 10, 5, 0.05F));
        }
    }
    public void wanderingTrades(WandererTradesEvent event) {
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 4), new ItemStack(AbyssalDecorBlocks.BARK_ORCHID.get()), 4, 5, 0.05F));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.ASTER.get(), 4), 10, 5, 0.05F));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.DAFFODIL.get(), 4), 10, 5, 0.05F));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.SNAPLEAF.get()), 10, 5, 0.05F));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.BOG_APPLE_LEAVES.get(), 2), 10, 5, 0.05F));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.SPIDERCORN.get(), 2), 10, 5, 0.05F));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD, 2), new ItemStack(AbyssalDecorBlocks.AMARANTH.get()), 10, 5, 0.05F));
        event.getGenericTrades().add(new BasicItemListing(new ItemStack(Items.EMERALD), new ItemStack(AbyssalDecorBlocks.WISTERIA.get(), 4), 10, 5, 0.05F));
    }

    void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        InteractionResult interactionResult = AbyssalDecor.rightClickBlock(event.getLevel(),event.getEntity(), event.getHand(), event.getPos(), event.getFace());
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
