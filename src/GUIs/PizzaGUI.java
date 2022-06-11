package GUIs;

import DAOs.DAOPizza;
import Entidades.Pizza;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
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
import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tools.UsarGridBagLayout;

public class PizzaGUI extends JDialog {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    JDialog dialogo = new JDialog();

    Container cp;

    JPanel painelnorte = new JPanel();
    JPanel painelcentro = new JPanel();
    JPanel painelsul = new JPanel();
    JPanel painelleste = new JPanel();
    JPanel paineloeste = new JPanel();

    JLabel lbId = new JLabel("ID Pizza");
    JTextField tfId = new JTextField(15);
    JButton botaobuscar = new JButton(" BUSCAR ");
    JButton botaoadicionar = new JButton(" ADICIONAR ");
    JButton botaosalvar = new JButton(" SALVAR ");
    JButton botaoalterar = new JButton(" ALTERAR ");
    JButton botaoexcluir = new JButton(" EXCLUIR ");
    JButton botaolistar = new JButton(" LISTAR ");
    JButton botaocancelar = new JButton(" CANCELAR ");

    // como formatar o codigo : voce aperta { ALT + SHIFT + F }
    JLabel lbnomepizza = new JLabel("nomepizza", JLabel.CENTER);
    JTextField tfnomepizza = new JTextField(30);
    JLabel lbsabor = new JLabel("sabor", JLabel.CENTER);
    JTextField tfsabor = new JTextField(30);
    JLabel lbpreco = new JLabel("preco", JLabel.CENTER);
    JTextField tfpreco = new JTextField(30);
    JLabel lbtamanho = new JLabel("tamanho", JLabel.CENTER);
    //JTextField tftamanho = new JTextField(30);
    DefaultComboBoxModel cbm = new DefaultComboBoxModel();
    JComboBox cbTamanho = new JComboBox(cbm);

    String acao = "";

    String[] colunas = new String[]{};
    String[][] dados = new String[0][6];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JScrollPane scrolltabela = new JScrollPane();

    private final JPanel pnavisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnlistagem = new JPanel(new GridLayout(1, 1));
    private final JPanel pnvazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;
    private String chavePrimaria = "";
    DAOPizza daoPizza = new DAOPizza();
    Pizza pizza = new Pizza();

    UsarGridBagLayout usarGridBagLayout = new UsarGridBagLayout(painelcentro);

