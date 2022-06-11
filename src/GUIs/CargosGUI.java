package GUIs;

import DAOs.DAOCargos;
import Entidades.Cargos;
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
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class CargosGUI extends JDialog {

    Container cp;

    JDialog dialogo = new JDialog();

    JPanel painelnorte = new JPanel();
    JPanel painelcentro = new JPanel();
    JPanel painelsul = new JPanel();
    JPanel painelleste = new JPanel();
    JPanel paineloeste = new JPanel();

    JLabel lbId = new JLabel("ID cargo");
    JTextField tfId = new JTextField(15);
    JButton botaobuscar = new JButton(" BUSCAR ");
    JButton botaoadicionar = new JButton(" ADICIONAR ");
    JButton botaosalvar = new JButton(" SALVAR ");
    JButton botaoalterar = new JButton(" ALTERAR ");
    JButton botaoexcluir = new JButton(" EXCLUIR ");
    JButton botaolistar = new JButton(" LISTAR ");
    JButton botaocancelar = new JButton(" CANCELAR ");

    // como formatar o codigo : voce aperta { ALT + SHIFT + F }
    JLabel lbNome = new JLabel("Nome do Cargo", JLabel.CENTER);
    JTextField tfNome = new JTextField(30);
    JLabel lPlanetaDoAlien = new JLabel("Descricao do cargo", JLabel.CENTER);
    JTextArea tfdescricao = new JTextArea();

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
    DAOCargos daoCargos = new DAOCargos();
    Cargos cargos = new Cargos();

    DefaultComboBoxModel cbm = new DefaultComboBoxModel();
    JComboBox planetaDoAlien = new JComboBox(cbm);

    public CargosGUI() {
        dialogo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = dialogo.getContentPane();
        cp.setLayout(new BorderLayout());
        dialogo.setTitle(" Cargos - CRUD");

        cp.add(painelnorte, BorderLayout.NORTH);
        cp.add(painelcentro, BorderLayout.CENTER);
        cp.add(painelsul, BorderLayout.SOUTH);
        cp.add(painelleste, BorderLayout.EAST);
        cp.add(paineloeste, BorderLayout.WEST);

        planetaDoAlien.setBorder(BorderFactory.createLineBorder(Color.black));
        planetaDoAlien.setFont(new Font("Impact", Font.PLAIN, 22));
        planetaDoAlien.setForeground(new Color(0, 96, 55));
        planetaDoAlien.setBackground(Color.ORANGE);
        planetaDoAlien.setEnabled(false);
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

        botaobuscar.setBackground(Color.GREEN);
        botaoadicionar.setBackground(Color.WHITE);
        botaosalvar.setBackground(Color.cyan);
        botaoalterar.setBackground(Color.ORANGE);
        botaoexcluir.setBackground(Color.blue);
        botaocancelar.setBackground(Color.red);
        botaolistar.setBackground(Color.LIGHT_GRAY);
        tfId.setBorder(BorderFactory.createLineBorder(Color.black));

        // painel centro
        painelcentro.setLayout(new GridLayout(4, 1));
        painelcentro.add(lbNome);
        painelcentro.add(tfNome);

        painelcentro.add(lPlanetaDoAlien);
        painelcentro.add(tfdescricao);

        tfNome.setEditable(false);
        tfdescricao.setEditable(false);

        lbNome.setFont(new Font("Algerian", Font.BOLD, 18));
        tfNome.setFont(new Font("Arial", Font.BOLD, 18));
        lPlanetaDoAlien.setFont(new Font("Arial", Font.BOLD, 18));
        tfdescricao.setFont(new Font("Arial", Font.BOLD, 18));

        // borda dos centro 
        lbNome.setBorder(BorderFactory.createLineBorder(Color.black));
        lPlanetaDoAlien.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tfNome.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        tfdescricao.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

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
            try {
                cardLayout.show(painelsul, "avisos");
                chavePrimaria = tfId.getText();
                cargos = daoCargos.obter(Integer.valueOf(tfId.getText()));
                if (cargos != null) {
                    botaoalterar.setVisible(true);
                    botaoexcluir.setVisible(true);
                    botaoadicionar.setVisible(false);
                    tfNome.setText(cargos.getNomecargo());
                    tfdescricao.setText(cargos.getDescricaoCargo());
                    tfNome.setEditable(false);
                    tfdescricao.setEditable(false);

                    botaosalvar.setVisible(false);

                    planetaDoAlien.setEnabled(false);

                } else {
                    planetaDoAlien.setEnabled(false);
                    tfNome.setText("");
                    tfdescricao.setText("");
                    botaoadicionar.setVisible(true);
                    tfNome.setEditable(false);
                    tfdescricao.setEditable(false);

                    botaosalvar.setVisible(false);
                    botaoalterar.setVisible(false);
                    botaoexcluir.setVisible(false);

                }
            } catch (Exception e) {
                System.out.println("deu bosta ao salvar");
                tfId.setText("");
                tfId.requestFocus();
                JOptionPane.showMessageDialog(null, "voce pesquisou algo estranho", "erro no buscamento", JOptionPane.PLAIN_MESSAGE);
                planetaDoAlien.setEnabled(false);
                tfNome.setText("");
                tfdescricao.setText("");
                botaoadicionar.setVisible(false);
                tfNome.setEditable(false);
                tfdescricao.setEditable(false);

                botaosalvar.setVisible(false);
                botaoalterar.setVisible(false);
                botaoexcluir.setVisible(false);

            }
        });

        botaoadicionar.addActionListener((ActionEvent ae) -> {
            tfId.setEditable(false);
            tfNome.setEditable(true);
            tfdescricao.setEditable(true);
            tfNome.requestFocus();
            planetaDoAlien.setEnabled(true);
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
                    cargos = new Cargos();
                    Integer id = Integer.valueOf(tfId.getText());
                    cargos.setIdcargos(id);
                    cargos.setNomecargo(tfNome.getText());
                    String idfornecedor = String.valueOf(tfdescricao.getText());
                    cargos.setDescricaoCargo(idfornecedor);
                    daoCargos.inserir(cargos);
                } else {
                    Integer id2 = Integer.valueOf(tfId.getText());
                    cargos.setIdcargos(id2);
                    cargos.setNomecargo(tfNome.getText());
                    String idfornecedor2 = String.valueOf(tfdescricao.getText());
                    cargos.setDescricaoCargo(idfornecedor2);
                    daoCargos.atualizar(cargos);
                }

                botaosalvar.setVisible(false);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfId.setText("");
                tfNome.setText("");
                tfdescricao.setText("");

                tfNome.setEditable(false);
                planetaDoAlien.setEnabled(false);
                tfdescricao.setEditable(false);

                botaobuscar.setVisible(true);
            } catch (NumberFormatException errou) {
                System.out.println("deu bosta ao salvar");
                JOptionPane.showMessageDialog(null, "voce digitou algo estranho", "erro no salvamento", JOptionPane.PLAIN_MESSAGE);
                tfNome.requestFocus();
                tfNome.setText("");
                tfdescricao.setText("");
                planetaDoAlien.setEnabled(true);
                botaocancelar.setVisible(true);
                tfNome.setEditable(true);
                tfdescricao.setEditable(true);
            }
        });

        botaoalterar.addActionListener((ActionEvent ae) -> {
            botaolistar.setVisible(false);
            botaoexcluir.setVisible(false);
            botaobuscar.setVisible(false);
            botaoalterar.setVisible(false);
            tfId.setEditable(false);
            tfNome.setEditable(true);
            tfdescricao.setEditable(true);
            planetaDoAlien.setEnabled(true);
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
            tfdescricao.setText("");
            planetaDoAlien.setEnabled(false);

            tfNome.setEditable(false);
            tfdescricao.setEditable(false);

            botaobuscar.setVisible(true);
            if (resposta == JOptionPane.YES_OPTION) {
                daoCargos.remover(cargos);
            } else {
                System.out.println(" OS DADOS DO ATLETA NÃO FORAM APAGADOS.");

            }
        });

        botaolistar.addActionListener((ActionEvent ae) -> {
            List<Cargos> cargos1 = daoCargos.list();
            String[] colunas1 = new String[]{" id cargos", " nome ", " Descricão"};
            Object[][] dados1 = new Object[cargos1.size()][colunas1.length];
            String aux[] = null;
            for (int i = 0; i < cargos1.size(); i++) {
                aux = cargos1.get(i).toString().split(";");
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
            planetaDoAlien.setEnabled(true);
            tfdescricao.setText("");

            tfNome.setEditable(false);
            tfdescricao.setEditable(false);

            botaobuscar.setVisible(true);
            botaolistar.setVisible(true);
            botaosalvar.setVisible(false);
        });

        // finaliza o gui
        dialogo.setModal(true);
        dialogo.setSize(900, 650);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);
    }
}
