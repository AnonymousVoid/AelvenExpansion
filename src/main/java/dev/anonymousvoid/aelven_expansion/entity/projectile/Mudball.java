package dev.anonymousvoid.aelven_expansion.entity.projectile;

import dev.anonymousvoid.aelven_expansion.entity.ModEntityTypes;
import dev.anonymousvoid.aelven_expansion.entity.mob.Gnome;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Mudball extends ThrowableItemProjectile {
    public Mudball(EntityType<? extends Mudball> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public Mudball(Level pLevel, LivingEntity pShooter) {
        super(ModEntityTypes.MUDBALL.get(), pShooter, pLevel);
    }

    public Mudball(Level pLevel, double pX, double pY, double pZ) {
        super(ModEntityTypes.MUDBALL.get(), pX, pY, pZ, pLevel);
    }

    protected Item getDefaultItem() {
        return Items.SNOWBALL; // ModItems.MUDBALL.get();
    }

    private ParticleOptions getParticle() {
        // TODO replace with mud particle?
        ItemStack itemstack = this.getItemRaw();
        return (ParticleOptions)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }

    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level.addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    protected void onHitEntity(EntityHitResult pResult) {
        // TODO give the entity blindness?
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int i = entity instanceof Gnome ? 0 : 1;
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)i);
    }

    protected void onHit(HitResult pResult) {
        // TODO make a mud smear block that can accumulate and that will place when the mudball hits a solid surface
        super.onHit(pResult);
        if (!this.level.isClientSide) {
            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }
}
