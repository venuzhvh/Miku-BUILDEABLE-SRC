//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.combat;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.client.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;

@ModuleManifest(label = "Velocity", category = Category.COMBAT)
public class Velocity extends Module
{
    @SubscribeEvent
    public void onPacketRec(final PacketEvent.Receive event) {
        if ((event.getPacket() instanceof SPacketEntityVelocity && ((SPacketEntityVelocity)event.getPacket()).getEntityID() == Velocity.mc.player.getEntityId()) || event.getPacket() instanceof SPacketExplosion) {
            event.setCanceled(true);
        }
    }
}
