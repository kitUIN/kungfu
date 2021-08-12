package io.github.kituin.kungfu.events;


import net.minecraftforge.eventbus.api.Event;

public class MijiChanged extends Event {
    /**
     * fired on miji learning ,miji level up
     *
     */
    public final String mijiName;
    public final int level;
    public final int proficiencyPre;
    public final int proficiencyNow;

    public MijiChanged(String mijiName,int level,int proficiencyPre,int proficiencyNow){
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
}
