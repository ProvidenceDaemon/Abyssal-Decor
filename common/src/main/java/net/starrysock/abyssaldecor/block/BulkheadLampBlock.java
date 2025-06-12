package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BulkheadLampBlock extends FaceAttachedBlock{
    public BulkheadLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(RedstoneLampBlock.LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(RedstoneLampBlock.LIT);
    }


    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        VoxelShape var10000;
        switch (state.getValue(FACING)) {
            case NORTH:
                switch (state.getValue(FACE)) {
                    case FLOOR:
                        var10000 = box(5.5, 0.0, 3.5, 10.5, 2.5, 12.5);
                        return var10000;
                    case WALL:
                        var10000 = box(5.5, 3.5, 13.5, 10.5, 12.5, 16.0);
                        return var10000;
                    case CEILING:
                        var10000 = box(5.5, 13.5, 3.5, 10.5, 16.0, 12.5);
                        return var10000;
                    default:
                        throw new IncompatibleClassChangeError();
                }
            case EAST:
                switch (state.getValue(FACE)) {
                    case FLOOR:
                        var10000 = box(3.5, 0.0, 5.5, 12.5, 2.5, 10.5);
                        return var10000;
                    case WALL:
                        var10000 = box(0.0, 3.5, 5.5, 2.5, 12.5, 10.5);
                        return var10000;
                    case CEILING:
                        var10000 = box(3.5, 13.5, 5.5, 12.5, 16.0, 10.5);
                        return var10000;
                    default:
                        throw new IncompatibleClassChangeError();
                }
            case WEST:
                switch (state.getValue(FACE)) {
                    case FLOOR:
                        var10000 = box(3.5, 0.0, 5.5, 12.5, 2.5, 10.5);
                        return var10000;
                    case WALL:
                        var10000 = box(13.5, 3.5, 5.5, 16.0, 12.5, 10.5);
                        return var10000;
                    case CEILING:
                        var10000 = box(3.5, 13.5, 5.5, 12.5, 16.0, 10.5);
                        return var10000;
                    default:
                        throw new IncompatibleClassChangeError();
                }
            default:
                switch (state.getValue(FACE)) {
                    case FLOOR -> var10000 = box(5.5, 0.0, 3.5, 10.5, 2.5, 12.5);
                    case WALL -> var10000 = box(5.5, 3.5, 0.0, 10.5, 12.5, 2.5);
                    case CEILING -> var10000 = box(5.5, 13.5, 3.5, 10.5, 16.0, 12.5);
                    default -> throw new IncompatibleClassChangeError();
                }
        }

        return var10000;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.getItemInHand(interactionHand).isEmpty()) {
            level.setBlockAndUpdate(blockPos, blockState.cycle(RedstoneLampBlock.LIT));
            level.playLocalSound(blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 0.5F, false);
            return InteractionResult.SUCCESS;
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

}
