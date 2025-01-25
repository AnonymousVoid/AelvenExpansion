package dev.anonymousvoid.aelven_expansion.entity.mob.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class GnomeMischiefGoal extends Goal {
    protected final PathfinderMob mob;

    public GnomeMischiefGoal(PathfinderMob pMob) {
        this.setFlags(EnumSet.of(Flag.LOOK));
        this.mob = pMob;
    }

    public boolean canUse() {
        if (this.mob.isVehicle()) {
            return false;
        }

        // Player player = lookForNearbyPlayer
        // if player !null, do return true
        // Entity entity = lookForNearbyEntity
        // if entity !null, do return true
        // BlockPos pos = find nearby wall or building?
        // if pos !null, do return true
        return false;
    }

    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone() && !this.mob.isVehicle();
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
    }

    public void stop() {
        this.mob.getNavigation().stop();
        super.stop();
    }

}
