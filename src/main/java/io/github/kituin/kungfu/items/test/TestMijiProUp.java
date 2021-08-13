package io.github.kituin.kungfu.items.test;

import io.github.kituin.kungfu.capabilities.ModCapability;
import io.github.kituin.kungfu.capabilities.kungfu.IKungFuCapability;
import io.github.kituin.kungfu.capabilities.qi.IQiCapability;
import io.github.kituin.kungfu.events.MijiChanged;
import io.github.kituin.kungfu.items.group.ModGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.ParametersAreNonnullByDefault;

import static io.github.kituin.kungfu.Utils.ITEM_MESSAGE;

public class TestMijiProUp extends Item{
    public final String NAME = "test_miji_proficiency_up";
    public TestMijiProUp() {
        super(new Item.Properties().group(ModGroup.ITEM_GROUP));
    }

    @ParametersAreNonnullByDefault
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            LazyOptional<IKungFuCapability> kungfuCap = playerIn.getCapability(ModCapability.KUNGFU_CAPABILITY);
            kungfuCap.ifPresent((k) -> {
                        k.addProficiency("yijinjing","neigong",100);
                        int pro = k.getKungfuNBT().getCompound("yijinjing").getInt("proficiency");
                        MinecraftForge.EVENT_BUS.post(new MijiChanged(playerIn,"yijinjing",1,pro,pro+100));
                        playerIn.sendMessage(new TranslationTextComponent(ITEM_MESSAGE+NAME).appendSibling(new TranslationTextComponent(String.valueOf(100))), playerIn.getUniqueID());
                    }
            );
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
