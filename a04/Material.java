package Goerisch858055.a05;


import Goerisch858055.a03.Ray;
import cgtools.Vec3;

/**
 * Created by Alex on 08.11.2017.
 */
public interface Material {
    Vec3 emittedRadiance(Ray r, Hit h);
    Ray scatteredRay(Ray r, Hit h);
    Vec3 albedo(Ray r, Hit h);
}
