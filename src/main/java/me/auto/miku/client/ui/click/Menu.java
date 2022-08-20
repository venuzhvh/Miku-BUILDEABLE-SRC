//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.ui.click;

import me.auto.miku.client.ui.click.panel.*;
import me.auto.miku.api.util.font.*;
import me.auto.miku.client.ui.click.panel.Panel;
import org.lwjgl.opengl.*;
import me.auto.miku.client.ui.click.panel.panels.*;
import net.minecraft.client.gui.*;
import java.util.*;
import me.auto.miku.api.util.*;
import java.io.*;
import java.awt.*;
import java.util.List;

public class Menu extends GuiScreen
{
    public final List<Panel> panels;
    public Panel currentPanel;
    public static final CFontRenderer font;
    
    public Menu() {
        this.panels = new ArrayList<Panel>();
    }
    
    public void init() {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.panels.add(this.currentPanel = new ModulesPanel());
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        final ScaledResolution res = new ScaledResolution(this.mc);
        int x = 0;
        for (final Panel panel : this.panels) {
            final float renderX = (float)(res.getScaledWidth() / 2 + x - this.panels.size() * 20);
            RenderUtil.drawRect((double)(renderX - 5.0f), 0.0, (double)(Menu.font.getStringWidth(panel.getLabel()) + 10.0f), (double)(Menu.font.getHeight() + 6.0f), new Color(0, 0, 0, (panel == this.currentPanel) ? 170 : 137).getRGB());
            Menu.font.drawStringWithShadow(panel.getLabel(), (double)renderX, 4.0, -1);
            x += Menu.font.getStringWidth(panel.getLabel()) + 10;
        }
        this.currentPanel.draw(mouseX, mouseY, partialTicks);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        final ScaledResolution res = new ScaledResolution(this.mc);
        int x = 0;
        for (final Panel panel : this.panels) {
            final float renderX = (float)(res.getScaledWidth() / 2 + x - this.panels.size() * 20);
            if (MouseUtil.withinBounds(mouseX, mouseY, renderX - 5.0f, 0.0f, Menu.font.getStringWidth(panel.getLabel()) + 10.0f, Menu.font.getHeight() + 6.0f)) {
                this.currentPanel = panel;
            }
            x += Menu.font.getStringWidth(panel.getLabel()) + 10;
        }
        this.currentPanel.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        super.mouseReleased(mouseX, mouseY, mouseButton);
        this.currentPanel.mouseReleased(mouseX, mouseY, mouseButton);
    }
    
    public void keyTyped(final char typedChar, final int key) {
        if (key == 1) {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
        this.currentPanel.keyTyped(typedChar, key);
    }
    
    public void onGuiClosed() {
        this.currentPanel.onGuiClosed();
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    static {
        font = new CFontRenderer(new Font("Calibri", 0, 20), true, true);
    }
}
