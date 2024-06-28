package br.edu.up;

import java.util.List;

public class App {

    public static void main(String[] args) {
        String caminhoArquivo = "src/br/edu/up/Alunos.csv";

        List<Aluno> listaDeAlunos = Arquivo.lerAlunosDoArquivo(caminhoArquivo);

        Arquivo.processarDados(listaDeAlunos);

        System.out.println("Arquivo resumo.csv criado com sucesso!");
    }
}
