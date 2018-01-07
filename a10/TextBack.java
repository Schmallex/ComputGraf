package Goerisch858055.a10;

import Goerisch858055.a03.Ray;
import Goerisch858055.a05.Hit;
import Goerisch858055.a05.Material;
import cgtools.Random;
import cgtools.Vec3;

import static cgtools.Vec3.add;
import static cgtools.Vec3.multiply;

/**
 * Created by Alex on 07.01.2018.
 */
public class TextBack implements Material {
    Texture texture;


    public TextBack (Texture texture){
        this.texture = texture;

    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return texture.getColor(h.twoDCord.x,h.twoDCord.y) ;
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
