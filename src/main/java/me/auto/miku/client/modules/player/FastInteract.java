//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.player;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.api.property.annotations.*;
import me.auto.miku.client.event.events.*;
import net.minecraft.init.*;
import me.auto.miku.api.util.*;
import net.minecraft.item.*;
import me.auto.miku.api.accessors.*;
import net.minecraftforge.fml.common.eventhandler.*;

@ModuleManifest(label = "FastInteract", category = Category.PLAYER)
public class FastInteract extends Module
{
    @Clamp(maximum = "2")
    @Property("Delay")
    public int delay;
    
    public FastInteract() {
        this.delay = 0;
    }
    
    @SubscribeEvent
    public void onUpdate(final UpdateEvent event) {
        if (this.isNull()) {
            return;
        }
        if ((ItemUtil.isHoldingItem(Items.END_CRYSTAL) || ItemUtil.isHoldingItem(Items.EXPERIENCE_BOTTLE)) && !(FastInteract.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)) {
            ((IMinecraft)FastInteract.mc).setRightClickDelayTimer(this.delay);
        }
    }
}
