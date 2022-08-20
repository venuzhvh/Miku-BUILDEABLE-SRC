//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.event.events.render;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.block.model.*;

public class RenderItemEvent extends Event
{
    final ItemStack stack;
    final Entity entity;
    final ItemCameraTransforms.TransformType brub;
    
    public RenderItemEvent(final ItemStack stack, final Entity entity, final ItemCameraTransforms.TransformType brub) {
        this.stack = stack;
        this.entity = entity;
        this.brub = brub;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public ItemStack getStack() {
        return this.stack;
    }
    
    public ItemCameraTransforms.TransformType getBrub() {
        return this.brub;
    }
}
