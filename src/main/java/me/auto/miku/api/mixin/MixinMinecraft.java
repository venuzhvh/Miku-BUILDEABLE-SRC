//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.mixin;

import me.auto.miku.api.accessors.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.gen.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.auto.miku.*;
import org.spongepowered.asm.mixin.injection.*;
import org.lwjgl.input.*;
import net.minecraftforge.common.*;
import me.auto.miku.client.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;

@Mixin({ Minecraft.class })
public abstract class MixinMinecraft implements IMinecraft
{
    @Accessor
    public abstract void setRightClickDelayTimer(final int p0);
    
    @Inject(method = { "init" }, at = { @At("RETURN") })
    public void init(final CallbackInfo ci) {
        Miku.INSTANCE.initiate();
    }
    
    @Inject(method = { "runTickKeyboard" }, at = { @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 0, shift = At.Shift.BEFORE) })
    private void onKeyboard(final CallbackInfo callbackInfo) {
        final int i = (Keyboard.getEventKey() == 0) ? (Keyboard.getEventCharacter() + '\u0100') : Keyboard.getEventKey();
        if (Keyboard.getEventKeyState()) {
            MinecraftForge.EVENT_BUS.post((Event)new KeyEvent(i));
        }
    }
}
