package Controller;

import Model.Aluno;
import Model.Disciplina;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Controlador {
    // Lista de disciplinas
    private ArrayList<Disciplina> listaDisciplinas;

    // Caminho para salvar o arquivo binário
    private final String FILE_PATH = "dados.bin";

    // Construtor que inicializa a lista de disciplinas e carrega os dados do arquivo
    public Controlador() {
        listaDisciplinas = new ArrayList<>();
        carregarDados();  // Carrega os dados salvos no início
    }

    // Método para salvar os dados no arquivo
    public void salvarDados() {
        try (ObjectOutputStream dados = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            dados.writeObject(listaDisciplinas);  // Escreve a lista de disciplinas no arquivo
            System.out.println("Dados salvos com sucesso!");  // Mensagem para confirmação
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + e.getMessage());
        }
    }

    // Método para carregar os dados do arquivo
    public void carregarDados() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Arquivo de dados não encontrado. Criando novo arquivo...");
            return;  // Se o arquivo não existir, simplesmente sai do método
        }

        try (ObjectInputStream dadosSalvos = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            listaDisciplinas = (ArrayList<Disciplina>) dadosSalvos.readObject();  // Lê os dados e carrega na lista
            System.out.println("Dados carregados com sucesso!");  // Mensagem de confirmação
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados: " + e.getMessage());
        }
    }

    // Método para cadastrar uma nova disciplina
    public void cadastrarDisciplina(String nomeDisciplina) {
        Disciplina novaDisciplina = new Disciplina(nomeDisciplina);
        listaDisciplinas.add(novaDisciplina);  // Adicionando a nova disciplina à lista
    }

    // Método para matricular um aluno em uma disciplina específica
    public void matricularAluno(int indexDisciplina, Aluno aluno) {
        if (indexDisciplina >= 0 && indexDisciplina < listaDisciplinas.size()) {
            Disciplina disciplina = listaDisciplinas.get(indexDisciplina);
            disciplina.addAluno(aluno);  // Matriculando o aluno na disciplina
        } else {
            JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
        }
    }

    // Método para listar todas as disciplinas cadastradas
    public void listarDisciplinas() {
        if (listaDisciplinas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma disciplina cadastrada.");
            return;
        }

        String disciplinas = "Disciplinas cadastradas:\n";
        for (int i = 0; i < listaDisciplinas.size(); i++) {
            disciplinas += i + ": " + listaDisciplinas.get(i).getNomeDisciplina() + "\n";
        }
        JOptionPane.showMessageDialog(null, disciplinas);
    }

    // Método para listar todos os alunos matriculados em uma disciplina, incluindo as notas e a situação
    public void listarAlunos(int indexDisciplina) {
        // Verifica se o índice da disciplina é válido
        if (indexDisciplina >= 0 && indexDisciplina < listaDisciplinas.size()) {
            Disciplina disciplina = listaDisciplinas.get(indexDisciplina);  // Obtendo a disciplina

            // Verifica se há alunos matriculados na disciplina
            if (disciplina.getAlunosMatriculados().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum aluno matriculado na disciplina " + disciplina.getNomeDisciplina());
                return;
            }

            // Construindo uma string com os alunos matriculados na disciplina, suas notas e situação
            String alunosList = "Alunos matriculados na disciplina " + disciplina.getNomeDisciplina() + ":\n";
            for (Aluno aluno : disciplina.getAlunosMatriculados()) {
                double[] notas = aluno.getNotas();
                String situacao = aluno.situacao();  // Obtendo a situação do aluno

                alunosList += "Aluno: " + aluno.getNome() + "\n";
                alunosList += "Notas: " + notas[0] + ", " + notas[1] + ", " + notas[2] + "\n";
                alunosList += "O aluno está " + situacao.toLowerCase() + "\n\n";
            }
            JOptionPane.showMessageDialog(null, alunosList);  // Exibindo os alunos matriculados, suas notas e situação
        } else {
            JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
        }
    }

    // Método para editar uma disciplina pelo índice
    public void editarDisciplina(int index, String novoNome) {
        if (index >= 0 && index < listaDisciplinas.size()) {
            Disciplina disciplina = listaDisciplinas.get(index);
            disciplina.setNomeDisciplina(novoNome);
        } else {
            JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
        }
    }

    // Método para excluir uma disciplina pelo índice
    public void excluirDisciplina(int index) {
        if (index >= 0 && index < listaDisciplinas.size()) {
            listaDisciplinas.remove(index);
        } else {
            JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
        }
    }
}
