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
        add(AbyssalDecorSounds.DUSTY_CD.get(), definition()
                .subtitle("subtitles.music.disk.tsm")
                .with(sound("abyssaldecor:unknown_artist_-_the_most_mysterious_song_on_the_internet_minus").stream()));

        add(AbyssalDecorSounds.LAMP_ON.get(), definition()
                //  .subtitle("subtitles.music.disk.tsm")
                .with(sound("abyssaldecor:lamp_on")));

        add(AbyssalDecorSounds.LAMP_OFF.get(), definition()
                //.subtitle("subtitles.music.disk.tsm")
                .with(sound("abyssaldecor:lamp_off")));

        add(AbyssalDecorSounds.DEEPBRONZE_BLOCK_BREAK.get(), definition()
                .with(
                        sound(AbyssalDecor.id("block/deepbronze/break1")),
                        sound(AbyssalDecor.id("block/deepbronze/break2")),
                        sound(AbyssalDecor.id("block/deepbronze/break3")),
                        sound(AbyssalDecor.id("block/deepbronze/break4"))
                )
        );

        add(AbyssalDecorSounds.DEEPBRONZE_BLOCK_STEP.get(), definition()
                .with(
                        sound(AbyssalDecor.id("block/deepbronze/step1")),
                        sound(AbyssalDecor.id("block/deepbronze/step2")),
                        sound(AbyssalDecor.id("block/deepbronze/step3")),
                        sound(AbyssalDecor.id("block/deepbronze/step4")),
                        sound(AbyssalDecor.id("block/deepbronze/step5"))
                )
        );

        add(AbyssalDecorSounds.SEABRASS_BLOCK_BREAK.get(), definition()
                .with(
                        sound(AbyssalDecor.id("block/seabrass/break1")),
                        sound(AbyssalDecor.id("block/seabrass/break2")),
                        sound(AbyssalDecor.id("block/seabrass/break3")),
                        sound(AbyssalDecor.id("block/seabrass/break4"))
                )
        );
        add(AbyssalDecorSounds.SEABRASS_BLOCK_STEP.get(), definition()
                .with(
                        sound(AbyssalDecor.id("block/seabrass/step1")),
                        sound(AbyssalDecor.id("block/seabrass/step2")),
                        sound(AbyssalDecor.id("block/seabrass/step3")),
                        sound(AbyssalDecor.id("block/seabrass/step4")),
                        sound(AbyssalDecor.id("block/seabrass/step5")),
                        sound(AbyssalDecor.id("block/seabrass/step6"))
                )
        );


        add(AbyssalDecorSounds.TRASH_BAG_BREAK.get(), definition()
                .with(
                        sound(AbyssalDecor.id("block/trash_bag/break1")),
                        sound(AbyssalDecor.id("block/trash_bag/break2")),
                        sound(AbyssalDecor.id("block/trash_bag/break3")),
                        sound(AbyssalDecor.id("block/trash_bag/break4")),
                        sound(AbyssalDecor.id("block/trash_bag/break5")),
                        sound(AbyssalDecor.id("block/trash_bag/break6"))

                )
        );
        add(AbyssalDecorSounds.TRASH_BAG_STEP.get(), definition()
                .with(
                   //     sound(AbyssalDecor.id("block/trash_bag/step1")),
                        sound(AbyssalDecor.id("block/trash_bag/step2")),
                        sound(AbyssalDecor.id("block/trash_bag/step3")),
                        sound(AbyssalDecor.id("block/trash_bag/step4")),
                        sound(AbyssalDecor.id("block/trash_bag/step5")),
                        sound(AbyssalDecor.id("block/trash_bag/step6"))
                )
        );


        add(AbyssalDecorSounds.TUBE_LAMP_OFF.get(), definition()
                .with(
                        sound(AbyssalDecor.id("block/tube_lamp/off1")),
                        sound(AbyssalDecor.id("block/tube_lamp/off2"))
                )
        );

        add(AbyssalDecorSounds.TUBE_LAMP_ON.get(), definition()
                .with(
                        sound(AbyssalDecor.id("block/tube_lamp/on1")),
                        sound(AbyssalDecor.id("block/tube_lamp/on2")),
                        sound(AbyssalDecor.id("block/tube_lamp/on3"))
                )
        );

        add(AbyssalDecorSounds.INDUSTRIAL_LEVER_ON.get(), definition()
                .with(
                        sound(AbyssalDecor.id("block/industrial_lever/on"))
                )
        );

        add(AbyssalDecorSounds.INDUSTRIAL_LEVER_OFF.get(), definition()
                .with(
                        sound(AbyssalDecor.id("block/industrial_lever/off"))
                )
        );
    }
}
