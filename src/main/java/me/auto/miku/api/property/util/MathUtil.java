//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.property.util;

public class MathUtil
{
    public static <T extends Number> T clamp(final T value, final T minimum, final T maximum) {
        return (value.floatValue() <= minimum.floatValue()) ? minimum : ((value.floatValue() >= maximum.floatValue()) ? maximum : value);
    }
}
