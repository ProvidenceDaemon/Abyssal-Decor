package net.starrysock.abyssaldecor.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.starrysock.abyssaldecor.block.WhitewoodPlanterBlock;

public class WhiteWoodPlanterBlockForge extends WhitewoodPlanterBlock {
    public WhiteWoodPlanterBlockForge(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        return true;
    }
}
