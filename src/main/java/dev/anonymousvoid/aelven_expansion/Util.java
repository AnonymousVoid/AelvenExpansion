package dev.anonymousvoid.aelven_expansion;

import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Util {

    public static double dist(double x, double y) {
        return Math.sqrt( ( x * x ) + ( y * y ) );
    }

    public static double dist3D(double x, double y, double z) {
        return Math.sqrt( ( x * x ) + ( y * y )  + ( z * z ) );
    }
}
