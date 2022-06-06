package GUIs;

import DAOs.DAOCliente;
import DAOs.DAOPessoa;
import Entidades.Cliente;
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

public class ClienteGUI extends JDialog {

    JDialog dialogo = new JDialog();

    Container cp;

    JPanel painelnorte = new JPanel();
    JPanel painelcentro = new JPanel();
    JPanel painelsul = new JPanel();
    JPanel painelleste = new JPanel();
    JPanel paineloeste = new JPanel();

    JLabel lbId = new JLabel("ID Cliente");
    JTextField tfId = new JTextField(15);
    JButton botaobuscar = new JButton(" BUSCAR ");
    JButton botaoadicionar = new JButton(" ADICIONAR ");
    JButton botaosalvar = new JButton(" SALVAR ");
    JButton botaoalterar = new JButton(" ALTERAR ");
    JButton botaoexcluir = new JButton(" EXCLUIR ");
    JButton botaolistar = new JButton(" LISTAR ");
    JButton botaocancelar = new JButton(" CANCELAR ");

    // como formatar o codigo : voce aperta { ALT + SHIFT + F }
    JLabel lbTotalCompras = new JLabel("Total de compras", JLabel.CENTER);
    JTextField tfTotalCompras = new JTextField(30);
    JLabel lbAvaliacaoServico = new JLabel("Avaliação do serviço", JLabel.CENTER);
    JTextField tfAvaliacaoServico = new JTextField(30);
    JLabel lbPessoa = new JLabel("Pessoa", JLabel.CENTER);
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
    DAOCliente daoCliente = new DAOCliente();
    Cliente cliente = new Cliente();
    DAOPessoa daoPessoa = new DAOPessoa();

    DefaultComboBoxModel cbm = new DefaultComboBoxModel();
    JComboBox planetaDoAlien = new JComboBox(cbm);

