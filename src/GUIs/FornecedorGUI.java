package GUIs;

import DAOs.DAOFornecedor;
import DAOs.DAOPessoa;
import Entidades.Fornecedor;
import Entidades.Pessoa;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.ParseException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class FornecedorGUI extends JDialog {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    JDialog dialogo = new JDialog();

    Container cp;

    JPanel painelnorte = new JPanel();
    JPanel painelcentro = new JPanel();
    JPanel painelsul = new JPanel();
    JPanel painelleste = new JPanel();
    JPanel paineloeste = new JPanel();

    JLabel lbId = new JLabel("ID Fornecedor");
    JTextField tfId = new JTextField(15);
    JButton botaobuscar = new JButton(" BUSCAR ");
    JButton botaoadicionar = new JButton(" ADICIONAR ");
    JButton botaosalvar = new JButton(" SALVAR ");
    JButton botaoalterar = new JButton(" ALTERAR ");
    JButton botaoexcluir = new JButton(" EXCLUIR ");
    JButton botaolistar = new JButton(" LISTAR ");
    JButton botaocancelar = new JButton(" CANCELAR ");

    // como formatar o codigo : voce aperta { ALT + SHIFT + F }
    JLabel lbNomeFornecedor = new JLabel("NOME DO FORNECEDOR", JLabel.CENTER);
    JTextField tfNomeFornecedor = new JTextField(30);
    JLabel lbInicioDaParceria = new JLabel("INICIO DA PARCERIA", JLabel.CENTER);
    JTextField tfInicioDaParceria = new JTextField(30);
    JLabel lbPorteDaEmpresa = new JLabel("PORTE DA EMPRESA", JLabel.CENTER);
    JTextField tfPorteDaEmpresa = new JTextField(30);

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
    DAOFornecedor daoFornecedor = new DAOFornecedor();
    Fornecedor fornecedor = new Fornecedor();

    public FornecedorGUI() {
        dialogo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = dialogo.getContentPane();
        cp.setLayout(new BorderLayout());
        dialogo.setTitle(" Fornecedor - CRUD");

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
        tfPorteDaEmpresa.setFont(new Font("Arial", Font.BOLD, 16));

        botaobuscar.setBackground(Color.GREEN);
        botaoadicionar.setBackground(Color.WHITE);
        botaosalvar.setBackground(Color.cyan);
        botaoalterar.setBackground(Color.ORANGE);
        botaoexcluir.setBackground(Color.blue);
        botaocancelar.setBackground(Color.red);
        botaolistar.setBackground(Color.LIGHT_GRAY);
        tfId.setBorder(BorderFactory.createLineBorder(Color.black));

        // painel centro
        painelcentro.setLayout(new GridLayout(3, 2));
        painelcentro.add(lbNomeFornecedor);
        painelcentro.add(tfNomeFornecedor);
        painelcentro.add(lbInicioDaParceria);
        painelcentro.add(tfInicioDaParceria);
        painelcentro.add(lbPorteDaEmpresa);
        painelcentro.add(tfPorteDaEmpresa);

        tfNomeFornecedor.setEditable(false);
        tfPorteDaEmpresa.setEditable(false);
        tfInicioDaParceria.setEditable(false);

        lbId.setFont(new Font("Arial", Font.BOLD, 16));
        tfId.setFont(new Font("Arial", Font.BOLD, 16));
        lbNomeFornecedor.setFont(new Font("Arial", Font.BOLD, 18));
        tfNomeFornecedor.setFont(new Font("Arial", Font.BOLD, 18));
        lbInicioDaParceria.setFont(new Font("Arial", Font.BOLD, 18));
        tfInicioDaParceria.setFont(new Font("Arial", Font.BOLD, 18));
        lbPorteDaEmpresa.setFont(new Font("Arial", Font.BOLD, 18));

        // borda dos centro 
        lbNomeFornecedor.setBorder(BorderFactory.createLineBorder(Color.black));
        lbInicioDaParceria.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbPorteDaEmpresa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tfNomeFornecedor.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        tfInicioDaParceria.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        // painel sul
        cardLayout = new CardLayout();
        painelsul.setLayout(cardLayout);

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
            fornecedor = daoFornecedor.obter(Integer.valueOf(tfId.getText()));
            if (fornecedor != null) {
                botaoalterar.setVisible(true);
                botaoexcluir.setVisible(true);
                botaoadicionar.setVisible(false);
                tfNomeFornecedor.setText(String.valueOf(fornecedor.getNomefornecedor()));
                tfInicioDaParceria.setText(simpleDateFormat.format(fornecedor.getInicioDaParceria()));
                tfPorteDaEmpresa.setText(String.valueOf(fornecedor.getPorteDaEmpresa()));
                tfNomeFornecedor.setEditable(false);
                tfPorteDaEmpresa.setEditable(false);
                tfInicioDaParceria.setEditable(false);

                botaosalvar.setVisible(false);

                tfPorteDaEmpresa.setEnabled(false);

            } else {
                tfPorteDaEmpresa.setEnabled(false);
                tfNomeFornecedor.setText("");
                tfInicioDaParceria.setText("");
                tfPorteDaEmpresa.setText("");
                botaoadicionar.setVisible(true);
                tfNomeFornecedor.setEditable(false);
                tfPorteDaEmpresa.setEditable(false);
                tfInicioDaParceria.setEditable(false);

                botaosalvar.setVisible(false);
                botaoalterar.setVisible(false);
                botaoexcluir.setVisible(false);

            }
        });

        botaoadicionar.addActionListener((ActionEvent ae) -> {
            tfId.setEditable(false);
            tfNomeFornecedor.setEditable(true);
            tfPorteDaEmpresa.setEditable(true);
            tfInicioDaParceria.setEditable(true);
            tfNomeFornecedor.requestFocus();
            tfPorteDaEmpresa.setEnabled(true);
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
                    fornecedor = new Fornecedor();
                    Integer id = Integer.valueOf(tfId.getText());

                    fornecedor.setIdfornecedor(id);
                    fornecedor.setNomefornecedor(tfNomeFornecedor.getText());
                    try {
                        fornecedor.setInicioDaParceria(simpleDateFormat.parse(tfInicioDaParceria.getText()));
                    } catch (ParseException ex) {
                        System.out.println("deu bosta na data");
                    }
                    fornecedor.setPorteDaEmpresa(tfPorteDaEmpresa.getText());
                    daoFornecedor.inserir(fornecedor);
                } else {
                    Integer id = Integer.valueOf(tfId.getText());

                    fornecedor.setIdfornecedor(id);
                    fornecedor.setNomefornecedor(tfNomeFornecedor.getText());
                    try {
                        fornecedor.setInicioDaParceria(simpleDateFormat.parse(tfInicioDaParceria.getText()));
                    } catch (ParseException ex) {
                        System.out.println("deu bosta na data");
                    }
                    fornecedor.setPorteDaEmpresa(tfPorteDaEmpresa.getText());
                    daoFornecedor.atualizar(fornecedor);
                }

                botaosalvar.setVisible(false);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfId.setText("");
                tfNomeFornecedor.setText("");
                tfInicioDaParceria.setText("");
                tfPorteDaEmpresa.setText("");

                tfNomeFornecedor.setEditable(false);
                tfPorteDaEmpresa.setEditable(false);
                tfPorteDaEmpresa.setEnabled(false);
                tfInicioDaParceria.setEditable(false);

                botaobuscar.setVisible(true);
            } catch (NumberFormatException errou) {
                System.out.println("deu bosta ao salvar");
                JOptionPane.showMessageDialog(null, "voce digitou algo estranho", "erro no salvamento", JOptionPane.PLAIN_MESSAGE);
                tfNomeFornecedor.requestFocus();
                tfNomeFornecedor.setText("");
                tfInicioDaParceria.setText("");
                tfPorteDaEmpresa.setText("");
                tfPorteDaEmpresa.setEnabled(true);
                botaocancelar.setVisible(true);
                tfNomeFornecedor.setEditable(true);
                tfPorteDaEmpresa.setEditable(true);
                tfInicioDaParceria.setEditable(true);
            }
        });

        botaoalterar.addActionListener((ActionEvent ae) -> {
            botaolistar.setVisible(false);
            botaoexcluir.setVisible(false);
            botaobuscar.setVisible(false);
            botaoalterar.setVisible(false);
            tfId.setEditable(false);
            tfNomeFornecedor.setEditable(true);
            tfPorteDaEmpresa.setEditable(true);
            tfInicioDaParceria.setEditable(true);
            tfPorteDaEmpresa.setEnabled(true);
            tfNomeFornecedor.requestFocus();

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
            tfNomeFornecedor.setText("");
            tfInicioDaParceria.setText("");
            tfPorteDaEmpresa.setText("");
            tfPorteDaEmpresa.setEnabled(false);

            tfNomeFornecedor.setEditable(false);
            tfPorteDaEmpresa.setEditable(false);
            tfInicioDaParceria.setEditable(false);

            botaobuscar.setVisible(true);
            if (resposta == JOptionPane.YES_OPTION) {
                daoFornecedor.remover(fornecedor);
            } else {
                System.out.println(" OS DADOS DO ATLETA NÃO FORAM APAGADOS.");

            }
        });

        botaolistar.addActionListener((ActionEvent ae) -> {
            List<Fornecedor> fornecedor1 = daoFornecedor.list();
            String[] colunas1 = new String[]{" id fornecedor", " INICIO PARCERIA ", "NOME do FORNECEDOR", " PORTE DA EMPRESA"};
            Object[][] dados1 = new Object[fornecedor1.size()][colunas1.length];
            String aux[];
            for (int i = 0; i < fornecedor1.size(); i++) {
                aux = fornecedor1.get(i).toString().split(";");
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
            tfNomeFornecedor.setText("");
            tfPorteDaEmpresa.setEnabled(false);
            tfInicioDaParceria.setText("");
            tfPorteDaEmpresa.setText("");

            tfNomeFornecedor.setEditable(false);
            tfPorteDaEmpresa.setEditable(false);
            tfInicioDaParceria.setEditable(false);

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
