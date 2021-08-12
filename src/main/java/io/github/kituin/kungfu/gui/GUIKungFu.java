package io.github.kituin.kungfu.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.kituin.kungfu.Config;
import io.github.kituin.kungfu.Utils;
import io.github.kituin.kungfu.gui.config.ConfigButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GUIKungFu extends Screen {
    TextFieldWidget textFieldWidget;
    KungFuButton button;
    ConfigButton configButton;
    TranslationTextComponent content = new TranslationTextComponent("gui." + Utils.MOD_ID + ".xxxxxx");
    ResourceLocation OBSIDIAN_FIRST_GUI_TEXTURE = new ResourceLocation(Utils.MOD_ID, "textures/gui/first_gui.png");

    public GUIKungFu(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        assert this.minecraft != null;
        this.minecraft.keyboardListener.enableRepeatEvents(true);
        this.textFieldWidget = new TextFieldWidget(this.font, this.width / 2 - 100, 66, 200, 20, new TranslationTextComponent("gui." + Utils.MOD_ID + ".first.context"));
        this.children.add(this.textFieldWidget);

        this.button = new KungFuButton(200, 200, 50, 50, new TranslationTextComponent("gui." + Utils.MOD_ID + ".first.abab"),
                (button) -> {
            this.closeScreen();
            Minecraft.getInstance().displayGuiScreen(new SettingGUI(new TranslationTextComponent(Utils.MOD_ID + ".settings")));
                });
        configButton = new ConfigButton(100, 100, 128, 64, new TranslationTextComponent("gui." + Utils.MOD_ID + ".first.bbbb"),
                (button) -> {
                });
        this.addButton(button);
        this.addButton(configButton);
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        assert this.minecraft != null;
        this.button.setAlpha(0.3F);
        this.minecraft.getTextureManager().bindTexture(OBSIDIAN_FIRST_GUI_TEXTURE);
        int textureWidth = 208;
        int textureHeight = 156;
        blit(matrixStack, this.width / 2 - 150, 10, 0, 0, 300, 200, textureWidth, textureHeight);
        drawCenteredString(matrixStack, this.font, content, this.width / 2 - 10, 30, 0xeb0505);
        this.textFieldWidget.render(matrixStack, mouseX, mouseY, partialTicks);
        this.button.render(matrixStack, mouseX, mouseY, partialTicks);
        this.configButton.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
    @Override
    public void tick(){
        this.buttons.clear();
        init();
    }

}

