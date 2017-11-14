package Goerisch858055.a05;


import Goerisch858055.a03.Ray;

import cgtools.Vec3;

import static cgtools.Vec3.dotProduct;
import static cgtools.Vec3.subtract;

/**
 * Created by Alex on 07.11.2017.
 */
public class Plane implements Shape {
    public Material material ;
    public Vec3 n;
    public Vec3 punkt;
    public Vec3 colour;


    public Plane(Vec3 punkt ,Vec3 n,Material material,Vec3 colour){
        this.n=n;
        this.punkt = punkt;
        this.material=material;
        this.colour=colour;
    }

    public Hit intersect(Ray r){

        double t = (dotProduct(subtract(punkt,r.x0),n))/dotProduct(r.d,n);
        if(t<=0){
            return null;
        }
        Vec3 treff = r.pointAt(t);
        return new Hit(t,treff,material,colour);


    }
}
