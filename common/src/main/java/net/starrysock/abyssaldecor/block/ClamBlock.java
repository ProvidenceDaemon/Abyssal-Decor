package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalDecorItems;
import org.jetbrains.annotations.Nullable;

public class ClamBlock extends AbstractHorizontalBlock implements SimpleWaterloggedBlock {

    public final boolean hasPearl;

    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

    public static final VoxelShape shape_closed = box(2,0,2,14,2,14);
    public static final VoxelShape shape_open = box(2,0,2,14,6,14);

    public ClamBlock(Properties properties, boolean hasPearl) {
        super(properties);
        this.hasPearl = hasPearl;
        registerDefaultState(defaultBlockState().setValue(OPEN,false).setValue(BlockStateProperties.WATERLOGGED,false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean open = state.getValue(OPEN);
        return open ? shape_open : shape_closed;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.getItemInHand(interactionHand).isEmpty()) {
            if (blockState.getValue(OPEN) && hasPearl) {
                level.setBlockAndUpdate(blockPos, AbyssalDecorBlocks.CLAM.get().defaultBlockState()
                        .setValue(FACING,blockState.getValue(FACING))
                        .setValue(BlockStateProperties.WATERLOGGED,blockState.getValue(BlockStateProperties.WATERLOGGED))
                );
                player.addItem(AbyssalDecorItems.WHITE_PEARL.get().getDefaultInstance());
            } else {
                level.setBlockAndUpdate(blockPos, blockState.cycle(OPEN));
                level.playLocalSound(blockPos, SoundEvents.WOOD_HIT, SoundSource.BLOCKS, 1.0F, 1, false);
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return super.isRandomlyTicking(state) && state.getValue(BlockStateProperties.WATERLOGGED);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSupportCenter(level, pos.below(), Direction.UP);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
        level.setBlockAndUpdate(pos, AbyssalDecorBlocks.CLAM_WITH_PEARL.get().defaultBlockState()
                .setValue(FACING,state.getValue(FACING))
                .setValue(BlockStateProperties.WATERLOGGED,state.getValue(BlockStateProperties.WATERLOGGED))
        );
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(OPEN,BlockStateProperties.WATERLOGGED);
    }
}
