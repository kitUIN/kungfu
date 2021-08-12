package io.github.kituin.kungfu.capabilities.kungfu;

import io.github.kituin.kungfu.items.miji.GitmijiList;
import net.minecraft.nbt.CompoundNBT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class KungFuCapability implements  IKungFuCapability{
    private static final Logger LOGGER = LogManager.getLogger();

    private CompoundNBT kungfuNBT = new CompoundNBT();

    public KungFuCapability(){

    }
    @Override
    public void setMiJiNBT(String mijiName, int level, int proficiency) {
        CompoundNBT mijiNBT = new CompoundNBT();
        mijiNBT.putString("name",mijiName);
        mijiNBT.putInt("level",level);
        mijiNBT.putInt("proficiency",proficiency);
        this.kungfuNBT.put(mijiName,mijiNBT);
    }
    @Override
    public void SetKungfuNBT(CompoundNBT kungfuNBT){
        this.kungfuNBT = kungfuNBT;
    }

    @Override
    public CompoundNBT getKungfuNBT() {
        return kungfuNBT;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.put("kungfu",this.kungfuNBT);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.kungfuNBT = nbt.getCompound("kungfu");
    }
}
