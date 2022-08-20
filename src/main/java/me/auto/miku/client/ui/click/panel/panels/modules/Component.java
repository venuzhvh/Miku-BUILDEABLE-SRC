//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.ui.click.panel.panels.modules;

import java.util.*;

public class Component
{
    private float x;
    private float y;
    private float offsetx;
    private float offsety;
    private float w;
    private float h;
    private String label;
    private final ArrayList<Component> subComponents;
    
    public Component(final String label, final float x, final float y, final float offsetx, final float offsety, final float w, final float h) {
        this.subComponents = new ArrayList<Component>();
        this.label = label;
        this.x = x + offsetx;
        this.y = y + offsety;
        this.w = w;
        this.h = h;
        this.offsetx = offsetx;
        this.offsety = offsety;
    }
    
    public void init() {
        this.subComponents.forEach(Component::init);
    }
    
    public void moved(final float x, final float y) {
        this.x = x + this.offsetx;
        this.y = y + this.offsety;
        this.subComponents.forEach(subComponents -> subComponents.moved(this.getX(), this.getY()));
    }
    
    public void onGuiClosed() {
        this.subComponents.forEach(Component::onGuiClosed);
    }
    
    public void drawScreen(final int mx, final int my, final float partialTicks) {
        this.subComponents.forEach(subComponents -> subComponents.drawScreen(mx, my, partialTicks));
    }
    
    public void mouseClicked(final int mx, final int my, final int button) {
        this.subComponents.forEach(subComponents -> subComponents.mouseClicked(mx, my, button));
    }
    
    public void mouseReleased(final int mx, final int my, final int button) {
        this.subComponents.forEach(subComponents -> subComponents.mouseReleased(mx, my, button));
    }
    
    public void keyTyped(final char character, final int key) {
        this.subComponents.forEach(subComponents -> subComponents.keyTyped(character, key));
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
    
    public float getOffsetx() {
        return this.offsetx;
    }
    
    public void setOffsetx(final float offsetx) {
        this.offsetx = offsetx;
    }
    
    public float getOffsety() {
        return this.offsety;
    }
    
    public void setOffsety(final float offsety) {
        this.offsety = offsety;
    }
    
    public ArrayList<Component> getSubComponents() {
        return this.subComponents;
    }
}
