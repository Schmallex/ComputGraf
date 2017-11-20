package Goerisch858055.a05;


import cgtools.Vec3;

/**
 * Created by Alex on 25.10.2017.
 */
public class Hit {
    public double t;
    public Vec3 posTreff;
    public Material material;

    public Hit(double t, Vec3 posTreff,Material material) {
        this.t = t;
        this.posTreff = posTreff;
        this.material=material;

    }
}
