package servicos;

import entities.Compromisso;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String ARQUIVO_COMPROMISSO = "C:\\OrganizaAi\\meusCompromissos.txt";

    public static void salvar (List<Compromisso> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_COMPROMISSO))) {
            for (Compromisso c : lista) {
                bw.write(c.getNomeCompromisso() + " ; " + c.getTipoDeCompromisso() + " ; " + c.getDataEhoraCompromisso());
                bw.newLine();
            }
        }
    }

    public static List<Compromisso> carregar() throws IOException {
        List<Compromisso> lista = new ArrayList<>();
        File file = new File(ARQUIVO_COMPROMISSO);
        if (!file.exists()) return lista;

        try (BufferedReader bf = new BufferedReader(new FileReader(ARQUIVO_COMPROMISSO))) {
            String linha;

            while ((linha = bf.readLine()) != null) {
                String[] partes = linha.split(" ; ");
                if (partes.length == 3) {
                    String titulo = partes[0];
                    String descricao = partes[1];
                    LocalDateTime dataEhora = LocalDateTime.parse(partes[2]);
                    lista.add(new Compromisso(titulo, descricao, dataEhora));

                }
                else {
                    System.out.println("A uma linha inválida no arquivo! " + linha);
                }
            }
            return lista;
        }

    }
}
