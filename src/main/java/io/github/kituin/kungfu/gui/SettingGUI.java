package io.github.kituin.kungfu.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.kituin.kungfu.Config;
import io.github.kituin.kungfu.Utils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SettingGUI extends Screen {
    Button button;
    public static double TEMP_HUD1X;
    public static double TEMP_HUD1Y;
    private SettingGUIBuilder settingHUD;

    protected SettingGUI(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        assert this.minecraft != null;
        this.minecraft.keyboardListener.enableRepeatEvents(true);
        this.settingHUD = new SettingGUIBuilder((int)TEMP_HUD1X, (int)TEMP_HUD1Y, 50, 50, new TranslationTextComponent(""));
        this.children.add(this.settingHUD);
        this.button = new Button(200, 200, 50, 25, new TranslationTextComponent("button." + Utils.MOD_ID + ".done"),
                (button) -> {
                    Config.QIHUDX.set(TEMP_HUD1X);
                    Config.QIHUDY.set(TEMP_HUD1Y);
                    this.closeScreen();
                });

        this.addButton(button);
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        assert this.minecraft != null;
        this.settingHUD.render(matrixStack, mouseX, mouseY, partialTicks);
        this.button.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void tick() {
        this.children.clear();
        init();
    }
}