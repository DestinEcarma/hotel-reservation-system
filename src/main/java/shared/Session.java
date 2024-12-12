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
    public static int userId = 0;
    
    public static void Logout(JFrame frame) {
        Session.userId = 0;
        frame.dispose();
        
        new main.Login().setVisible(true);
    }
}
