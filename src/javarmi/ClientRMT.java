/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarmi;

import com.sun.istack.internal.logging.Logger;
import java.awt.HeadlessException;
import static java.awt.SystemColor.text;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Duc
 */
public class ClientRMT extends JFrame{
    private JTextField txtkinometter,txtmiles;
    private JLabel milies,kinometers;
    private JButton calculating, exit; 

    
    
    
    public ClientRMT(){
        this.setSize(450, 250);
        this.setLocationRelativeTo(null);
        this.setTitle("Caculator client");
        
        this.txtkinometter = new JTextField();
        this.txtmiles = new JTextField();
        this.kinometers = new JLabel("Kilometers");
        this.milies= new JLabel("Miles");
        this.calculating =  new JButton("Calculating");
        this.exit =  new JButton("Exit");
        
        
        this.kinometers.setBounds(40, 30, 100, 25);
        this.txtkinometter.setBounds(120, 30, 200, 25);
        this.milies.setBounds(40, 70, 100, 25);
        this.txtmiles.setBounds(120, 70, 200, 25);
        this.calculating.setBounds(120, 100, 110, 25);
        this.exit.setBounds(250, 100, 70, 25);
        
        this.add(this.kinometers);
        this.add(this.milies);
        this.add(this.txtkinometter);
        this.add(this.txtmiles);
        this.add(this.calculating);
        this.add(this.exit);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.calculating.addActionListener(new Calculating());
        this.exit.addActionListener(new Exit());
    }
    
   
    class Calculating implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           try {
             
             KhuonMauTuXa khuon = (KhuonMauTuXa) Naming.lookup("rmi://localhost:9000/cong");
             float mile =  khuon.chuyendoi(Float.parseFloat(txtkinometter.getText()));  // 
             txtmiles.setText(mile + "");
             
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            java.util.logging.Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }
    
    class Exit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int hoi = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?",
                        null, JOptionPane.YES_NO_OPTION);
                if (hoi == JOptionPane.YES_OPTION) {
                    System.exit(0);
        }
    }
     }
    public static void main(String[] args) {
        ClientRMT r = new ClientRMT();
       r.setVisible(true);
       
    }
    
    
}

