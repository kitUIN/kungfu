package io.github.kituin.kungfu.events;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class MijiChanged extends Event {
    /**
     * fired on Learning miji,Level up in miji,Increased proficiency in miji
     *
     */
    private final PlayerEntity playerIn;
    private final String mijiName;
    private final int level;
    private final int proficiencyPre;
    private final int proficiencyNow;


    public MijiChanged(PlayerEntity playerIn,String mijiName,int level,int proficiencyPre,int proficiencyNow){
        this.playerIn=playerIn;
        this.mijiName = mijiName;
        this.level = level;
        this.proficiencyNow = proficiencyNow;
        this.proficiencyPre = proficiencyPre;
    }

    public int getLevel() {
        return level;
    }

    public int getProficiencyNow() {
        return proficiencyNow;
    }

    public int getProficiencyPre() {
        return proficiencyPre;
    }

    public String getMijiName() {
        return mijiName;
    }

    public PlayerEntity getPlayerIn() {
        return playerIn;
    }
}
