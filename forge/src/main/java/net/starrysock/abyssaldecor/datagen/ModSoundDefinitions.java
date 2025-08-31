package net.starrysock.abyssaldecor.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.starrysock.abyssaldecor.AbyssalDecor;
import net.starrysock.abyssaldecor.registry.AbyssalDecorSounds;

public class ModSoundDefinitions extends SoundDefinitionsProvider {
    /**
     * Creates a new instance of this data provider.
     *
     * @param output The {@linkplain PackOutput} instance provided by the data generator.
     * @param helper The existing file helper provided by the event you are initializing this provider in.
     */
    protected ModSoundDefinitions(PackOutput output, ExistingFileHelper helper) {
        super(output, AbyssalDecor.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        add(AbyssalDecorSounds.DUSTY_CD.get(),definition()
                .subtitle("subtitles.music.disk.tsm")
                .with(sound("abyssaldecor:unknown_artist_-_the_most_mysterious_song_on_the_internet_minus").stream()));

        add(AbyssalDecorSounds.LAMP_ON.get(),definition()
              //  .subtitle("subtitles.music.disk.tsm")
                .with(sound("abyssaldecor:lamp_on")));

        add(AbyssalDecorSounds.LAMP_OFF.get(),definition()
                //.subtitle("subtitles.music.disk.tsm")
                .with(sound("abyssaldecor:lamp_off")));
    }
}
