package Goerisch858055.bonus4;

import Goerisch858055.Image;
import Goerisch858055.Shapes.Cube;


import Goerisch858055.a05.*;
import Goerisch858055.a07.Kamera;

import cgtools.Mat4;
import cgtools.Vec3;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import static Goerisch858055.a06.Main.calculateRadiance;
import static Goerisch858055.a06.Main.gammaCorr;
import static Goerisch858055.a06.Main.raytrace;
import static Goerisch858055.bonus4.Mengen.Differenzmenge;
import static Goerisch858055.bonus4.Mengen.Schnittmenge;
import static Goerisch858055.bonus4.Mengen.UnionMenge;
import static cgtools.Vec3.*;


public class Main {
    static int width = 360;
    static int height =180;
    static int sample = 10;
    static Image image = new Image(width, height);


    public static void main(String[] args) {
        String filename = "doc/b01.png";
        Mat4 mat = Mat4.identity;

        mat=mat.translate(0,0.25,-0);
        //mat=mat.multiply(mat.rotate(vec3(1,0,0),-35));
        Kamera k = new Kamera(2.0, width, height,mat);
        double albedo = 0.7;
        double kugel_al = 0.9;

        //Materialien

        Material gr = new Diffus(kugel_al, Vec3.green);
        Material bl = new Diffus(kugel_al, Vec3.blue);
        Material re = new Diffus(kugel_al, Vec3.red);
        Material ye = new Diffus(kugel_al,vec3(1,1,0));
        Material plane = new Diffus(albedo, vec3(0.5, 0.5, 0.5));
        Material hin = new Hintergrund(vec3(1, 1, 1));

        //Group Erstellung
        Shape back = new Background(hin);
        Shape ground = new Plane(vec3(0.0, -0.5, 0), vec3(0, 1, 0), plane);
        Shape k_bl = new Kugel(vec3(0., 0.15, -2), 0.8, bl);
        Shape k_gr = new Kugel(vec3(-0.25,0.15, -1.8), 0.8, gr);
        Shape k_re = new Kugel(vec3(0.25,0.15, -1.6), 0.8, re);
        Shape k_ye = new Kugel(vec3(+0.55,0.075,-2.2),0.3,ye);


        Shape cube=new Cube(vec3(2.25,0.00,-5.4),vec3 (1.25,1.,-0.515),gr);
        Shape cube2=new Cube(vec3(-2.25,0.00,-5.4),vec3 (-1.25,1,-0.515),re);
       // Shape cube3=new Cube(vec3(0.25,0.00,-5.4),vec3 (-0.25,1.,-0.515),ye);



        Shape union = UnionMenge(k_re,k_bl,k_gr);
        Shape diff = Differenzmenge(k_gr,k_re,k_ye);
        Shape schnitt = Schnittmenge(k_gr,k_re);
        Shape schn=Schnittmenge(schnitt,k_bl);
        Shape diffe=Differenzmenge(diff,k_bl);
        Shape un = UnionMenge(k_gr,k_re);

        Shape un_diff=Schnittmenge(union,k_ye);





        List<Shape> s = new ArrayList<>();

        s.add(ground);
        s.add(back);
        s.add(cube);
        s.add(cube2);
       // s.add(cube3);

        Group g = new Group(s);
        raytrace(k, g, sample,image);

        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }

    }



}




