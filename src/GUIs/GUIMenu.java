/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public class GUIMenu  extends JFrame {
    // variaveis
    private final Container cp;
    private final JPanel painelnorte = new JPanel();
    private final JPanel painelcentro = new JPanel();
    private final JPanel painelsul = new JPanel();
    private final JPanel painelleste = new JPanel();
    private final JPanel paineloeste = new JPanel();
    
    private final JMenuBar menu = new JMenuBar();
    
    private final JMenu menu1 = new JMenu("Parte de Estoque");
    private final JMenu menu2 = new JMenu("Parte de Pessoas");
    private final JMenu menu3 = new JMenu("Parte de Venda/Compra");
    // estoque
    private final JMenuItem pizza = new JMenuItem("Pizza");
    private final JMenuItem ingredientesNaPizza = new JMenuItem("ingredientes Na Pizza");
    private final JMenuItem ingredientes = new JMenuItem("Ingredientes");
    private final JMenuItem acompanhamentos = new JMenuItem("Acompanhamentos");
    
    // venda
    private final JMenuItem fornecedor = new JMenuItem("Fornecedores");
    private final JMenuItem Pedidos = new JMenuItem("Pedidos");
    private final JMenuItem pizzasDoPedido = new JMenuItem("pizzas Do Pedido");
    
    // pessoa
    private final JMenuItem pessoa = new JMenuItem("Pessoa");
    private final JMenuItem cliente = new JMenuItem("Cliente");
    private final JMenuItem funcionario = new JMenuItem("Funcionario");
    private final JMenuItem cargos = new JMenuItem("Cargos");
    
    
    // 4 na parte de pessoa 3 na parte de Venda/compra e 4 na parte de estoque
    
    public GUIMenu() throws IOException{
        //montando interface
        //imagem
        BufferedImage bufferedImage = ImageIO.read(
                new File
        ("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\zzzCRUD-LP3-SEGUNDO-BIMESTRE\\src\\GUIs\\menu-logo-lp3.jpg"));   
        Image imagem = bufferedImage.getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(imagem);
        JLabel imagemilustrativa = new JLabel(icon);
        // menu 
        setJMenuBar(menu);
        menu.setLayout(new GridBagLayout());
        menu.add(menu1);
        menu.add(menu2);
        menu.add(menu3);
        menu.setBackground(new Color(253,219,112));
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        menu1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        menu2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        menu3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        menu1.setFont(new Font("Impact", Font.PLAIN, 22));
        menu2.setFont(new Font("Impact", Font.PLAIN, 22));
        menu3.setFont(new Font("Impact", Font.PLAIN, 22));
        
        
        // estoque
        menu1.add(pizza);
        menu1.add(ingredientesNaPizza);
        menu1.add(ingredientes);
        menu1.add(acompanhamentos);
        // venda
        menu3.add(fornecedor);
        menu3.add(Pedidos);
        menu3.add(pizzasDoPedido);
        
        //pessoa
        menu2.add(pessoa);
        menu2.add(cliente);
        menu2.add(funcionario);
        menu2.add(cargos);
        
        // gui
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        setTitle("      MENU PRINCIPAL. ");
        
        cp.add(painelnorte, BorderLayout.NORTH);
        cp.add(painelcentro, BorderLayout.CENTER);
        cp.add(painelsul, BorderLayout.SOUTH);
        cp.add(painelleste, BorderLayout.EAST);
        cp.add(paineloeste, BorderLayout.WEST);
        
        painelleste.setBackground(new Color (238,173,45));
        paineloeste.setBackground(new Color (238,173,45));
        painelnorte.setBackground(new Color (238,173,45));
        painelcentro.setBackground(new Color (238,173,45));
        painelsul.setBackground(new Color (238,173,45));
        
        // botao comida
        pizza.addActionListener((ActionEvent ae) -> {
            PizzaGUI pizzaGUI = new PizzaGUI();
        });
        
        ingredientesNaPizza.addActionListener((ActionEvent ae) -> {
            PizzaIngredienteNpraMGUI pizzaIngredienteNpraMGUI = new PizzaIngredienteNpraMGUI();
        });
        
        ingredientes.addActionListener((ActionEvent ae) -> {
            IngredientesGUI ingredientesGUI = new IngredientesGUI();
        });
        
        acompanhamentos.addActionListener((ActionEvent ae) -> {
            AcompanhamentosGUI acompanhamentosGUI = new AcompanhamentosGUI();
        });
        // venda
        fornecedor.addActionListener((ActionEvent ae) -> {
            FornecedorGUI fornecedorGUI = new FornecedorGUI();
        });
        
        Pedidos.addActionListener((ActionEvent ae) -> {
            PedidoGUI pedidoGUI = new PedidoGUI();
        });
        
        pizzasDoPedido.addActionListener((ActionEvent ae) -> {
            PedidoDaPizzaGUI pedidoDaPizzaGUI = new PedidoDaPizzaGUI();
        });
        // pessoa
        pessoa.addActionListener((ActionEvent ae) -> {
            PessoaGUI pessoaGUI = new PessoaGUI();
        });
        
        cliente.addActionListener((ActionEvent ae) -> {
            ClienteGUI clienteGUI = new ClienteGUI();
        });
        
        funcionario.addActionListener((ActionEvent ae) -> {
            FuncionarioGUI funcionarioGUI = new FuncionarioGUI();
        });
        
        cargos.addActionListener((ActionEvent ae) -> {
            CargosGUI cargosGUI = new CargosGUI();
        });
        
        // fim
        painelnorte.setLayout(new FlowLayout((FlowLayout.CENTER)));
        
        
        painelcentro.setLayout(new GridLayout(1,1));
        painelcentro.add(imagemilustrativa);
        
        
       painelsul.setLayout(new FlowLayout((FlowLayout.CENTER)));
        
        
        
        
        setSize(564,580);        
        setLocationRelativeTo(null);
        setVisible(true);
        
        
        
    
    }
    
}
