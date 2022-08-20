//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Lyo\Desktop\Minecraft-Deobfuscator3000-master\1.12 stable mappings"!

//Decompiled by Procyon!

package me.auto.miku.client.modules.combat;

import me.auto.miku.api.module.annotations.*;
import me.auto.miku.api.module.*;
import me.auto.miku.api.property.annotations.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.item.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.auto.miku.client.event.events.render.*;
import java.awt.*;
import me.auto.miku.api.util.*;
import me.auto.miku.client.event.events.*;
import net.minecraft.network.play.client.*;
import java.util.*;
import net.minecraft.network.play.server.*;
import me.auto.miku.api.accessors.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.potion.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;

@ModuleManifest(label = "AutoCrystal", category = Category.COMBAT)
public class AutoCrystal extends Module
{
    @Clamp(maximum = "15")
    @Property("EnemyRange")
    public int enemyRange;
    @Clamp(maximum = "100")
    @Property("Delay")
    public int delay;
    @Clamp(maximum = "6")
    @Property("PlaceRange")
    public float placeRange;
    @Clamp(maximum = "6")
    @Property("HitRange")
    public float hitRange;
    @Clamp(maximum = "36")
    @Property("FaceplaceHP")
    public float faceplaceHP;
    @Clamp(maximum = "20")
    @Property("MinDamage")
    public float minDamage;
    @Clamp(maximum = "36")
    @Property("MaxSelfDamage")
    public float maxSelf;
    private final ArrayList<BlockPos> placePositions;
    private final ArrayList<Entity> predicted;
    private final ArrayList<Entity> crystals;
    private final TimerUtil breakTimer;
    private final TimerUtil timerUtil;
    private BlockPos renderPos;
    
    public AutoCrystal() {
        this.enemyRange = 9;
        this.delay = 0;
        this.placeRange = 5.0f;
        this.hitRange = 5.0f;
        this.faceplaceHP = 8.0f;
        this.minDamage = 4.0f;
        this.maxSelf = 7.0f;
        this.placePositions = new ArrayList<BlockPos>();
        this.predicted = new ArrayList<Entity>();
        this.crystals = new ArrayList<Entity>();
        this.breakTimer = new TimerUtil();
        this.timerUtil = new TimerUtil();
        this.renderPos = null;
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (this.isNull()) {
            return;
        }
        if (this.timerUtil.hasReached(1000L)) {
            this.placePositions.clear();
            this.crystals.clear();
            this.renderPos = null;
            this.timerUtil.reset();
        }
        if (!ItemUtil.isHoldingItem(Items.END_CRYSTAL)) {
            return;
        }
        final EntityPlayer closestEnemy = CombatUtil.getEnemy((float)this.enemyRange);
        if (closestEnemy == null) {
            this.renderPos = null;
            return;
        }
        float maxDamage = (EntityUtil.totalHealth(closestEnemy) > this.faceplaceHP) ? this.minDamage : 2.0f;
        BlockPos placePos = null;
        for (final BlockPos pos : BlockUtil.getSphere(this.placeRange)) {
            if (!this.canPlaceCrystal(pos)) {
                continue;
            }
            final float targetDamage = this.calculate(pos, (EntityLivingBase)closestEnemy);
            if (targetDamage <= maxDamage) {
                continue;
            }
            final float selfDamage = this.calculate(pos, (EntityLivingBase)AutoCrystal.mc.player);
            if (EntityUtil.totalHealth((EntityPlayer)AutoCrystal.mc.player) - 0.5 <= selfDamage || this.maxSelf <= selfDamage || targetDamage <= selfDamage) {
                continue;
            }
            maxDamage = targetDamage;
            placePos = pos;
        }
        for (final Entity crystal : AutoCrystal.mc.world.loadedEntityList) {
            if (crystal instanceof EntityEnderCrystal) {
                if (this.predicted.contains(crystal)) {
                    return;
                }
                if (AutoCrystal.mc.player.getDistance(crystal) > (AutoCrystal.mc.player.canEntityBeSeen(crystal) ? this.hitRange : 3.0f)) {
                    continue;
                }
                if (this.crystals.contains(crystal) && !this.breakTimer.hasReached((long)this.delay)) {
                    return;
                }
                AutoCrystal.mc.getConnection().sendPacket((Packet)new CPacketUseEntity(crystal));
                this.crystals.add(crystal);
                this.breakTimer.reset();
            }
        }
        if (placePos != null) {
            AutoCrystal.mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(placePos, EnumFacing.UP, (AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.5f, 1.0f, 0.5f));
            this.placePositions.add(placePos);
            this.renderPos = placePos;
        }
        else {
            this.renderPos = null;
        }
    }
    
