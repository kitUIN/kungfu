package io.github.kituin.kungfu.items.miji;

import io.github.kituin.kungfu.events.MijiChanged;
import io.github.kituin.kungfu.items.group.ModGroup;
import io.github.kituin.kungfu.capabilities.kungfu.IKungFuCapability;
import io.github.kituin.kungfu.capabilities.ModCapability;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.Event;
import org.antlr.v4.runtime.atn.SemanticContext;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static io.github.kituin.kungfu.Utils.MIJI;

public class YiJinJing extends Item {
    public YiJinJing() {
        super(new Properties().group(ModGroup.ITEM_GROUP));
    }
    public static String NAME = "yijinying";
    public static String ORIGIN_NAME = MIJI+"."+NAME;
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
        tooltip.add(new StringTextComponent(I18n.format(ORIGIN_NAME+".text", TextFormatting.RESET)));

    }
    @ParametersAreNonnullByDefault
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            LazyOptional<IKungFuCapability> KungfuCap = playerIn.getCapability(ModCapability.KUNGFU_CAPABILITY);
            KungfuCap.ifPresent((k) -> {
                        k.setMiJiNBT(NAME,1,0);
                        MinecraftForge.EVENT_BUS.post(new MijiChanged(NAME,1,0,0));
                        playerIn.sendMessage(new TranslationTextComponent(ORIGIN_NAME+".success"), playerIn.getUniqueID());
                        playerIn.sendMessage(new TranslationTextComponent(String.valueOf(k.getKungfuNBT())), playerIn.getUniqueID());
                    }
            );
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}