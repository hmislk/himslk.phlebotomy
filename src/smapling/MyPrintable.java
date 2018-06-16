/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smapling;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.output.OutputException;
import static smapling.Printing.dump;

/**
 *
 * @author buddh
 */
public class MyPrintable implements Printable {

    @Override
    public int print(Graphics graphics, PageFormat pageFormat,
            int pageIndex) throws PrinterException {
        System.out.println(pageIndex);
        int result = NO_SUCH_PAGE;
        graphics.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

        if (pageIndex < 1) {
            try {
                Graphics2D g2d = (Graphics2D) graphics;
                
                double width = pageFormat.getImageableWidth();
                double height = pageFormat.getImageableHeight();
                g2d.translate((int) pageFormat.getImageableX(),
                        (int) pageFormat.getImageableY());
//                g2d.draw(new Rectangle2D.Double(1, 1, width - 10, height - 20));
                FontMetrics fm = g2d.getFontMetrics();
                g2d.drawString("Name :", 10, fm.getAscent());
                g2d.drawString("Tests :", 0, fm.getAscent());
                Barcode b = null;
                try {
                    
                    b = BarcodeFactory.createCode128("123456789012" );
                    b.setResolution(72);
                    Dimension d = new Dimension((int)height - 10, (int)width - 10);
                    b.setSize(d);
                    b.draw(g2d, 3, 3);
                } catch (BarcodeException ex) {
                    Logger.getLogger(Printing.class.getName()).log(Level.SEVERE, null, ex);
                } catch (OutputException ex) {
                    Logger.getLogger(MyPrintable.class.getName()).log(Level.SEVERE, null, ex);
                }
                result = PAGE_EXISTS;
            } catch (Exception ex) {
                Logger.getLogger(MyPrintable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    
    
    public BufferedImage rotate90DX(BufferedImage bi)
    {
        int width = bi.getWidth();
        int height = bi.getHeight();

        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());

        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
                biFlip.setRGB(height-1-j, width-1-i, bi.getRGB(i, j));

        return biFlip;
    }
}