    public PizzaGUI() {
        dialogo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = dialogo.getContentPane();
        cp.setLayout(new BorderLayout());
        dialogo.setTitle(" Pizza - CRUD");

        cp.add(painelnorte, BorderLayout.NORTH);
        cp.add(painelcentro, BorderLayout.CENTER);
        cp.add(painelsul, BorderLayout.SOUTH);
        cp.add(painelleste, BorderLayout.EAST);
        cp.add(paineloeste, BorderLayout.WEST);

        painelleste.setBackground(new Color(240, 190, 0));
        paineloeste.setBackground(new Color(240, 190, 0));
        painelnorte.setBackground(new Color(240, 190, 0));
        painelcentro.setBackground(new Color(238, 173, 45));
        painelsul.setBackground(new Color(240, 190, 0));
        // combo add
        cbm.addElement("4 Pedaços");
        cbm.addElement("6 Pedaços");
        cbm.addElement("8 Pedaços");
        cbm.addElement("12 Pedaços");
        cbTamanho.setBorder(BorderFactory.createLineBorder(Color.black));
        cbTamanho.setFont(new Font("Impact", Font.PLAIN, 22));
        cbTamanho.setForeground(new Color(0, 96, 55));
        cbTamanho.setBackground(Color.ORANGE);
        cbTamanho.setEnabled(false);

        // tipo dos paineis
        // painel norte
        painelnorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        painelnorte.add(lbId);
        painelnorte.add(tfId);
        painelnorte.add(botaobuscar);
        painelnorte.add(botaosalvar);
        painelnorte.add(botaolistar);
        painelnorte.add(botaoadicionar);
        painelnorte.add(botaocancelar);
        painelnorte.add(botaoalterar);
        painelnorte.add(botaoexcluir);

        botaoadicionar.setVisible(false);
        botaosalvar.setVisible(false);
        botaoalterar.setVisible(false);
        botaocancelar.setVisible(false);
        botaoexcluir.setVisible(false);
        // cor dos botoes

        lbId.setFont(new Font("Arial", Font.BOLD, 16));
        tfId.setFont(new Font("Arial", Font.BOLD, 16));

        botaobuscar.setBackground(Color.GREEN);
        botaoadicionar.setBackground(Color.WHITE);
        botaosalvar.setBackground(Color.cyan);
        botaoalterar.setBackground(Color.ORANGE);
        botaoexcluir.setBackground(Color.blue);
        botaocancelar.setBackground(Color.red);
        botaolistar.setBackground(Color.LIGHT_GRAY);
        tfId.setBorder(BorderFactory.createLineBorder(Color.black));

        // painel centro
        painelcentro.setLayout(new GridBagLayout());
        usarGridBagLayout.add(lbnomepizza, tfnomepizza);
        usarGridBagLayout.add(lbsabor, tfsabor);
        usarGridBagLayout.add(lbpreco, tfpreco);
        usarGridBagLayout.add(lbtamanho, cbTamanho);

        tfpreco.setEditable(false);
        tfnomepizza.setEditable(false);
        cbTamanho.setEnabled(false);
        tfsabor.setEditable(false);

        lbpreco.setFont(new Font("Arial", Font.BOLD, 20));
        tfpreco.setFont(new Font("Arial", Font.BOLD, 18));
        tfnomepizza.setFont(new Font("Arial", Font.BOLD, 18));
        tfsabor.setFont(new Font("Arial", Font.BOLD, 18));
        lbsabor.setFont(new Font("Arial", Font.BOLD, 20));
        lbtamanho.setFont(new Font("Arial", Font.BOLD, 20));
        lbnomepizza.setFont(new Font("Arial", Font.BOLD, 20));

        // borda dos centro 
        tfpreco.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        tfnomepizza.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        cbTamanho.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        tfsabor.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        // painel sul
        cardLayout = new CardLayout();
        painelsul.setLayout(cardLayout);

        // borda paineis
        painelsul.setBorder(BorderFactory.createLineBorder(Color.black));
        painelcentro.setBorder(BorderFactory.createLineBorder(Color.black));

        pnavisos.setBackground(new Color(240, 190, 0));
        pnlistagem.setBackground(new Color(240, 190, 0));
        pnvazio.setBackground(new Color(240, 190, 0));

        for (int i = 0; i < 5; i++) {
            pnvazio.add(new JLabel(" "));
        }
        painelsul.add(pnvazio, "vazio");
        painelsul.add(pnlistagem, "listagem");
        painelsul.add(pnavisos, "avisos");
        tabela.setEnabled(false);

        pnavisos.add(new JLabel("avisos"));

        paineloeste.setLayout(new FlowLayout(FlowLayout.LEFT));
        painelleste.setLayout(new FlowLayout(FlowLayout.LEFT));

        // funcionalidade dos botoes.
        botaobuscar.addActionListener((ActionEvent ae) -> {
            try {
                cardLayout.show(painelsul, "avisos");
                chavePrimaria = tfId.getText();
                pizza = daoPizza.obter(Integer.valueOf(tfId.getText()));
                if (pizza != null) {
                    botaoalterar.setVisible(true);
                    botaoexcluir.setVisible(true);
                    botaoadicionar.setVisible(false);
                    tfnomepizza.setText(pizza.getNomepizza());
                    cbTamanho.setSelectedItem(pizza.getTamanho());
                    tfsabor.setText(pizza.getSabor());
                    tfpreco.setText(String.valueOf(pizza.getPreco()));
                    tfpreco.setEditable(false);
                    botaosalvar.setVisible(false);
                } else {
                    tfpreco.setText("");
                    tfnomepizza.setText("");
                    tfsabor.setText("");
                    botaoadicionar.setVisible(true);
                    tfpreco.setEditable(false);
                    tfnomepizza.setEditable(false);
                    cbTamanho.setEnabled(false);
                    tfsabor.setEditable(false);
                    botaosalvar.setVisible(false);
                    botaoalterar.setVisible(false);
                    botaoexcluir.setVisible(false);
                }
            } catch (Exception e) {
                System.out.println("deu bosta ao salvar");
                tfId.setText("");
                tfId.requestFocus();
                JOptionPane.showMessageDialog(null, "voce pesquisou algo estranho", "erro no buscamento", JOptionPane.PLAIN_MESSAGE);
                tfpreco.setText("");
                tfnomepizza.setText("");
                tfsabor.setText("");
                botaoadicionar.setVisible(false);
                tfpreco.setEditable(false);
                tfnomepizza.setEditable(false);
                cbTamanho.setEnabled(false);
                tfsabor.setEditable(false);
                botaosalvar.setVisible(false);
                botaoalterar.setVisible(false);
                botaoexcluir.setVisible(false);
            }
        });

        botaoadicionar.addActionListener((ActionEvent ae) -> {
            tfId.setEditable(false);
            tfnomepizza.requestFocus();
            tfpreco.setEditable(true);
            tfnomepizza.setEditable(true);
            cbTamanho.setEnabled(true);
            tfsabor.setEditable(true);
            botaoadicionar.setVisible(false);
            botaosalvar.setVisible(true);
            botaobuscar.setVisible(false);
            botaocancelar.setVisible(true);
            botaolistar.setVisible(false);
            acao = "adicionar";
        });

        botaosalvar.addActionListener((ActionEvent ae) -> {
            try {
                botaoalterar.setVisible(false);
                botaoexcluir.setVisible(false);
                botaocancelar.setVisible(false);
                botaolistar.setVisible(true);

                if (acao.equals("adicionar")) {
                    pizza = new Pizza();
                    Integer id = Integer.valueOf(tfId.getText());
                    Double altura = Double.valueOf(tfpreco.getText());
                    String cpf = tfsabor.getText();
                    String tamanho = String.valueOf(cbTamanho.getSelectedItem());
                    String nomepizza = tfnomepizza.getText();
                    pizza.setIdtable1(id);
                    pizza.setNomepizza(nomepizza);
                    pizza.setPreco(altura);
                    pizza.setSabor(cpf);
                    pizza.setTamanho(tamanho);

                    daoPizza.inserir(pizza);
                } else {
                    Integer id = Integer.valueOf(tfId.getText());
                    Double altura = Double.valueOf(tfpreco.getText());
                    String cpf = tfsabor.getText();
                    String tamanho = String.valueOf(cbTamanho.getSelectedItem());
                    String nomepizza = tfnomepizza.getText();
                    pizza.setIdtable1(id);
                    pizza.setNomepizza(nomepizza);
                    pizza.setPreco(altura);
                    pizza.setSabor(cpf);
                    pizza.setTamanho(tamanho);
                    daoPizza.atualizar(pizza);
                }
                botaosalvar.setVisible(false);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfId.setText("");
                tfpreco.setText("");
                tfnomepizza.setText("");
                tfsabor.setText("");
                tfpreco.setEditable(false);
                tfnomepizza.setEditable(false);
                cbTamanho.setEnabled(false);
                tfsabor.setEditable(false);
                botaobuscar.setVisible(true);
            } catch (NumberFormatException errou) {
                System.out.println("deu bosta ao salvar");
                JOptionPane.showMessageDialog(null, "voce digitou algo estranho", "erro no salvamento", JOptionPane.PLAIN_MESSAGE);
                botaocancelar.setVisible(true);
                tfpreco.setEditable(true);
                tfnomepizza.setEditable(true);
                cbTamanho.setEnabled(true);
                tfsabor.setEditable(true);
            }
        });

        botaoalterar.addActionListener((ActionEvent ae) -> {
            botaolistar.setVisible(false);
            botaoexcluir.setVisible(false);
            botaobuscar.setVisible(false);
            botaoalterar.setVisible(false);
            tfId.setEditable(false);
            tfnomepizza.requestFocus();
            tfpreco.setEditable(true);
            tfnomepizza.setEditable(true);
            cbTamanho.setEnabled(true);
            tfsabor.setEditable(true);
            botaosalvar.setVisible(true);
            botaocancelar.setVisible(true);
            acao = "alterar";
        });

        botaoexcluir.addActionListener((ActionEvent ae) -> {
            int resposta = JOptionPane.showConfirmDialog(cp, "Deseja mesmo excluir?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            botaobuscar.setVisible(true);
            if (resposta == JOptionPane.YES_OPTION) {
                botaoexcluir.setVisible(false);
                botaoalterar.setVisible(false);
                tfId.setEditable(true);
                botaobuscar.setVisible(true);
                tfId.requestFocus();
                tfId.setText("");
                tfpreco.setText("");
                tfnomepizza.setText("");
                tfsabor.setText("");
                tfpreco.setEditable(false);
                tfnomepizza.setEditable(false);
                cbTamanho.setEnabled(false);
                tfsabor.setEditable(false);
                daoPizza.remover(pizza);
            } else {
                System.out.println(" OS DADOS DO ATLETA NÃO FORAM APAGADOS.");
            }
        });

        botaolistar.addActionListener((ActionEvent ae) -> {
            List<Pizza> pizza1 = daoPizza.list();
            String[] colunas1 = new String[]{"id", "nomepizza", "sabor", "preco", "tamanho"};
            Object[][] dados1 = new Object[pizza1.size()][colunas1.length];
            String aux[];
            for (int i = 0; i < pizza1.size(); i++) {
                aux = pizza1.get(i).toString().split(";");
                System.arraycopy(aux, 0, dados1[i], 0, colunas1.length);
            }
            cardLayout.show(painelsul, "listagem");
            scrolltabela.setPreferredSize(tabela.getPreferredSize());
            pnlistagem.add(scrolltabela);
            scrolltabela.setViewportView(tabela);
            model.setDataVector(dados1, colunas1);
        });

        botaocancelar.addActionListener((ActionEvent ae) -> {
            botaocancelar.setVisible(false);
            tfId.setEditable(true);
            tfId.requestFocus();
            tfId.setText("");
            tfpreco.setText("");
            tfnomepizza.setText("");
            tfsabor.setText("");
            tfpreco.setEditable(false);
            tfnomepizza.setEditable(false);
            cbTamanho.setEnabled(false);
            tfsabor.setEditable(false);
            botaobuscar.setVisible(true);
            botaolistar.setVisible(true);
            botaosalvar.setVisible(false);
        });
        // finaliza o gui
        dialogo.setModal(true);
        dialogo.setSize(1000, 600);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }
}
