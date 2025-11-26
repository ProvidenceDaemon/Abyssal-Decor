package net.starrysock.abyssaldecor.block.lamp;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import net.starrysock.abyssaldecor.block.AbstractHorizontalBlock;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;
import net.starrysock.abyssaldecor.registry.AbyssalDecorSounds;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;

import static net.minecraft.world.level.block.RedstoneLampBlock.LIT;

public class TubeLampBlock extends AbstractHorizontalBlock {
    private final EnumMap<Direction,VoxelShape> shapes = new EnumMap<>(Direction.class);
    private final EnumMap<Direction,VoxelShape> shapesCeil = new EnumMap<>(Direction.class);

    public TubeLampBlock(Properties properties) {
        super(properties);
        VoxelShape shape = Block.box(0, 0, 0, 16, 2, 2);

        VoxelShape shapeCeil = Block.box(0, 14, 0, 16, 16, 2);
        this.registerDefaultState(this.defaultBlockState().setValue(RedstoneLampBlock.LIT, false).setValue(BlockStateProperties.HALF, Half.BOTTOM));

        for (Direction direction : FACING.getPossibleValues()) {
            shapes.put(direction, AbyssalUtils.calculateShapes(direction, shape));
            shapesCeil.put(direction, AbyssalUtils.calculateShapes(direction, shapeCeil));
        }
        this.registerDefaultState(defaultBlockState().setValue(RedstoneLampBlock.LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(RedstoneLampBlock.LIT, BlockStateProperties.HALF);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        BlockState state = defaultBlockState();
        Direction face = context.getClickedFace();
        Direction playerFacing = context.getHorizontalDirection();

        Vec3 fraction = AbyssalUtils.getFraction(context.getClickLocation());

        switch (face) {
            case NORTH,SOUTH,EAST,WEST -> {
                state = state.setValue(FACING,face.getOpposite()).setValue(BlockStateProperties.HALF,fraction.y > .5 ? Half.TOP: Half.BOTTOM);
            }
            case DOWN -> {
                state = state.setValue(FACING,playerFacing.getOpposite()).setValue(BlockStateProperties.HALF,Half.TOP);
            }
            case UP -> {
                state = state.setValue(FACING,playerFacing.getOpposite()).setValue(BlockStateProperties.HALF,Half.BOTTOM);
            }
        }


        return state;
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(BlockStateProperties.HALF)) {
            case BOTTOM -> shapes.get(state.getValue(FACING));
            case TOP -> shapesCeil.get(state.getValue(FACING));
        };
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.isCrouching()) {
            level.setBlockAndUpdate(blockPos, AbyssalDecorBlocks.VERTICAL_TUBE_LAMP.get().defaultBlockState()
                    .setValue(RedstoneLampBlock.LIT,blockState.getValue(RedstoneLampBlock.LIT)).setValue(FACING,blockState.getValue(FACING).getOpposite()));
            level.playSound(null,blockPos, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundSource.BLOCKS,1,1);
        } else {
            return toggleTubeLamp(blockState,level,blockPos);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public static InteractionResult toggleTubeLamp(BlockState state,Level level,BlockPos pos) {
        if (!level.isClientSide) {
            level.setBlockAndUpdate(pos, state.cycle(LIT));
        }
        level.playLocalSound(pos, state.getValue(LIT) ? AbyssalDecorSounds.TUBE_LAMP_OFF.get() :
                AbyssalDecorSounds.TUBE_LAMP_ON.get(), SoundSource.BLOCKS, 1, 1, false);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
