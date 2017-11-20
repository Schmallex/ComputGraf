package Goerisch858055.a04;

import Goerisch858055.a03.Hit;
import Goerisch858055.a03.Ray;
import cgtools.Vec3;

import static cgtools.Vec3.*;

/**
 * Created by Alex on 07.11.2017.
 */
public class Plane implements Shape {
    public Vec3 colour ;
    public Vec3 n;
    public Vec3 punkt;
    public Vec3 p;

    public Plane(Vec3 punkt ,Vec3 n,Vec3 colour){
        this.n=n;
        this.punkt = punkt;
        this.colour=colour;
    }

    public Hit intersect(Ray r){

        double t = (dotProduct(subtract(punkt,r.x0),n))/dotProduct(r.d,n);
        if(t<=0){
            return null;
        }
        Vec3 treff = r.pointAt(t);
        return new Hit(t,treff,colour);


    }
}
