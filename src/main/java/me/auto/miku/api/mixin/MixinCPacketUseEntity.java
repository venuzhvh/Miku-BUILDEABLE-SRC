//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.mixin;

import me.auto.miku.api.accessors.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.network.play.client.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ CPacketUseEntity.class })
public abstract class MixinCPacketUseEntity implements ICPacketUseEntity
{
    @Accessor
    public abstract void setEntityId(final int p0);
    
    @Accessor
    public abstract void setAction(final CPacketUseEntity.Action p0);
}
