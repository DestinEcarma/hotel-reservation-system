/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared;

import javax.swing.JFrame;

/**
 *
 * @author maker
 */
public class Session {

    public static String token = null;

    public static void Logout(JFrame frame) {
        Session.token = null;
        frame.dispose();

        new main.Login().setVisible(true);
    }
}
