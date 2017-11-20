package Goerisch858055.a04;

import Goerisch858055.a03.Hit;
import Goerisch858055.a03.Ray;
import cgtools.Vec3;

import static cgtools.Vec3.vec3;

/**
 * Created by Alex on 07.11.2017.
 */
public class Background implements Shape {
    public Vec3 colour;
    public Background(Vec3 colour){
        this.colour=colour;
    }
    public Hit intersect(Ray r){
        return new Hit(800000,vec3(8000,8000,8000),colour);
    }
}
