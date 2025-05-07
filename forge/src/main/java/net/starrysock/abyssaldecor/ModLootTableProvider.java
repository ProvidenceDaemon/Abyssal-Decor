package net.starrysock.abyssaldecor;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.starrysock.abyssaldecor.block.MuckrootBlock;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput output, Set<ResourceLocation> requiredTables, List<SubProviderEntry> subProviders) {
        super(output, requiredTables, subProviders);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationcontext) {

    }

    public static LootTableProvider create(PackOutput output) {
        return new ModLootTableProvider(output, BuiltInLootTables.all(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLoot::new, LootContextParamSets.BLOCK)));
    }

    static class ModBlockLoot extends VanillaBlockLoot {
        @Override
        protected void generate() {
            dropSelf(AbyssalDecorBlocks.SOLAR_ROD.get());
            dropSelf(AbyssalDecorBlocks.STELLAR_ROD.get());
            dropSelf(AbyssalDecorBlocks.TERRESTRIAL_ROD.get());
            dropSelf(AbyssalDecorBlocks.LUNAR_ROD.get());
            dropSelf(AbyssalDecorBlocks.ETHEREAL_ROD.get());

            dropSelf(AbyssalDecorBlocks.ASTER.get());
            dropSelf(AbyssalDecorBlocks.DAFFODIL.get());
            dropSelf(AbyssalDecorBlocks.SNAPLEAF.get());

            dropSelf(AbyssalDecorBlocks.AMARANTH_CRATE.get());

            dropSelf(AbyssalDecorBlocks.BLAZE_LAMP.get());
            dropSelf(AbyssalDecorBlocks.BULKHEAD_LAMP.get());
            dropSelf(AbyssalDecorBlocks.FLOWER_LAMP.get());
            dropSelf(AbyssalDecorBlocks.FROSTED_LAMP.get());
            dropSelf(AbyssalDecorBlocks.IRON_LAMP.get());
            dropSelf(AbyssalDecorBlocks.JADE_LAMP.get());
            dropSelf(AbyssalDecorBlocks.LIGHTBULB.get());
            dropSelf(AbyssalDecorBlocks.QUARTZ_LAMP.get());
            dropSelf(AbyssalDecorBlocks.RAINBOW_LAMP.get());
            dropSelf(AbyssalDecorBlocks.SEAGLASS_LAMP.get());
            dropSelf(AbyssalDecorBlocks.TUBE_LAMP.get());
            dropSelf(AbyssalDecorBlocks.WALL_BULB_LAMP.get());

            dropSelf(AbyssalDecorBlocks.SEABRASS_ORE.get());
            dropSelf(AbyssalDecorBlocks.FRESNEL_LAMP.get());

            dropSelf(AbyssalDecorBlocks.ABYSSAL_LANTERN.get());
            dropSelf(AbyssalDecorBlocks.IRON_LANTERN.get());
            dropSelf(AbyssalDecorBlocks.JADE_LANTERN.get());

            dropSelf(AbyssalDecorBlocks.BARBED_WIRE_BARRIER.get());
            dropSelf(AbyssalDecorBlocks.IRON_BARRIER.get());
            dropSelf(AbyssalDecorBlocks.ROPE_BARRIER.get());

            dropSelf(AbyssalDecorBlocks.HANGING_MOSS.get());
            dropSelf(AbyssalDecorBlocks.BARK_ORCHID.get());

            //todo require shears?
            dropSelf(AbyssalDecorBlocks.WISTERIA_PETALS.get());
            dropSelf(AbyssalDecorBlocks.ELDER_WISTERIA_PETALS.get());
            dropSelf(AbyssalDecorBlocks.ELDER_WISTERIA_LEAVES.get());

            dropSelf(AbyssalDecorBlocks.ANCIENT_BIRCH_LOG.get());
            dropSelf(AbyssalDecorBlocks.STRIPPED_ANCIENT_BIRCH_LOG.get());
            dropSelf(AbyssalDecorBlocks.FOXY_PILLAR.get());
            dropSelf(AbyssalDecorBlocks.SCRIMSHAW.get());
            dropSelf(AbyssalDecorBlocks.DESK_BELL.get());
            dropSelf(AbyssalDecorBlocks.STARFISH.get());

            add(AbyssalDecorBlocks.VELVET_BARRIER.get(),createDoorTable(AbyssalDecorBlocks.VELVET_BARRIER.get()));

            //dropSelf(AbyssalDecorBlocks.VELVET_BARRIER_RIBBON.get());
            LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(AbyssalDecorBlocks.MUCKROOT.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MuckrootBlock.AGE, 2));
            this.add(AbyssalDecorBlocks.MUCKROOT.get(), this.applyExplosionDecay(AbyssalDecorBlocks.MUCKROOT.get(),
                    LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AbyssalDecorItems.MUCKROOT.get()))).withPool(LootPool.lootPool()
                            .when(builder).add(LootItem.lootTableItem(AbyssalDecorItems.MUCKROOT.get())
                                    .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {

            Set<Block> exclude = Set.of(AbyssalDecorBlocks.AMARANTH.get(),AbyssalDecorBlocks.TALL_AMARANTH.get());

            return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block)
                    .getNamespace().equals(AbyssalDecor.MOD_ID) && !exclude.contains(block)).toList();
        }
    }
}
