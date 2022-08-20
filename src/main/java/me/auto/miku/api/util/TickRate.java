//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.util;

import me.auto.miku.api.manage.*;
import net.minecraftforge.common.*;
import net.minecraft.util.math.*;
import me.auto.miku.client.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class TickRate implements IManager
{
    private long prevTime;
    private final float[] ticks;
    private int currentTick;
    
    public TickRate() {
        this.ticks = new float[20];
    }
    
    public void load() {
        this.prevTime = -1L;
        for (int i = 0, len = this.ticks.length; i < len; ++i) {
            this.ticks[i] = 0.0f;
        }
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public float getTickRate() {
        int tickCount = 0;
        float tickRate = 0.0f;
        for (final float tick : this.ticks) {
            if (tick > 0.0f) {
                tickRate += tick;
                ++tickCount;
            }
        }
        return MathHelper.clamp(tickRate / tickCount, 0.0f, 20.0f);
    }
    
    public void unload() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    public void receivePacket(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketTimeUpdate) {
            if (this.prevTime != -1L) {
                this.ticks[this.currentTick % this.ticks.length] = MathHelper.clamp(20.0f / ((System.currentTimeMillis() - this.prevTime) / 1000.0f), 0.0f, 20.0f);
                ++this.currentTick;
            }
            this.prevTime = System.currentTimeMillis();
        }
    }
}
