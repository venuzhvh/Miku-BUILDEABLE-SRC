//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.module;

public enum Category
{
    COMBAT("Combat", 16530235), 
    MOVEMENT("Movement", 7789181), 
    PLAYER("Player", 15522171), 
    MISC("Misc", 10660302), 
    VISUAL("Visual", 13200619), 
    WORLD("World", -1);
    
    int color;
    String label;
    
    private Category(final String label, final int color) {
        this.color = color;
        this.label = label;
    }
    
    public int getColor() {
        return this.color;
    }
    
    public String getLabel() {
        return this.label;
    }
}
