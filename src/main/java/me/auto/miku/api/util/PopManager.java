//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.util;

import me.auto.miku.api.manage.type.*;
import net.minecraft.entity.*;
import me.auto.miku.api.wrapper.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.auto.miku.client.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.common.*;

public class PopManager extends HashMapManager<Entity, Integer> implements Minecraftable
{
    @SubscribeEvent
    public void onUpdate(final UpdateEvent event) {
        for (final EntityPlayer player : PopManager.mc.world.playerEntities) {
            if (player.getHealth() <= 0.0f && this.registry.containsKey(player)) {
                ChatUtil.sendDeletableMessageWithCustomId((player.getName().equals(PopManager.mc.player.getName()) ? "You" : player.getName()) + " died after popping " + this.registry.get(player.getName()) + " totems!", player.getEntityId());
                this.registry.remove(player, this.registry.get(player));
            }
        }
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent.Receive event) {
        if (PopManager.mc.player == null || PopManager.mc.world == null) {
            return;
        }
        if (event.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus packet = (SPacketEntityStatus)event.getPacket();
            if (packet.getOpCode() == 35) {
                final Entity entity = packet.getEntity((World)PopManager.mc.world);
                if (this.registry.get(entity) == null) {
                    this.registry.put(entity, 1);
                    ChatUtil.sendDeletableMessageWithCustomId((entity.getName().equals(PopManager.mc.player.getName()) ? "You" : entity.getName()) + " popped a totem!", entity.getEntityId());
                }
                else if (this.registry.get(entity) != null) {
                    final int popCounter = this.registry.get(entity);
                    final int newPopCounter = popCounter + 1;
                    this.registry.put(entity, newPopCounter);
                    ChatUtil.sendDeletableMessageWithCustomId((entity.getName().equals(PopManager.mc.player.getName()) ? "You" : entity.getName()) + " popped " + newPopCounter + " totems!", entity.getEntityId());
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onLogout(final PlayerEvent.PlayerLoggedOutEvent event) {
        this.registry.clear();
    }
    
    public void load() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void unload() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
}
