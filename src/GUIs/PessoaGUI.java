package GUIs;

import DAOs.DAOPessoa;
import Entidades.Pessoa;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import java.text.ParseException;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tools.UsarGridBagLayout;

public class PessoaGUI extends JDialog {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    JDialog dialogo = new JDialog();

    Container cp;

    JPanel painelnorte = new JPanel();
    JPanel painelcentro = new JPanel();
    JPanel painelsul = new JPanel();
    JPanel painelleste = new JPanel();
    JPanel paineloeste = new JPanel();

    JLabel lbId = new JLabel("ID Pessoa");
    JTextField tfId = new JTextField(15);
    JButton botaobuscar = new JButton(" BUSCAR ");
    JButton botaoadicionar = new JButton(" ADICIONAR ");
    JButton botaosalvar = new JButton(" SALVAR ");
    JButton botaoalterar = new JButton(" ALTERAR ");
    JButton botaoexcluir = new JButton(" EXCLUIR ");
    JButton botaolistar = new JButton(" LISTAR ");
    JButton botaocancelar = new JButton(" CANCELAR ");

    // como formatar o codigo : voce aperta { ALT + SHIFT + F }
    JLabel lbnome = new JLabel("nome", JLabel.CENTER);
    JTextField tfnome = new JTextField(30);
    JLabel lbCpf = new JLabel("Cpf", JLabel.CENTER);
    JTextField tfCpf = new JTextField(30);
    JLabel lbAltura = new JLabel("Altura", JLabel.CENTER);
    JTextField tfAltura = new JTextField(30);
    JLabel lbCep = new JLabel("Cep", JLabel.CENTER);
    JTextField tfCep = new JTextField(30);
    JLabel lbDataNascimento = new JLabel("Data de Nascimento", JLabel.CENTER);
    JTextField tfDataNascimento = new JTextField(30);

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
    DAOPessoa daoPessoa = new DAOPessoa();
    Pessoa pessoa = new Pessoa();

    UsarGridBagLayout usarGridBagLayout = new UsarGridBagLayout(painelcentro);

