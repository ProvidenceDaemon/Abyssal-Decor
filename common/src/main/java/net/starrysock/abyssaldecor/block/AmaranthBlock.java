package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

public class AmaranthBlock extends FlowerBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;
    private final TagKey<Block> allowed;

    public AmaranthBlock(Properties properties, TagKey<Block> allowed) {
        super(MobEffects.MOVEMENT_SPEED,100, properties);
        this.allowed = allowed;
        registerDefaultState(defaultBlockState().setValue(AGE,0));
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(allowed);
    }

    public int getMaxAge() {
        return 1;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        grow(state, level, pos, random);
    }

    public void grow(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getRawBrightness(pos, 0) >= 9) {
            int i = state.getValue(AGE);
            float f = .5f;
            if (random.nextFloat()<f) {
                if (i < this.getMaxAge()) {
                    level.setBlock(pos,state.setValue(AGE,i + 1), 2);
                } else {
                    DoublePlantBlock doubleplantblock = AbyssalDecorBlocks.TALL_AMARANTH.get();
                    if (doubleplantblock.defaultBlockState().canSurvive(level,pos) && level.isEmptyBlock(pos.above())) {
                        DoublePlantBlock.placeAt(level, doubleplantblock.defaultBlockState(), pos, 2);
                    }
                }
            }
        }
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        grow(state, level, pos, random);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
