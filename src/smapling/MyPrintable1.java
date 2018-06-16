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
public class MyPrintable1 implements Printable {

    
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, 
            int pageIndex) throws PrinterException {    
            System.out.println(pageIndex);                
            int result = NO_SUCH_PAGE;    
            if (pageIndex < 2) {                    
                Graphics2D g2d = (Graphics2D) graphics;                    
                System.out.println("[Print] " + dump(pageFormat));                    
                double width = pageFormat.getImageableWidth();
                double height = pageFormat.getImageableHeight();    
                g2d.translate((int) pageFormat.getImageableX(), 
                    (int) pageFormat.getImageableY());                    
                g2d.draw(new Rectangle2D.Double(2, 2, width - 2, height - 2));                    
                FontMetrics fm = g2d.getFontMetrics();
                g2d.drawString("1234567890", 10, fm.getAscent());    
                result = PAGE_EXISTS;    
            }    
            return result;    
        }
    
}
