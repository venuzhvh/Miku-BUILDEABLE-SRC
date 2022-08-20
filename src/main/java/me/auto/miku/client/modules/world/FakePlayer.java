//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.world;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.client.entity.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;

@ModuleManifest(label = "FakePlayer", category = Category.WORLD)
public class FakePlayer extends Module
{
    public void onEnable() {
        if (this.isNull()) {
            this.setEnabled(false);
            return;
        }
        final EntityOtherPlayerMP entity = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("66666666-6666-6666-6666-666666666600"), "realPlayer"));
        entity.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        entity.inventory.copyInventory(FakePlayer.mc.player.inventory);
        FakePlayer.mc.world.addEntityToWorld(3000, (Entity)entity);
    }
    
    public void onDisable() {
        if (this.isNull()) {
            return;
        }
        FakePlayer.mc.world.removeEntityFromWorld(3000);
    }
}
