//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.ui.click.panel.panels.modules.impl;

import me.auto.miku.client.ui.click.Menu;
import me.auto.miku.client.ui.click.panel.panels.modules.*;
import me.auto.miku.api.property.impl.string.*;
import me.auto.miku.client.ui.click.*;
import java.awt.*;

import me.auto.miku.client.ui.click.panel.panels.modules.Component;
import org.apache.commons.lang3.*;
import me.auto.miku.api.util.*;
import net.minecraft.client.gui.*;

public class StringComponent extends Component
{
    private final StringProperty stringProperty;
    private String text;
    private boolean typing;
    
    public StringComponent(final StringProperty stringProperty, final float x, final float y, final float offsetX, final float offsetY, final float w, final float h) {
        super(stringProperty.getLabel(), x, y, offsetX, offsetY, w, h);
        this.text = "";
        this.stringProperty = stringProperty;
    }
    
    public void init() {
        super.init();
        this.text = (String)this.stringProperty.getValue();
    }
    
    public void drawScreen(final int mx, final int my, final float partialTicks) {
        super.drawScreen(mx, my, partialTicks);
        RenderUtil.drawRect((double)(this.getX() + Menu.font.getStringWidth(this.getLabel() + ": ")), (double)(this.getY() + this.getH() - 1.5f), (double)this.getW(), 0.5, this.isTyping() ? Color.GRAY.darker().getRGB() : -1);
        Menu.font.drawStringWithShadow(StringUtils.capitalize(this.getLabel()) + ": " + (this.isTyping() ? this.getText() : ((String)this.stringProperty.getValue())), (double)(this.getX() + 2.0f), (double)(this.getY() + this.getH() / 2.0f - Menu.font.getHeight() / 2.0f), -1);
    }
    
    public void mouseClicked(final int mx, final int my, final int button) {
        super.mouseClicked(mx, my, button);
        final boolean hovered = MouseUtil.withinBounds(mx, my, this.getX(), this.getY(), this.getW(), this.getH());
        if (button == 0) {
            if (hovered) {
                this.setTyping(true);
            }
            else if (this.isTyping()) {
                this.setTyping(false);
                this.stringProperty.setValue((String) this.text);
            }
        }
    }
    
    public void keyTyped(final char character, final int key) {
        super.keyTyped(character, key);
        if (this.isTyping()) {
            final String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
            if (key == 14) {
                if (this.text.length() > 1) {
                    this.text = this.text.substring(0, this.text.length() - 1);
                }
                else if (this.text.length() == 1) {
                    this.text = "";
                }
            }
            else if (GuiScreen.isKeyComboCtrlV(key)) {
                final char[] charArray;
                final char[] chars = charArray = GuiScreen.getClipboardString().toCharArray();
                for (final char char1 : charArray) {
                    if (Character.isLetterOrDigit(char1) || Character.isSpaceChar(char1) || specialChars.contains(Character.toString(char1))) {
                        this.text += Character.toString(char1);
                    }
                }
            }
            else if (Character.isLetterOrDigit(character) || Character.isSpaceChar(character) || specialChars.contains(Character.toString(character))) {
                if (Menu.font.getStringWidth(this.text) < 230) {
                    this.text += Character.toString(character);
                }
            }
            else if (key == 28) {
                this.setTyping(false);
                this.stringProperty.setValue((String) this.text);
            }
        }
    }
    
    public void onGuiClosed() {
        super.onGuiClosed();
        this.setTyping(false);
        this.stringProperty.setValue((String) this.text);
    }
    
    public boolean isTyping() {
        return this.typing;
    }
    
    public void setTyping(final boolean typing) {
        this.typing = typing;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
}