    public PessoaGUI() {
        dialogo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = dialogo.getContentPane();
        cp.setLayout(new BorderLayout());
        dialogo.setTitle(" Pessoa - CRUD");

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
        usarGridBagLayout.add(lbnome, tfnome);
        usarGridBagLayout.add(lbCpf, tfCpf);
        usarGridBagLayout.add(lbAltura, tfAltura);
        usarGridBagLayout.add(lbCep, tfCep);
        usarGridBagLayout.add(lbDataNascimento, tfDataNascimento);

        tfDataNascimento.setEditable(false);
        tfAltura.setEditable(false);
        tfnome.setEditable(false);
        tfCep.setEditable(false);
        tfCpf.setEditable(false);

        lbDataNascimento.setFont(new Font("Arial", Font.BOLD, 18));
        tfDataNascimento.setFont(new Font("Arial", Font.BOLD, 18));
        lbAltura.setFont(new Font("Arial", Font.BOLD, 18));
        tfAltura.setFont(new Font("Arial", Font.BOLD, 18));
        lbCpf.setFont(new Font("Arial", Font.BOLD, 18));
        lbCep.setFont(new Font("Arial", Font.BOLD, 18));
        lbnome.setFont(new Font("Arial", Font.BOLD, 18));

        // borda dos centro 
        tfDataNascimento.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        tfAltura.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        tfnome.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        tfCep.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        tfCpf.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
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
            pessoa = daoPessoa.obter(Integer.valueOf(tfId.getText()));
            if (pessoa != null) {
                botaoalterar.setVisible(true);
                botaoexcluir.setVisible(true);
                botaoadicionar.setVisible(false);
                tfnome.setText(pessoa.getNome());
                tfCep.setText(pessoa.getCep());
                tfCpf.setText(pessoa.getCpf());
                tfDataNascimento.setText(simpleDateFormat.format(pessoa.getDatanascimento()));
                tfAltura.setText(String.valueOf(pessoa.getAltura()));
                tfDataNascimento.setEditable(false);
                tfAltura.setEditable(false);

                botaosalvar.setVisible(false);

            } else {

                tfDataNascimento.setText("");
                tfAltura.setText("");
                tfnome.setText("");
                tfCep.setText("");
                tfCpf.setText("");
                botaoadicionar.setVisible(true);
                tfDataNascimento.setEditable(false);
                tfAltura.setEditable(false);
                tfnome.setEditable(false);
                tfCep.setEditable(false);
                tfCpf.setEditable(false);

                botaosalvar.setVisible(false);
                botaoalterar.setVisible(false);
                botaoexcluir.setVisible(false);

            }
        });

        botaoadicionar.addActionListener((ActionEvent ae) -> {
            tfId.setEditable(false);
            tfnome.requestFocus();
            tfDataNascimento.setEditable(true);
            tfAltura.setEditable(true);
            tfnome.setEditable(true);
            tfCep.setEditable(true);
            tfCpf.setEditable(true);

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
                    pessoa = new Pessoa();
                    Integer id = Integer.valueOf(tfId.getText());
                    Double altura = Double.valueOf(tfAltura.getText());
                    String cpf = tfCpf.getText();
                    String cep = tfCep.getText();
                    String nome = tfnome.getText();
                    pessoa.setIdpessoa(id);
                    pessoa.setNome(nome);
                    pessoa.setAltura(altura);
                    pessoa.setCpf(cpf);
                    pessoa.setCep(cep);
                    try {
                        pessoa.setDatanascimento(simpleDateFormat.parse(tfDataNascimento.getText()));
                    } catch (ParseException ex) {
                        System.out.println("deu ruim na data");
                    }
                    daoPessoa.inserir(pessoa);
                } else {
                    Integer id = Integer.valueOf(tfId.getText());
                    Double altura = Double.valueOf(tfAltura.getText());
                    String cpf = tfCpf.getText();
                    String cep = tfCep.getText();
                    String nome = tfnome.getText();
                    pessoa.setIdpessoa(id);
                    pessoa.setNome(nome);
                    pessoa.setAltura(altura);
                    pessoa.setCpf(cpf);
                    pessoa.setCep(cep);
                    try {
                        pessoa.setDatanascimento(simpleDateFormat.parse(tfDataNascimento.getText()));
                    } catch (ParseException ex) {
                        System.out.println("deu ruim na data");
                    }
                    daoPessoa.atualizar(pessoa);
                }

                botaosalvar.setVisible(false);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfId.setText("");
                tfDataNascimento.setText("");
                tfAltura.setText("");
                tfnome.setText("");
                tfCep.setText("");
                tfCpf.setText("");

                tfDataNascimento.setEditable(false);
                tfAltura.setEditable(false);
                tfnome.setEditable(false);
                tfCep.setEditable(false);
                tfCpf.setEditable(false);

                botaobuscar.setVisible(true);
            } catch (NumberFormatException errou) {
                System.out.println("deu bosta ao salvar");
                JOptionPane.showMessageDialog(null, "voce digitou algo estranho", "erro no salvamento", JOptionPane.PLAIN_MESSAGE);
                tfDataNascimento.requestFocus();

                botaocancelar.setVisible(true);
                tfDataNascimento.setEditable(true);
                tfAltura.setEditable(true);
                tfnome.setEditable(true);
                tfCep.setEditable(true);
                tfCpf.setEditable(true);
            }
        });

        botaoalterar.addActionListener((ActionEvent ae) -> {
            botaolistar.setVisible(false);
            botaoexcluir.setVisible(false);
            botaobuscar.setVisible(false);
            botaoalterar.setVisible(false);
            tfId.setEditable(false);
            tfnome.requestFocus();
            tfDataNascimento.setEditable(true);
            tfAltura.setEditable(true);
            tfnome.setEditable(true);
            tfCep.setEditable(true);
            tfCpf.setEditable(true);

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
                tfDataNascimento.setText("");
                tfAltura.setText("");
                tfnome.setText("");
                tfCep.setText("");
                tfCpf.setText("");
                tfDataNascimento.setEditable(false);
                tfAltura.setEditable(false);
                tfnome.setEditable(false);
                tfCep.setEditable(false);
                tfCpf.setEditable(false);
                daoPessoa.remover(pessoa);
            } else {
                System.out.println(" OS DADOS DO ATLETA NÃO FORAM APAGADOS.");

            }
        });

        botaolistar.addActionListener((ActionEvent ae) -> {
            List<Pessoa> pessoa1 = daoPessoa.list();
            String[] colunas1 = new String[]{"id", "nome", "cpf", "altura", "idade", "data de nascimento"};
            Object[][] dados1 = new Object[pessoa1.size()][colunas1.length];
            String aux[];
            for (int i = 0; i < pessoa1.size(); i++) {
                aux = pessoa1.get(i).toString().split(";");
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
            tfDataNascimento.setText("");
            tfAltura.setText("");
            tfnome.setText("");
            tfCep.setText("");
            tfCpf.setText("");
            tfDataNascimento.setEditable(false);
            tfAltura.setEditable(false);
            tfnome.setEditable(false);
            tfCep.setEditable(false);
            tfCpf.setEditable(false);

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
