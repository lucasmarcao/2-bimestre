package GUIs;


import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class PedidoDaPizzaGUI extends JDialog{
    Container cp;
    
    JPanel painelnorte = new JPanel();
    JPanel painelcentro = new JPanel();
    JPanel painelsul = new JPanel();
    JPanel painelleste = new JPanel();
    JPanel paineloeste = new JPanel();
    
    private CardLayout cardLayout;
    
    public PedidoDaPizzaGUI(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle(" PEDIDO DA PIZZA - CRUD");
        
        cp.add(painelnorte, BorderLayout.NORTH);
        cp.add(painelcentro, BorderLayout.CENTER);
        cp.add(painelsul, BorderLayout.SOUTH);
        cp.add(painelleste, BorderLayout.EAST);
        cp.add(paineloeste, BorderLayout.WEST);
        
        painelleste.setBackground(new Color (240,190,0));
        paineloeste.setBackground(new Color (240,190,0));
        painelnorte.setBackground(new Color (240,190,0));
        painelcentro.setBackground(new Color (238,173,45));
        painelsul.setBackground(new Color (240,190,0));
        // tipo dos paineis
        painelnorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        painelcentro.setLayout(new GridLayout(2,2));
        
        painelsul.setLayout(cardLayout);
        
        paineloeste.setLayout(new FlowLayout(FlowLayout.LEFT));
        painelleste.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        
        // finaliza o gui
        
        setModal(true);
        setSize(900,600);        
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
