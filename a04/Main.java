package Goerisch858055.a05;

import Goerisch858055.Image;

import Goerisch858055.a03.Kamera;
import Goerisch858055.a03.Ray;
import cgtools.Random;
import cgtools.Vec3;

import java.io.IOException;

import static cgtools.Vec3.*;


public class Main {
    static int width = 640;
    static int height = 500;
    static int sample = 10;
    static Image image = new Image(width, height);


    public static void main(String[] args) {
        String filename = "doc/a05-scenetest_0.8_2.png";
        Kamera k = new Kamera(Math.PI / 2, width, height);
        double al = 0.95;
        double ke =0.85;
        Material kug1 = new Diffus(ke, Vec3.green);
        Material kug2 = new Diffus(ke, Vec3.blue);
        Material kug3 = new Diffus(ke, Vec3.red);
        Material kug4 = new Diffus(ke,vec3(0.9,0.9,0.9));
        Material pl = new Diffus(al, vec3(0.5,0.5,0.5));

        Material hin = new Hintergrund(vec3(1, 1, 1), 0);

        Shape back = new Background(hin);
        Shape ground = new Plane(vec3(0.0, -0.5, 0), vec3(0, 1, 0), pl);
        Shape globe2 = new Kugel(vec3(0, 0.25, -5), 1, kug1);
        Shape globe3 = new Kugel(vec3(2.5, 0.55, -4.4), 1, kug2);
        Shape globe4 = new Kugel(vec3(-2.5, 0.45, -4.4), 1, kug3);
        Shape globe5 = new Kugel(vec3(4.5, 6.45, -8.4), 1, kug4);



        Shape[] s = { globe2, globe3,globe4,globe5, ground, back};
        Group g = new Group(s);
        raytrace(k, g, 10);

        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }

    }

    public static Image raytrace(Kamera k, Group g, int abtast) {

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                ///**

                Vec3 color = new Vec3(0,0,0);
                for (int xi = 0; xi <abtast; xi++) {
                    for (int yi = 0; yi <abtast; yi++) {
                        double rx = Random.random();
                        double ry = Random.random();
                        double xs = x + (xi + rx) / abtast;
                        double ys = y + (yi + ry) / abtast;

                        Ray ray = k.generateRay(xs, ys);
                        Hit h = g.intersect(ray);
                        if (h!=null){
                            Vec3 rad= calculateRadiance(g,ray,4);
                            color = add(color,rad);
                        }
                        else{
                            color.add(color,vec3(0.6, 1, 1));
                        }
                    }
                }
                color = divide(color, abtast*abtast);
                Vec3 colour=gammaCorr(color,1.4);
                /*

               Vec3 color=null;
                Ray ray = k.generateRay(x, y);

                color = calculateRadiance(g, ray, 4);
                if(x==250&&y==250){
                    System.out.println("k");
                }*/

                //color=gammaCorr(color,2.2);
                //*/
                image.setPixel(x, y, colour);
            }
        }

        return image;

    }

    public static Vec3 gammaCorr(Vec3 color, double gamma) {
        double gamma_new = 1 / gamma;
        double r = Math.pow(color.x, gamma_new);
        double g = Math.pow(color.y, gamma_new);
        double b = Math.pow(color.z, gamma_new);
        return vec3(r, g, b);
    }

    public static Vec3 calculateRadiance(Shape scene, Ray ray, int depth) {
        if (depth == 0) {
            return Vec3.black;
        }
        Hit hit = scene.intersect(ray);
        Vec3 emission = hit.material.emittedRadiance(ray, hit);
        Ray scattered = hit.material.scatteredRay(ray, hit);
        //Hit hi = scene.intersect(scattered);
        if (scattered != null) {
            return add(emission, multiply(hit.material.albedo(ray, hit), calculateRadiance(scene, scattered, depth - 1)));
        } else {
            return emission;
        }

    }
}




