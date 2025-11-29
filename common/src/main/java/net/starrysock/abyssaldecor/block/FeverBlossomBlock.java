package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import net.starrysock.abyssaldecor.registry.ModTags;

public class FeverBlossomBlock extends CropBlock {

    public FeverBlossomBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModTags.Blocks.MOLDY_PLANT_VALID_BLOCKS);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getRawBrightness(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getAltGrowthSpeed(this, level, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    level.setBlock(pos, this.getStateForAge(i + 1), 2);
                }
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return BogAppleLeavesBlock.tryRightClickHarvest(this,state,level,pos,player,hand);
    }

    protected static float getAltGrowthSpeed(Block block, BlockGetter level, BlockPos pos) {
        float f = 1.0F;

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 1.0F;

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = level.getBlockState(blockpos3).is(block) || level.getBlockState(blockpos4).is(block);
        boolean flag1 = level.getBlockState(blockpos1).is(block) || level.getBlockState(blockpos2).is(block);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = level.getBlockState(blockpos3.north()).is(block) || level.getBlockState(blockpos4.north()).is(block) || level.getBlockState(blockpos4.south()).is(block) || level.getBlockState(blockpos3.south()).is(block);
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return box(2.0, 0.0, 2.0, 14.0, 7.0, 14.0);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return AbyssalDecorItems.FEVER_BLOSSOM_SEEDS.get();
    }
}
