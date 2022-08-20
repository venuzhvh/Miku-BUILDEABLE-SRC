//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.util;

import me.auto.miku.api.wrapper.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import me.auto.miku.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;

public class CombatUtil implements Minecraftable
{
    public static EntityPlayer getEnemy(final float range) {
        float minVal = Float.POSITIVE_INFINITY;
        Entity bestEntity = null;
        for (final Entity e : CombatUtil.mc.world.loadedEntityList) {
            if (!(e instanceof EntityPlayer)) {
                continue;
            }
            final float val = (float)CombatUtil.mc.player.getDistanceSq(e);
            if (!EntityUtil.isValidEntity(e, range) || Miku.INSTANCE.getFriendManager().isFriend(e.getName()) || val >= minVal) {
                continue;
            }
            minVal = val;
            bestEntity = e;
        }
        return (EntityPlayer)bestEntity;
    }
    
    public static EntityPlayer getFriend(final float range) {
        float minVal = Float.POSITIVE_INFINITY;
        Entity bestEntity = null;
        for (final Entity e : CombatUtil.mc.world.loadedEntityList) {
            if (!(e instanceof EntityPlayer)) {
                continue;
            }
            final float val = (float)CombatUtil.mc.player.getDistanceSq(e);
            if (!EntityUtil.isValidEntity(e, range) || !Miku.INSTANCE.getFriendManager().isFriend(e.getName()) || val >= minVal) {
                continue;
            }
            minVal = val;
            bestEntity = e;
        }
        return (EntityPlayer)bestEntity;
    }
    
    public static boolean isInHole(final EntityPlayer entity) {
        return isBlockValid(new BlockPos(entity.posX, entity.posY, entity.posZ));
    }
    
    public static boolean isBlockValid(final BlockPos blockPos) {
        return isBedrockHole(blockPos) || isObbyHole(blockPos) || isBothHole(blockPos);
    }
    
    public static boolean isObbyHole(final BlockPos blockPos) {
        final BlockPos[] array;
        final BlockPos[] touchingBlocks = array = new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (final BlockPos pos : array) {
            final IBlockState touchingState = CombatUtil.mc.world.getBlockState(pos);
            if (touchingState.getBlock() == Blocks.AIR || touchingState.getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isBedrockHole(final BlockPos blockPos) {
        final BlockPos[] array;
        final BlockPos[] touchingBlocks = array = new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (final BlockPos pos : array) {
            final IBlockState touchingState = CombatUtil.mc.world.getBlockState(pos);
            if (touchingState.getBlock() == Blocks.AIR || touchingState.getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isBothHole(final BlockPos blockPos) {
        final BlockPos[] array;
        final BlockPos[] touchingBlocks = array = new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (final BlockPos pos : array) {
            final IBlockState touchingState = CombatUtil.mc.world.getBlockState(pos);
            if (touchingState.getBlock() == Blocks.AIR || (touchingState.getBlock() != Blocks.BEDROCK && touchingState.getBlock() != Blocks.OBSIDIAN)) {
                return false;
            }
        }
        return true;
    }
}
