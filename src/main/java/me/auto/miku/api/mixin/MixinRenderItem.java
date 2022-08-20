//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.mixin;

import me.auto.miku.api.wrapper.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import me.auto.miku.client.modules.visual.*;
import me.auto.miku.*;
import me.auto.miku.api.module.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderItem.class })
public class MixinRenderItem implements Minecraftable
{
    @ModifyArg(method = { "renderEffect" }, at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/RenderItem.renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;I)V"))
    private int renderEffect(final int glintVal) {
        final EnchantColor enchantColor = (EnchantColor)Miku.INSTANCE.getModuleManager().findByClass(EnchantColor.class);
        return enchantColor.isEnabled() ? enchantColor.getColor() : glintVal;
    }
}
