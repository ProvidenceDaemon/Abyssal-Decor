package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.starrysock.abyssaldecor.AbyssalDecor;

public class AbyssalCreativeTabs {

    public static final RegistrySupplier<CreativeModeTab> PALETTE_TAB = AbyssalDecor.TABS.register("palette_tab", () ->
            CreativeModeTab.builder(null,-1)
                    .title(Component.translatable("itemGroup." + AbyssalDecor.MOD_ID + ".palette_tab"))
                    .icon(() -> new ItemStack(AbyssalDecorItems.SOLAR_ROD.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(AbyssalDecorItems.SOLAR_ROD.get());
                        output.accept(AbyssalDecorItems.STELLAR_ROD.get());
                        output.accept(AbyssalDecorItems.TERRESTRIAL_ROD.get());
                        output.accept(AbyssalDecorItems.LUNAR_ROD.get());
                        output.accept(AbyssalDecorItems.ETHEREAL_ROD.get());
                        output.accept(AbyssalDecorItems.HANGING_MOSS.get());

                        output.accept(AbyssalDecorItems.DAFFODIL.get());
                        output.accept(AbyssalDecorItems.ASTER.get());
                        output.accept(AbyssalDecorItems.SNAPLEAF.get());

                        output.accept(AbyssalDecorItems.AMARANTH_SEEDS.get());
                        output.accept(AbyssalDecorItems.AMARANTH_PINNACLE.get());
                        output.accept(AbyssalDecorItems.AMARANTH_CRATE.get());
                        output.accept(AbyssalDecorItems.MUCKROOT.get());
                        output.accept(AbyssalDecorItems.COOKED_MUCKROOT.get());
                        output.accept(AbyssalDecorItems.MUCKROOT_SOUP.get());
                        output.accept(AbyssalDecorItems.BARK_ORCHID.get());

                        output.accept(AbyssalDecorItems.WISTERIA_PETALS.get());
                        output.accept(AbyssalDecorItems.ELDER_WISTERIA_PETALS.get());
                        output.accept(AbyssalDecorItems.ELDER_WISTERIA_LEAVES.get());
                        output.accept(AbyssalDecorItems.ANCIENT_BIRCH_LOG.get());
                        output.accept(AbyssalDecorItems.STRIPPED_ANCIENT_BIRCH_LOG.get());

                        output.accept(AbyssalDecorItems.FOXY_PILLAR.get());

                        output.accept(AbyssalDecorItems.DUSTY_CD.get());
                        output.accept(AbyssalDecorItems.SCRIMSHAW.get());
                        output.accept(AbyssalDecorItems.DESK_BELL.get());

                        output.accept(AbyssalDecorItems.LIGHTBULB.get());
                        output.accept(AbyssalDecorItems.WALL_BULB_LAMP.get());
                        output.accept(AbyssalDecorItems.TUBE_LAMP.get());
                        output.accept(AbyssalDecorItems.IRON_LAMP.get());
                        output.accept(AbyssalDecorItems.FLOWER_LAMP.get());
                    })
                    .build());

    public static void register() {
        AbyssalDecor.TABS.register();
    }
}
