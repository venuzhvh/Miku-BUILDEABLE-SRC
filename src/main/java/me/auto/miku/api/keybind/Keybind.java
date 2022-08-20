//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.keybind;

public class Keybind
{
    private int key;
    private final Action action;
    
    public Keybind(final int key, final Action action) {
        this.key = key;
        this.action = action;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public Action getAction() {
        return this.action;
    }
}
