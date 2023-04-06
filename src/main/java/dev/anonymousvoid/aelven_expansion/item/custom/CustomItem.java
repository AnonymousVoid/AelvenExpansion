package dev.anonymousvoid.aelven_expansion.item.custom;

import net.minecraft.core.Direction;
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

    protected void spawnParticleCube(Level level, ParticleOptions particle, UseOnContext context) {
        Random rand = new Random();
        Vec3 vPos = context.getClickLocation();
        int rX = rand.nextInt(2);
        int rY = rand.nextInt(2);
        int rZ = rand.nextInt(2);
        double vX = context.getClickedFace() == Direction.EAST ? Math.floor(vPos.x) - 1 : Math.floor(vPos.x);
        double vY = context.getClickedFace() == Direction.UP ? Math.floor(vPos.y) - 1 : Math.floor(vPos.y);
        double vZ = context.getClickedFace() == Direction.SOUTH ? Math.floor(vPos.z) - 1 : Math.floor(vPos.z);
        System.out.println(vPos.x % 1);
        double x = rand.nextFloat() * rX + vX;
        double y = rand.nextFloat() * rY + vY;
        double z = rand.nextFloat() * rZ + vZ;
        level.addParticle(particle, x, y, z, 0.0D, 0.0D, 0.0D);
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
