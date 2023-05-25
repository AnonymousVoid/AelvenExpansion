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

    public static ArrayList<Vec2> getLine(int x0, int y0, int x1, int y1) {
        ArrayList<Vec2> line = new ArrayList<>();
        boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
        int t = 0;

        if (steep) {
            t = x0;
            x0 = y0;
            y0 = t;
            t = x1;
            x1 = y1;
            y1 = t;
        }
        if (x0 > x1) {
            t = x0;
            x0 = x1;
            x1 = t;
            t = y0;
            y0 = y1;
            y1 = t;
        }

        int dx = x1 - x0;
        int dy = y1 - y0;
        double gradient = dy / dx;
        int x_end = Math.round(x0);
        double y_end = y0 + (gradient * (x_end - x0));
        int xpxl0 = x_end;
        int ypxl0 = (int)Math.round(y_end);

        if (steep) {
            line.add(new Vec2(ypxl0, xpxl0));
            line.add(new Vec2(ypxl0 + 1, xpxl0));
        } else {
            line.add(new Vec2(xpxl0, ypxl0));
            line.add(new Vec2(xpxl0, ypxl0 + 1));
        }

        double interp_y = y_end + gradient;
        x_end = Math.round(x1);
        y_end = y1 + (gradient * (x_end - x1));
        int xpxl1 = x_end;
        int ypxl1 = (int)Math.round(y_end);

        for (int x = xpxl0 + 1; x < xpxl1; x ++) {
            if (steep) {
                line.add(new Vec2((float)Math.floor(interp_y), x));
                line.add(new Vec2((float)Math.floor(interp_y) + 1, x));
            } else {
                line.add(new Vec2(x, (float)Math.floor(interp_y)));
                line.add(new Vec2(x, (float)Math.floor(interp_y) + 1));
            }
            interp_y += gradient;
        }
        if (steep) {
            line.add(new Vec2(ypxl1, xpxl1));
            line.add(new Vec2(ypxl1 + 1, xpxl1));
        } else {
            line.add(new Vec2(xpxl1, ypxl1));
            line.add(new Vec2(xpxl1, ypxl1 + 1));
        }

        return line;
    }
}
