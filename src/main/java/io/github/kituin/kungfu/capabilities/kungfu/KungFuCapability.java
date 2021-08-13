package io.github.kituin.kungfu.capabilities.kungfu;

import net.minecraft.nbt.CompoundNBT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class KungFuCapability implements  IKungFuCapability{
    private static final Logger LOGGER = LogManager.getLogger();

    private CompoundNBT kungfuNBT = new CompoundNBT();


    public KungFuCapability(){

    }
    public CompoundNBT setMiJiNBT(int level, int proficiency) {
        CompoundNBT mijiNBT = new CompoundNBT();
        mijiNBT.putInt("level",level);
        mijiNBT.putInt("proficiency",proficiency);
        return mijiNBT;
    }
    @Override
    public void addProficiency(String mijiName,String type,int value){
        CompoundNBT gongfa = this.kungfuNBT.getCompound(mijiName);
        gongfa.putInt("proficiency",gongfa.getInt("proficiency")+value);
        this.kungfuNBT.put(mijiName,gongfa);
    }
    @Override
    public void setGongfaNBT(String mijiName,String type, int level, int proficiency) {
        if(!this.kungfuNBT.contains(mijiName)){
            this.kungfuNBT.put(mijiName,this.setMiJiNBT(level,proficiency));
        }
    }
    @Override
    public void setKungfuNBT(CompoundNBT kungfuNBT){
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
