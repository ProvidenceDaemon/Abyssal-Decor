package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.starrysock.abyssaldecor.AbyssalUtils;
import net.starrysock.abyssaldecor.registry.AbyssalDecorBlocks;

import java.util.EnumMap;

public class TubeLampBlock extends FaceAttachedBlock{
    private final EnumMap<Direction,VoxelShape> shapes = new EnumMap<>(Direction.class);
    private final EnumMap<Direction,VoxelShape> shapesWall = new EnumMap<>(Direction.class);
    private final EnumMap<Direction,VoxelShape> shapesCeil = new EnumMap<>(Direction.class);

    public TubeLampBlock(Properties properties) {
        super(properties);
        VoxelShape shape = Block.box(0, 0, 0, 16, 2, 2);

        VoxelShape shapeWall = Block.box(0, 0, 14, 16, 2, 16);

        VoxelShape shapeCeil = Block.box(0, 14, 0, 16, 16, 2);
        this.registerDefaultState(this.defaultBlockState().setValue(RedstoneLampBlock.LIT, false));

        for (Direction direction : FACING.getPossibleValues()) {
            shapes.put(direction, AbyssalUtils.calculateShapes(direction, shape));
            shapesWall.put(direction, AbyssalUtils.calculateShapes(direction, shapeWall));
            shapesCeil.put(direction, AbyssalUtils.calculateShapes(direction, shapeCeil));
        }
        this.registerDefaultState(defaultBlockState().setValue(RedstoneLampBlock.LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(RedstoneLampBlock.LIT);
    }


    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACE)) {
            case FLOOR -> shapes.get(state.getValue(FACING));
            case WALL -> shapesWall.get(state.getValue(FACING));
            case CEILING -> shapesCeil.get(state.getValue(FACING));
        };
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.isCrouching()) {
            level.setBlockAndUpdate(blockPos, AbyssalDecorBlocks.VERTICAL_TUBE_LAMP.get().defaultBlockState()
                    .setValue(RedstoneLampBlock.LIT,blockState.getValue(RedstoneLampBlock.LIT)).setValue(FACING,blockState.getValue(FACING)));
        } else {
            return ToggleableDirectionalLampBlock.toggleLamp(blockState,level,blockPos);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
