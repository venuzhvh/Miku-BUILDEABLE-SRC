//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.player;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import org.lwjgl.input.*;
import me.auto.miku.*;
import com.mojang.realmsclient.gui.*;
import me.auto.miku.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

@ModuleManifest(label = "MCF", category = Category.PLAYER)
public class MiddleClickFriends extends Module
{
    @SubscribeEvent
    public void MouseEvent(final InputEvent.MouseInputEvent event) {
        if (MiddleClickFriends.mc.objectMouseOver.typeOfHit.equals((Object)RayTraceResult.Type.ENTITY) && MiddleClickFriends.mc.objectMouseOver.entityHit instanceof EntityPlayer && Mouse.getEventButton() == 2 && Mouse.getEventButtonState()) {
            if (Miku.INSTANCE.getFriendManager().isFriend(MiddleClickFriends.mc.objectMouseOver.entityHit.getName())) {
                Miku.INSTANCE.getFriendManager().removeFriend(MiddleClickFriends.mc.objectMouseOver.entityHit.getName());
                ChatUtil.sendClientMessage(ChatFormatting.RED + "Removed " + MiddleClickFriends.mc.objectMouseOver.entityHit.getName() + " from friends list");
            }
            else {
                Miku.INSTANCE.getFriendManager().addFriend(MiddleClickFriends.mc.objectMouseOver.entityHit.getName());
                ChatUtil.sendClientMessage(ChatFormatting.AQUA + "Added " + MiddleClickFriends.mc.objectMouseOver.entityHit.getName() + " to friends list");
            }
        }
    }
}
