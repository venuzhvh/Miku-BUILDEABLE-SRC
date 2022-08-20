//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.block.model.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraftforge.common.*;
import me.auto.miku.client.event.events.render.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ItemRenderer.class })
public class MixinItemRenderer
{
    @Inject(method = { "renderItemSide" }, at = { @At("INVOKE") })
    public void onRenderItem(final EntityLivingBase entitylivingbaseIn, final ItemStack heldStack, final ItemCameraTransforms.TransformType transform, final boolean leftHanded, final CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post((Event)new RenderItemEvent(heldStack, (Entity)entitylivingbaseIn, transform));
    }
}
