package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;

    String nomeDisciplina;
    ArrayList<Aluno> alunosMatriculados;

    public Disciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
        this.alunosMatriculados = new ArrayList<>();
    }

    public void editarNome(String novoNome) {
        this.nomeDisciplina = novoNome;
        System.out.println("Nome alterado para: " + nomeDisciplina);
    }

    public void addAluno(Aluno aluno) {
        alunosMatriculados.add(aluno);
        System.out.println("Aluno " + aluno.getNome() + " matriculado com sucesso em: " + nomeDisciplina);
    }

    public String listarAlunos() {
        StringBuilder listaAlunos = new StringBuilder();
        listaAlunos.append("Alunos matriculados em ").append(nomeDisciplina).append(":\n");
        if (!alunosMatriculados.isEmpty()) {
            for (Aluno aluno : alunosMatriculados) {
                listaAlunos.append(aluno.getNome()).append("\n");
            }
        } else {
            listaAlunos.append("Nenhum aluno matriculado.\n");
        }
        return listaAlunos.toString();
    }

    public void removerAluno(String nomeAluno) {
        Aluno alunoARemover = null;
        for (Aluno aluno : alunosMatriculados) {
            if (aluno.getNome().equals(nomeAluno)) {
                alunoARemover = aluno;
                break;
            }
        }

        if (alunoARemover != null) {
            alunosMatriculados.remove(alunoARemover);
            System.out.println("Aluno " + nomeAluno + " removido da disciplina " + nomeDisciplina);
        } else {
            System.out.println("Aluno " + nomeAluno + " não encontrado.");
        }
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public ArrayList<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void setAlunosMatriculados(ArrayList<Aluno> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }
}
