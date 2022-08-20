//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.util;

import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;

public class ColorUtil
{
    public static int getRainbow(final int speed, final int offset, final float s) {
        float hue = (float)((System.currentTimeMillis() + offset) % speed);
        hue /= speed;
        return Color.getHSBColor(hue, s, 1.0f).getRGB();
    }
    
    public static int mainColor() {
        return new Color(165, 9, 182).getRGB();
    }
    
    public static int friendColor() {
        return new Color(0, 237, 255).getRGB();
    }
    
    public static void color(final int color) {
        GL11.glColor4f((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, (color >> 24 & 0xFF) / 255.0f);
    }
    
    public static int getPurpleWave(final int offset) {
        final double scale = 0.01568627450980392;
        final double speed = 5000.0;
        final double d = 0.7071067811865483;
        final double pos = d * d * scale + (System.currentTimeMillis() + offset) % speed * 2.0 * 3.141592653589793 / speed;
        return 0xFF000000 | MathHelper.clamp(MathHelper.floor(255.0 * (0.5 + Math.sin(0.0 + pos))), 200, 255) << 16 | MathHelper.clamp(MathHelper.floor(255.0 * (0.5 + Math.sin(4.1887902047863905 + pos))), 200, 255);
    }
}
