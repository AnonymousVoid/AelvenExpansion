package dev.anonymousvoid.aelven_expansion.entity.mob.goals;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class GnomeRunGoal extends Goal {
    protected final PathfinderMob mob;
    protected final LivingEntity target;
    protected final double speedModifier;
    protected double posX;
    protected double posY;
    protected double posZ;

    public GnomeRunGoal(PathfinderMob pMob, LivingEntity target, double pSpeedModifier) {
        this.mob = pMob;
        this.target = target;
        this.speedModifier = pSpeedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean canUse() {
        return this.findPosition();
    }

    protected boolean findPosition() {
        Vec3 vec3 = DefaultRandomPos.getPosAway(this.mob, 10, 2, this.target.position());
        if (vec3 == null) {
            return false;
        } else {
            this.posX = vec3.x;
            this.posY = vec3.y;
            this.posZ = vec3.z;
            return true;
        }
    }

    public void start() {
        this.mob.getNavigation().moveTo(this.posX, this.posY, this.posZ, this.speedModifier);
    }

    public void stop() {
//        this.mob.goalSelector.removeGoal(this);
    }

    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone();
    }
}
