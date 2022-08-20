//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.ui.click.panel.panels.modules.impl;

import me.auto.miku.client.ui.click.panel.panels.modules.*;
import me.auto.miku.api.property.impl.string.impl.*;
import me.auto.miku.client.ui.click.*;
import org.apache.commons.lang3.*;
import me.auto.miku.api.util.*;

public class ModeComponent extends Component
{
    private final ModeStringProperty modeStringProperty;
    
    public ModeComponent(final ModeStringProperty ModeStringProperty, final float x, final float y, final float offsetx, final float offsety, final float w, final float h) {
        super(ModeStringProperty.getLabel(), x, y, offsetx, offsety, w, h);
        this.modeStringProperty = ModeStringProperty;
    }
    
    public void init() {
        super.init();
    }
    
    public void moved(final float x, final float y) {
        super.moved(x, y);
    }
    
    public void drawScreen(final int mx, final int my, final float partialTicks) {
        super.drawScreen(mx, my, partialTicks);
        Menu.font.drawStringWithShadow(StringUtils.capitalize(this.getLabel()) + ": " + StringUtils.capitalize(((String)this.modeStringProperty.getValue()).toLowerCase()), (double)(this.getX() + 2.0f), (double)(this.getY() + this.getH() / 2.0f - Menu.font.getHeight() / 2.0f), -1);
    }
    
    public void mouseClicked(final int mx, final int my, final int button) {
        super.mouseClicked(mx, my, button);
        final boolean hovered = MouseUtil.withinBounds(mx, my, this.getX(), this.getY(), this.getW(), this.getH());
        if (button == 0 && hovered) {
            this.modeStringProperty.increment();
        }
        if (button == 1 && hovered) {
            this.modeStringProperty.decrement();
        }
    }
}
