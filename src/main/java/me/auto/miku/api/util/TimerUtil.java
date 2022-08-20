//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.util;

public class TimerUtil
{
    private long current;
    
    public TimerUtil() {
        this.current = System.currentTimeMillis();
    }
    
    public boolean hasReached(final long delay) {
        return System.currentTimeMillis() - this.current >= delay;
    }
    
    public boolean hasReached(final long delay, final boolean reset) {
        if (reset) {
            this.reset();
        }
        return System.currentTimeMillis() - this.current >= delay;
    }
    
    public void reset() {
        this.current = System.currentTimeMillis();
    }
    
    public long getTimePassed() {
        return System.currentTimeMillis() - this.current;
    }
}
