package GUIs;

import DAOs.DAOIngredientes;
import DAOs.DAOFornecedor;
import Entidades.Ingredientes;
import Entidades.Fornecedor;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class IngredientesGUI extends JDialog {

    JDialog dialogo = new JDialog();

    Container cp;

    JPanel painelnorte = new JPanel();
    JPanel painelcentro = new JPanel();
    JPanel painelsul = new JPanel();
    JPanel painelleste = new JPanel();
    JPanel paineloeste = new JPanel();

    JLabel lbId = new JLabel("ID Ingredientes");
    JTextField tfId = new JTextField(15);
    JButton botaobuscar = new JButton(" BUSCAR ");
    JButton botaoadicionar = new JButton(" ADICIONAR ");
    JButton botaosalvar = new JButton(" SALVAR ");
    JButton botaoalterar = new JButton(" ALTERAR ");
    JButton botaoexcluir = new JButton(" EXCLUIR ");
    JButton botaolistar = new JButton(" LISTAR ");
    JButton botaocancelar = new JButton(" CANCELAR ");

    // como formatar o codigo : voce aperta { ALT + SHIFT + F }
    JLabel lbNome = new JLabel("Nome do Ingrediente", JLabel.CENTER);
    JTextField tfNome = new JTextField(30);
    JLabel lbFornecedor = new JLabel("ID do Fornecedor", JLabel.CENTER);
    JTextField tffornecedor = new JTextField(30);

    String acao = "";

    String[] colunas = new String[]{" sigla" + " Planeta"};
    String[][] dados = new String[0][6];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JScrollPane scrolltabela = new JScrollPane();

    private final JPanel pnavisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnlistagem = new JPanel(new GridLayout(1, 1));
    private final JPanel pnvazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;
    private String chavePrimaria = "";
    DAOIngredientes daoIngredientes = new DAOIngredientes();
    Ingredientes acompanhamentos = new Ingredientes();
    DAOFornecedor daoFornecedor = new DAOFornecedor();

    DefaultComboBoxModel cbm = new DefaultComboBoxModel();
    JComboBox cbFornecedor = new JComboBox(cbm);

    public IngredientesGUI() {
        dialogo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = dialogo.getContentPane();
        cp.setLayout(new BorderLayout());
        dialogo.setTitle(" Ingredientes - CRUD");

        cp.add(painelnorte, BorderLayout.NORTH);
        cp.add(painelcentro, BorderLayout.CENTER);
        cp.add(painelsul, BorderLayout.SOUTH);
        cp.add(painelleste, BorderLayout.EAST);
        cp.add(paineloeste, BorderLayout.WEST);

        List<Fornecedor> listaFornecedor = daoFornecedor.list();
        listaFornecedor.forEach((trabalhador) -> {
            cbm.addElement(trabalhador.getIdfornecedor() + "-" + trabalhador.getNomefornecedor());
        });
        cbFornecedor.setBorder(BorderFactory.createLineBorder(Color.black));
        cbFornecedor.setFont(new Font("Impact", Font.PLAIN, 22));
        cbFornecedor.setForeground(new Color(0, 96, 55));
        cbFornecedor.setBackground(Color.ORANGE);
        cbFornecedor.setEnabled(false);
        painelleste.setBackground(new Color(240, 190, 0));
        paineloeste.setBackground(new Color(240, 190, 0));
        painelnorte.setBackground(new Color(240, 190, 0));
        painelcentro.setBackground(new Color(238, 173, 45));
        painelsul.setBackground(new Color(240, 190, 0));
        // tipo dos paineis
        // painel norte
        painelnorte.setLayout(new FlowLayout(FlowLayout.CENTER));
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
        painelcentro.setLayout(new GridLayout(2, 2));
        painelcentro.add(lbNome);
        painelcentro.add(tfNome);

        painelcentro.add(lbFornecedor);
        painelcentro.add(cbFornecedor);

        tfNome.setEditable(false);
        tffornecedor.setEditable(false);

        lbNome.setFont(new Font("Arial", Font.BOLD, 18));
        tfNome.setFont(new Font("Arial", Font.BOLD, 18));
        lbFornecedor.setFont(new Font("Arial", Font.BOLD, 18));
        tffornecedor.setFont(new Font("Arial", Font.BOLD, 18));

        // borda dos centro 
        lbNome.setBorder(BorderFactory.createLineBorder(Color.black));
        lbFornecedor.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tfNome.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        tffornecedor.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

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
            cardLayout.show(painelsul, "avisos");
            chavePrimaria = tfId.getText();
            acompanhamentos = daoIngredientes.obter(Integer.valueOf(tfId.getText()));
            if (acompanhamentos != null) {
                botaoalterar.setVisible(true);
                botaoexcluir.setVisible(true);
                botaoadicionar.setVisible(false);
                tfNome.setText(acompanhamentos.getNome());
                tffornecedor.setText(acompanhamentos.getFornecedoridfornecedor().getIdfornecedor()
                        + " --> " + acompanhamentos.getFornecedoridfornecedor().getNomefornecedor());
                tfNome.setEditable(false);
                tffornecedor.setEditable(false);

                botaosalvar.setVisible(false);

                cbFornecedor.setEnabled(false);

                cbFornecedor.setSelectedItem(acompanhamentos.getFornecedoridfornecedor().getIdfornecedor()
                        + "-" + acompanhamentos.getFornecedoridfornecedor().getNomefornecedor());

            } else {
                cbFornecedor.setEnabled(false);
                tfNome.setText("");
                tffornecedor.setText("");
                botaoadicionar.setVisible(true);
                tfNome.setEditable(false);
                tffornecedor.setEditable(false);

                botaosalvar.setVisible(false);
                botaoalterar.setVisible(false);
                botaoexcluir.setVisible(false);

            }
        });

        botaoadicionar.addActionListener((ActionEvent ae) -> {
            tfId.setEditable(false);
            tfNome.setEditable(true);
            tffornecedor.setEditable(true);
            tfNome.requestFocus();
            cbFornecedor.setEnabled(true);
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
                    acompanhamentos = new Ingredientes();
                    Integer id = Integer.valueOf(tfId.getText());
                    acompanhamentos.setIdingredientes(id);
                    acompanhamentos.setNome(tfNome.getText());
                    Integer idfornecedor = Integer.valueOf(String.valueOf(cbFornecedor.getSelectedItem()).split("-")[0]);
                    acompanhamentos.setFornecedoridfornecedor(daoFornecedor.obter(idfornecedor));
                    daoIngredientes.inserir(acompanhamentos);
                } else {
                    Integer id2 = Integer.valueOf(tfId.getText());
                    acompanhamentos.setIdingredientes(id2);
                    acompanhamentos.setNome(tfNome.getText());
                    Integer idfornecedor2 = Integer.valueOf(String.valueOf(cbFornecedor.getSelectedItem()).split("-")[0]);
                    acompanhamentos.setFornecedoridfornecedor(daoFornecedor.obter(idfornecedor2));
                    daoIngredientes.atualizar(acompanhamentos);
                }

                botaosalvar.setVisible(false);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfId.setText("");
                tfNome.setText("");
                tffornecedor.setText("");

                tfNome.setEditable(false);
                cbFornecedor.setEnabled(false);
                tffornecedor.setEditable(false);

                botaobuscar.setVisible(true);
            } catch (NumberFormatException errou) {
                System.out.println("deu bosta ao salvar");
                JOptionPane.showMessageDialog(null, "voce digitou algo estranho", "erro no salvamento", JOptionPane.PLAIN_MESSAGE);
                tfNome.requestFocus();
                tfNome.setText("");
                tffornecedor.setText("");
                cbFornecedor.setEnabled(true);
                botaocancelar.setVisible(true);
                tfNome.setEditable(true);
                tffornecedor.setEditable(true);
            }
        });

        botaoalterar.addActionListener((ActionEvent ae) -> {
            botaolistar.setVisible(false);
            botaoexcluir.setVisible(false);
            botaobuscar.setVisible(false);
            botaoalterar.setVisible(false);
            tfId.setEditable(false);
            tfNome.setEditable(true);
            tffornecedor.setEditable(true);
            cbFornecedor.setEnabled(true);
            tfNome.requestFocus();

            botaosalvar.setVisible(true);
            botaocancelar.setVisible(true);
            acao = "alterar";
        });

        botaoexcluir.addActionListener((ActionEvent ae) -> {
            int resposta = JOptionPane.showConfirmDialog(cp, "Deseja mesmo excluir?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            botaoexcluir.setVisible(false);
            botaoalterar.setVisible(false);
            tfId.setEditable(true);
            tfId.requestFocus();
            tfId.setText("");
            tfNome.setText("");
            tffornecedor.setText("");
            cbFornecedor.setEnabled(false);

            tfNome.setEditable(false);
            tffornecedor.setEditable(false);

            botaobuscar.setVisible(true);
            if (resposta == JOptionPane.YES_OPTION) {
                daoIngredientes.remover(acompanhamentos);
            } else {
                System.out.println(" OS DADOS DO ATLETA NÃO FORAM APAGADOS.");

            }
        });

        botaolistar.addActionListener((ActionEvent ae) -> {
            List<Ingredientes> acompanhamentos1 = daoIngredientes.list();
            String[] colunas1 = new String[]{" id acompanhamentos", " nome ", " id fornecedor"};
            Object[][] dados1 = new Object[acompanhamentos1.size()][colunas1.length];
            String aux[] = null;
            for (int i = 0; i < acompanhamentos1.size(); i++) {
                aux = acompanhamentos1.get(i).toString().split(";");
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
            tfNome.setText("");
            cbFornecedor.setEnabled(false);
            tffornecedor.setText("");

            tfNome.setEditable(false);
            tffornecedor.setEditable(false);

            botaobuscar.setVisible(true);
            botaolistar.setVisible(true);
            botaosalvar.setVisible(false);
        });

        // finaliza o gui
        dialogo.setModal(true);
        dialogo.setSize(900, 600);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }
}
