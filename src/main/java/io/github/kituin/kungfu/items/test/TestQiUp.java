package io.github.kituin.kungfu.items.test;



import io.github.kituin.kungfu.capabilities.qi.IQiCapability;
import io.github.kituin.kungfu.capabilities.ModCapability;
import io.github.kituin.kungfu.items.group.ModGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.ParametersAreNonnullByDefault;

public class TestQiUp extends Item {
    public TestQiUp() {
        super(new Properties().group(ModGroup.ITEM_GROUP));
    }
    @ParametersAreNonnullByDefault
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            LazyOptional<IQiCapability> speedCap = playerIn.getCapability(ModCapability.QI_CAPABILITY);
            speedCap.ifPresent((l) -> {
                        l.setCurrent(l.getCurrent()+10);
                        playerIn.sendMessage(new TranslationTextComponent("message.KungFu_qi_show"+l.getCurrent()), playerIn.getUniqueID());
                    }
            );
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
