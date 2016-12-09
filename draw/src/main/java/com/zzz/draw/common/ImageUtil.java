package com.zzz.draw.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by dell_2 on 2016/12/8.
 */
public class ImageUtil {

    private static File rw_file = new File(ImageUtil.class.getResource("/images/renwu.png").getFile());
    private static BufferedImage re_buffImage;

    static {
        try {
            re_buffImage = ImageIO.read(rw_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage[] getRightRunImages() {
        BufferedImage[] images = new BufferedImage[4];
        images[3] = re_buffImage.getSubimage(0, 80, 38, 43);
        images[0] = re_buffImage.getSubimage(38, 80, 38, 43);
        images[1] = re_buffImage.getSubimage(76, 80, 38, 43);
        images[2] = re_buffImage.getSubimage(114, 80, 38, 43);
        return images;
    }


}
