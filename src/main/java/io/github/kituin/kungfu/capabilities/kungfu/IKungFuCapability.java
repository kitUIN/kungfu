package io.github.kituin.kungfu.capabilities.kungfu;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IKungFuCapability extends INBTSerializable<CompoundNBT> {
    CompoundNBT getKungfuNBT();
    void setGongfaNBT(String mijiName,String type, int level, int proficiency);
    void addProficiency(String mijiName,String type,int value);
    void setKungfuNBT(CompoundNBT kungfuNBT);
}
