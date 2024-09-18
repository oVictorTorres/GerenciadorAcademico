package View;

import Controller.Controlador;
import Model.Aluno;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Interface extends JFrame {
    Controlador controlador;
    JLabel nomeLabel, nota1Label, nota2Label, nota3Label;
    JTextField nomeField, nota1Field, nota2Field, nota3Field;
    JButton calcular, adicionarDisciplina, listarDisciplinas, listarAlunos, cadastrarAluno, editarDisciplina, excluirDisciplina;
    JTextArea situacaoArea;

    public Interface() {
        super("Gerenciamento de Alunos e Disciplinas");
        controlador = new Controlador();

        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Layout manual sem Layout Manager
        setSize(600, 600);
        setLocationRelativeTo(null);  // Centraliza a janela na tela
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controlador.salvarDados();  // Salva os dados ao fechar o programa
            }
        });

        // Inicializando os componentes
        nomeLabel = new JLabel("Nome do Aluno:");
        nomeLabel.setBounds(20, 60, 120, 25);
        add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(150, 60, 200, 25);
        add(nomeField);

        nota1Label = new JLabel("Nota 1:");
        nota1Label.setBounds(20, 100, 80, 25);
        add(nota1Label);

        nota1Field = new JTextField();
        nota1Field.setBounds(150, 100, 50, 25);
        add(nota1Field);

        nota2Label = new JLabel("Nota 2:");
        nota2Label.setBounds(20, 140, 80, 25);
        add(nota2Label);

        nota2Field = new JTextField();
        nota2Field.setBounds(150, 140, 50, 25);
        add(nota2Field);

        nota3Label = new JLabel("Nota 3:");
        nota3Label.setBounds(20, 180, 80, 25);
        add(nota3Label);

        nota3Field = new JTextField();
        nota3Field.setBounds(150, 180, 50, 25);
        add(nota3Field);

        calcular = new JButton("Calcular Situação");
        calcular.setBounds(150, 220, 150, 30);
        add(calcular);

        adicionarDisciplina = new JButton("Adicionar disciplina");
        adicionarDisciplina.setBounds(20, 20, 200, 30);
        add(adicionarDisciplina);

        listarDisciplinas = new JButton("Listar disciplinas");
        listarDisciplinas.setBounds(240, 20, 200, 30);
        add(listarDisciplinas);

        listarAlunos = new JButton("Listar Alunos");
        listarAlunos.setBounds(240, 260, 200, 30);
        add(listarAlunos);

        cadastrarAluno = new JButton("Cadastrar Aluno");
        cadastrarAluno.setBounds(20, 260, 200, 30);
        add(cadastrarAluno);

        editarDisciplina = new JButton("Editar Disciplina");
        editarDisciplina.setBounds(20, 300, 200, 30);
        add(editarDisciplina);

        excluirDisciplina = new JButton("Excluir Disciplina");
        excluirDisciplina.setBounds(240, 300, 200, 30);
        add(excluirDisciplina);

        situacaoArea = new JTextArea();
        situacaoArea.setBounds(20, 340, 330, 60);
        situacaoArea.setEditable(false);
        add(situacaoArea);

        // Ações dos botões
        calcular.addActionListener(this::calcular);
        adicionarDisciplina.addActionListener(this::adicionarDisciplina);
        listarDisciplinas.addActionListener(this::listarDisciplinas);
        listarAlunos.addActionListener(this::listarAlunos);
        cadastrarAluno.addActionListener(this::cadastrarAluno);
        editarDisciplina.addActionListener(this::editarDisciplina);
        excluirDisciplina.addActionListener(this::excluirDisciplina);

        setVisible(true);
    }

    // Método para calcular a situação do aluno
    private void calcular(ActionEvent e) {
        try {
            String nome = nomeField.getText();
            double nota1 = Double.parseDouble(nota1Field.getText());
            double nota2 = Double.parseDouble(nota2Field.getText());
            double nota3 = Double.parseDouble(nota3Field.getText());

            // Criando o objeto Aluno com as notas fornecidas
            Aluno aluno = new Aluno(nome, new double[]{nota1, nota2, nota3});

            // Calcula a situação inicial do aluno
            String situacao = aluno.situacao();

            // Exibe a situação do aluno
            situacaoArea.setText("Aluno: " + nome + " - Situação: " + situacao);

            // Se o aluno estiver em recuperação, chama a lógica da recuperação
            if (situacao.equals("Você está em recuperação.")) {
                boolean passou = aluno.calculoRecuperacao();
                if (passou) {
                    situacaoArea.setText("Aluno: " + nome + " - Aprovado após a recuperação!");
                } else {
                    situacaoArea.setText("Aluno: " + nome + " - Reprovado após a recuperação.");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, insira valores numéricos válidos para as notas.");
        }
    }

    // Método para adicionar uma disciplina
    private void adicionarDisciplina(ActionEvent e) {
        String nomeDisciplina = JOptionPane.showInputDialog("Digite o nome da disciplina");
        if (nomeDisciplina != null && !nomeDisciplina.trim().isEmpty()) {
            controlador.cadastrarDisciplina(nomeDisciplina);
            JOptionPane.showMessageDialog(null, "Disciplina adicionada com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Nome da disciplina não pode estar vazio.");
        }
    }

    // Método para listar disciplinas
    private void listarDisciplinas(ActionEvent e) {
        controlador.listarDisciplinas();
    }

    // Método para listar alunos
    private void listarAlunos(ActionEvent e) {
        String disciplinaIndex = JOptionPane.showInputDialog("Digite o índice da disciplina:");
        if (disciplinaIndex != null && !disciplinaIndex.trim().isEmpty()) {
            try {
                int index = Integer.parseInt(disciplinaIndex);
                controlador.listarAlunos(index);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um índice válido.");
            }
        }
    }

    // Método para cadastrar um aluno em uma disciplina
    private void cadastrarAluno(ActionEvent e) {
        try {
            String nomeAluno = JOptionPane.showInputDialog("Digite o nome do aluno:");
            if (nomeAluno == null || nomeAluno.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome do aluno não pode estar vazio.");
                return;
            }

            double nota1 = Double.parseDouble(JOptionPane.showInputDialog("Digite a Nota 1:"));
            double nota2 = Double.parseDouble(JOptionPane.showInputDialog("Digite a Nota 2:"));
            double nota3 = Double.parseDouble(JOptionPane.showInputDialog("Digite a Nota 3:"));

            Aluno aluno = new Aluno(nomeAluno, new double[]{nota1, nota2, nota3});

            String disciplinaIndex = JOptionPane.showInputDialog("Digite o índice da disciplina para matrícula:");
            int index = Integer.parseInt(disciplinaIndex);

            controlador.matricularAluno(index, aluno);

            JOptionPane.showMessageDialog(null, "Aluno " + nomeAluno + " cadastrado com sucesso na disciplina.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro: Por favor, insira valores numéricos válidos para as notas.");
        }
    }

    // Método para editar uma disciplina
    private void editarDisciplina(ActionEvent e) {
        String disciplinaIndex = JOptionPane.showInputDialog("Digite o índice da disciplina que deseja editar:");
        if (disciplinaIndex != null && !disciplinaIndex.trim().isEmpty()) {
            try {
                int index = Integer.parseInt(disciplinaIndex);
                String novoNome = JOptionPane.showInputDialog("Digite o novo nome da disciplina:");
                if (novoNome != null && !novoNome.trim().isEmpty()) {
                    controlador.editarDisciplina(index, novoNome);
                    JOptionPane.showMessageDialog(null, "Disciplina editada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "O nome da disciplina não pode estar vazio.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um índice válido.");
            }
        }
    }

    // Método para excluir uma disciplina
    private void excluirDisciplina(ActionEvent e) {
        String disciplinaIndex = JOptionPane.showInputDialog("Digite o índice da disciplina que deseja excluir:");
        if (disciplinaIndex != null && !disciplinaIndex.trim().isEmpty()) {
            try {
                int index = Integer.parseInt(disciplinaIndex);
                controlador.excluirDisciplina(index);
                JOptionPane.showMessageDialog(null, "Disciplina excluída com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um índice válido.");
            }
        }
    }
}
