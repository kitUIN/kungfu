package io.github.kituin.kungfu.capabilities;


import io.github.kituin.kungfu.Config;
import io.github.kituin.kungfu.capabilities.kungfu.IKungFuCapability;
import io.github.kituin.kungfu.capabilities.kungfu.KungFuCapabilityProvider;
import io.github.kituin.kungfu.capabilities.qi.IQiCapability;
import io.github.kituin.kungfu.capabilities.qi.QiCapabilityProvider;
import io.github.kituin.kungfu.events.MijiChanged;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.github.kituin.kungfu.Utils.MOD_ID;
import static io.github.kituin.kungfu.config.MiJiConfig.fileconfig;
import static io.github.kituin.kungfu.gui.SettingGUI.TEMP_HUD1X;
import static io.github.kituin.kungfu.gui.SettingGUI.TEMP_HUD1Y;

@Mod.EventBusSubscriber()
public class CapabilitiesEventHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private static double positionY= Config.QIHUDY.get();
    private static double positionX = Config.QIHUDX.get();
    private static CompoundNBT miji = new CompoundNBT();
    @SubscribeEvent
    public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(MOD_ID, "qi"), new QiCapabilityProvider());
            event.addCapability(new ResourceLocation(MOD_ID, "kungfu"), new KungFuCapabilityProvider());
        }
    }
    @SubscribeEvent
    public static void onMijiChaged(MijiChanged event){
        String name = event.getMijiName();
        PlayerEntity player = event.getPlayerIn();

        if(player instanceof ServerPlayerEntity){
            fileconfig.load();
            LazyOptional<IQiCapability> QiCap = player.getCapability(ModCapability.QI_CAPABILITY);
            LazyOptional<IKungFuCapability> KungfuCap = player.getCapability(ModCapability.KUNGFU_CAPABILITY);
            KungfuCap.ifPresent((k)->{
                miji = k.getKungfuNBT();
                String type = fileconfig.get(name+".type");
                CompoundNBT gongfa = miji.getCompound(type);
                int level = gongfa.getInt("level");
                int maxPro = fileconfig.getInt(name+".proficiencyPer")*(level-1)+fileconfig.getInt(name+".proficiency");
                if( level < fileconfig.getInt(name+".level")&& ( event.getProficiencyNow()>= maxPro)){
                    int nowPro = event.getProficiencyNow()-maxPro;
                    gongfa.putInt("level",level+1);
                    gongfa.putInt("proficiency",nowPro);
                    miji.put(type,gongfa);
                    k.setKungfuNBT(miji);
                    MinecraftForge.EVENT_BUS.post(new MijiChanged(player,name,level,event.getProficiencyNow(),nowPro));
                }
            });
            QiCap.ifPresent((q) -> {
                int level = miji.getCompound("neigong").getInt("level");
                q.setMaximum(+fileconfig.getInt(name+"qiPer")*(level-1));
            });
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            LazyOptional<IQiCapability> oldQiCap = event.getOriginal().getCapability(ModCapability.QI_CAPABILITY);
            LazyOptional<IQiCapability> newQiCap = event.getPlayer().getCapability(ModCapability.QI_CAPABILITY);
            LazyOptional<IKungFuCapability> oldKungFuCap = event.getOriginal().getCapability(ModCapability.KUNGFU_CAPABILITY);
            LazyOptional<IKungFuCapability> newKungFuCap = event.getPlayer().getCapability(ModCapability.KUNGFU_CAPABILITY);
            if (oldQiCap.isPresent() && newQiCap.isPresent()) {
                newQiCap.ifPresent((newCap) -> oldQiCap.ifPresent((oldCap) -> newCap.deserializeNBT(oldCap.serializeNBT())));
            }
            if (oldKungFuCap.isPresent() && newKungFuCap.isPresent()) {
                newKungFuCap.ifPresent((newCap) -> oldKungFuCap.ifPresent((oldCap) -> newCap.deserializeNBT(oldCap.serializeNBT())));
            }
        }
    }
    @SubscribeEvent
    public static void onPlayer(TickEvent.PlayerTickEvent event){
        if(event.side == LogicalSide.SERVER){
            PlayerEntity player =event.player;
            if(player instanceof ServerPlayerEntity){
                LazyOptional<IQiCapability> QiCap = player.getCapability(ModCapability.QI_CAPABILITY);
                QiCap.ifPresent((q) -> {
                    q.speedUp(player);
                    q.healthCure(player);
                });
            }
        }
    }

    @SubscribeEvent
    public static void onBreakEvent(PlayerEvent.BreakSpeed event){
        PlayerEntity player = event.getPlayer();

    }
    @SubscribeEvent
    public static void onDrag(GuiScreenEvent.MouseDragEvent event){
        positionX = positionX+event.getDragX();
        positionY = positionY+event.getDragY();
        TEMP_HUD1X = positionX;
        TEMP_HUD1Y = positionY;
    }
    //use item Listener
    @SubscribeEvent
    public static void onItemFinish(LivingEntityUseItemEvent.Finish event){

    }
}
