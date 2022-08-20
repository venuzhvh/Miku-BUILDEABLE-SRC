//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.mixin;

import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.*;
import me.auto.miku.*;
import me.auto.miku.client.modules.visual.*;
import me.auto.miku.api.module.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.projectile.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin({ World.class })
public abstract class MixinWorld
{
    @Shadow
    public abstract World init();
    
    @Inject(method = { "spawnEntity" }, at = { @At("HEAD") }, cancellable = true)
    public void spawnEntity(final Entity entityIn, final CallbackInfoReturnable<Boolean> cir) {
        if (Miku.INSTANCE.getModuleManager().findByClass(AntiFireWork.class).isEnabled() && (entityIn instanceof EntityFireworkRocket || entityIn instanceof EntityWitherSkull)) {
            cir.cancel();
        }
    }
    
    @Inject(method = { "updateEntity" }, at = { @At("HEAD") }, cancellable = true)
    public void bruh(final Entity ent, final CallbackInfo ci) {
        if (Miku.INSTANCE.getModuleManager().findByClass(AntiFireWork.class).isEnabled() && (ent instanceof EntityFireworkRocket || ent instanceof EntityWitherSkull)) {
            ci.cancel();
        }
    }
}
