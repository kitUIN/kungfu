package io.github.kituin.kungfu.capabilities.qi;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;

public interface IQiCapability extends INBTSerializable<CompoundNBT> {

    float getCurrent();
    float getMaximum();

    void setCurrent(float current);
    void setMaximum(float maximum);
    void setRecuperation(float recuperation);
    void recuperation();
    void setSpeedUp(float speed);
    void speedUp(PlayerEntity playerIn);
    void setHealthCureSpeed(float healthCureSpeed);
    void healthCure(PlayerEntity playerIn);
    void setMineSpeed(float mineSpeed);
    void mineSpeedUp(BreakSpeed event);
}
