package Goerisch858055.a05;


import Goerisch858055.a03.Ray;

import cgtools.Vec3;

import static cgtools.Vec3.*;

/**
 * Created by Alex on 26.10.2017.
 */
public class Kugel implements Shape {


    public double rad;
    public Vec3 cm;
    public Material material;


    public Kugel(Vec3 c, double r, Material material) {
        this.rad = r;
        this.cm = c;
        this.material = material;

    }


    public Hit intersect(Ray r) {
        Vec3 pos = subtract(r.x0, cm);
        double a = dotProduct(r.d, r.d);
        double b = 2. * (dotProduct(r.d, pos));
        double c = dotProduct(pos, pos) - Math.pow(rad, 2.);
        double disc = Math.pow(b, 2.) - 4 * a * c;
        if (disc < 0) {
            return null;
        }
        double t1 = (-b + Math.sqrt(disc)) / 2. * a;
        double t2 = (-b - Math.sqrt(disc)) / 2. * a;
        double t;


        t = Math.min(t1, t2);
        t2 = Math.max(t1, t2);
        if (t < r.t0 || t > r.t1) {
            return null;
        }

        Vec3 treff = r.pointAt(t);
        Vec3 normal = normalize(subtract(treff, pos));
        return new Hit(t, t2, treff, normal, material,get2dCord(treff));
    }

    @Override
    public Vec3 get2dCord(Vec3 point) {
        double teta = Math.acos(point.y);
        double phi = Math.atan2(point.x, point.z);
        return new Vec3(phi / (Math.PI * 2), -teta / Math.PI,0);
    }
}


