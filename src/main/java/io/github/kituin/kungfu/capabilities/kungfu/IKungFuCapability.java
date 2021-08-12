package io.github.kituin.kungfu.capabilities.kungfu;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IKungFuCapability extends INBTSerializable<CompoundNBT> {
    CompoundNBT getKungfuNBT();
    void setMiJiNBT(String mijiName, int level, int proficiency);
    void SetKungfuNBT(CompoundNBT kungfuNBT);
}
