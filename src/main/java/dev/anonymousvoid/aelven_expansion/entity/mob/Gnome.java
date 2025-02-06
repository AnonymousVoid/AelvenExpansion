package dev.anonymousvoid.aelven_expansion.entity.mob;

import dev.anonymousvoid.aelven_expansion.entity.mob.goals.GnomeMischiefGoal;
import dev.anonymousvoid.aelven_expansion.entity.projectile.Mudball;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;

public class Gnome extends PathfinderMob implements RangedAttackMob {

    public Gnome(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.5D));
        this.goalSelector.addGoal(3, new GnomeMischiefGoal(this, Player.class, 1.25F, 8.0F));

    }

    // TODO create SoundEvents GNOME_AMBIENT, GNOME_HURT, and GNOME_DEATH
    protected SoundEvent getAmbientSound() { return SoundEvents.ALLAY_AMBIENT_WITH_ITEM; }
    protected SoundEvent getHurtSound(DamageSource pDamageSource) { return SoundEvents.ALLAY_HURT; }
    protected SoundEvent getDeathSound() { return SoundEvents.ALLAY_DEATH; }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    public void aiStep() {
        super.aiStep();
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.8F;
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        Mudball mudball = new Mudball(this.level, this);
        double d0 = pTarget.getEyeY() - (double)1.1F;
        double d1 = pTarget.getX() - this.getX();
        double d2 = d0 - mudball.getY();
        double d3 = pTarget.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
        mudball.shoot(d1, d2 + d4, d3, 1.6F, 6.0F);
        this.playSound(SoundEvents.SLIME_JUMP, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(mudball);
    }
}
