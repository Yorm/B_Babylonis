
package runner;

import java.util.logging.Level;
import java.util.logging.Logger;
import x.gui.MainWindow;


public class Runner {
    public static void main(String[] args) {      
        try {
            MainWindow mainWindow= new MainWindow();
        }catch(Exception ex) { ex.printStackTrace();}     
    }
}