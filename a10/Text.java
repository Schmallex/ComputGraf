package Goerisch858055.a10;

import Goerisch858055.a03.Ray;
import Goerisch858055.a05.Hit;
import Goerisch858055.a05.Material;
import cgtools.Random;
import cgtools.Vec3;

import static cgtools.Vec3.add;
import static cgtools.Vec3.multiply;

/**
 * Created by Alex on 07.01.2018.
 */
public class Text implements Material {
    Texture texture;
    private double albedo;

    public Text (Texture texture,double albedo){
        this.texture = texture;
        this.albedo = albedo;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return Vec3.black;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        Vec3 rnd=new Vec3(Random.random()*2-1,Random.random()*2-1,Random.random()*2-1);
        while(rnd.length()<1) {
            rnd = new Vec3(Random.random() * 2 - 1, Random.random() * 2 - 1, Random.random() * 2 - 1);
        }
        double e=0.1;
        return new Ray(h.posTreff,add(rnd,h.normal),e,Double.POSITIVE_INFINITY);
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return multiply(albedo,texture.getColor(h.twoDCord.x,h.twoDCord.y));
    }
}
