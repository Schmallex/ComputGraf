package Goerisch858055.a10;


import Goerisch858055.a03.Ray;
import Goerisch858055.a05.Hit;
import Goerisch858055.a05.Material;
import Goerisch858055.a05.Shape;
import cgtools.Vec3;


import static cgtools.Vec3.add;
import static cgtools.Vec3.multiply;
import static cgtools.Vec3.vec3;

/**
 * Created by Alex on 07.11.2017.
 */
public class Background implements Shape {
    public Material material;
    public Background(Material material){
        this.material=material;
    }
    public Hit intersect(Ray r){
        double t = Double.POSITIVE_INFINITY;

        Vec3 pos = add(r.x0, multiply(t, r.d));

        return new Hit(t,t,pos, multiply(-1,r.d),material,get2dCord(r.d));
    }

    @Override
    public Vec3 get2dCord(Vec3 point) {
        double teta = Math.acos(point.y);
        double phi = Math.atan2(point.x, point.z);
        return new Vec3((Math.atan2(point.x, point.z) + Math.PI) / (2 * Math.PI), Math.acos(point.y) / Math.PI, 0);
    }
}