    @SubscribeEvent
    public void onRender3D(final Render3DEvent event) {
        if (this.renderPos != null) {
            RenderUtil.drawWireframeOutlinedBox(this.renderPos, new Color(ColorUtil.getPurpleWave(0)), 1.0f);
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketUseEntity) {
            final CPacketUseEntity packet = (CPacketUseEntity)event.getPacket();
            if (packet.getAction() == CPacketUseEntity.Action.ATTACK && packet.getEntityFromWorld((World)AutoCrystal.mc.world) instanceof EntityEnderCrystal) {
                AutoCrystal.mc.getConnection().sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect packet = (SPacketSoundEffect)event.getPacket();
            if (packet.getCategory() == SoundCategory.BLOCKS && packet.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                for (final Entity entity : new ArrayList<Entity>(AutoCrystal.mc.world.loadedEntityList)) {
                    if (entity instanceof EntityEnderCrystal && entity.getDistance(packet.getX(), packet.getY(), packet.getZ()) < 4.0) {
                        entity.setDead();
                    }
                }
            }
        }
        if (event.getPacket() instanceof SPacketSpawnObject) {
            final SPacketSpawnObject packet2 = (SPacketSpawnObject)event.getPacket();
            if (packet2.getType() == 51 && this.placePositions.contains(new BlockPos(packet2.getX(), packet2.getY(), packet2.getZ()).down())) {
                final int id = packet2.getEntityID();
                final ICPacketUseEntity packetUseEntity = (ICPacketUseEntity)new CPacketUseEntity();
                packetUseEntity.setEntityId(id);
                packetUseEntity.setAction(CPacketUseEntity.Action.ATTACK);
                AutoCrystal.mc.getConnection().sendPacket((Packet)packetUseEntity);
                this.predicted.add(AutoCrystal.mc.world.getEntityByID(id));
            }
        }
    }
    
    private float calculate(final BlockPos pos, final EntityLivingBase base) {
        return this.calculate(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, base);
    }
    
    private float calculate(final double x, final double y, final double z, final EntityLivingBase base) {
        double distance = base.getDistance(x, y, z) / 12.0;
        if (distance > 1.0) {
            return 0.0f;
        }
        final double density = AutoCrystal.mc.world.getBlockDensity(new Vec3d(x, y, z), base.getEntityBoundingBox());
        final double densityDistance;
        distance = (densityDistance = (1.0 - distance) * density);
        float damage = this.getDifficultyMultiplier((float)((densityDistance * densityDistance + distance) / 2.0 * 85.0));
        final DamageSource damageSource = DamageSource.causeExplosionDamage(new Explosion((World)AutoCrystal.mc.world, (Entity)AutoCrystal.mc.player, x, y, z, 6.0f, false, true));
        damage = CombatRules.getDamageAfterAbsorb(damage, (float)base.getTotalArmorValue(), (float)base.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        final int modifierDamage = EnchantmentHelper.getEnchantmentModifierDamage(base.getArmorInventoryList(), damageSource);
        if (modifierDamage > 0) {
            damage = CombatRules.getDamageAfterMagicAbsorb(damage, (float)modifierDamage);
        }
        final PotionEffect resistance = base.getActivePotionEffect(MobEffects.RESISTANCE);
        if (resistance != null) {
            damage = damage * (25 - (resistance.getAmplifier() + 1) * 5) / 25.0f;
        }
        return Math.max(damage, 0.0f);
    }

    private boolean canPlaceCrystal(final BlockPos pos) {
        final BlockPos boost = pos.up();
        if (AutoCrystal.mc.world.getBlockState(boost).getBlock() != Blocks.AIR || !this.checkEntities(boost)) {
            return false;
        }
        final BlockPos boost2 = boost.up();
        return AutoCrystal.mc.world.getBlockState(boost2).getBlock() == Blocks.AIR && this.checkEntities(boost2);
    }

    private boolean checkEntities(final BlockPos pos) {
        for (final Object entity : AutoCrystal.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(pos))) {
            if (entity instanceof EntityEnderCrystal) {
                continue;
            }
            return false;
        }
        return true;
    }
    
    private float getDifficultyMultiplier(final float distance) {
        switch (AutoCrystal.mc.world.getDifficulty()) {
            case PEACEFUL: {
                return 0.0f;
            }
            case EASY: {
                return Math.min(distance / 2.0f + 1.0f, distance);
            }
            case HARD: {
                return distance * 3.0f / 2.0f;
            }
            default: {
                return distance;
            }
        }
    }
}
