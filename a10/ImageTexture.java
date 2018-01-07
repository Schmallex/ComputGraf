package Goerisch858055.a10;

import cgtools.Vec3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Alex on 07.01.2018.
 */
public class ImageTexture implements Texture {

    public  String path ;
    public Vec3 colour;
    public BufferedImage image;

    public ImageTexture(Vec3 color, String path) {
        this.colour = color;
        this.image = null;
        this.path=path;
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public Vec3 getColor(double u,double v) {
        double cord1 = ImageTexture.getRelativeCoord(u);
        double cord2 = ImageTexture.getRelativeCoord(v);
        double x = (this.image.getWidth() - 1) * cord1;
        double y = (this.image.getHeight() - 1) - ((image.getHeight() - 1) * cord2);
        return ImageTexture.getColorOfPosition(this.image, (int) Math.round(x), (int) Math.round(y));
    }

    public static double getRelativeCoord(double in) {
        double out = in % 1.0;
        if (out < 0.0) {
            out = out + 1.0;
        }
        return out;
    }

    public static Vec3 getColorOfPosition( BufferedImage image, int x,  int y) {
        final java.awt.Color c = new java.awt.Color(image.getRGB(x, y));
      //  String s =" "+c.getRed()/250.0+" "+c.getGreen()+" "+c.getBlue();
       // System.out.println(s);
        return new Vec3(c.getRed()/255.0,c.getGreen()/255.0,c.getBlue()/255.0);
    }

}
