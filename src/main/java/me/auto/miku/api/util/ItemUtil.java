//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.api.util;

import me.auto.miku.api.wrapper.*;
import net.minecraft.item.*;
import java.util.function.*;
import net.minecraft.init.*;

public class ItemUtil implements Minecraftable
{
    public static int getItemDamage(final ItemStack stack) {
        return stack.getMaxDamage() - stack.getItemDamage();
    }
    
    public static float getDamageInPercent(final ItemStack stack) {
        return getItemDamage(stack) / (float)stack.getMaxDamage() * 100.0f;
    }
    
    public static int getRoundedDamage(final ItemStack stack) {
        return (int)getDamageInPercent(stack);
    }
    
    public static boolean hasDurability(final ItemStack stack) {
        final Item item = stack.getItem();
        return item instanceof ItemArmor || item instanceof ItemSword || item instanceof ItemTool || item instanceof ItemShield;
    }
    
    public static boolean isHoldingItem(final Item item) {
        return ItemUtil.mc.player.getHeldItemOffhand().getItem() == item || ItemUtil.mc.player.getHeldItemMainhand().getItem() == item;
    }
    
    public static int getItems(final Item i) {
        return ItemUtil.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == i).mapToInt(ItemStack::getCount).sum() + ItemUtil.mc.player.inventory.offHandInventory.stream().filter(itemStack -> itemStack.getItem() == i).mapToInt(ItemStack::getCount).sum();
    }
    
    public static int getItemSlot(final Item item) {
        int itemSlot = -1;
        for (int i = 45; i > 0; --i) {
            if (ItemUtil.mc.player.inventory.getStackInSlot(i).getItem().equals(item)) {
                itemSlot = i;
                break;
            }
        }
        return itemSlot;
    }
    
    public static int getItemSlotInHotbar(final Item item) {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack is = ItemUtil.mc.player.inventory.getStackInSlot(i);
            if (is.getItem() == item) {
                slot = i;
                break;
            }
        }
        return slot;
    }
    
    public static boolean hasAtleastOneTotem() {
        return getItems(Items.TOTEM_OF_UNDYING) > 0;
    }
}
