package dev.anonymousvoid.aelven_expansion.entity.mob.goals;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class GnomeMischiefGoal extends Goal {
    private final PathfinderMob mob;
    private final RangedAttackMob rangedAttackMob;
    @Nullable
    private LivingEntity target;
    private int attackTime = -1;
    private final double speedModifier;
    private int seeTime;
    private final float attackRadius;
    private final float attackRadiusSqr;
    protected final Class<? extends LivingEntity> targetType;
    protected final TargetingConditions targetContext;

    public GnomeMischiefGoal(RangedAttackMob pRangedAttackMob, Class<? extends LivingEntity> pTargetType, double pSpeedModifier, float pAttackRadius) {
        if (!(pRangedAttackMob instanceof LivingEntity)) {
            throw new IllegalArgumentException("GnomeMischiefGoal requires Mob implements RangedAttackMob");
        } else {
            this.rangedAttackMob = pRangedAttackMob;
            this.mob = (PathfinderMob)pRangedAttackMob;
            this.targetType = pTargetType;
            this.speedModifier = pSpeedModifier;
            this.attackRadius = pAttackRadius;
            this.attackRadiusSqr = pAttackRadius * pAttackRadius;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
            if (pTargetType == Player.class) {
                this.targetContext = TargetingConditions.forNonCombat().range(pAttackRadius).selector((entity) -> {
                    return EntitySelector.notRiding((Mob)pRangedAttackMob).test(entity);
                });
            } else {
                this.targetContext = TargetingConditions.forNonCombat().range(pAttackRadius);
            }
        }
    }

    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            this.target = livingentity;
            return true;
        }

        if (this.targetType == Player.class) {
            this.target = this.mob.level.getNearestPlayer(this.targetContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
//            System.out.println("do player mischief?");
        } else {
            this.target = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.targetType, this.mob.getBoundingBox().inflate((double)this.attackRadius, 3.0D, (double)this.attackRadius), (entity) -> {
                return true;
            }), this.targetContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
//            System.out.println("do entity mischief?");
        }

        return this.target != null;
    }

    public boolean canContinueToUse() {
        return this.canUse() || this.checkTarget() && !this.mob.getNavigation().isDone();
    }

    public void stop() {
        this.target = null;
        this.seeTime = 0;
        this.attackTime = -1;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public boolean checkTarget() {
        if (this.target != null) {
            return this.target.isAlive();
        }
        return false;
    }

    public void tick() {
        double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
        boolean flag = this.mob.getSensing().hasLineOfSight(this.target);
        if (flag) {
            ++this.seeTime;
        } else {
            this.seeTime = 0;
        }

        if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 5) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().moveTo(this.target, this.speedModifier);
        }

        this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
        if (--this.attackTime == 0) {
            if (!flag) {
                return;
            }

            float f = (float)Math.sqrt(d0) / this.attackRadius;
            float f1 = Mth.clamp(f, 0.1F, 1.0F);
            this.rangedAttackMob.performRangedAttack(this.target, f1);
        } else if (this.attackTime < 0) {
            this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0) / (double)this.attackRadius, 20, 20));
        }

    }

}
