package Goerisch858055.bonus4;

import Goerisch858055.a03.Ray;
import Goerisch858055.a05.Hit;
import Goerisch858055.a05.Shape;

/**
 * Created by Alex on 30.11.2017.
 */
public class Union implements Shape {
    Shape[] shapes;

    protected Union(Shape... sh) {
        this.shapes = new Shape[sh.length];
        for (int i = 0; i < sh.length; i++) {
            shapes[i] = sh[i];
        }
    }

    @Override
    public Hit intersect(Ray r) {
        Hit hit = null;
        for (Shape s : shapes) {

            Hit h = s.intersect(r);
            if (h == null) {
                continue;
            }
            if (hit == null) {
                hit = h;
            } else if (h.t < hit.t) {
                hit = h;
            }
        }
        return hit;
    }
}
