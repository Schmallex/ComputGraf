package Goerisch858055.a05;


import Goerisch858055.a03.Ray;
import cgtools.Vec3;

import static cgtools.Vec3.subtract;

/**
 * Created by Alex on 25.10.2017.
 */
public class Hit {
    public double t;
    public double t2;
    public Vec3 posTreff;
    public Vec3 normal;
    public Material material;
    public Vec3 twoDCord;

    public Hit(double t,double t2, Vec3 posTreff,Vec3 normal, Material material,Vec3 twoDCord) {
        this.t = t;
        this.t2 = t2;
        this.posTreff = posTreff;
        this.normal = normal;
        this.material = material;
        this.twoDCord = twoDCord;
    }
    public double length(Ray r){
    Vec3 verbind = subtract(posTreff, r.x0);
    return Math.sqrt(Math.pow(verbind.x, 2) + Math.pow(verbind.y, 2) + Math.pow(verbind.z, 2));
    }
}
