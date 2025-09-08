package net.starrysock.abyssaldecor;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.starrysock.abyssaldecor.block.*;
import net.starrysock.abyssaldecor.block.properties.HorizontalPart;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
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
        protected final Set<Block> skip = Set.of(AbyssalDecorBlocks.AMARANTH.get());


        @Override
        protected void generate() {

            Set<Block> specialDrops = Set.of(AbyssalDecorBlocks.VELVET_BARRIER.get(),AbyssalDecorBlocks.IRON_BARRIER.get(),
                    AbyssalDecorBlocks.ROPE_BARRIER.get(),AbyssalDecorBlocks.BARBED_WIRE_BARRIER.get(),
                    AbyssalDecorBlocks.MUCKROOT.get(),AbyssalDecorBlocks.LION_STATUE.get(),AbyssalDecorBlocks.GARGOYLE.get(),
                    AbyssalDecorBlocks.NITHING_POLE.get(),AbyssalDecorBlocks.TELESCOPE.get(),AbyssalDecorBlocks.HANGING_WEB.get(),
                    AbyssalDecorBlocks.DANGLING_WEB.get(),AbyssalDecorBlocks.CINNAMON_LEAVES.get(),AbyssalDecorBlocks.FLOWERING_CINNAMON_LEAVES.get(),
                    AbyssalDecorBlocks.SPIDERCORN.get(),AbyssalDecorBlocks.BOG_APPLE_LEAVES.get(),AbyssalDecorBlocks.HEART_OF_THE_SEA.get(),AbyssalDecorBlocks.MOLDY_HANGER.get());

            AbyssalDecor.BLOCKS.forEach(blockRegistrySupplier ->{
                Block block = blockRegistrySupplier.get();
                if (!skip.contains(block) && !specialDrops.contains(block) && !(block instanceof SmallBarsCornerBlock)
                       &&!(block instanceof DoubleBlock)
                        &&!(block instanceof DoublePlantBlock)
                        && !(block instanceof BarrierRibbonBlock)) {
                    dropSelf(block);
                } else if (block instanceof BarrierRibbonBlock) {
                    add(block,noDrop());
                } else if (block instanceof DoublePlantBlock || block instanceof DoubleBlock) {
                    barrierDrop(block);
                }
            } );

            dropOther(AbyssalDecorBlocks.HEART_OF_THE_SEA.get(),Items.HEART_OF_THE_SEA);


            barrierDrop(AbyssalDecorBlocks.BARBED_WIRE_BARRIER.get());
            barrierDrop(AbyssalDecorBlocks.IRON_BARRIER.get());
            barrierDrop(AbyssalDecorBlocks.ROPE_BARRIER.get());
            barrierDrop(AbyssalDecorBlocks.VELVET_BARRIER.get());

            barrierDrop(AbyssalDecorBlocks.LION_STATUE.get());
            barrierDrop(AbyssalDecorBlocks.NITHING_POLE.get());
            barrierDrop(AbyssalDecorBlocks.TELESCOPE.get());

            add(AbyssalDecorBlocks.GARGOYLE.get(),createHorizontalStatueTable(AbyssalDecorBlocks.GARGOYLE.get()));

            hangingDrop(AbyssalDecorBlocks.DANGLING_WEB.get());

            LootItemCondition.Builder builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(AbyssalDecorBlocks.MUCKROOT.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MuckrootBlock.AGE, 2));
            this.add(AbyssalDecorBlocks.MUCKROOT.get(), this.applyExplosionDecay(AbyssalDecorBlocks.MUCKROOT.get(),
                    LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AbyssalDecorItems.MUCKROOT.get()))).withPool(LootPool.lootPool()
                            .when(builder).add(LootItem.lootTableItem(AbyssalDecorItems.MUCKROOT.get())
                                    .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

            this.add(AbyssalDecorBlocks.HANGING_WEB.get(), block -> createSilkTouchOrShearsDispatchTable(block, this.applyExplosionCondition(block, LootItem.lootTableItem(Items.STRING))));

            cornerTable(AbyssalDecorBlocks.SMALL_BLACK_PEARL_BARS_CORNER.get(),AbyssalDecorItems.SMALL_BLACK_PEARL_BARS.get());
            cornerTable(AbyssalDecorBlocks.SMALL_BLOOD_CORAL_BARS_CORNER.get(),AbyssalDecorItems.SMALL_BLOOD_CORAL_BARS.get());
            cornerTable(AbyssalDecorBlocks.SMALL_CLEAN_IRON_BARS_CORNER.get(),AbyssalDecorItems.SMALL_CLEAN_IRON_BARS.get());
            cornerTable(AbyssalDecorBlocks.SMALL_DEEPBRONZE_BARS_CORNER.get(),AbyssalDecorItems.SMALL_DEEPBRONZE_BARS.get());
            cornerTable(AbyssalDecorBlocks.SMALL_DULL_IRON_BARS_CORNER.get(),AbyssalDecorItems.SMALL_DULL_IRON_BARS.get());
            cornerTable(AbyssalDecorBlocks.SMALL_SEABRASS_BARS_CORNER.get(),AbyssalDecorItems.SMALL_SEABRASS_BARS.get());
            cornerTable(AbyssalDecorBlocks.SMALL_STONE_BARS_CORNER.get(),AbyssalDecorItems.SMALL_STONE_BARS.get());

            cornerTable(AbyssalDecorBlocks.SMALL_WHITE_PEARL_BARS_CORNER.get(),AbyssalDecorItems.SMALL_WHITE_PEARL_BARS.get());

            this.add(AbyssalDecorBlocks.CINNAMON_LEAVES.get(), block -> this.createLeavesDrops(block,AbyssalDecorBlocks.CINNAMON_BUSH.get(), NORMAL_LEAVES_SAPLING_CHANCES));
            this.add(AbyssalDecorBlocks.FLOWERING_CINNAMON_LEAVES.get(), block -> this.createLeavesDrops(block,AbyssalDecorBlocks.CINNAMON_BUSH.get(), NORMAL_LEAVES_SAPLING_CHANCES));

            LootItemCondition.Builder builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(AbyssalDecorBlocks.BOG_APPLE_LEAVES.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BogAppleLeavesBlock.AGE, 2));

            this.add(AbyssalDecorBlocks.BOG_APPLE_LEAVES.get(), this.createBogAppleDrops(AbyssalDecorBlocks.BOG_APPLE_LEAVES.get(),
                    AbyssalDecorItems.BOG_APPLE.get(), AbyssalDecorItems.BOG_APPLE_LEAVES.get(), builder1));

            LootItemCondition.Builder builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(AbyssalDecorBlocks.SPIDERCORN.get())
                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SpiderCornCropBlock.AGE, 2));


            this.add(AbyssalDecorBlocks.SPIDERCORN.get(), this.applyExplosionDecay(AbyssalDecorBlocks.SPIDERCORN.get(),
                    LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AbyssalDecorItems.SPIDERCORN.get())))
                            .withPool(LootPool.lootPool().when(builder2)
                                    .add(LootItem.lootTableItem(AbyssalDecorItems.SPIDERCORN.get())
                                            .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3)))
                                    .add(LootItem.lootTableItem(Items.STRING)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0,1))))
                            )));

            this.add(AbyssalDecorBlocks.MOLDY_HANGER.get(), this.applyExplosionDecay(AbyssalDecorBlocks.MOLDY_HANGER.get(),
                    LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(AbyssalDecorItems.MOLDY_HANGER.get())))
                            .withPool(LootPool.lootPool().when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(AbyssalDecorBlocks.MOLDY_HANGER.get())
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MoldyHangersBlock.BERRIES, true))
                            ).add(LootItem.lootTableItem(Items.SPIDER_EYE)
                                    .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))))));

        }

        protected LootTable.Builder createBogAppleDrops(Block cropBlock, Item grownCropItem, Item seedsItem, LootItemCondition.Builder dropGrownCropCondition) {
            return this.applyExplosionDecay(cropBlock, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(grownCropItem).when(dropGrownCropCondition)
                    .otherwise(LootItem.lootTableItem(seedsItem)))).withPool(LootPool.lootPool().when(dropGrownCropCondition)
                    .add(LootItem.lootTableItem(seedsItem).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5F, 2)))));
        }

        protected void cornerTable(SmallBarsCornerBlock block, Item small) {
             add(block,LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                    .add(this.applyExplosionDecay(block, LootItem.lootTableItem(small)
                            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2)))))));
        }

        protected void barrierDrop(Block block) {
            add(block,createDoorTable(block));
        }

        protected void hangingDrop(Block block) {
            add(block,createUpperBlockTable(block));
        }

        protected LootTable.Builder createUpperBlockTable(Block doorBlock) {
            return this.createSinglePropConditionTable(doorBlock, DoorBlock.HALF, DoubleBlockHalf.UPPER);
        }


        protected LootTable.Builder createHorizontalStatueTable(Block block) {
            return this.createSinglePropConditionTable(block, ModBlockStateProperties.PART, HorizontalPart.BACK);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {

            return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block)
                    .getNamespace().equals(AbyssalDecor.MOD_ID) && !skip.contains(block)).toList();
        }



    }
}
