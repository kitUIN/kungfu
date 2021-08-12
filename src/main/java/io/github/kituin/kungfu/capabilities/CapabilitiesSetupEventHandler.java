package io.github.kituin.kungfu.capabilities;

import io.github.kituin.kungfu.capabilities.kungfu.IKungFuCapability;
import io.github.kituin.kungfu.capabilities.qi.IQiCapability;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilitiesSetupEventHandler {
    @SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(
                    IQiCapability.class,
                    new Capability.IStorage<IQiCapability>() {
                        @Nullable
                        @Override
                        public INBT writeNBT(Capability<IQiCapability> capability, IQiCapability instance, Direction side) {
                            return null;
                        }

                        @Override
                        public void readNBT(Capability<IQiCapability> capability, IQiCapability instance, Direction side, INBT nbt) {

                        }
                    },
                    () -> null
            );
            CapabilityManager.INSTANCE.register(
                    IKungFuCapability.class,
                    new Capability.IStorage<IKungFuCapability>() {
                        @Nullable
                        @Override
                        public INBT writeNBT(Capability<IKungFuCapability> capability, IKungFuCapability instance, Direction side) {
                            return null;
                        }

                        @Override
                        public void readNBT(Capability<IKungFuCapability> capability, IKungFuCapability instance, Direction side, INBT nbt) {

                        }
                    },
                    () -> null
            );
        });
    }
}