package Goerisch858055.a10;

import Goerisch858055.Image;
import Goerisch858055.a03.Ray;
import Goerisch858055.a05.*;
import Goerisch858055.a07.Kamera;
import Goerisch858055.a08.Group;
import Goerisch858055.a08.Transform;
import Goerisch858055.a09.createPic;
import cgtools.Mat4;
import cgtools.Random;
import cgtools.Vec3;

import java.io.IOException;
import java.util.ArrayList;

import static Goerisch858055.a06.Main.calculateRadiance;
import static Goerisch858055.a06.Main.gammaCorr;
import static cgtools.Vec3.*;


public class Main {
    static int width =720;
    static int height = 450;
    static int sample = 10;
    static int threads=4;
    static Image image = new Image(width, height);
    static Mat4 mat = Mat4.identity;


    public static void main(String[] args) {
        String filename = "doc/a10-1.png";

        int[] grad={0,90,180,270,360};

        //mat =mat.rotate(vec3(0,0,1),grad[2]);

/*
        mat=(mat.translate(0,4,-40));
        mat =mat.multiply(mat.rotate(vec3(1,0,0),grad[2]));
        mat =mat.multiply(mat.rotate(vec3(0,1,0),grad[2]));
        mat =mat.multiply(mat.rotate(vec3(0,0,1),grad[2]));
*/
/*
        mat=mat.translate(0,12,-10);
        mat =mat.multiply(mat.rotate(vec3(0,1,0),grad[2]));
        mat =mat.multiply(mat.rotate(vec3(1,0,0),-45));
*/
        //mat =mat.multiply(mat.rotate(vec3(0,0,1),grad[2]));




       // mat = mat.multiply(mat.rotate(vec3(0,0,1),45));
        Kamera k = new Kamera(2, width, height,mat);
        double al = 0.8;
        double ke = 0.75;
        Material gr = new Diffus(ke, Vec3.green);
        Material re = new Diffus(ke, Vec3.red);
        Material bl = new Diffus(ke, Vec3.blue);
        Material pu=new Diffus(ke,vec3(0.8,0.2,1));
        Material ye=new Diffus(ke,vec3(1,1,0));
        Material or=new Diffus(ke,vec3(1,0.8,0));
        Material grau=new Diffus(ke,vec3(0.2,0.2,0.2));
        Material weiß = new Diffus(al,Vec3.white);

        Material pl = new Diffus(ke, vec3(0.4, 1, 0.2));
        ImageTexture t = new ImageTexture(Vec3.black,"D:/map.jpg");
        ImageTexture cl = new ImageTexture(Vec3.black,"D:/hin.jpg");
        TextBack cloud = new TextBack(cl);//0,0.8,1
        Text te =new Text(t,al);


        Shape back = new Background(cloud);
        Shape ground = new Plane(vec3(0.0, -0.5, 0), vec3(0, 1, 0), pl);
        ArrayList<Shape> shapes = new ArrayList<>();

        shapes.add(back);
        shapes.add(ground);

        shapes.add(new Kugel(vec3(0,0.5,-3),2,te));

        Mat4 bla = Mat4.identity;
       // bla=mat.translate(0,0,5);
        Transform tr= new Transform(bla);

        Group g = new Group(shapes,tr);

        int abschnitt = image.height/threads;
        Thread t1 =new Thread(new createPic(k,g,sample,image,0));
        Thread t2 =new Thread(new createPic(k,g,sample,image,abschnitt));
        Thread t3 =new Thread(new createPic(k,g,sample,image,2*abschnitt));
        Thread t4 =new Thread(new createPic(k,g,sample,image,3*abschnitt));
        long time1=System.currentTimeMillis();


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch(InterruptedException e){
            System.out.println("Interrupted");
        }

        long time2 = System.currentTimeMillis();
        System.out.println("Für "+threads+" Threads:");
        System.out.println("Took "+((time2-time1)/1000)+" Sekunden");


        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }

    }
    public static Image raytrace(Kamera k, Group g, int abtast, Image image,int startPixel) {

        for (int x = 0; x != image.width; x++) {
            for (int y = startPixel; y != startPixel +image.height/threads; y++) {
                Vec3 color = new Vec3(0, 0, 0);
                for (int xi = 0; xi < abtast; xi++) {
                    for (int yi = 0; yi < abtast; yi++) {
                        double rx = Random.random();
                        double ry = Random.random();
                        double xs = x + (xi + rx) / abtast;
                        double ys = y + (yi + ry) / abtast;

                        Ray ray = k.generateRay(xs, ys);
                        Vec3 rad = calculateRadiance(g, ray, 4);
                        color = add(color, rad);
                    }
                }
                color = divide(color, abtast * abtast);
                Vec3 colour = gammaCorr(color, 2.2);
                image.setPixel(x, y, colour);
            }
        }
        return image;
    }



}




