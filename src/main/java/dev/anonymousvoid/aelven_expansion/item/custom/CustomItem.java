package dev.anonymousvoid.aelven_expansion.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class CustomItem extends Item {
    public CustomItem(Properties properties) {
        super(properties);
    }

    protected void spawnParticleCube(Level level, ParticleOptions particle, UseOnContext context,
                                     double minX, double maxX, double minY, double maxY, double minZ, double maxZ) {
        Random rand = new Random();
        BlockPos pos = context.getClickedPos();
        Vec3 vPos = context.getClickLocation();
        double rX = rand.nextFloat() * 1.1 - 0.05;
        double rY = rand.nextFloat() * 1.1 - 0.05;
        double rZ = rand.nextFloat() * 1.1 - 0.05;

        int axis = rand.nextInt(3);
        if (axis == 0) rX = rand.nextInt(2) * 1.1 - 0.05;
        else if (axis == 1) rY = rand.nextInt(2) * 1.1 - 0.05;
        else if (axis == 2) rZ = rand.nextInt(2) * 1.1 - 0.05;

        double vX = minX == maxX ? 0.0D : rand.nextDouble(maxX - minX) + minX;
        double vY = minY == maxY ? 0.0D : rand.nextDouble(maxY - minY) + minY;
        double vZ = minZ == maxZ ? 0.0D : rand.nextDouble(maxZ - minZ) + minZ;

        double x = rX + pos.getX();
        double y = rY + pos.getY();
        double z = rZ + pos.getZ();
        level.addParticle(particle, x, y, z, vX, vY, vZ);
    }

    protected void spawnParticleCube(Level level, ParticleOptions particle, UseOnContext context, double min, double max) {
        spawnParticleCube(level, particle, context, min, max, min, max, min, max);
    }

    protected void spawnParticleCube(Level level, ParticleOptions particle, UseOnContext context) {
        spawnParticleCube(level, particle, context, 0.0D, 0.0D);
    }

    protected void spawnParticleFace(Level level, ParticleOptions particle, UseOnContext context) {
        Random rand = new Random();
        Vec3 vPos = context.getClickLocation();
        double x = rand.nextFloat() * (Math.ceil(vPos.x) - Math.floor(vPos.x)) + Math.floor(vPos.x);
        double y = rand.nextFloat() * (Math.ceil(vPos.y) - Math.floor(vPos.y)) + Math.floor(vPos.y);
        double z = rand.nextFloat() * (Math.ceil(vPos.z) - Math.floor(vPos.z)) + Math.floor(vPos.z);
        level.addParticle(particle, x, y, z, 0.0D, 0.0D, 0.0D);
    }
}
