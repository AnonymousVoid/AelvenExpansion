package dev.anonymousvoid.aelven_expansion.entity.mob.goals;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class GnomeMischiefGoal extends Goal {
    protected final Mob mob;
    @Nullable
    protected Entity mischiefTarget;
    protected final float seekDistance;
    private int seekTime;
    protected final Class<? extends LivingEntity> targetType;
    protected final TargetingConditions targetContext;

    public GnomeMischiefGoal(Mob pMob, Class<? extends LivingEntity> pTargetType, float pSeekDistance) {
        this.mob = pMob;
        this.targetType = pTargetType;
        this.seekDistance = pSeekDistance;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        if (pTargetType == Player.class) {
            this.targetContext = TargetingConditions.forNonCombat().range((double)pSeekDistance).selector((p_25531_) -> {
                return EntitySelector.notRiding(pMob).test(p_25531_);
            });
        } else {
            this.targetContext = TargetingConditions.forNonCombat().range((double)pSeekDistance);
        }
    }

    public boolean canUse() {
        // Player player = lookForNearbyPlayer
        // if player !null, do return true
        // Entity entity = lookForNearbyEntity
        // if entity !null, do return true
        // BlockPos pos = find nearby wall or building?
        // if pos !null, do return true
        if (this.mob.getTarget() != null) {
            this.mischiefTarget = this.mob.getTarget();
        }

        if (this.targetType == Player.class) {
            this.mischiefTarget = this.mob.level.getNearestPlayer(this.targetContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            System.out.println("do player mischief?");
        } else {
            this.mischiefTarget = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.targetType, this.mob.getBoundingBox().inflate((double)this.seekDistance, 3.0D, (double)this.seekDistance), (entity) -> {
                return true;
            }), this.targetContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            System.out.println("do entity mischief?");
        }

        return this.mischiefTarget != null;
    }

    public boolean canContinueToUse() {
        if (!this.mischiefTarget.isAlive()) {
            return false;
        } else if (this.mob.distanceToSqr(this.mischiefTarget) > (double)(this.seekDistance * this.seekDistance)) {
            return false;
        } else {
            return this.seekTime > 0;
        }
    }

    public void start() {
        /*
         * Look for a player to bother
         * Look for an entity to bother
         * Look for something to vandalize
         * If a target is found:
         * * Commit some form of mischief
         * * Add RunMischiefGoal
         */
        // if player !null, do console log player mischief, add run away goal, and return
        // if entity !null, do console log entity mischief, add run away goal, and return
        // if pos !null, do console log vandal mischief, add run away goal, and return
        this.seekTime = this.adjustedTickDelay(40 + this.mob.getRandom().nextInt(40));
    }

    public void stop() {
        this.mischiefTarget = null;
    }

    public void tick() {
        if (this.mischiefTarget.isAlive()) {
            this.mob.getLookControl().setLookAt(this.mischiefTarget.getX(), this.mischiefTarget.getEyeY(), this.mischiefTarget.getZ());
            --this.seekTime;
        }
    }

}
