package io.github.kituin.kungfu.items.miji;

import io.github.kituin.kungfu.events.MijiChanged;
import io.github.kituin.kungfu.items.group.ModGroup;
import io.github.kituin.kungfu.capabilities.kungfu.IKungFuCapability;
import io.github.kituin.kungfu.capabilities.ModCapability;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;


import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static io.github.kituin.kungfu.Utils.*;
import static io.github.kituin.kungfu.config.MiJiConfig.fileconfig;
import static io.github.kituin.kungfu.utils.LearnedMiji.YIJINJING_LEARNED;

public class YiJinJing extends Item {
    public static final String NAME = "yijinjing";
    public YiJinJing() {
        super(new Properties().group(ModGroup.ITEM_GROUP));
    }
    private static ItemStack itemStack;

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
        itemStack =stack;
        if (YIJINJING_LEARNED){
            tooltip.add(new StringTextComponent(I18n.format(MOD_ID+".miji.learned.yes", TextFormatting.RESET)));
        }else{
            tooltip.add(new StringTextComponent(I18n.format(MOD_ID+".miji.learned.no", TextFormatting.RESET)));
        }
        tooltip.add(new StringTextComponent(I18n.format(ITEM_TEXT+NAME+".one", TextFormatting.RESET)));
        tooltip.add(new StringTextComponent(I18n.format(MOD_ID+".miji.neigong", TextFormatting.RESET)));

        this.addMiji(tooltip);

    }
    public void addMiji(List<ITextComponent> tooltip){
        tooltip.add(new StringTextComponent(""));
        fileconfig.load();
        tooltip.add(new StringTextComponent(I18n.format(MOD_ID+".miji.level",
                TextFormatting.RESET)).appendString(fileconfig.get(NAME+".level").toString()));
        tooltip.add(new StringTextComponent(I18n.format(MOD_ID+".miji.proficiency",
                TextFormatting.RESET)).appendString(fileconfig.get(NAME+".proficiency").toString()));
        tooltip.add(new StringTextComponent(I18n.format(MOD_ID+".miji.qi",
                TextFormatting.RESET)).appendString(fileconfig.get(NAME+".qi").toString()));
        tooltip.add(new StringTextComponent(I18n.format(MOD_ID+".miji.recuperation",
                TextFormatting.RESET)).appendString(String.format("%.3f",(double)fileconfig.get(NAME+".recuperation"))+"/tick"));
    }


    @ParametersAreNonnullByDefault
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            LazyOptional<IKungFuCapability> KungfuCap = playerIn.getCapability(ModCapability.KUNGFU_CAPABILITY);
            KungfuCap.ifPresent((k) -> {
                TranslationTextComponent item = new TranslationTextComponent(ITEM+"miji."+NAME);
                Style style = item.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM,new HoverEvent.ItemHover(itemStack)));
                    if(k.getKungfuNBT().contains(NAME)){
                        playerIn.sendMessage(new TranslationTextComponent(ITEM_MESSAGE+NAME+".already.one").appendSibling(item.setStyle(style)).appendSibling(new TranslationTextComponent(ITEM_MESSAGE+NAME+".already.two")), playerIn.getUniqueID());
                            System.out.println(k.getKungfuNBT());
                    }else {
                        k.setGongfaNBT(NAME,"neigong",1,0);
                        MinecraftForge.EVENT_BUS.post(new MijiChanged(playerIn,NAME,1,0,0));
                        playerIn.sendMessage(new TranslationTextComponent(ITEM_MESSAGE+NAME+".success.one").appendSibling(item.setStyle(style)).appendSibling(new TranslationTextComponent(ITEM_MESSAGE+NAME+".success.two")), playerIn.getUniqueID());
                    }

                        // playerIn.sendMessage(new TranslationTextComponent(String.valueOf(k.getKungfuNBT())), playerIn.getUniqueID());
                }
            );
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}