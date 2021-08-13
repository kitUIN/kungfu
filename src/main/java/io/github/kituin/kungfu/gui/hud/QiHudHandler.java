package io.github.kituin.kungfu.gui.hud;

import io.github.kituin.kungfu.capabilities.qi.IQiCapability;
import io.github.kituin.kungfu.capabilities.ModCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class QiHudHandler {
    private static float height = 1;
    private static final Logger LOGGER = LogManager.getLogger();
    private static boolean HUD_ON = false;
    @SubscribeEvent
    public static void onOverlayRender(RenderGameOverlayEvent event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if(HUD_ON){
            QiHud obsidianGUI = new QiHud(event.getMatrixStack());
            obsidianGUI.render(height);
        }
    }
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event) {
        PlayerEntity player = event.player;
        if(player instanceof ServerPlayerEntity){
            LazyOptional<IQiCapability> speedCap = player.getCapability(ModCapability.QI_CAPABILITY);
            speedCap.ifPresent((l) -> {
                HUD_ON = l.getMaximum()>0;
                height =l.getCurrent()/ l.getMaximum();
                l.recuperation();
            });
        }
    }
}