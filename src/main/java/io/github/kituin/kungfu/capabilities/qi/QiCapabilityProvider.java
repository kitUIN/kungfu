package io.github.kituin.kungfu.capabilities.qi;

import io.github.kituin.kungfu.capabilities.ModCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class QiCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private IQiCapability speedUpCapability;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapability.QI_CAPABILITY ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    @Nonnull
    IQiCapability getOrCreateCapability() {
        if (speedUpCapability == null) {
            this.speedUpCapability = new QiCapability(100.00F,100.00F,0.025F,0.4F,0.00F,1.0F);
        }
        return this.speedUpCapability;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getOrCreateCapability().deserializeNBT(nbt);
    }
}
