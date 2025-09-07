package net.starrysock.abyssaldecor.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.loading.FMLPaths;
import net.starrysock.abyssaldecor.block.FeverBlossomBlock;
import net.starrysock.abyssaldecor.block.MoldBushBlock;
import net.starrysock.abyssaldecor.forge.FeverBlossomBlockForge;
import net.starrysock.abyssaldecor.forge.MoldBushBlockForge;
import net.starrysock.abyssaldecor.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.nio.file.Path;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }

    public boolean onCropsGrowPre(Level level, BlockPos pos, BlockState state, boolean def) {
        return ForgeHooks.onCropsGrowPre(level, pos, state, def);
    }

    public void onCropsGrowPost(Level level, BlockPos pos, BlockState state) {
        ForgeHooks.onCropsGrowPost(level, pos, state);
    }

    @Override
    public FeverBlossomBlock feverBlossom(BlockBehaviour.Properties properties) {
        return new FeverBlossomBlockForge(properties);
    }

    @Override
    public MoldBushBlock moldBush(BlockBehaviour.Properties properties) {
        return new MoldBushBlockForge(properties);
    }
}