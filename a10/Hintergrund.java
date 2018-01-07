package Goerisch858055.a10;

import Goerisch858055.a03.Ray;
import Goerisch858055.a05.Hit;
import Goerisch858055.a05.Material;
import cgtools.Vec3;

import static cgtools.Vec3.divide;
import static cgtools.Vec3.multiply;

/**
 * Created by Alex on 08.11.2017.
 */
public class Hintergrund implements Material {
    public Vec3 colour;


    public Hintergrund(Vec3 colour) {
        this.colour = colour;

    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return colour;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        return null;
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return null;
    }
}
