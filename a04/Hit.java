package Goerisch858055.a05;


import cgtools.Vec3;

/**
 * Created by Alex on 25.10.2017.
 */
public class Hit {
    public double t;
    public Vec3 posTreff;
    public Material material;
    public Vec3 colour;
    public Hit(double t, Vec3 posTreff,Material material,Vec3 colour) {
        this.t = t;
        this.posTreff = posTreff;
        this.material=material;
        this.colour=colour;
    }
}
