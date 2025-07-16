package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class TallCinammonSaplingBlock extends DoublePlantBlock implements BonemealableBlock {
    private final AbstractTreeGrower treeGrower;

    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;

    public TallCinammonSaplingBlock(AbstractTreeGrower treeGrower, Properties properties) {
        super(properties);
        this.treeGrower = treeGrower;
        registerDefaultState(defaultBlockState().setValue(STAGE,0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(STAGE);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) {
            this.advanceTree(level, pos, state, random);
        }
    }

    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        DoubleBlockHalf half = state.getValue(HALF);
        if (state.getValue(STAGE) == 0) {
            switch (half){
                case UPPER -> {
                    DoublePlantBlock.placeAt(level, state.cycle(STAGE),pos.below(),Block.UPDATE_ALL);
                }
                case LOWER -> {
                    DoublePlantBlock.placeAt(level, state.cycle(STAGE),pos,Block.UPDATE_ALL);
                }
            }

        } else {
            BlockPos growPos = half == DoubleBlockHalf.LOWER ? pos : pos.below();
            this.treeGrower.growTree(level, level.getChunkSource().getGenerator(), growPos, state, random);
        }

    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return level.random.nextDouble() < 0.5;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.advanceTree(level, pos, state, random);
    }
}
