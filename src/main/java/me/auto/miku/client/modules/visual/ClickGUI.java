//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.visual;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.client.ui.click.*;
import net.minecraft.client.gui.*;

@ModuleManifest(label = "ClickGUI", category = Category.VISUAL, key = 25)
public class ClickGUI extends Module
{
    public Menu menu;
    
    public void onEnable() {
        if (this.isNull()) {
            return;
        }
        if (this.menu == null) {
            (this.menu = new Menu()).init();
        }
        ClickGUI.mc.displayGuiScreen((GuiScreen)this.menu);
        this.setEnabled(false);
    }
}
