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
        String filename = "doc/a05-scenetest.png";
        Kamera k = new Kamera(Math.PI / 2, width, height);
        double al = 0.5;
        Material ku = new Diffus(al, Vec3.red);
        Material pl = new Diffus(al, vec3(0.8, 0.8, 1));
        Material hin = new Hintergrund(vec3(0.6, 1, 1), 0);
        Shape back = new Background(hin);
        Shape ground = new Plane(vec3(0.0, -0.5, 0), vec3(0, 1, 0), pl, vec3(0.6, 0.6, 0.6));

        Shape globe2 = new Kugel(vec3(0, 0.25, -2), 1, ku, Vec3.black);
        Shape globe3 = new Kugel(vec3(1.8, 0.25, -7), 1, ku, vec3(1, 1, 0));
        Shape globe4 = new Kugel(vec3(-1.8, 0.35, -8), 0.75, ku, Vec3.green);

        //globe4, globe2, globe3,
        Shape[] s = {globe4, globe2, globe3, ground, back};
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
                Vec3 hin = new Vec3(1, 1, 1);
                Vec3 color = hin;
                if(x==width/2&&y==height/2){
                    System.out.println("k");
                }

                for (int xi = 0; xi <abtast; xi++) {
                    for (int yi = 0; yi <abtast; yi++) {
                        double rx = Random.random();
                        double ry = Random.random();
                        double xs = x + (xi + rx) / abtast;
                        double ys = y + (yi + ry) / abtast;

                        Ray ray = k.generateRay(xs, ys);
                        Vec3 rad= calculateRadiance(g,ray,4);
                        color = add(color,rad);

                    }
                }
                color = divide(color, abtast*abtast);
                // Vec3 colour=gammaCorr(color,0.3);
                /*

               Vec3 color=null;
                Ray ray = k.generateRay(x, y);

                color = calculateRadiance(g, ray, 4);
                if(x==250&&y==250){
                    System.out.println("k");
                }*/

                color=gammaCorr(color,2.2);
                //*/
                image.setPixel(x, y, color);
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




