//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.ui.click.panel.panels.modules.frame.impl;

import me.auto.miku.client.ui.click.panel.panels.modules.frame.*;
import me.auto.miku.*;
import me.auto.miku.api.module.*;
import me.auto.miku.client.ui.click.panel.panels.modules.impl.*;
import java.util.*;
import me.auto.miku.client.ui.click.*;
import me.auto.miku.api.util.*;
import org.lwjgl.input.*;
import me.auto.miku.client.ui.click.panel.panels.modules.*;

public class CategoryFrame extends Frame
{
    private final Category moduleCategory;
    
    public CategoryFrame(final Category moduleCategory, final float x, final float y, final float w, final float h) {
        super(moduleCategory.getLabel(), x, y, w, h);
        this.moduleCategory = moduleCategory;
    }
    
    public void init() {
        float offsetY = this.getH() - 2.0f;
        for (final Module iModule : Miku.INSTANCE.getModuleManager().findByCategory(this.moduleCategory)) {
            if (iModule.isPersistent()) {
                this.getComponents().add(new ModuleComponent(iModule, this.getX(), this.getY(), 2.0f, offsetY, this.getW() - 4.0f, 15.0f));
                offsetY += 15.0f;
            }
        }
        super.init();
    }
    
    public void moved(final float x, final float y) {
        super.moved(x, y);
    }
    
    public void drawScreen(final int x, final int y, final float partialTicks) {
        super.drawScreen(x, y, partialTicks);
        RenderUtil.drawRect((double)(this.getX() - 1.0f), (double)(this.getY() - 2.0f), (double)(this.getW() + 3.0f), (double)this.getH(), ColorUtil.mainColor());
        Menu.font.drawStringWithShadow(this.getLabel(), (double)(this.getX() + this.getW() / 2.0f - Menu.font.getStringWidth(this.getLabel()) / 2.0f), (double)(this.getY() - 2.0f + this.getH() / 2.0f - Menu.font.getHeight() / 2.0f), -1);
        if (this.isExtended()) {
            if (this.isScrollable()) {
                if (MouseUtil.withinBounds(x, y, this.getX(), this.getY() + this.getH(), this.getW(), 280.0f)) {
                    final int wheel = Mouse.getDWheel();
                    if (wheel < 0) {
                        if (this.getScrollY() - 6.0 < -(this.getHeight() - Math.min(this.getHeight(), 280.0f))) {
                            this.setScrollY((double)(int)(-(this.getHeight() - Math.min(this.getHeight(), 280.0f))));
                        }
                        else {
                            this.setScrollY(this.getScrollY() - 6.0);
                        }
                    }
                    else if (wheel > 0) {
                        this.setScrollY(this.getScrollY() + 6.0);
                    }
                }
                if (this.getScrollY() > 0.0) {
                    this.setScrollY(0.0);
                }
                if (this.getHeight() > 280.0f) {
                    if (this.getScrollY() - 6.0 < -(this.getHeight() - 280.0f)) {
                        this.setScrollY((double)(int)(-(this.getHeight() - 280.0f)));
                    }
                }
                else if (this.getScrollY() < 0.0) {
                    this.setScrollY(0.0);
                }
            }
            this.resetHeights();
        }
    }
    
    public void mouseClicked(final int x, final int y, final int button) {
        super.mouseClicked(x, y, button);
    }
    
    public void mouseReleased(final int x, final int y, final int button) {
        super.mouseReleased(x, y, button);
    }
    
    public void keyTyped(final char character, final int key) {
        super.keyTyped(character, key);
    }
    
    private void resetHeights() {
        float offsetY = this.getH() - 2.0f;
        for (final Component component : this.getComponents()) {
            component.setOffsety(offsetY);
            component.moved(this.getX(), (float)(this.getY() + this.getScrollY()));
            if (component instanceof ModuleComponent && ((ModuleComponent)component).isExtended()) {
                for (final Component component2 : component.getSubComponents()) {
                    offsetY += component2.getH();
                }
            }
            offsetY += component.getH();
        }
    }
}
