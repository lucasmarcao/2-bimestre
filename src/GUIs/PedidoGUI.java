package GUIs;

import DAOs.DAOAcompanhamentos;
import DAOs.DAOPedido;
import DAOs.DAOCliente;
import Entidades.Acompanhamentos;
import Entidades.Pedido;
import Entidades.Cliente;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
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
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.text.MaskFormatter;

public class PedidoGUI extends JDialog {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    JDialog dialogo = new JDialog();

    Container cp;

    JPanel painelnorte = new JPanel();
    JPanel painelcentro = new JPanel();
    JPanel painelsul = new JPanel();
    JPanel painelleste = new JPanel();
    JPanel paineloeste = new JPanel();

    JLabel lbId = new JLabel("ID Pedido");
    JTextField tfId = new JTextField(15);
    JButton botaobuscar = new JButton(" BUSCAR ");
    JButton botaoadicionar = new JButton(" ADICIONAR ");
    JButton botaosalvar = new JButton(" SALVAR ");
    JButton botaoalterar = new JButton(" ALTERAR ");
    JButton botaoexcluir = new JButton(" EXCLUIR ");
    JButton botaolistar = new JButton(" LISTAR ");
    JButton botaocancelar = new JButton(" CANCELAR ");

    // como formatar o codigo : voce aperta { ALT + SHIFT + F }
    JLabel lbDataPedido = new JLabel("Data do Pedido", JLabel.CENTER);
    JTextField tfDataPedido = new JTextField(30);
    JLabel lbEntregue = new JLabel("Pedido foi entregue?", JLabel.CENTER);
    JPanel painel = new JPanel();
    Short conta = 0;
    JRadioButton sim = new JRadioButton("sim");
    JRadioButton nao = new JRadioButton("não");

    JLabel lbCliente = new JLabel("Cliente", JLabel.CENTER);
    JLabel lbAcompanhamentos = new JLabel("Acompanhamentos", JLabel.CENTER);
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
    DAOPedido daoPedido = new DAOPedido();
    Pedido pedido = new Pedido();
    DAOCliente daoCliente = new DAOCliente();
    DAOAcompanhamentos daoAcompanhamentos = new DAOAcompanhamentos();

    DefaultComboBoxModel cbm = new DefaultComboBoxModel();
    JComboBox cbCliente = new JComboBox(cbm);

    DefaultComboBoxModel cbm2 = new DefaultComboBoxModel();
    JComboBox cbAcompanhamentos = new JComboBox(cbm2);

