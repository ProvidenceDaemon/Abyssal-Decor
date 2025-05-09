package net.starrysock.abyssaldecor;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
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
import net.starrysock.abyssaldecor.block.HorizontalDoubleBlock;
import net.starrysock.abyssaldecor.block.MuckrootBlock;
import net.starrysock.abyssaldecor.block.properties.HorizontalPart;
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
        protected final Set<Block> skip = Set.of(AbyssalDecorBlocks.AMARANTH.get(),AbyssalDecorBlocks.TALL_AMARANTH.get());


        @Override
        protected void generate() {

            Set<Block> specialDrops = Set.of(AbyssalDecorBlocks.VELVET_BARRIER.get(),AbyssalDecorBlocks.IRON_BARRIER.get(),
                    AbyssalDecorBlocks.ROPE_BARRIER.get(),AbyssalDecorBlocks.BARBED_WIRE_BARRIER.get(),
                    AbyssalDecorBlocks.MUCKROOT.get(),AbyssalDecorBlocks.LION_STATUE.get(),AbyssalDecorBlocks.GARGOYLE.get(),
                    AbyssalDecorBlocks.NITHING_POLE.get(),AbyssalDecorBlocks.TELESCOPE.get(),AbyssalDecorBlocks.HANGING_WEB.get(),
                    AbyssalDecorBlocks.DANGLING_WEB.get());

            AbyssalDecor.BLOCKS.forEach(blockRegistrySupplier ->{
                Block block = blockRegistrySupplier.get();
                if (!skip.contains(block) && !specialDrops.contains(block)) {
                    dropSelf(block);
                }
            } );

            barrierDrop(AbyssalDecorBlocks.BARBED_WIRE_BARRIER.get());
            barrierDrop(AbyssalDecorBlocks.IRON_BARRIER.get());
            barrierDrop(AbyssalDecorBlocks.ROPE_BARRIER.get());
            barrierDrop(AbyssalDecorBlocks.VELVET_BARRIER.get());

            barrierDrop(AbyssalDecorBlocks.LION_STATUE.get());
            barrierDrop(AbyssalDecorBlocks.NITHING_POLE.get());
            barrierDrop(AbyssalDecorBlocks.TELESCOPE.get());

            add(AbyssalDecorBlocks.GARGOYLE.get(),createHorizontalStatueTable(AbyssalDecorBlocks.GARGOYLE.get()));

            barrierDrop(AbyssalDecorBlocks.DANGLING_WEB.get());

            LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(AbyssalDecorBlocks.MUCKROOT.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MuckrootBlock.AGE, 2));
            this.add(AbyssalDecorBlocks.MUCKROOT.get(), this.applyExplosionDecay(AbyssalDecorBlocks.MUCKROOT.get(),
                    LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AbyssalDecorItems.MUCKROOT.get()))).withPool(LootPool.lootPool()
                            .when(builder).add(LootItem.lootTableItem(AbyssalDecorItems.MUCKROOT.get())
                                    .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

            this.add(AbyssalDecorBlocks.HANGING_WEB.get(), block -> createSilkTouchOrShearsDispatchTable(block, this.applyExplosionCondition(block, LootItem.lootTableItem(Items.STRING))));

        }

        protected void barrierDrop(Block block) {
            add(block,createDoorTable(block));
        }

        protected LootTable.Builder createHorizontalStatueTable(Block block) {
            return this.createSinglePropConditionTable(block, HorizontalDoubleBlock.PART, HorizontalPart.BACK);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {

            return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block)
                    .getNamespace().equals(AbyssalDecor.MOD_ID) && !skip.contains(block)).toList();
        }
    }
}
