package io.github.kituin.kungfu.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.kituin.kungfu.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SettingGUIBuilder extends Widget {
    ResourceLocation OBSIDIAN_FIRST_GUI_TEXTURE = new ResourceLocation(Utils.MOD_ID, "textures/gui/miji.png");
    public SettingGUIBuilder(int x, int y, int width, int height, ITextComponent title) {
        super(x, y, width, height, title);
    }

    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(OBSIDIAN_FIRST_GUI_TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 0.2F);
        this.blit(matrixStack, this.x, this.y, 50, 0, this.width, this.height);
        this.renderBg(matrixStack, minecraft, mouseX, mouseY);
    }
}