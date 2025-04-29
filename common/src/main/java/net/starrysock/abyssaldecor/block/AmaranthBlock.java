package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;

public class AmaranthBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;
    private final TagKey<Block> allowed;

    public AmaranthBlock(Properties properties, TagKey<Block> allowed) {
        super(properties);
        this.allowed = allowed;
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(allowed);
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 2;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return AbyssalDecorItems.AMARANTH_SEEDS.get();
    }

    @Override
    public void growCrops(Level level, BlockPos blockPos, BlockState blockState) {
        int age = getAge(blockState);
        if (age < getMaxAge()) {
            super.growCrops(level, blockPos, blockState);
        } else {
            DoublePlantBlock doubleplantblock = AbyssalDecorBlocks.TALL_AMARANTH.get();
            if (doubleplantblock.defaultBlockState().canSurvive(level,blockPos) && level.isEmptyBlock(blockPos.above())) {
                DoublePlantBlock.placeAt(level, doubleplantblock.defaultBlockState(), blockPos, 2);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);//don't call super!
    }
}
