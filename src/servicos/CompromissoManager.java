package servicos;

import entities.Agenda;
import entities.Compromisso;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CompromissoManager {

    private final Agenda agenda;
    private final Scanner scan;
    private final DateTimeFormatter dataFormatada;

    public CompromissoManager() {
        agenda = new Agenda();
        scan = new Scanner(System.in);
        dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        carregarCompromissos();
    }

    public Agenda getAgenda() {
        return agenda;
    }

    private void carregarCompromissos() {
        try {
            List<Compromisso> compromissosSalvos = FileManager.carregar();
            for (Compromisso c : compromissosSalvos) {
                agenda.adiciona(c);
            }
            System.out.println("Compromissos carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar compromissos: " + e.getMessage());
        }
    }

    public void adicionarCompromissos() {
        System.out.print("Digite o título do compromisso: ");
        String nome = scan.nextLine();

        System.out.print("Digite o tipo do compromisso: ");
        String tipo = scan.nextLine();

        System.out.print("Digite a data e hora do compromisso (dd/MM/yyyy HH:mm): ");
        String dataStr = scan.nextLine();

        try {
            LocalDateTime dataHora = LocalDateTime.parse(dataStr, dataFormatada);
            Compromisso novo = new Compromisso(nome, tipo, dataHora);
            agenda.adiciona(novo);

            FileManager.salvar(agenda.listar());
            System.out.println("Compromisso adicionado com sucesso:");
            System.out.println(novo);

        } catch (DateTimeException e) {
            System.out.println("Data e hora em formato inválido! Use dd/MM/yyyy HH:mm");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o compromisso: " + e.getMessage());
        }
    }
}
