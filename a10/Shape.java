package Goerisch858055.a05;

import Goerisch858055.a03.Ray;
import cgtools.Vec3;

/**
 * Created by Alex on 07.11.2017.
 */
public interface Shape {
    Hit intersect(Ray r);
    Vec3 get2dCord(Vec3 point);
}
