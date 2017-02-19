/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.daffodilvarsity.studentdatabase;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Mushfiqus Salehin
 */
public class MainFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Database");
        AddPanel panel = new AddPanel();
        frame.add(panel);
        frame.getContentPane().setPreferredSize(new Dimension(870, 620));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
