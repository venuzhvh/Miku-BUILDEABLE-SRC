//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.ui.click.panel.panels.modules.impl;

import me.auto.miku.client.ui.click.panel.panels.modules.*;
import me.auto.miku.api.property.impl.*;
import me.auto.miku.client.ui.click.*;
import me.auto.miku.api.util.*;

public class BooleanComponent extends Component
{
    private final BooleanProperty booleanProperty;
    
    public BooleanComponent(final BooleanProperty booleanProperty, final float x, final float y, final float offsetx, final float offsety, final float w, final float h) {
        super(booleanProperty.getLabel(), x, y, offsetx, offsety, w, h);
        this.booleanProperty = booleanProperty;
    }
    
    public void init() {
        super.init();
    }
    
    public void moved(final float x, final float y) {
        super.moved(x, y);
    }
    
    public void drawScreen(final int mx, final int my, final float partialTicks) {
        super.drawScreen(mx, my, partialTicks);
        Menu.font.drawStringWithShadow(this.getLabel(), (double)(this.getX() + 2.0f), (double)(this.getY() + this.getH() / 2.0f - Menu.font.getHeight() / 2.0f), ((boolean)this.booleanProperty.getValue()) ? ColorUtil.mainColor() : -1);
        RenderUtil.drawRect((double)(this.getX() + this.getW() - 13.0f), (double)(this.getY() + 4.0f), 9.0, 9.0, ((boolean)this.booleanProperty.getValue()) ? ColorUtil.mainColor() : -1);
        if (this.booleanProperty.getValue()) {
            RenderUtil.drawRect((double)(this.getX() + this.getW() - 12.0f), (double)(this.getY() + 5.0f), 7.0, 7.0, ((boolean)this.booleanProperty.getValue()) ? ColorUtil.mainColor() : -1);
        }
    }
    
    public void mouseClicked(final int mx, final int my, final int button) {
        super.mouseClicked(mx, my, button);
        final boolean hovered = MouseUtil.withinBounds(mx, my, this.getX(), this.getY(), this.getW(), this.getH());
        if (button == 0 && hovered) {
            this.booleanProperty.setValue((Boolean) !(boolean)this.booleanProperty.getValue());
        }
    }
}
