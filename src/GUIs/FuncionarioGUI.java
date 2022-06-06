package GUIs;

import DAOs.DAOCargos;
import DAOs.DAOFuncionario;
import DAOs.DAOPessoa;
import Entidades.Cargos;
import Entidades.Funcionario;
import Entidades.Pessoa;
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

public class FuncionarioGUI extends JDialog {

    JDialog dialogo = new JDialog();

    Container cp;

    JPanel painelnorte = new JPanel();
    JPanel painelcentro = new JPanel();
    JPanel painelsul = new JPanel();
    JPanel painelleste = new JPanel();
    JPanel paineloeste = new JPanel();

    JLabel lbId = new JLabel("ID Funcionario");
    JTextField tfId = new JTextField(15);
    JButton botaobuscar = new JButton(" BUSCAR ");
    JButton botaoadicionar = new JButton(" ADICIONAR ");
    JButton botaosalvar = new JButton(" SALVAR ");
    JButton botaoalterar = new JButton(" ALTERAR ");
    JButton botaoexcluir = new JButton(" EXCLUIR ");
    JButton botaolistar = new JButton(" LISTAR ");
    JButton botaocancelar = new JButton(" CANCELAR ");

    // como formatar o codigo : voce aperta { ALT + SHIFT + F }
    JLabel lbSalario = new JLabel("SALARIO", JLabel.CENTER);
    JTextField tfSalario = new JTextField(30);
    JLabel lbTrabalhando = new JLabel("ESTÁ TRABALHANDO?", JLabel.CENTER);
    JTextField tfTrabalhando = new JTextField(30);
    JLabel lbPessoa = new JLabel("Pessoa", JLabel.CENTER);
    JLabel lbCargos = new JLabel("Cargo", JLabel.CENTER);
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
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    Funcionario funcionario = new Funcionario();
    DAOPessoa daoPessoa = new DAOPessoa();
    DAOCargos daoCargos = new DAOCargos();

    DefaultComboBoxModel cbm = new DefaultComboBoxModel();
    JComboBox cbPessoa = new JComboBox(cbm);

    DefaultComboBoxModel cbm2 = new DefaultComboBoxModel();
    JComboBox cbCargos = new JComboBox(cbm2);

