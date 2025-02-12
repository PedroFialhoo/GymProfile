/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

/**
 *
 * @author aluno
 */
import telas.TelaInicial;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      TelaInicial inicial = new TelaInicial();
      inicial.setVisible(true);
      inicial.setExtendedState(inicial.MAXIMIZED_BOTH);
    }
    
}
