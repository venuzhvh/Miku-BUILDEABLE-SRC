//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.ui.click.panel.panels.modules.impl;

import me.auto.miku.client.ui.click.Menu;
import me.auto.miku.client.ui.click.panel.panels.modules.*;
import me.auto.miku.api.module.*;
import me.auto.miku.*;
import me.auto.miku.api.property.*;
import me.auto.miku.api.property.impl.*;
import me.auto.miku.api.property.impl.string.impl.*;
import me.auto.miku.api.property.impl.string.*;
import java.util.*;
import java.awt.*;
import me.auto.miku.client.ui.click.*;
import me.auto.miku.client.ui.click.panel.panels.modules.Component;
import org.lwjgl.input.*;
import me.auto.miku.api.util.*;

public class ModuleComponent extends Component
{
    private final Module module;
    private boolean extended;
    private boolean binding;
    
    public ModuleComponent(final Module module, final float x, final float y, final float offsetx, final float offsety, final float w, final float h) {
        super(module.getDisplayLabel().equals("") ? module.getLabel() : module.getDisplayLabel(), x, y, offsetx, offsety, w, h);
        this.module = module;
    }
    
    public void init() {
        super.init();
        if (Miku.INSTANCE.getPropertyManager().getPropertiesFromObject((Object)this.module) != null) {
            float offsetY = this.getH();
            for (final AbstractProperty property : Miku.INSTANCE.getPropertyManager().getPropertiesFromObject((Object)this.module)) {
                if (property.getValue() instanceof Boolean) {
                    this.getSubComponents().add(new BooleanComponent((BooleanProperty)property, this.getX(), this.getY(), 0.0f, offsetY, this.getW(), this.getH()));
                    offsetY += 15.0f;
                }
                if (property.getValue() instanceof Number) {
                    this.getSubComponents().add(new NumberComponent((NumberProperty)property, this.getX(), this.getY(), 0.0f, offsetY, this.getW(), this.getH()));
                    offsetY += 15.0f;
                }
                if (property.getValue() instanceof String) {
                    if (property instanceof ModeStringProperty) {
                        this.getSubComponents().add(new ModeComponent((ModeStringProperty)property, this.getX(), this.getY(), 0.0f, offsetY, this.getW(), this.getH()));
                        offsetY += 15.0f;
                    }
                    else {
                        if (!(property instanceof StringProperty)) {
                            continue;
                        }
                        this.getSubComponents().add(new StringComponent((StringProperty)property, this.getX(), this.getY(), 0.0f, offsetY, this.getW(), this.getH()));
                        offsetY += 15.0f;
                    }
                }
            }
        }
    }
    
    public void moved(final float x, final float y) {
        super.moved(x, y);
        this.getSubComponents().forEach(component -> component.moved(this.getX(), this.getY()));
    }
    
    public void drawScreen(final int mx, final int my, final float partialTicks) {
        final boolean hovered = MouseUtil.withinBounds(mx, my, this.getX(), this.getY(), this.getW(), this.getH());
        RenderUtil.drawRect((double)this.getX(), (double)this.getY(), (double)this.getW(), (double)this.getH(), hovered ? new Color(0, 0, 0, 200).getRGB() : (this.module.isEnabled() ? new Color(5, 5, 5, 200).getRGB() : new Color(14, 14, 14, 80).getRGB()));
        if (this.module.isEnabled()) {
            RenderUtil.drawRect((double)this.getX(), (double)this.getY(), 1.0, (double)this.getH(), new Color(165, 9, 182, 180).getRGB());
        }
        try {
            if (!Miku.INSTANCE.getPropertyManager().getPropertiesFromObject((Object)this.module).isEmpty()) {
                Menu.font.drawStringWithShadow(this.isExtended() ? "-" : "+", (double)(this.getX() + this.getW() - 12.0f), (double)(this.getY() + this.getH() / 2.0f - Menu.font.getHeight() / 2.0f), new Color(200, 200, 200, 255).getRGB());
            }
        }
        catch (Exception ex) {}
        Menu.font.drawStringWithShadow(this.isBinding() ? "Press a key..." : this.getLabel(), (double)(this.getX() + this.getW() / 2.0f - Menu.font.getStringWidth(this.isBinding() ? "Press a key..." : this.getLabel()) / 2.0f), (double)(this.getY() + this.getH() / 2.0f - Menu.font.getHeight() / 2), this.module.isEnabled() ? -1 : -9408400);
        if (this.isExtended()) {
            super.drawScreen(mx, my, partialTicks);
        }
    }
    
    public void mouseClicked(final int mx, final int my, final int button) {
        final boolean hovered = MouseUtil.withinBounds(mx, my, this.getX(), this.getY(), this.getW(), this.getH());
        switch (button) {
            case 0: {
                if (hovered && !this.isBinding()) {
                    this.module.setEnabled(!this.module.isEnabled());
                    break;
                }
                break;
            }
            case 1: {
                if (hovered && !this.getSubComponents().isEmpty()) {
                    this.setExtended(!this.isExtended());
                    break;
                }
                break;
            }
            case 2: {
                if (hovered) {
                    this.setBinding(!this.isBinding());
                    break;
                }
                break;
            }
        }
        if (this.isExtended()) {
            super.mouseClicked(mx, my, button);
        }
    }
    
    public void mouseReleased(final int x, final int y, final int button) {
        if (this.isExtended()) {
            super.mouseReleased(x, y, button);
        }
    }
    
    public void keyTyped(final char character, final int key) {
        super.keyTyped(character, key);
        if (this.isBinding() && this.module instanceof Module) {
            this.module.getKeybind().setKey((key == 1 || key == 57 || key == 211) ? 0 : key);
            ChatUtil.sendNonDeletableMessage("Bound " + this.getLabel() + " to " + Keyboard.getKeyName(this.module.getKeybind().getKey()));
            this.setBinding(false);
        }
    }
    
    public Module getModule() {
        return this.module;
    }
    
    public boolean isExtended() {
        return this.extended;
    }
    
    public void setExtended(final boolean extended) {
        this.extended = extended;
    }
    
    public boolean isBinding() {
        return this.binding;
    }
    
    public void setBinding(final boolean binding) {
        this.binding = binding;
    }
}
