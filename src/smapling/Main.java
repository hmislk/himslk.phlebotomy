/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smapling;

import javax.swing.JFrame;

/**
 *
 * @author buddh
 */
public class Main {

    public static void main(String args[]) {
        Login frame = new Login();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);  // *** this will center your app ***
        frame.setVisible(true);
    }
}
