package Goerisch858055.a04;

import Goerisch858055.Image;
import Goerisch858055.a03.Hit;
import Goerisch858055.a03.Kamera;

import Goerisch858055.a03.Ray;
import cgtools.Random;
import cgtools.Vec3;

import java.io.IOException;

import static cgtools.Vec3.normalize;
import static cgtools.Vec3.vec3;


public class Main {
    static int width = 160;
    static int height =120;
    static int sample=10;
    static Image image = new Image(width, height);



    public static void main(String[] args) {
        String filename = "doc/a04-sceneteset.png";
        Kamera k =new Kamera(Math.PI/2,width,height);
        Shape ground = new Plane(vec3(0.0, -0.5, 0), vec3(0, 1, 0), vec3(0.5,0.5,0.5));
        Shape globe1 = new Kugel(vec3(0, 0.75, -2.5), 0.7, Vec3.white);
        Shape globe2 = new Kugel(vec3(0, 1.6, -2.5), 0.5, Vec3.white);
        Shape globe3 = new Kugel(vec3(0, -0.25, -2.5), 0.7,Vec3.white);
        Shape globe4 = new Kugel(vec3(-0.15, 1.6, -2.), 0.1,Vec3.black);
        Shape globe5 = new Kugel(vec3(0.15, 1.6, -2.), 0.1,vec3(1,1,0));
        Shape globe6 = new Kugel(vec3(0., 1.25, -2.25), 0.3,Vec3.black);
        Shape globe7 = new Kugel(vec3(0.15, 1.75, -2.), 0.1,Vec3.white);
        Shape back = new Background(vec3(0.5,0.7,0.6));

        Shape[] s ={globe1,globe2,globe3,ground,globe4,globe5,globe6,globe7,back};
        Group g =new Group(s);
        raytrace(k,g,100);

        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }

    }

    public static Image raytrace(Kamera k,Group g,int abtast){
        int ab =(int)Math.sqrt(abtast);
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                Vec3 hin = new Vec3(0,0,0);
                Vec3 color= hin;
                for (int xi = 0; xi < ab; xi++) {
                    for (int yi = 0; yi < ab; yi++) {
                        double rx = Random.random();
                        double ry = Random.random();
                        double xs = x + (xi + rx) / ab;
                        double ys = y + (yi + ry) / ab;

                        Ray ray = k.generateRay(xs,ys);
                        Hit hit = g.intersect(ray);
                        if (hit != null) {
                            color=Vec3.add(color,hit.normTreff);
                        } else {
                            color=Vec3.add(color,hin);
                        }

                    }
                }
                color = Vec3.divide(color,ab*ab);
               // Vec3 colour=gammaCorr(color,0.3);


                image.setPixel(x, y,color);
            }
        }
        return image;

    }
    public  static Vec3 gammaCorr(Vec3 color, double gamma) {
        double gamma_new = 1 / gamma;
        double r =Math.pow(color.x, gamma_new);
        double g=Math.pow(color.y, gamma_new);
        double b =Math.pow(color.x, gamma_new);
        return vec3(r,g,b);
    }

    }




