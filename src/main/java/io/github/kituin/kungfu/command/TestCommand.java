package io.github.kituin.kungfu.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.github.kituin.kungfu.capabilities.qi.IQiCapability;
import io.github.kituin.kungfu.capabilities.ModCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestCommand implements Command<CommandSource> {
    public static TestCommand instance = new TestCommand();
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public int run(CommandContext<CommandSource> context) {
        Entity sender = context.getSource().getEntity();
        assert Minecraft.getInstance().player != null;
        PlayerEntity playerIn = (PlayerEntity) Minecraft.getInstance().player.getEntity();
        LazyOptional<IQiCapability> speedCap = playerIn.getCapability(ModCapability.QI_CAPABILITY);
        speedCap.ifPresent((l) -> {
                    float level = l.getCurrent();
                    context.getSource().sendFeedback(new TranslationTextComponent(String.valueOf(level)), false);
                }
        );
        // context.getSource().sendFeedback(new TranslationTextComponent("cmd." + Utils.MOD_ID + ".hello"), false);
        return 0;
    }
}