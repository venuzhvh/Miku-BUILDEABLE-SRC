//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.mixin;

import me.auto.miku.api.accessors.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.common.*;
import me.auto.miku.client.event.events.render.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin({ RenderManager.class })
public abstract class MixinRenderManager implements IRenderManager
{
    @Accessor
    public abstract double getRenderPosX();
    
    @Accessor
    public abstract double getRenderPosY();
    
    @Accessor
    public abstract double getRenderPosZ();
    
    @Inject(method = { "renderEntity" }, at = { @At("RETURN") }, locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    public void onRenderEntity(final Entity entityIn, final double x, final double y, final double z, final float yaw, final float partialTicks, final boolean p_188391_10_, final CallbackInfo ci, final Render<Entity> render) {
        MinecraftForge.EVENT_BUS.post((Event)new RenderEntityEvent(render, entityIn, x, y, z, yaw, partialTicks));
    }
}
