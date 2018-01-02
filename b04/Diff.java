package Goerisch858055.bonus4;

import Goerisch858055.a03.Ray;
import Goerisch858055.a05.Hit;
import Goerisch858055.a05.Shape;

/**
 * Created by Alex on 30.11.2017.
 */
public class Diff implements Shape {

    Shape[] shapes;


    protected Diff(Shape... sh) {
        this.shapes = new Shape[sh.length];
        for (int i = 0; i < sh.length; i++) {
            shapes[i] = sh[i];
        }
    }

    @Override
    public Hit intersect(Ray r) {

        double t2 = 0;


        Hit h = shapes[0].intersect(r);
        Hit erg = h;
        if (h == null) {
            return null;
        }

        for (Shape sh : shapes) {
            if (sh.equals(shapes[0])) {
                continue;
            }
            Hit hs = sh.intersect(r);
            if (hs == null) {
                continue;
            }
            //Ausschneiden aus bisherigem Ergebnis
            if (erg != h) {
                if (erg.t < hs.t && erg.t2 < hs.t2) {
                    return null;
                }
            }

            //Abdruck und innen aushöhlen
            if (hs.t2 > t2 && (hs.t2 < h.t2 && hs.t2 > h.t)) {
                erg = hs;
            }

            //Äußeres abschcneiden
            if ((h.t > hs.t && h.t < h.t2)) {
                erg = hs;
            }

        }
        if (erg == h) {
            return h;
        }
        return new Hit(erg.t2, h.t2, r.pointAt(erg.t2), erg.normal, erg.material);

    }
}
