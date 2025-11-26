package net.starrysock.abyssaldecor.block.lamp;

import net.minecraft.core.BlockPos;
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
import net.starrysock.abyssaldecor.block.FaceAttachedBlock;

public class BulkheadLampBlock extends FaceAttachedBlock {
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
        return ToggleableDirectionalLampBlock.toggleLamp(blockState,level,blockPos);
    }

}
