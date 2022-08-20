//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.visual;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.api.property.annotations.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import me.auto.miku.client.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.auto.miku.client.event.events.render.*;
import java.awt.*;
import me.auto.miku.api.util.*;
import java.util.*;
import java.util.List;

import net.minecraft.util.math.*;

@ModuleManifest(label = "HoleESP", category = Category.VISUAL)
public class HoleESP extends Module
{
    @Clamp(maximum = "30")
    @Property("Range")
    public float range;
    private BlockPos[] surroundOffset;
    private List<BlockPos> holes;
    private final List<Block> unSolidBlocks;
    private final List<BlockPos> midSafety;
    
    public HoleESP() {
        this.range = 6.0f;
        this.surroundOffset = toBlockPos(BlockUtil.getOffsets(0, true));
        this.holes = new ArrayList<BlockPos>();
        this.unSolidBlocks = Arrays.asList((Block)Blocks.FLOWING_LAVA, Blocks.FLOWER_POT, Blocks.SNOW, Blocks.CARPET, Blocks.END_ROD, (Block)Blocks.SKULL, Blocks.FLOWER_POT, Blocks.TRIPWIRE, (Block)Blocks.TRIPWIRE_HOOK, Blocks.WOODEN_BUTTON, Blocks.LEVER, Blocks.STONE_BUTTON, Blocks.LADDER, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.POWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, Blocks.UNLIT_REDSTONE_TORCH, Blocks.REDSTONE_TORCH, (Block)Blocks.REDSTONE_WIRE, Blocks.AIR, (Block)Blocks.PORTAL, Blocks.END_PORTAL, (Block)Blocks.WATER, (Block)Blocks.FLOWING_WATER, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_LAVA, Blocks.SAPLING, (Block)Blocks.RED_FLOWER, (Block)Blocks.YELLOW_FLOWER, (Block)Blocks.BROWN_MUSHROOM, (Block)Blocks.RED_MUSHROOM, Blocks.WHEAT, Blocks.CARROTS, Blocks.POTATOES, Blocks.BEETROOTS, (Block)Blocks.REEDS, Blocks.PUMPKIN_STEM, Blocks.MELON_STEM, Blocks.WATERLILY, Blocks.NETHER_WART, Blocks.COCOA, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, (Block)Blocks.TALLGRASS, (Block)Blocks.DEADBUSH, Blocks.VINE, (Block)Blocks.FIRE, Blocks.RAIL, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.TORCH);
        this.midSafety = new ArrayList<BlockPos>();
    }
    
    public List<BlockPos> getHoles() {
        return this.holes;
    }
    
    public List<BlockPos> getSortedHoles() {
        this.holes.sort(Comparator.comparingDouble(hole -> HoleESP.mc.player.getDistanceSq(hole)));
        return this.getHoles();
    }
    
    @SubscribeEvent
    public void onUpdate(final UpdateEvent event) {
        this.holes = this.calcHoles();
    }
    
    @SubscribeEvent
    public void onRender3D(final Render3DEvent event) {
        for (final BlockPos pos : this.getSortedHoles()) {
            if (pos.equals((Object)new BlockPos(HoleESP.mc.player.posX, HoleESP.mc.player.posY, HoleESP.mc.player.posZ))) {
                continue;
            }
            RenderUtil.drawWireframeOutlinedBox(pos, this.isSafe(pos) ? new Color(0, 255, 0) : new Color(255, 0, 0), 2.0f);
        }
    }
    
    public List<BlockPos> calcHoles() {
        final List<BlockPos> safeSpots = new ArrayList<BlockPos>();
        this.midSafety.clear();
        final List<BlockPos> positions = (List<BlockPos>)BlockUtil.getSphere(this.range);
        for (final BlockPos pos : positions) {
            if (!HoleESP.mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleESP.mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleESP.mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            boolean isSafe = true;
            boolean midSafe = true;
            for (final BlockPos offset : this.surroundOffset) {
                final Block block = HoleESP.mc.world.getBlockState(pos.add((Vec3i)offset)).getBlock();
                if (this.isBlockUnSolid(block)) {
                    midSafe = false;
                }
                if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
                    isSafe = false;
                }
            }
            if (isSafe) {
                safeSpots.add(pos);
            }
            if (!midSafe) {
                continue;
            }
            this.midSafety.add(pos);
        }
        return safeSpots;
    }
    
    public boolean isSafe(final BlockPos pos) {
        boolean isSafe = true;
        for (final BlockPos offset : this.surroundOffset) {
            final Block block = HoleESP.mc.world.getBlockState(pos.add((Vec3i)offset)).getBlock();
            if (block != Blocks.BEDROCK) {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }
    
    public boolean isBlockSolid(final BlockPos pos) {
        return !this.isBlockUnSolid(pos);
    }
    
    public boolean isBlockUnSolid(final BlockPos pos) {
        return this.isBlockUnSolid(BlockUtil.mc.world.getBlockState(pos).getBlock());
    }
    
    public boolean isBlockUnSolid(final Block block) {
        return this.unSolidBlocks.contains(block);
    }
    
    public static BlockPos[] toBlockPos(final Vec3d[] vec3ds) {
        final BlockPos[] list = new BlockPos[vec3ds.length];
        for (int i = 0; i < vec3ds.length; ++i) {
            list[i] = new BlockPos(vec3ds[i]);
        }
        return list;
    }
}
