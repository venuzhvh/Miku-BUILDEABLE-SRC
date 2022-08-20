//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.auto.miku.*;
import net.minecraftforge.common.*;
import me.auto.miku.client.event.*;
import me.auto.miku.client.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityPlayerSP.class })
public class MixinEntityPlayerSP
{
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") })
    public void onUpdatePre(final CallbackInfo c) {
        Miku.INSTANCE.getRotationManager().updateRotations();
        MinecraftForge.EVENT_BUS.post((Event)new UpdateEvent(EventStage.PRE));
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") })
    public void onUpdatePost(final CallbackInfo c) {
        Miku.INSTANCE.getRotationManager().restoreRotations();
        MinecraftForge.EVENT_BUS.post((Event)new UpdateEvent(EventStage.POST));
    }
}