    public PedidoGUI() {
        try {
            this.tfDataPedido = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException ex) {
            System.out.println("fudeu o parse" + ex);
        }
        dialogo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = dialogo.getContentPane();
        cp.setLayout(new BorderLayout());
        dialogo.setTitle(" Pedido - CRUD");

        cp.add(painelnorte, BorderLayout.NORTH);
        cp.add(painelcentro, BorderLayout.CENTER);
        cp.add(painelsul, BorderLayout.SOUTH);
        cp.add(painelleste, BorderLayout.EAST);
        cp.add(paineloeste, BorderLayout.WEST);
        // combo box do cargo
        List<Acompanhamentos> listaAcompanhamentos = daoAcompanhamentos.list();
        listaAcompanhamentos.forEach((cargos) -> {
            cbm2.addElement(cargos.getIdacompanhamentos() + "-" + cargos.getNomeaconpanhamento());
        });
        cbAcompanhamentos.setBorder(BorderFactory.createLineBorder(Color.black));
        cbAcompanhamentos.setFont(new Font("Impact", Font.PLAIN, 22));
        cbAcompanhamentos.setForeground(new Color(0, 96, 55));
        cbAcompanhamentos.setBackground(Color.ORANGE);
        cbAcompanhamentos.setEnabled(false);

        // combo box da pessoa
        List<Cliente> listaCliente = daoCliente.list();
        listaCliente.forEach((pessoa) -> {
            cbm.addElement(pessoa.getIdcliente() + "-" + pessoa.getPessoaidpessoa().getNome());
        });
        cbCliente.setBorder(BorderFactory.createLineBorder(Color.black));
        cbCliente.setFont(new Font("Impact", Font.PLAIN, 22));
        cbCliente.setForeground(new Color(0, 96, 55));
        cbCliente.setBackground(Color.ORANGE);
        cbCliente.setEnabled(false);
        cbAcompanhamentos.setEnabled(false);
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

        // radio 
        painel.setLayout(new GridLayout(1, 2));
        painel.add(sim);
        painel.add(nao);

        sim.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        nao.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        sim.setBackground(new Color(240, 190, 0));
        nao.setBackground(new Color(240, 190, 0));
        sim.setFont(new Font("Times New Roman", Font.BOLD, 25));
        nao.setFont(new Font("Times New Roman", Font.BOLD, 25));

        // painel centro
        painelcentro.setLayout(new GridLayout(4, 2));
        painelcentro.add(lbCliente);
        painelcentro.add(cbCliente);
        painelcentro.add(lbAcompanhamentos);
        painelcentro.add(cbAcompanhamentos);
        painelcentro.add(lbDataPedido);
        painelcentro.add(tfDataPedido);
        painelcentro.add(lbEntregue);
        painelcentro.add(painel);

        tfDataPedido.setEditable(false);
        sim.setEnabled(false);
        nao.setEnabled(false);

        lbDataPedido.setFont(new Font("Arial", Font.BOLD, 18));
        tfDataPedido.setFont(new Font("Arial", Font.BOLD, 18));
        lbEntregue.setFont(new Font("Arial", Font.BOLD, 18));

        lbCliente.setFont(new Font("Arial", Font.BOLD, 18));
        lbAcompanhamentos.setFont(new Font("Arial", Font.BOLD, 18));

        // borda dos centro 
        lbDataPedido.setBorder(BorderFactory.createLineBorder(Color.black));
        lbEntregue.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbAcompanhamentos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tfDataPedido.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        painel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
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
        // radio
        sim.addActionListener((ActionEvent ae) -> {
            if (sim.isSelected() && nao.isSelected()) {
                nao.doClick();
            }

        });

        nao.addActionListener((ActionEvent ae) -> {
            if (sim.isSelected() && nao.isSelected()) {
                sim.doClick();
            }

        });
        //listeners
        botaobuscar.addActionListener((ActionEvent ae) -> {
            try {
                cardLayout.show(painelsul, "avisos");
                chavePrimaria = tfId.getText();
                pedido = daoPedido.obter(Integer.valueOf(tfId.getText()));
                if (pedido != null) {
                    botaoalterar.setVisible(true);
                    botaoexcluir.setVisible(true);
                    botaoadicionar.setVisible(false);
                    tfDataPedido.setText(simpleDateFormat.format(pedido.getDatapedido()));
                    //tfEntregue.setText(String.valueOf(pedido.getEntregue()));
                    sim.setEnabled(true);
                    nao.setEnabled(true);
                    if (sim.isSelected() && nao.isSelected()) {
                        sim.doClick();
                        nao.doClick();
                    } else if (sim.isSelected() && nao.isSelected() == false) {
                        sim.doClick();
                    } else if (sim.isSelected() == false && nao.isSelected()) {
                        nao.doClick();
                    }
                    if (1 == pedido.getEntregue()) {
                        sim.doClick();
                    } else {
                        nao.doClick();
                    }
                    tfDataPedido.setEditable(false);
                    sim.setEnabled(false);
                    nao.setEnabled(false);

                    botaosalvar.setVisible(false);

                    cbCliente.setEnabled(false);
                    cbAcompanhamentos.setEnabled(false);

                    cbCliente.setSelectedItem(pedido.getClienteIdcliente().getIdcliente()
                            + "-" + pedido.getClienteIdcliente().getPessoaidpessoa().getNome());
                    cbAcompanhamentos.setSelectedItem(pedido.getAcompanhamentosidacompanhamentos().getIdacompanhamentos()
                            + "-" + pedido.getAcompanhamentosidacompanhamentos().getNomeaconpanhamento());

                } else {
                    cbCliente.setEnabled(false);
                    cbAcompanhamentos.setEnabled(false);
                    tfDataPedido.setText("");
                    sim.setEnabled(true);
                    nao.setEnabled(true);
                    if (sim.isSelected() && nao.isSelected()) {
                        sim.doClick();
                        nao.doClick();
                    } else if (sim.isSelected() && nao.isSelected() == false) {
                        sim.doClick();
                    } else if (sim.isSelected() == false && nao.isSelected()) {
                        nao.doClick();
                    }
                    botaoadicionar.setVisible(true);
                    tfDataPedido.setEditable(false);
                    sim.setEnabled(false);
                    nao.setEnabled(false);

                    botaosalvar.setVisible(false);
                    botaoalterar.setVisible(false);
                    botaoexcluir.setVisible(false);

                }
            } catch (Exception e) {
                System.out.println("deu bosta ao salvar");
                tfId.setText("");
                tfId.requestFocus();
                JOptionPane.showMessageDialog(null, "voce pesquisou algo estranho", "erro no buscamento", JOptionPane.PLAIN_MESSAGE);
                cbCliente.setEnabled(false);
                cbAcompanhamentos.setEnabled(false);
                tfDataPedido.setText("");
                sim.setEnabled(true);
                nao.setEnabled(true);
                if (sim.isSelected() && nao.isSelected()) {
                    sim.doClick();
                    nao.doClick();
                } else if (sim.isSelected() && nao.isSelected() == false) {
                    sim.doClick();
                } else if (sim.isSelected() == false && nao.isSelected()) {
                    nao.doClick();
                }
                botaoadicionar.setVisible(false);
                tfDataPedido.setEditable(false);
                sim.setEnabled(false);
                nao.setEnabled(false);

                botaosalvar.setVisible(false);
                botaoalterar.setVisible(false);
                botaoexcluir.setVisible(false);
            }
        });

        botaoadicionar.addActionListener((ActionEvent ae) -> {
            tfId.setEditable(false);
            tfDataPedido.setEditable(true);
            sim.setEnabled(true);
            nao.setEnabled(true);
            tfDataPedido.requestFocus();
            cbCliente.setEnabled(true);
            cbAcompanhamentos.setEnabled(true);
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
                    pedido = new Pedido();
                    Integer id = Integer.valueOf(tfId.getText());
                    Integer idcliente = Integer.valueOf(String.valueOf(cbCliente.getSelectedItem()).split("-")[0]);
                    Integer idAcompanhamentos = Integer.valueOf(String.valueOf(cbAcompanhamentos.getSelectedItem()).split("-")[0]);
                    if (sim.isSelected()) {
                        conta = 1;
                    } else if (nao.isSelected()) {
                        conta = 0;
                    } else if (nao.isSelected() == false && sim.isSelected() == false) {
                        conta = 0;
                    }
                    pedido.setIdpedido(id);
                    pedido.setEntregue(conta);
                    pedido.setClienteIdcliente(daoCliente.obter(idcliente));
                    pedido.setAcompanhamentosidacompanhamentos(daoAcompanhamentos.obter(idAcompanhamentos));
                    try {
                        pedido.setDatapedido(simpleDateFormat.parse(tfDataPedido.getText()));
                    } catch (ParseException ex) {
                        System.out.println("deu ruim na data");
                    }
                    daoPedido.inserir(pedido);
                } else {
                    Integer id = Integer.valueOf(tfId.getText());
                    Integer idcliente = Integer.valueOf(String.valueOf(cbCliente.getSelectedItem()).split("-")[0]);
                    Integer idAcompanhamentos = Integer.valueOf(String.valueOf(cbAcompanhamentos.getSelectedItem()).split("-")[0]);
                    if (sim.isSelected()) {
                        conta = 1;
                    } else if (nao.isSelected()) {
                        conta = 0;
                    } else if (nao.isSelected() == false && sim.isSelected() == false) {
                        conta = 0;
                    }
                    pedido.setIdpedido(id);
                    pedido.setEntregue(conta);
                    pedido.setClienteIdcliente(daoCliente.obter(idcliente));
                    pedido.setAcompanhamentosidacompanhamentos(daoAcompanhamentos.obter(idAcompanhamentos));
                    try {
                        pedido.setDatapedido(simpleDateFormat.parse(tfDataPedido.getText()));
                    } catch (ParseException ex) {
                        System.out.println("deu ruim na data");
                    }
                    daoPedido.atualizar(pedido);
                }
                botaosalvar.setVisible(false);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfId.setText("");
                tfDataPedido.setText("");
                sim.setEnabled(true);
                nao.setEnabled(true);
                if (sim.isSelected() && nao.isSelected()) {
                    sim.doClick();
                    nao.doClick();
                } else if (sim.isSelected() && nao.isSelected() == false) {
                    sim.doClick();
                } else if (sim.isSelected() == false && nao.isSelected()) {
                    nao.doClick();
                }
                tfDataPedido.setEditable(false);
                cbCliente.setEnabled(false);
                cbAcompanhamentos.setEnabled(false);
                sim.setEnabled(false);
                nao.setEnabled(false);
                botaobuscar.setVisible(true);
            } catch (NumberFormatException errou) {
                System.out.println("deu bosta ao salvar");
                JOptionPane.showMessageDialog(null, "voce digitou algo estranho", "erro no salvamento", JOptionPane.PLAIN_MESSAGE);
                tfDataPedido.requestFocus();
                tfDataPedido.setText("");
                sim.setEnabled(true);
                nao.setEnabled(true);
                if (sim.isSelected() && nao.isSelected()) {
                    sim.doClick();
                    nao.doClick();
                } else if (sim.isSelected() && nao.isSelected() == false) {
                    sim.doClick();
                } else if (sim.isSelected() == false && nao.isSelected()) {
                    nao.doClick();
                }
                cbCliente.setEnabled(true);
                cbAcompanhamentos.setEnabled(true);
                botaocancelar.setVisible(true);
                tfDataPedido.setEditable(true);
            }
        });

        botaoalterar.addActionListener((ActionEvent ae) -> {
            botaolistar.setVisible(false);
            botaoexcluir.setVisible(false);
            botaobuscar.setVisible(false);
            botaoalterar.setVisible(false);
            tfId.setEditable(false);
            tfDataPedido.setEditable(true);
            sim.setEnabled(true);
            nao.setEnabled(true);
            cbCliente.setEnabled(true);
            cbAcompanhamentos.setEnabled(true);
            tfDataPedido.requestFocus();

            botaosalvar.setVisible(true);
            botaocancelar.setVisible(true);
            acao = "alterar";
        });

        botaoexcluir.addActionListener((ActionEvent ae) -> {
            int resposta = JOptionPane.showConfirmDialog(cp, "Deseja mesmo excluir?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resposta == JOptionPane.YES_OPTION) {
                daoPedido.remover(pedido);
                botaoexcluir.setVisible(false);
                botaoalterar.setVisible(false);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfId.setText("");
                tfDataPedido.setText("");
                sim.setEnabled(true);
                nao.setEnabled(true);
                if (sim.isSelected() && nao.isSelected()) {
                    sim.doClick();
                    nao.doClick();
                } else if (sim.isSelected() && nao.isSelected() == false) {
                    sim.doClick();
                } else if (sim.isSelected() == false && nao.isSelected()) {
                    nao.doClick();
                }
                cbCliente.setEnabled(false);
                cbAcompanhamentos.setEnabled(false);
                tfDataPedido.setEditable(false);
                sim.setEnabled(false);
                nao.setEnabled(false);
                botaobuscar.setVisible(true);
            } else {
                System.out.println(" OS DADOS DO ATLETA NÃO FORAM APAGADOS.");

            }
        });

        botaolistar.addActionListener((ActionEvent ae) -> {
            List<Pedido> pedido1 = daoPedido.list();
            String[] colunas1 = new String[]{" id pedido", " data do pedido ", "foi entregue?", "acompanhamento", "CLIENTE"};
            Object[][] dados1 = new Object[pedido1.size()][colunas1.length];
            String aux[];
            for (int i = 0; i < pedido1.size(); i++) {
                aux = pedido1.get(i).toString().split(";");
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
            tfDataPedido.setText("");
            cbCliente.setEnabled(false);
            cbAcompanhamentos.setEnabled(false);
            sim.setEnabled(true);
            nao.setEnabled(true);
            if (sim.isSelected() && nao.isSelected()) {
                sim.doClick();
                nao.doClick();
            } else if (sim.isSelected() && nao.isSelected() == false) {
                sim.doClick();
            } else if (sim.isSelected() == false && nao.isSelected()) {
                nao.doClick();
            }
            tfDataPedido.setEditable(false);
            sim.setEnabled(false);
            nao.setEnabled(false);
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
