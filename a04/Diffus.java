package Goerisch858055.a05;

import Goerisch858055.a03.Ray;
import cgtools.Random;
import cgtools.Vec3;

import static cgtools.Vec3.*;

/**
 * Created by Alex on 08.11.2017.
 */
public class Diffus implements Material {
    public double albedo;
    public Vec3 colour;

    public Diffus(double albedo,Vec3 colour){
        this.albedo=albedo;
        this.colour=colour;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return vec3(0,0,0);
        //return Vec3.multiply(h.colour,vec3(albedo,albedo,albedo));
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        Vec3 rnd=new Vec3(Random.random()*2-1,Random.random()*2-1,Random.random()*2-1);
        while(rnd.length()>=1){
            rnd=new Vec3(Random.random()*2-1,Random.random()*2-1,Random.random()*2-1);
        }
        return new Ray(h.posTreff,add(rnd,normalize(h.posTreff)));
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return multiply(albedo,colour);
    }
}
