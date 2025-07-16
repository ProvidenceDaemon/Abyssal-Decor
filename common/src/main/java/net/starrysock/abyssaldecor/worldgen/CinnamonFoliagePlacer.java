package net.starrysock.abyssaldecor.worldgen;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class CinnamonFoliagePlacer extends FoliagePlacer {

    public static final Codec<CinnamonFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> blobParts(instance)
            .apply(instance, CinnamonFoliagePlacer::new));

    protected final int height;

    protected static <P extends CinnamonFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer>
    blobParts(RecordCodecBuilder.Instance<P> instance) {
        return foliagePlacerParts(instance).and(Codec.intRange(0, 16).fieldOf("height").forGetter(blobFoliagePlacer -> blobFoliagePlacer.height));
    }


    public CinnamonFoliagePlacer(IntProvider radius, IntProvider offset,int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.CINNAMON_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config,
                                 int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        for(int localY = offset; localY >= offset - foliageHeight; --localY) {

            int absY = offset - localY;

            int range;


            //thanks desmos {x<.75*d:c*x ,3c*(d -x)}
            double width = 1/4d;

            if (absY < foliageHeight * .75) {
                range = (int) (absY * width);
            } else {
                range = (int) (3 * width * (foliageHeight - absY));
            }

            System.out.println(range);
            this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), range, localY, attachment.doubleTrunk());
            //int range = Math.max(foliageRadius + attachment.radiusOffset() - 1 - localY / 2, 0);//attachment is at the top of the tree
            //this.placeLeavesRow(level, blockSetter, random, config, attachment.pos(), range, localY, attachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return localX == range && localZ == range && range > 0;
    }
}
