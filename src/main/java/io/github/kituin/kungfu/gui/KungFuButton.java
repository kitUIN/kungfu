package io.github.kituin.kungfu.gui;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class KungFuButton extends ButtonBuilder {
    public static final ITooltip EMPTY_TOOLTIP = (button, matrixStack, mouseX, mouseY) -> {
    };
    protected final IPressable onPress;
    protected final ITooltip onTooltip;

    public KungFuButton(int x, int y, int width, int height, ITextComponent title, IPressable pressedAction) {
        this(x, y, width, height, title, pressedAction, EMPTY_TOOLTIP);
    }

    public KungFuButton(int x, int y, int width, int height, ITextComponent title,IPressable pressedAction, ITooltip onTooltip) {
        super(x, y, width, height, title);
        this.onPress = pressedAction;
        this.onTooltip = onTooltip;
    }

    public void onPress() {
        this.onPress.onPress(this);
    }

    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.renderWidget(matrixStack, mouseX, mouseY, partialTicks);
        if (this.isHovered()) {
            this.renderToolTip(matrixStack, mouseX, mouseY);
        }

    }

    public void renderToolTip(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.onTooltip.onTooltip(this, matrixStack, mouseX, mouseY);
    }

    @OnlyIn(Dist.CLIENT)
    public interface IPressable {
        void onPress(ButtonBuilder p_onPress_1_);
    }

    @OnlyIn(Dist.CLIENT)
    public interface ITooltip {
        void onTooltip(ButtonBuilder p_onTooltip_1_, MatrixStack p_onTooltip_2_, int p_onTooltip_3_, int p_onTooltip_4_);
    }
}