package io.github.kituin.kungfu.gui.config;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.kituin.kungfu.Config;
import io.github.kituin.kungfu.Utils;
import io.github.kituin.kungfu.gui.SettingGUIBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;


public class ConfigGUI extends Screen {
    private ConfigBuilder settingHUD;

    public ConfigGUI(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        assert this.minecraft != null;
        this.minecraft.keyboardListener.enableRepeatEvents(true);
        this.settingHUD = new ConfigBuilder(100,100, 128, 128, new TranslationTextComponent("666"));
        this.children.add(this.settingHUD);
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        assert this.minecraft != null;
        this.settingHUD.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }


}