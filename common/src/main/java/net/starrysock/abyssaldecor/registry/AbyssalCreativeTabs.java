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
                        AbyssalDecor.ITEMS.forEach(itemRegistrySupplier -> {
                            if (itemRegistrySupplier != AbyssalDecorItems.LE_FISHE_AU_CHOCOLAT) {
                                output.accept(itemRegistrySupplier.get());
                            }
                        });
                    })
                    .build());

    public static void register() {
        AbyssalDecor.TABS.register();
    }
}
