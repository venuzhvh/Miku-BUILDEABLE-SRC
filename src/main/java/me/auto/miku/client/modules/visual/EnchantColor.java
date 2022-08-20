//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.visual;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.api.property.annotations.*;
import me.auto.miku.api.util.*;
import java.awt.*;

@ModuleManifest(label = "GlintModifier", category = Category.VISUAL)
public class EnchantColor extends Module
{
    @Clamp(maximum = "255")
    @Property("Red")
    public int red;
    @Clamp(maximum = "255")
    @Property("Green")
    public int green;
    @Clamp(maximum = "255")
    @Property("Blue")
    public int blue;
    @Property("Rainbow")
    public boolean rainbow;
    
    public EnchantColor() {
        this.red = 165;
        this.green = 9;
        this.blue = 182;
        this.rainbow = true;
    }
    
    public int getColor() {
        return this.rainbow ? ColorUtil.getRainbow(3500, 0, 1.0f) : new Color(this.red, this.green, this.blue).getRGB();
    }
}
