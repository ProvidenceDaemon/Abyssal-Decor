package net.starrysock.abyssaldecor.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.starrysock.abyssaldecor.block.FeverBlossomBlock;
import net.starrysock.abyssaldecor.block.MoldBushBlock;
import net.starrysock.abyssaldecor.block.WhitewoodPlanterBlock;
import net.starrysock.abyssaldecor.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public boolean onCropsGrowPre(Level level, BlockPos pos, BlockState state, boolean def) {
        return false;
    }

    @Override
    public void onCropsGrowPost(Level level, BlockPos pos, BlockState state) {

    }

    @Override
    public FeverBlossomBlock feverBlossom(BlockBehaviour.Properties properties) {
        return new FeverBlossomBlock(properties);
    }

    @Override
    public MoldBushBlock moldBush(BlockBehaviour.Properties properties) {
        return new MoldBushBlock(properties);
    }

    @Override
    public WhitewoodPlanterBlock whitewoodPlanter(BlockBehaviour.Properties properties) {
        return new WhitewoodPlanterBlock(properties);
    }
}
