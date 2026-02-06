package net.starrysock.abyssaldecor;

public enum VillagerLevel {
    NOVICE,APPRENTICE,JOURNEYMAN,EXPERT,MASTER;
    public int level() {
        return ordinal()+1;
    }
}
