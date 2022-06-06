/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GUIs.GUIMenu;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            GUIMenu guiMenu = new GUIMenu();
        } catch (IOException ex) {
            System.out.println("deu alguma merda nas importações. !!!!");
        }
    }
    
}
