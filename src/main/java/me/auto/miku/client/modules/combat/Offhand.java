//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.combat;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.api.property.annotations.*;
import me.auto.miku.client.event.events.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.*;
import me.auto.miku.api.util.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.auto.miku.*;
import net.minecraft.entity.*;
import net.minecraft.inventory.*;

@ModuleManifest(label = "Offhand", category = Category.COMBAT)
public class Offhand extends Module
{
    @Clamp(maximum = "36")
    @Property("TotemHealth")
    public float totemHealth;
    
    public Offhand() {
        this.totemHealth = 10.0f;
    }
    
    @SubscribeEvent
    public void onUpdate(final UpdateEvent event) {
        if (Offhand.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        final int getSlot = this.shouldTotem() ? ItemUtil.getItemSlot(Items.TOTEM_OF_UNDYING) : ItemUtil.getItemSlot(Items.END_CRYSTAL);
        if (Offhand.mc.player.getHeldItemOffhand() != ItemStack.EMPTY && this.shouldTotem()) {
            if (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
                return;
            }
        }
        else if (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            return;
        }
        final int slot = (getSlot < 9) ? (getSlot + 36) : getSlot;
        if (getSlot != -1) {
            Offhand.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            Offhand.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            Offhand.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
        }
    }
    
    private boolean nearPlayers() {
        return Offhand.mc.world.playerEntities.stream().anyMatch(e -> e != Offhand.mc.player && !Miku.INSTANCE.getFriendManager().isFriend(((EntityPlayer)e).getName()) && Offhand.mc.player.getDistance((Entity)e) <= ((AutoCrystal)Miku.INSTANCE.getModuleManager().findByClass((Class)AutoCrystal.class)).enemyRange);
    }
    
    private boolean shouldTotem() {
        return Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount() <= this.totemHealth || Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA || !this.nearPlayers();
    }
}
