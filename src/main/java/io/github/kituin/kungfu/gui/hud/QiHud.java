package io.github.kituin.kungfu.gui.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.kituin.kungfu.Config;
import io.github.kituin.kungfu.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;


public class QiHud extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(Utils.MOD_ID, "textures/gui/hud.png");
    private MatrixStack matrixStack;


    public QiHud(MatrixStack matrixStack) {
        this.width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        this.height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        this.minecraft = Minecraft.getInstance();
        this.matrixStack = matrixStack;
    }
    public void setMatrixStack(MatrixStack stack) {
        this.matrixStack = stack;
    }

    public void render(float current_height) {
        int positionX = Config.QIHUDX.get().intValue();
        int positionY = Config.QIHUDY.get().intValue();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(HUD);
        int num = (int) (current_height*64);
        blit(matrixStack, positionX, positionY, 64, 0, 64, 64, 128, 128);
        blit(matrixStack, positionX,positionY+64-num, 0, 64-num, 64, num, 128, 128);
        drawCenteredString(matrixStack, minecraft.fontRenderer, String.format("%.2f", current_height*100)+"%", positionX+25 , positionY+25, 0xeb0505);

    }
}