package Model;

import java.io.Serializable;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    String nome;
    double[] notas;

    public Aluno(String nome, double[] notas) {
        this.nome = nome;
        this.notas = notas;
    }

    public double calcularMedia() {
        Arrays.sort(notas);
        return (notas[1] + notas[2]) / 2;
    }

    public String situacao() {
        double media = calcularMedia();
        if (media >= 7.0) {
            return "aprovado(a).";
        } else if (media >= 3.0) {
            return "em recuperação.";
        } else {
            return "reprovado(a).";
        }
    }

    public boolean calculoRecuperacao() {
        double media = calcularMedia();
        if (media >= 3.0 && media < 7.0) {
            double notaNecessaria = 10.0 - media;
            JOptionPane.showMessageDialog(null, "Você precisa tirar: " + notaNecessaria + " para ser aprovado.");
            String input = JOptionPane.showInputDialog("Digite a nota na prova final:");
            try {
                double resultado = Double.parseDouble(input);
                if (resultado >= notaNecessaria) {
                    JOptionPane.showMessageDialog(null, "Você foi aprovado, parabéns!");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Você foi reprovado.");
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Nota inválida. Tente novamente.");
                return false;
            }
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double[] getNotas() {
        return notas;
    }

    public void setNotas(double[] notas) {
        this.notas = notas;
    }
}
