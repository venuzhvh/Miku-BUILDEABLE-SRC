//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.event;

import net.minecraftforge.fml.common.eventhandler.*;

public class EventStaged extends Event
{
    EventStage stage;
    
    public EventStaged(final EventStage stage) {
        this.stage = stage;
    }
    
    public EventStage getStage() {
        return this.stage;
    }
}
