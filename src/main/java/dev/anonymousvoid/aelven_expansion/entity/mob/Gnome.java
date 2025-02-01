package dev.anonymousvoid.aelven_expansion.entity.mob;

import dev.anonymousvoid.aelven_expansion.entity.mob.goals.GnomeMischiefGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Gnome extends PathfinderMob {
    private static final EntityDataAccessor<Boolean> DATA_USING_ITEM = SynchedEntityData.defineId(Gnome.class, EntityDataSerializers.BOOLEAN);
//    private NearestAttackableWitchTargetGoal<Player> attackPlayersGoal; TODO create new goal to target and or trade with players

    public Gnome(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    protected void registerGoals() {
        super.registerGoals();
//        this.attackPlayersGoal = new NearestAttackableWitchTargetGoal<>(this, Player.class, 10, true, false, (Predicate<LivingEntity>)null);
//        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 60, 10.0F));
//        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
//        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.5D));
        this.targetSelector.addGoal(3, new GnomeMischiefGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(4, new GnomeMischiefGoal(this, LivingEntity.class, 8.0F));
//        this.targetSelector.addGoal(5, new GnomeMischiefGoal(this, Player.class, 8.0F)); somehow make it look for structures?

//        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class));

    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_USING_ITEM, false);
    }

    public void setUsingItem(boolean pUsingItem) {
        this.getEntityData().set(DATA_USING_ITEM, pUsingItem);
    }

    public boolean isAdmiringFlower() {
        return this.getEntityData().get(DATA_USING_ITEM);
    }

    // TODO create SoundEvents GNOME_AMBIENT, GNOME_HURT, and GNOME_DEATH
    protected SoundEvent getAmbientSound() { return SoundEvents.WITCH_AMBIENT; }
    protected SoundEvent getHurtSound(DamageSource pDamageSource) { return SoundEvents.WITCH_HURT; }
    protected SoundEvent getDeathSound() { return SoundEvents.WITCH_DEATH; }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D);
    }

    // TODO this is the tick method;
    public void aiStep() {
        super.aiStep();
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.8F;
    }
}