    public ClienteGUI() {
        dialogo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = dialogo.getContentPane();
        cp.setLayout(new BorderLayout());
        dialogo.setTitle(" Cliente - CRUD");

        cp.add(painelnorte, BorderLayout.NORTH);
        cp.add(painelcentro, BorderLayout.CENTER);
        cp.add(painelsul, BorderLayout.SOUTH);
        cp.add(painelleste, BorderLayout.EAST);
        cp.add(paineloeste, BorderLayout.WEST);

        List<Pessoa> listaPessoa = daoPessoa.list();
        listaPessoa.forEach((trabalhador) -> {
            cbm.addElement(trabalhador.getIdpessoa() + "-" + trabalhador.getNome());
        });
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
        painelcentro.setLayout(new GridLayout(3, 2));
        painelcentro.add(lbTotalCompras);
        painelcentro.add(tfTotalCompras);
        painelcentro.add(lbAvaliacaoServico);
        painelcentro.add(tfAvaliacaoServico);
        painelcentro.add(lbPessoa);
        painelcentro.add(planetaDoAlien);

        tfTotalCompras.setEditable(false);
        tfAvaliacaoServico.setEditable(false);

        lbTotalCompras.setFont(new Font("Arial", Font.BOLD, 18));
        tfTotalCompras.setFont(new Font("Arial", Font.BOLD, 18));
        lbAvaliacaoServico.setFont(new Font("Arial", Font.BOLD, 18));
        tfAvaliacaoServico.setFont(new Font("Arial", Font.BOLD, 18));
        lbPessoa.setFont(new Font("Arial", Font.BOLD, 18));

        // borda dos centro 
        lbTotalCompras.setBorder(BorderFactory.createLineBorder(Color.black));
        lbAvaliacaoServico.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbPessoa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tfTotalCompras.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        tfAvaliacaoServico.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
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
            cliente = daoCliente.obter(Integer.valueOf(tfId.getText()));
            if (cliente != null) {
                botaoalterar.setVisible(true);
                botaoexcluir.setVisible(true);
                botaoadicionar.setVisible(false);
                tfTotalCompras.setText(String.valueOf(cliente.getTotalDeCompras()));
                tfAvaliacaoServico.setText(String.valueOf(cliente.getAvaliacaoDoServico()));
                tfTotalCompras.setEditable(false);
                tfAvaliacaoServico.setEditable(false);

                botaosalvar.setVisible(false);

                planetaDoAlien.setEnabled(false);

                planetaDoAlien.setSelectedItem(cliente.getPessoaidpessoa().getIdpessoa()
                        + "-" + cliente.getPessoaidpessoa().getNome());

            } else {
                planetaDoAlien.setEnabled(false);
                tfTotalCompras.setText("");
                tfAvaliacaoServico.setText("");
                botaoadicionar.setVisible(true);
                tfTotalCompras.setEditable(false);
                tfAvaliacaoServico.setEditable(false);

                botaosalvar.setVisible(false);
                botaoalterar.setVisible(false);
                botaoexcluir.setVisible(false);

            }
        });

        botaoadicionar.addActionListener((ActionEvent ae) -> {
            tfId.setEditable(false);
            tfTotalCompras.setEditable(true);
            tfAvaliacaoServico.setEditable(true);
            tfTotalCompras.requestFocus();
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
                    cliente = new Cliente();
                    Integer id = Integer.valueOf(tfId.getText());
                    cliente.setIdcliente(id);
                    Integer avalia = Integer.valueOf(tfAvaliacaoServico.getText());
                    cliente.setAvaliacaoDoServico(avalia);
                    cliente.setTotalDeCompras(Integer.valueOf(tfTotalCompras.getText()));
                    Integer idpessoa = Integer.valueOf(String.valueOf(planetaDoAlien.getSelectedItem()).split("-")[0]);
                    cliente.setPessoaidpessoa(daoPessoa.obter(idpessoa));
                    daoCliente.inserir(cliente);
                } else {
                    Integer id = Integer.valueOf(tfId.getText());
                    cliente.setIdcliente(id);
                     Integer avalia = Integer.valueOf(tfAvaliacaoServico.getText());
                    cliente.setAvaliacaoDoServico(avalia);
                    cliente.setTotalDeCompras(Integer.valueOf(tfTotalCompras.getText()));
                    Integer idpessoa = Integer.valueOf(String.valueOf(planetaDoAlien.getSelectedItem()).split("-")[0]);
                    cliente.setPessoaidpessoa(daoPessoa.obter(idpessoa));
                    daoCliente.atualizar(cliente);
                }

                botaosalvar.setVisible(false);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfId.setText("");
                tfTotalCompras.setText("");
                tfAvaliacaoServico.setText("");

                tfTotalCompras.setEditable(false);
                planetaDoAlien.setEnabled(false);
                tfAvaliacaoServico.setEditable(false);

                botaobuscar.setVisible(true);
            } catch (NumberFormatException errou) {
                System.out.println("deu bosta ao salvar");
                JOptionPane.showMessageDialog(null, "voce digitou algo estranho", "erro no salvamento", JOptionPane.PLAIN_MESSAGE);
                tfTotalCompras.requestFocus();
                tfTotalCompras.setText("");
                tfAvaliacaoServico.setText("");
                planetaDoAlien.setEnabled(true);
                botaocancelar.setVisible(true);
                tfTotalCompras.setEditable(true);
                tfAvaliacaoServico.setEditable(true);
            }
        });

        botaoalterar.addActionListener((ActionEvent ae) -> {
            botaolistar.setVisible(false);
            botaoexcluir.setVisible(false);
            botaobuscar.setVisible(false);
            botaoalterar.setVisible(false);
            tfId.setEditable(false);
            tfTotalCompras.setEditable(true);
            tfAvaliacaoServico.setEditable(true);
            planetaDoAlien.setEnabled(true);
            tfTotalCompras.requestFocus();

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
            tfTotalCompras.setText("");
            tfAvaliacaoServico.setText("");
            planetaDoAlien.setEnabled(false);

            tfTotalCompras.setEditable(false);
            tfAvaliacaoServico.setEditable(false);

            botaobuscar.setVisible(true);
            if (resposta == JOptionPane.YES_OPTION) {
                daoCliente.remover(cliente);
            } else {
                System.out.println(" OS DADOS DO ATLETA NÃO FORAM APAGADOS.");

            }
        });

        botaolistar.addActionListener((ActionEvent ae) -> {
            List<Cliente> cliente1 = daoCliente.list();
            String[] colunas1 = new String[]{" id cliente", " total de Compras ", "avaliação do serviço", " id pessoa"};
            Object[][] dados1 = new Object[cliente1.size()][colunas1.length];
            String aux[];
            for (int i = 0; i < cliente1.size(); i++) {
                aux = cliente1.get(i).toString().split(";");
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
            tfTotalCompras.setText("");
            planetaDoAlien.setEnabled(false);
            tfAvaliacaoServico.setText("");

            tfTotalCompras.setEditable(false);
            tfAvaliacaoServico.setEditable(false);

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
