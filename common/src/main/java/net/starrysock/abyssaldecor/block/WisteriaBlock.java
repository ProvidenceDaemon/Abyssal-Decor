package net.starrysock.abyssaldecor.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starrysock.abyssaldecor.block.properties.TriPart;
import net.starrysock.abyssaldecor.platform.Services;
import org.jetbrains.annotations.Nullable;

public class WisteriaBlock extends AbstractHorizontalBlock implements SimpleWaterloggedBlock, BonemealableBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<TriPart> TRI_PART = EnumProperty.create("tri_part", TriPart.class);
    private final double growPerTickProbability = .25;

    public WisteriaBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(TRI_PART,TriPart.TOP));
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }


    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }


    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        tryGrow(state, level, pos, random);

    }

    protected void tryGrow(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockPos down = pos.relative(Direction.DOWN);
        if (Services.PLATFORM.onCropsGrowPre(level, down, level.getBlockState(down),random.nextDouble() < this.growPerTickProbability)) {
            level.setBlockAndUpdate(down, defaultBlockState().setValue(TRI_PART,TriPart.BOTTOM).setValue(FACING,state.getValue(FACING)));
            Services.PLATFORM.onCropsGrowPost(level, down, level.getBlockState(down));
        }
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> box(1, 2, 10, 15, 16, 16);
            case EAST -> box(0, 2, 1, 6, 16, 15);
            case WEST -> box(10, 2, 1, 16, 16, 15);
            default -> box(1, 2, 0, 15, 16, 6);
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED,TRI_PART);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() :
                super.updateShape(state, facing, facingState, world, currentPos, facingPos);

    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();

        BlockPos above = blockpos.above();
        boolean wisteriaAbove = levelreader.getBlockState(above).is(this);
        BlockPos below = blockpos.below();
        boolean wisteriaBelow = levelreader.getBlockState(below).is(this);

        TriPart part = TriPart.TOP;

        //try wall placement first

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate = blockstate.setValue(FACING, direction);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    return blockstate.setValue(TRI_PART,part);
                }
            }
        }

        //if that fails try ceiling
        if (context.getClickedFace() ==Direction.DOWN) {
            Direction playerFacing = context.getHorizontalDirection().getOpposite();
            if (blockstate.canSurvive(levelreader,blockpos)) {
                return blockstate.setValue(FACING,playerFacing);
            }
        }

        return null;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return checkWalls(state, level, pos) || checkCeiling(state, level, pos);
    }

    protected boolean checkCeiling(BlockState state, LevelReader level, BlockPos pos) {
        Direction growthDirection = Direction.DOWN;
        BlockPos blockpos = pos.relative(growthDirection.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        return (blockstate.is(this)&& blockstate.getValue(FACING) == state.getValue(FACING)) || blockstate.is(BlockTags.LEAVES);//blockstate.isFaceSturdy(level, blockpos, growthDirection);
    }

    protected boolean checkWalls(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.isFaceSturdy(level, blockpos, direction);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        if (!level.getBlockState(pos.below()).canBeReplaced()) {
            return false;
        }
        TriPart triPart = state.getValue(TRI_PART);
        if (triPart == TriPart.MIDDLE) return false;

        else if (triPart == TriPart.BOTTOM)return true;

        else return !level.getBlockState(pos.below()).is(this);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        tryGrow(state, level, pos, random);
    }
}