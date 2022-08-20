//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.accessors;

import net.minecraft.network.play.client.*;

public interface ICPacketUseEntity
{
    void setEntityId(final int p0);
    
    void setAction(final CPacketUseEntity.Action p0);
}
