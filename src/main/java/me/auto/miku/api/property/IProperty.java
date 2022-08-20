//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.property;

public interface IProperty<T>
{
    String getLabel();
    
    T getValue();
    
    void setValue(final T p0);
    
    void setValue(final String p0);
}
