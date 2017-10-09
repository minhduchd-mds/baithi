/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarmi;

import com.sun.istack.internal.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javarmi.PhuongThucTuXa;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author Duc
 */
public class ServerRMI extends JFrame {

    private JTextField text;
    private JLabel kinometer;
    private JLabel milies;
    private JButton start, stop, exit;

    public ServerRMI() {
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setTitle("Server RMI");

        this.text = new JTextField();
        this.start = new JButton("Start");
        this.stop = new JButton("Stop");
        this.exit = new JButton("Exit");

        this.text.setBounds(0, 0, 300, 100);
        this.start.setBounds(30, 100, 70, 25);
        this.stop.setBounds(120, 100, 70, 25);
        this.exit.setBounds(200, 100, 70, 25);

        this.add(this.text);
        this.add(this.start);
        this.add(this.stop);
        this.add(this.exit);

        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.start.addActionListener(new Strat());
        this.stop.addActionListener(new Stop());
        this.exit.addActionListener(new Exit());
    }

    class Strat implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                LocateRegistry.createRegistry(9000);
                PhuongThucTuXa tuxa = new PhuongThucTuXa();
                Naming.rebind("rmi://localhost:9000/cong", tuxa);
                text.setText("Server ready...");
            } catch (RemoteException | MalformedURLException ex) {
                ex.printStackTrace();
                
            }
        }

    }

    class Stop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                Naming.unbind("rmi://localhost:9000/cong");
                text.setText("Stop ready...");
            } catch (RemoteException | MalformedURLException | NotBoundException ex) {
                ex.printStackTrace();
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
        ServerRMI run = new ServerRMI();
        run.setVisible(true);
    }

}
