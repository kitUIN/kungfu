package io.github.kituin.kungfu.gui.config;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.kituin.kungfu.Config;
import io.github.kituin.kungfu.Utils;
import io.github.kituin.kungfu.gui.SettingGUIBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.github.kituin.kungfu.Utils.MOD_ID;
import static io.github.kituin.kungfu.config.MiJiConfig.fileconfig;


public class ConfigGUI extends Screen {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(MOD_ID,"textures/gui/background_config.png");
    private ConfigButton settingHUD;
    List<String> list = new ArrayList<>();
    public ConfigGUI(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        for (Map.Entry<String,Object> entry:fileconfig.valueMap().entrySet()){
            list.add(entry.getKey());
            System.out.println(entry.getKey());
        }
        assert this.minecraft != null;
        this.minecraft.keyboardListener.enableRepeatEvents(true);
        this.settingHUD = new ConfigButton(100,100, 128, 128, new TranslationTextComponent("666"),
                (button) -> {
                });
        this.children.add(this.settingHUD);
        super.init();
    }


    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        assert this.minecraft != null;
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        blit(matrixStack, this.width / 2 - 150, this.height /2 - 100, 0, 0, 300, 200, 300, 200);
        drawCenteredString(matrixStack, this.font, new TranslationTextComponent("item.kungfu.miji.yijinjing"), this.width / 2 - 75, this.height / 2 - 70, 0xeb0505);
        this.settingHUD.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }


}