package dev.anonymousvoid.aelven_expansion.entity.mob;

import dev.anonymousvoid.aelven_expansion.block.ModBlocks;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

public class Gnome extends PathfinderMob implements RangedAttackMob {
//    private static final UUID SPEED_MODIFIER_DRINKING_UUID = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
//    private static final AttributeModifier SPEED_MODIFIER_DRINKING = new AttributeModifier(SPEED_MODIFIER_DRINKING_UUID, "Drinking speed penalty", -0.25D, AttributeModifier.Operation.ADDITION);
    private static final EntityDataAccessor<Boolean> DATA_USING_ITEM = SynchedEntityData.defineId(Gnome.class, EntityDataSerializers.BOOLEAN);
    private int usingTime;
//    private NearestAttackableWitchTargetGoal<Player> attackPlayersGoal; TODO create new goal to target and or trade with players

    public Gnome(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    protected void registerGoals() {
        super.registerGoals();
//        this.attackPlayersGoal = new NearestAttackableWitchTargetGoal<>(this, Player.class, 10, true, false, (Predicate<LivingEntity>)null);
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 60, 10.0F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class));
//        this.targetSelector.addGoal(3, this.attackPlayersGoal);
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

    // TODO this is the tick method; configure the cooldown, figure out UUIDs, create SoundEvent GNOME_ADMIRE
    public void aiStep() {
        if (!this.level.isClientSide && this.isAlive()) {
//            this.healRaidersGoal.decrementCooldown();
//            if (this.healRaidersGoal.getCooldown() <= 0) {
//                this.attackPlayersGoal.setCanAttack(true);
//            } else {
//                this.attackPlayersGoal.setCanAttack(false);
//            }

            if (this.isAdmiringFlower()) {
                if (this.usingTime-- <= 0) {
                    this.setUsingItem(false);
                    ItemStack itemstack = this.getMainHandItem();
                    this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);

//                    this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER_DRINKING);
                }
            } else {
                this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.SNOWBALL)); // TODO replace with mudball
                this.usingTime = this.getMainHandItem().getUseDuration();
                this.setUsingItem(true);

//                AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
//                attributeinstance.removeModifier(SPEED_MODIFIER_DRINKING);
//                attributeinstance.addTransientModifier(SPEED_MODIFIER_DRINKING);
            }

            if (this.random.nextFloat() < 7.5E-4F) {
                this.level.broadcastEntityEvent(this, (byte)15);
            }
        }

        super.aiStep();
    }

    // TODO maybe make it only appear if the gnome is on mud, plus different particles for different blocks?
    public void handleEntityEvent(byte pId) {
        if (pId == 15) {
            ParticleOptions particle = new BlockParticleOption(ParticleTypes.BLOCK, ModBlocks.MUDDY_MULCH.get().defaultBlockState());
            for(int i = 0; i < this.random.nextInt(35) + 10; ++i) {
                this.level.addParticle(particle,
                        this.getX() + this.random.nextGaussian() * (double)0.13F,
                        this.getY() + 0.4D,
                        this.getZ() + this.random.nextGaussian() * (double)0.13F,
                        0.0D, 0.0D, 0.0D);
            }
        } else {
            super.handleEntityEvent(pId);
        }
    }

    // TODO create SoundEvent GNOME_THROW
    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        if (!this.isAdmiringFlower()) {
            Vec3 vec3 = pTarget.getDeltaMovement();
            double d0 = pTarget.getX() + vec3.x - this.getX();
            double d1 = pTarget.getEyeY() - (double)1.1F - this.getY();
            double d2 = pTarget.getZ() + vec3.z - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);

            Snowball mudball = new Snowball(this.level, this);
            mudball.setXRot(mudball.getXRot() - -20.0F);
            mudball.shoot(d0, d1 + d3 * 0.2D, d2, 0.75F, 8.0F);
            if (!this.isSilent()) {
                this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.WITCH_THROW, this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
            }

            this.level.addFreshEntity(mudball);
        }
    }

    // TODO get eye height from model
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.6F;
    }
}
