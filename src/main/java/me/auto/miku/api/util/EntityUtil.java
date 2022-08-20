//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.util;

import me.auto.miku.api.wrapper.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class EntityUtil implements Minecraftable
{
    public static int totalHealth(final EntityPlayer player) {
        return (int)(player.getHealth() + player.getAbsorptionAmount());
    }
    
    public static boolean isValidEntity(final Entity player, final float range) {
        return !isntValidEntity(player, range);
    }
    
    public static boolean isntValidEntity(final Entity player, final float range) {
        return player == EntityUtil.mc.player || player.isDead || ((EntityLivingBase)player).getHealth() <= 0.0f || EntityUtil.mc.player.getDistance(player) > range;
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float time) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * time, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * time, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * time);
    }
}
