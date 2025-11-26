package net.starrysock.abyssaldecor.block.lamp;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.starrysock.abyssaldecor.block.properties.ModBlockStateProperties;
import net.starrysock.abyssaldecor.block.properties.TriPart;

import java.util.ArrayList;
import java.util.List;

public class BloodLampMultiBlock extends LampBlock{

    private final Block block;


    public BloodLampMultiBlock(Properties properties, Block block) {
        super(properties, Shapes.block());
        this.block = block;
        registerDefaultState(defaultBlockState().setValue(ModBlockStateProperties.TRI_PART,TriPart.BOTTOM));
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.getItemInHand(interactionHand).isEmpty()) {

            if (!level.isClientSide) {

                boolean lit = blockState.getValue(RedstoneLampBlock.LIT);

                List<BlockPos> gatherConnected = getConnected(blockState, level, blockPos);

                for (BlockPos pos : gatherConnected) {
                    BlockState state = level.getBlockState(pos);
                    level.setBlockAndUpdate(pos, state.setValue(RedstoneLampBlock.LIT,!lit));
                }
            }

            level.playLocalSound(blockPos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 0.5F, false);

            return InteractionResult.SUCCESS;
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    List<BlockPos> getConnected(BlockState state,Level level,BlockPos blockPos) {
        List<BlockPos> list = new ArrayList<>(1);
        list.add(blockPos);

        boolean reachedTop = false;

        boolean reachedBottom = false;

        for (int i = 1; i < level.getHeight();i++) {
            if (!reachedTop) {
                BlockPos offsetAbove = blockPos.above(i);
                if (!level.isInWorldBounds(offsetAbove)) {
                    reachedTop = true;
                } else {
                    if (level.getBlockState(offsetAbove).is(this)) {
                        list.add(offsetAbove);
                    } else {
                        reachedTop = true;
                    }
                }
            }
            if (!reachedBottom) {
                BlockPos offsetBottom = blockPos.below(i);
                if (!level.isInWorldBounds(offsetBottom)) {
                    reachedBottom = true;
                } else {
                    if (level.getBlockState(offsetBottom).is(this)) {
                        list.add(offsetBottom);
                    } else {
                        reachedBottom = true;
                    }
                }
            }
            if (reachedTop && reachedBottom) {
                break;
            }
        }

        return list;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction.getAxis() == Direction.Axis.Y) {

            BlockPos below = pos.below();
            BlockPos above = pos.above();

            boolean lampAbove = isSameBlock(level.getBlockState(above));
            boolean lampBelow = isSameBlock(level.getBlockState(below));

            TriPart triPart = TriPart.getForPlacement(lampAbove,lampBelow);

            if (triPart == null) {
                return block.defaultBlockState().setValue(RedstoneLampBlock.LIT,state.getValue(RedstoneLampBlock.LIT));
            }

            level.setBlock(pos,defaultBlockState().setValue(ModBlockStateProperties.TRI_PART, triPart)
                    .setValue(RedstoneLampBlock.LIT,state.getValue(RedstoneLampBlock.LIT)),3);
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return block.getCloneItemStack(level, pos, state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ModBlockStateProperties.TRI_PART);
    }

    public boolean isSameBlock(BlockState other) {
        return other.is(this) || other.getBlock() == block;
    }
}
