/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * to change this license Click
 * nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this
 * template
 */
package shared;

import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author maker
 */
public class Defs {
  public static final Dotenv dotenv;

  public static final DefaultTableCellRenderer centerRenderer =
      new DefaultTableCellRenderer();

  static {
    // dotenv = Dotenv.configure().directory("path\\hotel-reservation-system\\.env").load(); // Uncomment this to fix the Dashboard, also adjust the path C:\\...\\.env.
                                                                                             // You only need to uncomment this once and run the file and comment it
                                                                                             // back; that should fix the error. The reason being that it is running
                                                                                             // the files that use this class, and it is trying to look for a .env file.
    dotenv = Dotenv.load();

    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
  }
}
