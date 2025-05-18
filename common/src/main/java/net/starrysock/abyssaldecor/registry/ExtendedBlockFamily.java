package net.starrysock.abyssaldecor.registry;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

/**
 * like BlockFamily except better
 */
public class ExtendedBlockFamily {
    private final Block baseBlock;
    final Map<Variant, Block> variants = Maps.newHashMap();
    FeatureFlagSet requiredFeatures = FeatureFlags.VANILLA_SET;
    boolean generateModel = true;
    boolean generateRecipe = true;
    @Nullable
    String recipeGroupPrefix;
    @Nullable
    String recipeUnlockedBy;

    ExtendedBlockFamily(Block baseBlock) {
        this.baseBlock = baseBlock;
    }

    public Block getBaseBlock() {
        return this.baseBlock;
    }

    public Map<Variant, Block> getVariants() {
        return this.variants;
    }

    public Block get(Variant variant) {
        return this.variants.get(variant);
    }

    public boolean exists(Variant variant) {
        return variants.containsKey(variant);
    }

    public boolean shouldGenerateModel() {
        return this.generateModel;
    }

    public boolean shouldGenerateRecipe(FeatureFlagSet enabledFeatures) {
        return this.generateRecipe && this.requiredFeatures.isSubsetOf(enabledFeatures);
    }

    public Optional<String> getRecipeGroupPrefix() {
        return Util.isBlank(this.recipeGroupPrefix) ? Optional.empty() : Optional.of(this.recipeGroupPrefix);
    }

    public Optional<String> getRecipeUnlockedBy() {
        return Util.isBlank(this.recipeUnlockedBy) ? Optional.empty() : Optional.of(this.recipeUnlockedBy);
    }

    public static class Builder {
        private final ExtendedBlockFamily family;

        public Builder(Block baseBlock) {
            this.family = new ExtendedBlockFamily(baseBlock);
        }

        public ExtendedBlockFamily getFamily() {
            return this.family;
        }

        public Builder button(Block buttonBlock) {
            this.family.variants.put(Variant.BUTTON, buttonBlock);
            return this;
        }

        public Builder chiseled(Block chiseledBlock) {
            this.family.variants.put(Variant.CHISELED, chiseledBlock);
            return this;
        }

        public Builder mosaic(Block mosaicBlock) {
            this.family.variants.put(Variant.MOSAIC, mosaicBlock);
            return this;
        }

        public Builder cracked(Block crackedBlock) {
            this.family.variants.put(Variant.CRACKED, crackedBlock);
            return this;
        }

        public Builder cut(Block cutBlock) {
            this.family.variants.put(Variant.CUT, cutBlock);
            return this;
        }

        public Builder door(Block doorBlock) {
            this.family.variants.put(Variant.DOOR, doorBlock);
            return this;
        }

        public Builder customFence(Block customFenceBlock) {
            this.family.variants.put(Variant.CUSTOM_FENCE, customFenceBlock);
            return this;
        }

        public Builder fence(Block fenceBlock) {
            this.family.variants.put(Variant.FENCE, fenceBlock);
            return this;
        }

        public Builder customFenceGate(Block customFenceGateBlock) {
            this.family.variants.put(Variant.CUSTOM_FENCE_GATE, customFenceGateBlock);
            return this;
        }

        public Builder fenceGate(Block fenceGateBlock) {
            this.family.variants.put(Variant.FENCE_GATE, fenceGateBlock);
            return this;
        }

        public Builder sign(Block signBlock, Block wallSignBlock) {
            this.family.variants.put(Variant.SIGN, signBlock);
            this.family.variants.put(Variant.WALL_SIGN, wallSignBlock);
            return this;
        }

        public Builder hangingSign(Block hangingSign,Block hangingWallSign) {
            family.variants.put(Variant.HANGING_SIGN,hangingSign);
            family.variants.put(Variant.HANGING_WALL_SIGN,hangingWallSign);
            return this;
        }

        public Builder slab(Block slabBlock) {
            this.family.variants.put(Variant.SLAB, slabBlock);
            return this;
        }

        public Builder stairs(Block stairsBlock) {
            this.family.variants.put(Variant.STAIRS, stairsBlock);
            return this;
        }

        public Builder pressurePlate(Block pressurePlateBlock) {
            this.family.variants.put(Variant.PRESSURE_PLATE, pressurePlateBlock);
            return this;
        }

        public Builder polished(Block polishedBlock) {
            this.family.variants.put(Variant.POLISHED, polishedBlock);
            return this;
        }

        public Builder trapdoor(Block trapdoorBlock) {
            this.family.variants.put(Variant.TRAPDOOR, trapdoorBlock);
            return this;
        }

        public Builder wall(Block wallBlock) {
            this.family.variants.put(Variant.WALL, wallBlock);
            return this;
        }

        public Builder dontGenerateModel() {
            this.family.generateModel = false;
            return this;
        }

        public Builder dontGenerateRecipe() {
            this.family.generateRecipe = false;
            return this;
        }

        public Builder recipeGroupPrefix(String recipeGroupPrefix) {
            this.family.recipeGroupPrefix = recipeGroupPrefix;
            return this;
        }

        public Builder recipeUnlockedBy(String recipeUnlockedBy) {
            this.family.recipeUnlockedBy = recipeUnlockedBy;
            return this;
        }
    }

    public enum Variant {
        BUTTON("button"),
        CHISELED("chiseled"),
        CRACKED("cracked"),
        CUT("cut"),
        DOOR("door"),
        CUSTOM_FENCE("custom_fence"),
        FENCE("fence"),
        CUSTOM_FENCE_GATE("custom_fence_gate"),
        FENCE_GATE("fence_gate"),
        MOSAIC("mosaic"),
        SIGN("sign"),
        HANGING_SIGN("hanging_sign"),
        SLAB("slab"),
        STAIRS("stairs"),
        PRESSURE_PLATE("pressure_plate"),
        POLISHED("polished"),
        TRAPDOOR("trapdoor"),
        WALL("wall"),
        WALL_SIGN("wall_sign"),
        HANGING_WALL_SIGN("hanging_wall_sign");

        private final String name;

        Variant(String variantName) {
            this.name = variantName;
        }

        public String getName() {
            return this.name;
        }
    }
}
