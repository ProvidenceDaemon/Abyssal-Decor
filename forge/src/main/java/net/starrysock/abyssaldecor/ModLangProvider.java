package net.starrysock.abyssaldecor;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SignBlock;
import net.minecraftforge.common.data.LanguageProvider;
import net.starrysock.abyssaldecor.registry.AbyssalCreativeTabs;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import org.codehaus.plexus.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, AbyssalDecor.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        Set<RegistrySupplier<Block>> exclude = new HashSet<>();

        AbyssalDecor.BLOCKS.forEach(blockRegistrySupplier -> {
            Block b = blockRegistrySupplier.get();
            if (!(b instanceof SignBlock)) {
                addDefaultBlock(blockRegistrySupplier.get());
            } else {
                addDefaultBlock(blockRegistrySupplier.get());
            }
        });

        addDefaultItem(AbyssalDecorItems.BOG_ROLL.get());
        addDefaultItem(AbyssalDecorItems.BOTTOMLESS_BAG_OF_COBBLESTONE.get());
        addDefaultItem(AbyssalDecorItems.BOTTOMLESS_BAG_OF_DIRT.get());
        addDefaultItem(AbyssalDecorItems.BOTTOMLESS_BAG_OF_NETHERRACK.get());
        addDefaultItem(AbyssalDecorItems.BOTTOMLESS_BAG_OF_SNOW.get());

        addDefaultItem(AbyssalDecorItems.DEEPBRONZE_INGOT.get());
        addDefaultItem(AbyssalDecorItems.DEEPBRONZE_NUGGET.get());

        addDefaultItem(AbyssalDecorItems.CANDY_BOG_APPLE.get());
        addDefaultItem(AbyssalDecorItems.COOKED_MUCKROOT.get());
        addDefaultItem(AbyssalDecorItems.MUCKROOT.get());
        addDefaultItem(AbyssalDecorItems.MUCKROOT_SOUP.get());
        addDefaultItem(AbyssalDecorItems.POPPED_SPIDERCORN.get());

        addDefaultItem(AbyssalDecorItems.RAW_SEABRASS.get());

        addDefaultItem(AbyssalDecorItems.SEABRASS_INGOT.get());
        addDefaultItem(AbyssalDecorItems.SEABRASS_NUGGET.get());

        addDefaultItem(AbyssalDecorItems.SPIDERCORN.get());
        addDefaultItem(AbyssalDecorItems.SPIDERCORN_TORTILLA.get());

        addDefaultItem(AbyssalDecorItems.TOASTED_AMARANTH_SEEDS.get());

        add("item.abyssaldecor.amaranth_pinnacle", "Amaranth Pinnacle");
        add("item.abyssaldecor.amaranth_seeds", "Amaranth Seeds");
        add("item.abyssaldecor.black_pearl", "Black Pearl");
        add("item.abyssaldecor.black_pearl_meal", "Black Pearl Meal");
        add("item.abyssaldecor.blood_coral_bud", "Blood Coral Bud");
        add("item.abyssaldecor.bog_apple_leaves", "Bog Apple Leaves");
        add("item.abyssaldecor.bog_apple", "Bog Apple");
        add("item.abyssaldecor.seabrass_chain", "Seabrass Chain");
        add("item.abyssaldecor.deepbronze_chain", "Deepbronze Chain");

        add("item.abyssaldecor.cinnamon_apple", "Cinnamon Apple");
        add("item.abyssaldecor.cinnamon_roll", "Cinnamon Roll");
        add("item.abyssaldecor.cinnamon_stick", "Cinnamon Stick");
        add("item.abyssaldecor.cinnamon_tea", "Cinnamon Tea");
        add("item.abyssaldecor.fever_blossom", "Fever Blossom");

        add("item.abyssaldecor.fever_blossom_tea", "Fever Blossom Tea");
        add("item.abyssaldecor.grime", "Grime");
        add("item.abyssaldecor.grime_carpet", "Grime Carpet");
        add("item.abyssaldecor.moldy_fuzz", "Moldy Fuzz");
        add("item.abyssaldecor.bulbous_moldy_hanger", "Bulbous Moldy Hanger");
        add("item.abyssaldecor.moldy_hanger", "Moldy Hanger");
        add("item.abyssaldecor.moldy_stalk_solo", "Moldy Stalk Solo");
        add("item.abyssaldecor.moldy_sprouts", "Moldy sprouts");
        add("item.abyssaldecor.moldy_stalks", "Moldy Stalks");
        add("item.abyssaldecor.bark_orchid", "Bark Orchid");

        add("item.abyssaldecor.dried_starfish", "Dried Starfish");
        add("item.abyssaldecor.starlight", "Starlight");
        add("item.abyssaldecor.telescope_bottom", "Telescope");
        add("item.abyssaldecor.velvet_barrier_base", "Velvet Barrier");
        add("item.abyssaldecor.snapleaf", "Snapleaf");
        add("item.abyssaldecor.white_pearl", "White Pearl");
        add("item.abyssaldecor.white_pearlmeal", "White Pearlmeal");
        add("item.abyssaldecor.wisteria_elder_wall", "Wisteria Elder");
        add("item.abyssaldecor.wisteria_purple_wall", "Wisteria Purple");
        add("item.abyssaldecor.dusty_cd", "Dusty CD");

        add("effect.spore_protection", "Spore Protection");


        add("item.abyssaldecor.dusty_cd.desc", "Unknown Artist - Unknown Track");

        add("subtitles.music.disk.tsm", "Mysterious CD Plays");

        add("effect.abyssaldecor.spore_protection", "Spore Protection");

        add("item.abyssaldecor.laser_assisted_electrical_rifle", "Laser Assisted Electrical Rifle");

        add("item.abyssaldecor.black_pearlmeal", "Black Pearlmeal");

        add("item.abyssaldecor.bog_apple_pie", "Bog Apple Pie");

        addCreativeModeTab(AbyssalCreativeTabs.PALETTE_TAB.get(),"Palletes");
        paintings();
    }

    protected void addCreativeModeTab(CreativeModeTab tab, String s) {
        addTextComponent((MutableComponent) tab.getDisplayName(),s);
    }

    protected void paintings() {
        add("painting.abyssaldecor.purple_sky_painting.author", "Starrysock");

        add("painting.abyssaldecor.void_painting.title", "Void Painting");
        add("painting.abyssaldecor.werewolf_painting.author", "Starrysock");
        add("painting.abyssaldecor.beach_painting.title", "Beach Painting");
        add("painting.abyssaldecor.new_dawn_hill_painting.author", "Starrysock");
        add("painting.abyssaldecor.another_sunset_painting.title", "Another Sunset Painting");

        add("painting.abyssaldecor.live_laugh_love_painting.title", "Live Laugh Love Painting");
        add("painting.abyssaldecor.midnight_pines_painting.author", "Starrysock");
        add("painting.abyssaldecor.pink_clouds.author", "Starrysock");
        add("painting.abyssaldecor.werewolf_painting.title", "Werewolf Painting");

        add("painting.abyssaldecor.misty_marsh_painting.author", "Starrysock");
        add("painting.abyssaldecor.three_wolf_moon_painting.author", "Starrysock");
        add("painting.abyssaldecor.snowy_sunset_painting.author", "Starrysock");

        add("painting.abyssaldecor.overcast_painting.title", "Overcast Painting");


        add("painting.abyssaldecor.grey_candle_painting.title", "Grey Candle Painting");
        add("painting.abyssaldecor.moon_tree_painting.title", "Moon Tree Painting");
        add("painting.abyssaldecor.lighthouse_painting.title", "Lighthouse Painting");
        add("painting.abyssaldecor.red_fog_painting.author", "Starrysock");
        add("painting.abyssaldecor.shadowbox_painting_2.author", "Starrysock");
        add("painting.abyssaldecor.pink_clouds.title", "Pink Clouds");
        add("painting.abyssaldecor.ai_field_painting.title", "AI Field Painting");
        add("painting.abyssaldecor.sunset_field_painting.title", "Sunset Field Painting");
        add("painting.abyssaldecor.new_dawn_hill_painting.title", "New Dawn Hill Painting");
        add("painting.abyssaldecor.shadowbox_painting_1.author", "Starrysock");

        add("painting.abyssaldecor.overcast_painting.author", "Starrysock");
        add("painting.abyssaldecor.another_sunset_painting.author", "Starrysock");
        add("painting.abyssaldecor.beige_grove_painting.title", "Beige Grove Painting");
        add("painting.abyssaldecor.mossy_tree_painting.title", "Mossy Tree Painting");

        add("painting.abyssaldecor.beige_grove_painting.author", "Starrysock");

        add("painting.abyssaldecor.blue_moon_painting.title", "Blue Moon Painting");
        add("painting.abyssaldecor.bloody_orchard_painting.title", "Bloody Orchard Painting");
        add("painting.abyssaldecor.shadowbox_painting_2.title", "Shadowbox Painting 2");

        add("painting.abyssaldecor.three_wolf_moon_painting.title", "Three Wolf Moon Painting");
        add("painting.abyssaldecor.pastel_rose_painting.author", "Starrysock");

        add("painting.abyssaldecor.hang_in_there_painting.author", "Starrysock");
        add("painting.abyssaldecor.snowy_sunset_painting.title", "Snowy Sunset Painting");

        add("painting.abyssaldecor.sunset_painting.title", "Sunset Painting");

        add("painting.abyssaldecor.pastel_rose_painting.title", "Pastel Rose Painting");

        add("painting.abyssaldecor.sunset_painting.author", "Starrysock");
        add("painting.abyssaldecor.purple_sky_painting.title", "Purple Sky Painting");


        add("painting.abyssaldecor.misty_marsh_painting.title", "Misty Marsh Painting");

        add("painting.abyssaldecor.grey_candle_painting.author", "Starrysock");
        add("painting.abyssaldecor.shyguy_painting.author", "Starrysock");
        add("painting.abyssaldecor.live_laugh_love_painting.author", "Starrysock");
        add("painting.abyssaldecor.clocktower_painting.author", "Starrysock");
        add("painting.abyssaldecor.blue_moon_painting.author", "Starrysock");
        add("painting.abyssaldecor.red_fog_painting.title", "Red Fog Painting");
        add("painting.abyssaldecor.green_ocean_painting.title", "Green Ocean Painting");
        add("painting.abyssaldecor.hang_in_there_painting.title", "Hang In There Painting");

        add("painting.abyssaldecor.lily_painting.author", "Starrysock");
        add("painting.abyssaldecor.ai_field_painting.author", "Starrysock");
        add("painting.abyssaldecor.clocktower_painting.title", "Clocktower Painting");
        add("painting.abyssaldecor.mossy_tree_painting.author", "Starrysock");
        add("painting.abyssaldecor.bloody_orchard_painting.author", "Starrysock");
        add("painting.abyssaldecor.void_painting.author", "Starrysock");
        add("painting.abyssaldecor.sunset_field_painting.author", "Starrysock");

        add("painting.abyssaldecor.flower_painting.title", "Flower Painting");
        add("painting.abyssaldecor.midnight_pines_painting.title", "Midnight Pines Painting");
        add("painting.abyssaldecor.beach_painting.author", "Starrysock");
        add("painting.abyssaldecor.shyguy_painting_small.title", "Shyguy Painting Small");
        add("painting.abyssaldecor.weezer_painting.title", "Curious Painting");
        add("painting.abyssaldecor.lily_painting.title", "Lily Painting");
        add("painting.abyssaldecor.flower_painting.author", "Starrysock");
        add("painting.abyssaldecor.green_ocean_painting.author", "Starrysock");

        add("painting.abyssaldecor.weezer_painting.author", "Starrysock");

        add("painting.abyssaldecor.shadowbox_painting_1.title", "Shadowbox Painting 1");


        add("painting.abyssaldecor.shyguy_painting.title", "Shyguy Painting");
        add("painting.abyssaldecor.lighthouse_painting.author", "Starrysock");

        add("painting.abyssaldecor.moon_tree_painting.author", "Starrysock");

        add("painting.abyssaldecor.shyguy_painting_small.author", "Starrysock");

    }

    protected void addDefaultItem(Item item) {
        add(item, getNameFromItem(item));
    }

    protected void addDefaultBlock(Block block) {
        add(block, getNameFromBlock(block));
    }

    protected void addDefaultEntityType(EntityType<?> type) {
        add(type, getNameFromEntity(type));
    }

    public static String getNameFromItem(Item item) {
        return StringUtils.capitaliseAllWords(item.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromBlock(Block block) {
        return StringUtils.capitaliseAllWords(block.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    public static String getNameFromEntity(EntityType<?> entity) {
        return StringUtils.capitaliseAllWords(entity.getDescriptionId().split("\\.")[2].replace("_", " "));
    }

    protected void addTextComponent(MutableComponent component, String text) {
        ComponentContents contents = component.getContents();
        if (contents instanceof TranslatableContents translatableContents) {
            add(translatableContents.getKey(), text);
        } else {
            throw new UnsupportedOperationException(component + " is not translatable");
        }
    }
}
