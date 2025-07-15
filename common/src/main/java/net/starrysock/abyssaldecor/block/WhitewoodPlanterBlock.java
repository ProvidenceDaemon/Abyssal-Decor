package net.starrysock.abyssaldecor.block;

import net.minecraft.world.level.block.Block;

public class WhitewoodPlanterBlock extends Block {
    public WhitewoodPlanterBlock(Properties properties) {
        super(properties);
    }


    //forge added method
   /* @SuppressWarnings("unused")
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
        BlockState plant = plantable.getPlant(world, pos.relative(facing));
        net.minecraftforge.common.PlantType type = plantable.getPlantType(world, pos.relative(facing));

        if (plant.getBlock() == Blocks.CACTUS)
            return state.is(Blocks.CACTUS) || state.is(BlockTags.SAND);

        if (plant.getBlock() == Blocks.SUGAR_CANE && this == Blocks.SUGAR_CANE)
            return true;

        if (plantable instanceof BushBlock && ((BushBlock)plantable).mayPlaceOn(state, world, pos))
            return true;

        if (net.minecraftforge.common.PlantType.DESERT.equals(type)) {
            return state.is(BlockTags.SAND) || this == Blocks.TERRACOTTA || this instanceof GlazedTerracottaBlock;
        } else if (net.minecraftforge.common.PlantType.NETHER.equals(type)) {
            return this == Blocks.SOUL_SAND;
        } else if (net.minecraftforge.common.PlantType.CROP.equals(type)) {
            return state.is(Blocks.FARMLAND);
        } else if (net.minecraftforge.common.PlantType.CAVE.equals(type)) {
            return state.isFaceSturdy(world, pos, Direction.UP);
        } else if (net.minecraftforge.common.PlantType.PLAINS.equals(type)) {
            return state.is(BlockTags.DIRT) || this == Blocks.FARMLAND;
        } else if (net.minecraftforge.common.PlantType.WATER.equals(type)) {
            return (state.is(Blocks.WATER) || state.getBlock() instanceof IceBlock) && world.getFluidState(pos.relative(facing)).isEmpty();
        } else if (net.minecraftforge.common.PlantType.BEACH.equals(type)) {
            boolean isBeach = state.is(BlockTags.DIRT) || state.is(BlockTags.SAND);
            boolean hasWater = false;
            for (Direction face : Direction.Plane.HORIZONTAL) {
                BlockState adjacentBlockState = world.getBlockState(pos.relative(face));
                var adjacentFluidState = world.getFluidState(pos.relative(face));
                hasWater = hasWater || adjacentBlockState.is(Blocks.FROSTED_ICE) || adjacentFluidState.is(net.minecraft.tags.FluidTags.WATER);
                if (hasWater)
                    break; //No point continuing.
            }
            return isBeach && hasWater;
        }
        return false;
    }*/
}
