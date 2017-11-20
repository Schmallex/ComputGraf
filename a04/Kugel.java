package Goerisch858055.a05;


import Goerisch858055.a03.Ray;

import cgtools.Vec3;

import static cgtools.Vec3.*;

/**
 * Created by Alex on 26.10.2017.
 */
public class Kugel implements Shape{


    public double rad;
    public Vec3 cm;
    public Material material;


    public Kugel( Vec3 c,double r,Material material) {
        this.rad = r;
        this.cm = c;
        this.material=material;

    }


    public Hit intersect(Ray r) {
        Vec3 pos = subtract(r.x0, cm);
        double a = dotProduct(r.d, r.d);
        double b = 2. * (dotProduct(r.d, pos));
        double c = dotProduct(pos, pos) - Math.pow(rad, 2.);
        //System.out.println(a+" "+b+" " +c);
        double disc = Math.pow(b, 2.) - 4 * a * c;
        if (disc < 0) {
            return null;
        }
        double t1 = (-b + Math.sqrt(disc)) / 2. * a;
        double t2 = (-b - Math.sqrt(disc)) / 2. * a;
        double t;
        if (t1 < 0 && t2 < 0) {
            return null;
        } else if (t1 < 0) {
            t = t2;
        } else if (t2 < 0) {
            t = t1;
        } else {
            t = Math.min(t1, t2);
        }
        Vec3 treff = r.pointAt(t);
        return new Hit(t, treff, material);
    }

}
