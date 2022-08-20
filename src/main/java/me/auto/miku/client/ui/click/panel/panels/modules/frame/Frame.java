//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.ui.click.panel.panels.modules.frame;

import me.auto.miku.client.ui.click.panel.panels.modules.*;
import me.auto.miku.client.ui.click.panel.panels.modules.Component;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import me.auto.miku.api.util.*;
import me.auto.miku.client.ui.click.panel.panels.modules.impl.*;
import java.util.*;

public class Frame
{
    private float x;
    private float y;
    private float lastx;
    private float lasty;
    private float w;
    private float h;
    private String label;
    private boolean dragging;
    private boolean scrollable;
    private boolean extended;
    private final ArrayList<Component> components;
    private double scrollY;
    
    public Frame(final String label, final float x, final float y, final float w, final float h) {
        this.extended = true;
        this.components = new ArrayList<Component>();
        this.label = label;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    public void init() {
        this.components.forEach(Component::init);
    }
    
    public void moved(final float x, final float y) {
        this.components.forEach(component -> component.moved(x, y));
    }
    
    public void drawScreen(final int mx, final int my, final float partialTicks) {
        if (this.isDragging()) {
            this.setX(mx + this.getLastx());
            this.setY(my + this.getLasty());
            this.moved(this.getX(), (float)(this.getY() + this.getScrollY()));
        }
        if (this.getX() < 0.0f) {
            this.setX(0.0f);
            this.moved(this.getX(), (float)(this.getY() + this.getScrollY()));
        }
        if (this.getX() + this.getW() > new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth()) {
            this.setX(new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() - this.getW());
            this.moved(this.getX(), (float)(this.getY() + this.getScrollY()));
        }
        if (this.getY() < 0.0f) {
            this.setY(0.0f);
            this.moved(this.getX(), (float)(this.getY() + this.getScrollY()));
        }
        if (this.getY() + this.getH() > new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight()) {
            this.setY(new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight() - this.getH());
            this.moved(this.getX(), (float)(this.getY() + this.getScrollY()));
        }
        if (this.isExtended()) {
            RenderUtil.drawBorderedRect(this.getX() - 1.0f, this.getY() + this.getH() - 2.0f, this.getW() + 3.0f, (this.getHeight() > 280.0f) ? 280.0f : this.getHeight(), 1.5f, new Color(26, 26, 31, 150).getRGB(), new Color(20, 20, 20, 235).getRGB());
            GL11.glPushMatrix();
            GL11.glEnable(3089);
            RenderUtil.prepareScissorBox(new ScaledResolution(Minecraft.getMinecraft()), this.getX() - 1.0f, this.getY() + this.getH(), this.getW() + 3.0f, 277.0f);
            this.components.forEach(component -> component.drawScreen(mx, my, partialTicks));
            GL11.glDisable(3089);
            GL11.glPopMatrix();
            if (this.getHeight() > 280.0f) {
                this.scrollable = true;
            }
        }
    }
    
    public void mouseClicked(final int mx, final int my, final int button) {
        final boolean hovered = MouseUtil.withinBounds(mx, my, this.getX(), this.getY(), this.getW(), this.getH() - 1.0f);
        switch (button) {
            case 0: {
                if (hovered) {
                    this.setDragging(true);
                    this.setLastx(this.getX() - mx);
                    this.setLasty(this.getY() - my);
                    break;
                }
                break;
            }
            case 1: {
                if (hovered && this.components.size() > 0) {
                    this.setExtended(!this.isExtended());
                    break;
                }
                break;
            }
        }
        if (this.isExtended()) {
            this.components.forEach(component -> component.mouseClicked(mx, my, button));
        }
    }
    
    public void mouseReleased(final int mx, final int my, final int button) {
        if (button == 0 && this.isDragging()) {
            this.setDragging(false);
        }
        if (this.isExtended()) {
            this.components.forEach(component -> component.mouseReleased(mx, my, button));
        }
    }
    
    public void onGuiClosed() {
        this.components.forEach(Component::onGuiClosed);
    }
    
    public void keyTyped(final char character, final int key) {
        if (this.isExtended()) {
            this.components.forEach(component -> component.keyTyped(character, key));
        }
    }
    
    public float getX() {
        return this.x;
    }
    
    public void setX(final float x) {
        this.x = x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public void setY(final float y) {
        this.y = y;
    }
    
    public float getW() {
        return this.w;
    }
    
    public void setW(final float w) {
        this.w = w;
    }
    
    public float getH() {
        return this.h;
    }
    
    public void setH(final float h) {
        this.h = h;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public boolean isDragging() {
        return this.dragging;
    }
    
    public void setDragging(final boolean dragging) {
        this.dragging = dragging;
    }
    
    public boolean isExtended() {
        return this.extended;
    }
    
    public void setExtended(final boolean extended) {
        this.extended = extended;
    }
    
    public float getLastx() {
        return this.lastx;
    }
    
    public void setLastx(final float lastx) {
        this.lastx = lastx;
    }
    
    public float getLasty() {
        return this.lasty;
    }
    
    public void setLasty(final float lasty) {
        this.lasty = lasty;
    }
    
    public ArrayList<Component> getComponents() {
        return this.components;
    }
    
    public int getVisualComponentCount() {
        int count = this.components.size();
        for (final Component component : this.components) {
            if (component instanceof ModuleComponent && ((ModuleComponent)component).isExtended()) {
                count += component.getSubComponents().size();
            }
        }
        return count;
    }
    
    public float getHeight() {
        float offsetY = 1.0f;
        for (final Component component : this.getComponents()) {
            if (component instanceof ModuleComponent && ((ModuleComponent)component).isExtended()) {
                for (final Component component2 : component.getSubComponents()) {
                    offsetY += component2.getH();
                }
            }
            offsetY += component.getH();
        }
        return offsetY;
    }
    
    public double getScrollY() {
        return this.scrollY;
    }
    
    public void setScrollY(final double scrollY) {
        this.scrollY = scrollY;
    }
    
    public boolean isScrollable() {
        return this.scrollable;
    }
}
