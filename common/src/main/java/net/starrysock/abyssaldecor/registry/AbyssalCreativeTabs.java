package net.starrysock.abyssaldecor.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.starrysock.abyssaldecor.AbyssalDecor;

public class AbyssalCreativeTabs {

    public static final RegistrySupplier<CreativeModeTab> MAIN_TAB = AbyssalDecor.TABS.register("main", () ->
            CreativeModeTab.builder(null,-1)
                    .title(Component.translatable("itemGroup." + AbyssalDecor.MOD_ID + ".main"))
                    .icon(() -> new ItemStack(AbyssalDecorItems.ABYSSAL_LANTERN.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        AbyssalDecor.ITEMS.forEach(itemRegistrySupplier -> {
                            if (!hidden(itemRegistrySupplier.get())) {
                                output.accept(itemRegistrySupplier.get());
                            }
                        });
                    })
                    .build());

    static boolean hidden(Item item) {
        return item == AbyssalDecorItems.LE_FISHE_AU_CHOCOLAT.get() || item == AbyssalDecorItems.HEALING_CINNAMON_LOG.get()
                || item == AbyssalDecorItems.HEALING_CINNAMON_WOOD.get()
                || item == AbyssalDecorItems.CUT_CINNAMON_LOG.get()
                || item == AbyssalDecorItems.CUT_CINNAMON_WOOD.get();
    }

    public static void register() {
        AbyssalDecor.TABS.register();
    }
}
