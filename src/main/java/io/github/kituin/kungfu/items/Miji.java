package io.github.kituin.kungfu.items;

import net.minecraft.nbt.CompoundNBT;

public class Miji {
    public String getInfomation(String mijiName){
        switch(mijiName){
            case "miji.yijinjing":
                return "1";
        }
        return "NULL";
    }
    public CompoundNBT getInfomation(String mijiName, int level){
        CompoundNBT nbt = new CompoundNBT();
        
        return nbt;
    }
}