    public FuncionarioGUI() {
        dialogo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = dialogo.getContentPane();
        cp.setLayout(new BorderLayout());
        dialogo.setTitle(" Funcionario - CRUD");

        cp.add(painelnorte, BorderLayout.NORTH);
        cp.add(painelcentro, BorderLayout.CENTER);
        cp.add(painelsul, BorderLayout.SOUTH);
        cp.add(painelleste, BorderLayout.EAST);
        cp.add(paineloeste, BorderLayout.WEST);
        // combo box do cargo
        List<Cargos> listaCargos = daoCargos.list();
        listaCargos.forEach((cargos) -> {
            cbm2.addElement(cargos.getIdcargos() + "-" + cargos.getNomecargo());
        });
        cbCargos.setBorder(BorderFactory.createLineBorder(Color.black));
        cbCargos.setFont(new Font("Impact", Font.PLAIN, 22));
        cbCargos.setForeground(new Color(0, 96, 55));
        cbCargos.setBackground(Color.ORANGE);
        cbCargos.setEnabled(false);

        // combo box da pessoa
        List<Pessoa> listaPessoa = daoPessoa.list();
        listaPessoa.forEach((pessoa) -> {
            cbm.addElement(pessoa.getIdpessoa() + "-" + pessoa.getNome());
        });
        cbPessoa.setBorder(BorderFactory.createLineBorder(Color.black));
        cbPessoa.setFont(new Font("Impact", Font.PLAIN, 22));
        cbPessoa.setForeground(new Color(0, 96, 55));
        cbPessoa.setBackground(Color.ORANGE);
        cbPessoa.setEnabled(false);
        cbCargos.setEnabled(false);
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
        painelcentro.setLayout(new GridLayout(4, 2));
        painelcentro.add(lbSalario);
        painelcentro.add(tfSalario);
        painelcentro.add(lbTrabalhando);
        painelcentro.add(tfTrabalhando);
        painelcentro.add(lbPessoa);
        painelcentro.add(cbPessoa);
        painelcentro.add(lbCargos);
        painelcentro.add(cbCargos);

        tfSalario.setEditable(false);
        tfTrabalhando.setEditable(false);

        lbSalario.setFont(new Font("Arial", Font.BOLD, 18));
        tfSalario.setFont(new Font("Arial", Font.BOLD, 18));
        lbTrabalhando.setFont(new Font("Arial", Font.BOLD, 18));
        tfTrabalhando.setFont(new Font("Arial", Font.BOLD, 18));
        lbPessoa.setFont(new Font("Arial", Font.BOLD, 18));
        lbCargos.setFont(new Font("Arial", Font.BOLD, 18));

        // borda dos centro 
        lbSalario.setBorder(BorderFactory.createLineBorder(Color.black));
        lbTrabalhando.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbPessoa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbCargos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tfSalario.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        tfTrabalhando.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
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
            funcionario = daoFuncionario.obter(Integer.valueOf(tfId.getText()));
            if (funcionario != null) {
                botaoalterar.setVisible(true);
                botaoexcluir.setVisible(true);
                botaoadicionar.setVisible(false);
                tfSalario.setText(String.valueOf(funcionario.getSalario()));
                tfTrabalhando.setText(String.valueOf(funcionario.getTrabalhando()));
                tfSalario.setEditable(false);
                tfTrabalhando.setEditable(false);

                botaosalvar.setVisible(false);

                cbPessoa.setEnabled(false);
                cbCargos.setEnabled(false);

                cbPessoa.setSelectedItem(funcionario.getPessoaidpessoa().getIdpessoa()
                        + "-" + funcionario.getPessoaidpessoa().getNome());
                cbCargos.setSelectedItem(funcionario.getCargosidcargos().getIdcargos()
                        + "-" + funcionario.getCargosidcargos().getNomecargo());

            } else {
                cbPessoa.setEnabled(false);
                cbCargos.setEnabled(false);
                tfSalario.setText("");
                tfTrabalhando.setText("");
                botaoadicionar.setVisible(true);
                tfSalario.setEditable(false);
                tfTrabalhando.setEditable(false);

                botaosalvar.setVisible(false);
                botaoalterar.setVisible(false);
                botaoexcluir.setVisible(false);

            }
        });

        botaoadicionar.addActionListener((ActionEvent ae) -> {
            tfId.setEditable(false);
            tfSalario.setEditable(true);
            tfTrabalhando.setEditable(true);
            tfSalario.requestFocus();
            cbPessoa.setEnabled(true);
            cbCargos.setEnabled(true);
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
                    funcionario = new Funcionario();
                    Integer id = Integer.valueOf(tfId.getText());
                    funcionario.setIdfuncionario(id);
                    Short avalia = Short.valueOf(tfTrabalhando.getText());
                    funcionario.setTrabalhando(avalia);
                    funcionario.setSalario(Double.valueOf(tfSalario.getText()));
                    Integer idpessoa = Integer.valueOf(String.valueOf(cbPessoa.getSelectedItem()).split("-")[0]);
                    Integer idCargos = Integer.valueOf(String.valueOf(cbCargos.getSelectedItem()).split("-")[0]);
                    funcionario.setPessoaidpessoa(daoPessoa.obter(idpessoa));
                    funcionario.setCargosidcargos(daoCargos.obter(idCargos));
                    daoFuncionario.inserir(funcionario);
                } else {
                    Integer id = Integer.valueOf(tfId.getText());
                    funcionario.setIdfuncionario(id);
                    Short avalia = Short.valueOf(tfTrabalhando.getText());
                    funcionario.setTrabalhando(avalia);
                    funcionario.setSalario(Double.valueOf(tfSalario.getText()));
                    Integer idpessoa = Integer.valueOf(String.valueOf(cbPessoa.getSelectedItem()).split("-")[0]);
                    Integer idCargos = Integer.valueOf(String.valueOf(cbCargos.getSelectedItem()).split("-")[0]);
                    funcionario.setPessoaidpessoa(daoPessoa.obter(idpessoa));
                    funcionario.setCargosidcargos(daoCargos.obter(idCargos));
                    daoFuncionario.atualizar(funcionario);
                }

                botaosalvar.setVisible(false);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfId.setText("");
                tfSalario.setText("");
                tfTrabalhando.setText("");

                tfSalario.setEditable(false);
                cbPessoa.setEnabled(false);
                cbCargos.setEnabled(false);
                tfTrabalhando.setEditable(false);

                botaobuscar.setVisible(true);
            } catch (NumberFormatException errou) {
                System.out.println("deu bosta ao salvar");
                JOptionPane.showMessageDialog(null, "voce digitou algo estranho", "erro no salvamento", JOptionPane.PLAIN_MESSAGE);
                tfSalario.requestFocus();
                tfSalario.setText("");
                tfTrabalhando.setText("");
                cbPessoa.setEnabled(true);
                cbCargos.setEnabled(true);
                botaocancelar.setVisible(true);
                tfSalario.setEditable(true);
                tfTrabalhando.setEditable(true);
            }
        });

        botaoalterar.addActionListener((ActionEvent ae) -> {
            botaolistar.setVisible(false);
            botaoexcluir.setVisible(false);
            botaobuscar.setVisible(false);
            botaoalterar.setVisible(false);
            tfId.setEditable(false);
            tfSalario.setEditable(true);
            tfTrabalhando.setEditable(true);
            cbPessoa.setEnabled(true);
            cbCargos.setEnabled(true);
            tfSalario.requestFocus();

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
            tfSalario.setText("");
            tfTrabalhando.setText("");
            cbPessoa.setEnabled(false);
            cbCargos.setEnabled(false);

            tfSalario.setEditable(false);
            tfTrabalhando.setEditable(false);

            botaobuscar.setVisible(true);
            if (resposta == JOptionPane.YES_OPTION) {
                daoFuncionario.remover(funcionario);
            } else {
                System.out.println(" OS DADOS DO ATLETA NÃO FORAM APAGADOS.");

            }
        });

        botaolistar.addActionListener((ActionEvent ae) -> {
            List<Funcionario> funcionario1 = daoFuncionario.list();
            String[] colunas1 = new String[]{" id funcionario", " salario ", "trabalhando", "pessoa", "cargo"};
            Object[][] dados1 = new Object[funcionario1.size()][colunas1.length];
            String aux[];
            for (int i = 0; i < funcionario1.size(); i++) {
                aux = funcionario1.get(i).toString().split(";");
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
            tfSalario.setText("");
            cbPessoa.setEnabled(false);
            cbCargos.setEnabled(false);
            tfTrabalhando.setText("");

            tfSalario.setEditable(false);
            tfTrabalhando.setEditable(false);

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
