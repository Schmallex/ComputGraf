package Goerisch858055.a04;

import Goerisch858055.a03.Hit;
import Goerisch858055.a03.Ray;
import cgtools.Vec3;

import java.util.List;

import static cgtools.Vec3.subtract;
import static cgtools.Vec3.vec3;

/**
 * Created by Alex on 07.11.2017.
 */
public class Group implements Shape {
    public Shape[] shapes;

    public Group (Shape[] shape){
        this.shapes=shape;
    }
    public Hit intersect(Ray r){
        double t =Double.POSITIVE_INFINITY;

        Hit hit =null;
        for (Shape s:shapes) {

            Hit h =s.intersect(r);
            if (h==null){
                continue;
            }
            Vec3 verbind = subtract(h.posTreff,r.x0);
            double length =Math.sqrt(Math.pow(verbind.x,2)+Math.pow(verbind.y,2)+Math.pow(verbind.z,2));
            if(length<t){
                t=length;
                hit=h;
            }

        }
        return hit;
    }
}
