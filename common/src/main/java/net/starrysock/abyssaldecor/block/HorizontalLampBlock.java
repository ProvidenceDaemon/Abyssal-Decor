package net.starrysock.abyssaldecor.block;

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
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.AbyssalUtils;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;

public class HorizontalLampBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private final VoxelShape shape;

    private final EnumMap<Direction,VoxelShape> shapes = new EnumMap<>(Direction.class);

    public HorizontalLampBlock(Properties properties,VoxelShape shape) {
        super(properties);
        this.shape = shape;
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH).setValue(RedstoneLampBlock.LIT, false)
                .setValue(BlockStateProperties.WATERLOGGED, false));

        if (shape != null) {
            for (Direction direction : FACING.getPossibleValues()) {
                shapes.put(direction, AbyssalUtils.calculateShapes(direction, shape));
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, RedstoneLampBlock.LIT,BlockStateProperties.WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {



        if (shape != null) {
            return shapes.get(state.getValue(FACING));
        }
        return super.getShape(state, level, pos, context);
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

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate = blockstate.setValue(FACING, direction);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    return blockstate;
                }
            }
        }
        return null;
    }

}
