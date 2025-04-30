package net.starrysock.abyssaldecor.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.RecordItem;

public class SupplierRecordItem extends RecordItem {
    public SupplierRecordItem(int analogOutput, SoundEvent sound, Properties properties, int lengthInSeconds) {
        super(analogOutput, sound, properties, lengthInSeconds);
    }
}
