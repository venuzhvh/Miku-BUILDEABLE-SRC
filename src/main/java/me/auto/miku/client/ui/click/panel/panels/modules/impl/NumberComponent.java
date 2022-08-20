//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.ui.click.panel.panels.modules.impl;

import me.auto.miku.client.ui.click.Menu;
import me.auto.miku.client.ui.click.panel.panels.modules.*;
import me.auto.miku.api.property.impl.*;
import me.auto.miku.client.ui.click.panel.panels.modules.Component;
import net.minecraft.util.math.*;
import java.awt.*;
import me.auto.miku.client.ui.click.*;
import me.auto.miku.api.util.*;

public class NumberComponent extends Component
{
    private final NumberProperty numberProperty;
    private boolean sliding;
    
    public NumberComponent(final NumberProperty numberProperty, final float x, final float y, final float offsetx, final float offsety, final float w, final float h) {
        super(numberProperty.getLabel(), x, y, offsetx, offsety, w, h);
        this.numberProperty = numberProperty;
    }
    
    public void init() {
        super.init();
    }
    
    public void moved(final float x, final float y) {
        super.moved(x, y);
    }
    
    public void drawScreen(final int mx, final int my, final float partialTicks) {
        super.drawScreen(mx, my, partialTicks);
        final float length = (float)MathHelper.floor((((Number)this.numberProperty.getValue()).floatValue() - this.numberProperty.getMinimum().floatValue()) / (this.numberProperty.getMaximum().floatValue() - this.numberProperty.getMinimum().floatValue()) * (this.getW() - 1.0f));
        RenderUtil.drawRect((double)(this.getX() + 0.5f), (double)(this.getY() + this.getH() - 1.5f), (double)length, 1.0, this.sliding ? ColorUtil.mainColor() : Color.WHITE.getRGB());
        Menu.font.drawStringWithShadow(this.getLabel() + ": " + this.numberProperty.getValue(), (double)(this.getX() + 2.0f), (double)(this.getY() + this.getH() / 2.0f - Menu.font.getHeight() / 2.0f + 1.0f), -1);
        if (this.sliding) {
            if (this.numberProperty.getValue() instanceof Float) {
                final float preval = (mx - this.getX()) * (this.numberProperty.getMaximum().floatValue() - this.numberProperty.getMinimum().floatValue()) / this.getW() + this.numberProperty.getMinimum().floatValue();
                this.numberProperty.setValue((Number)MathUtil.roundFloat(preval, 2));
            }
            else if (this.numberProperty.getValue() instanceof Integer) {
                final int preval2 = (int)((mx - this.getX()) * (this.numberProperty.getMaximum().intValue() - this.numberProperty.getMinimum().intValue()) / this.getW() + this.numberProperty.getMinimum().intValue());
                this.numberProperty.setValue((Number)preval2);
            }
            else if (this.numberProperty.getValue() instanceof Double) {
                final double preval3 = (mx - this.getX()) * (this.numberProperty.getMaximum().doubleValue() - this.numberProperty.getMinimum().doubleValue()) / this.getW() + this.numberProperty.getMinimum().doubleValue();
                this.numberProperty.setValue((Number)MathUtil.roundDouble(preval3, 2));
            }
        }
    }
    
    public void mouseClicked(final int mx, final int my, final int button) {
        super.mouseClicked(mx, my, button);
        if (MouseUtil.withinBounds(mx, my, this.getX(), this.getY(), this.getW(), this.getH()) && button == 0) {
            this.sliding = true;
        }
    }
    
    public void mouseReleased(final int mx, final int my, final int button) {
        super.mouseReleased(mx, my, button);
        if (this.sliding) {
            this.sliding = false;
        }
    }
    
    public void onGuiClosed() {
        super.onGuiClosed();
        if (this.sliding) {
            this.sliding = false;
        }
    }
}
