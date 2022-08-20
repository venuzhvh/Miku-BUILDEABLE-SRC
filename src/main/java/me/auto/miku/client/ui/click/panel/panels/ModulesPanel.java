//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.ui.click.panel.panels;

import me.auto.miku.client.ui.click.panel.*;
import java.util.*;
import me.auto.miku.client.ui.click.panel.panels.modules.frame.*;
import me.auto.miku.api.module.*;
import me.auto.miku.client.ui.click.panel.panels.modules.frame.impl.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;

public class ModulesPanel extends Panel
{
    public static ArrayList<Frame> frames;
    
    public ModulesPanel() {
        super("Modules");
        ModulesPanel.frames = new ArrayList<Frame>();
        int x = 2;
        int y = 20;
        for (final Category moduleCategory : Category.values()) {
            ModulesPanel.frames.add((Frame)new CategoryFrame(moduleCategory, (float)x, (float)y, 100.0f, 18.0f));
            if (x + 225 >= new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth()) {
                x = 2;
                y += 20;
            }
            else {
                x += 125;
            }
        }
        ModulesPanel.frames.forEach(Frame::init);
    }
    
    public void draw(final int mouseX, final int mouseY, final float partialTicks) {
        ModulesPanel.frames.forEach(frame -> frame.drawScreen(mouseX, mouseY, partialTicks));
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        ModulesPanel.frames.forEach(frame -> frame.mouseClicked(mouseX, mouseY, mouseButton));
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
        ModulesPanel.frames.forEach(frame -> frame.keyTyped(typedChar, keyCode));
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        ModulesPanel.frames.forEach(frame -> frame.mouseReleased(mouseX, mouseY, state));
    }
    
    public void onGuiClosed() {
        ModulesPanel.frames.forEach(frame -> {
            frame.onGuiClosed();
            frame.setDragging(false);
        });
    }
}
