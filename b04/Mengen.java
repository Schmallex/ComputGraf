package Goerisch858055.bonus4;

import Goerisch858055.a05.Shape;

/**
 * Created by Alex on 30.11.2017.
 */
public class Mengen {

    /**
     * Erzeugt ein Shape ,welches die Schnittmenge aus allen Para darstellt
     * @param sh beliebige Anzahl Shapes
     * @return Schnittmenge der Shape Objekte
     */
    public static Shape Schnittmenge(Shape... sh){
        return new Schnitt(sh);
    }

    /**
     * Erzeugt ein Shape Objekt,welches die Differenz zwischen  dem ersten Objekt und den darauf folgenden bildet.
     * @param sh beliebige Anzahl Shapes
     * @return Das erste Shape minus alle anderen
     */
    public static Shape Differenzmenge (Shape...sh){
        return new Diff(sh);
    }

    /**
     * Verbindet Shape Objekte zu einem Objekt
     * @param sh beliebige Anzahl an Shapes
     * @return ein Shape Objekt bestehend aus param
     */
    public static Shape UnionMenge(Shape... sh){
        return new Union(sh);
    }
}
