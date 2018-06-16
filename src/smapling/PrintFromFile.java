/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smapling;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author buddh
 */
public class PrintFromFile implements Printable {

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        int result = NO_SUCH_PAGE;
        if (pageIndex == 0) {

            graphics.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

            result = PAGE_EXISTS;

            try {

                // You may want to rescale the image to better fit the label??
                BufferedImage read = ImageIO.read(new File("mybarcode.png"));
                graphics.drawImage(read, 0, 0, null);

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

        return result;
    }

}
