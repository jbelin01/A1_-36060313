package br.edu.up;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    public static List<Aluno> lerAlunosDoArquivo(String caminhoArquivo) {
        List<Aluno> listaDeAlunos = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            leitor.readLine(); // Pular cabeçalho
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                String matricula = dados[0];
                String nome = dados[1];
                double nota = Double.parseDouble(dados[2].replace(",", "."));
                Aluno aluno = new Aluno(matricula, nome, nota);
                listaDeAlunos.add(aluno);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaDeAlunos;
    }

    public static void processarDados(List<Aluno> listaDeAlunos) {
        double somaNotas = 0;
        double menorNota = Double.MAX_VALUE;
        double maiorNota = Double.MIN_VALUE;
        int quantidadeAprovados = 0;
        int quantidadeReprovados = 0;

        for (Aluno aluno : listaDeAlunos) {
            double nota = aluno.getNota();
            somaNotas += nota;
            if (nota < menorNota) {
                menorNota = nota;
            }
            if (nota > maiorNota) {
                maiorNota = nota;
            }
            if (nota >= 6.0) {
                quantidadeAprovados += 1;
            } else {
                quantidadeReprovados += 1;
            }
        }

        double mediaNotas = somaNotas / listaDeAlunos.size();

        gravarResumo("src/br/edu/up/resumo.csv", listaDeAlunos.size(), quantidadeAprovados, quantidadeReprovados, menorNota, maiorNota, mediaNotas);
    }

    public static void gravarResumo(String caminhoArquivoResumo, int quantidadeAlunos, int quantidadeAprovados, int quantidadeReprovados, double menorNota, double maiorNota, double mediaNotas) {
        try (FileWriter escritor = new FileWriter(caminhoArquivoResumo)) {
            escritor.write("Quantidade de alunos; " + quantidadeAlunos + ";\n");
            escritor.write("Quantidade de alunos aprovados; " + quantidadeAprovados + ";\n");
            escritor.write("Quantidade de alunos reprovados; " + quantidadeReprovados + ";\n");
            escritor.write("Menor nota; " + menorNota + ";\n");
            escritor.write("Maior nota; " + maiorNota + ";\n");
            escritor.write("Média das notas; " + mediaNotas + ";\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
