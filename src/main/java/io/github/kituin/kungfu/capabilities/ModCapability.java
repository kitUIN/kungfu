package io.github.kituin.kungfu.capabilities;

import io.github.kituin.kungfu.capabilities.kungfu.IKungFuCapability;
import io.github.kituin.kungfu.capabilities.qi.IQiCapability;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class ModCapability {
    @CapabilityInject(IQiCapability.class)
    public static Capability<IQiCapability> QI_CAPABILITY;
    @CapabilityInject(IKungFuCapability.class)
    public static Capability<IKungFuCapability> KUNGFU_CAPABILITY;
}
