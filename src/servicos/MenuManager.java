package servicos;

import entities.Compromisso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MenuManager {

    private final Scanner scan = new Scanner(System.in);
    private final CompromissoManager compromissoManager;

    public MenuManager(CompromissoManager compromissoManager) {
        this.compromissoManager = compromissoManager;
    }

    public void exibirMenu() {
        int opcaoMenu;
        do {
            System.out.println("------ Bem-vindo ao OrganizaAi! ------");
            System.out.println("1 - Adicionar um novo Compromisso");
            System.out.println("2 - Listar todos os compromissos agendados");
            System.out.println("3 - Sair do menu");
            System.out.println("4 - Buscar compromisso por data");
            System.out.println("5 - Remover Compromisso");
            System.out.print("Escolha uma das opções: ");

            opcaoMenu = scan.nextInt();
            scan.nextLine();

            switch (opcaoMenu) {
                case 1:
                    compromissoManager.adicionarCompromissos();
                    break;

                case 2:
                    listarCompromissos();
                    break;

                case 3:
                    System.out.println("Menu finalizado!");
                    break;

                case 4:
                  buscarPorData();
                   break;

                case 5:
                  removerCompromisso();
                  break;
                default:
                    System.out.println("Você digitou uma opção inválida! Digite novamente");
            }

        } while (opcaoMenu != 3);

    }

    private void listarCompromissos() {
        System.out.print("---- Lista de Compromissos ----");
        System.out.println();
        if (compromissoManager.getAgenda().listar().isEmpty()) {
            System.out.println("Nenhum compromisso agendado.");
        } else {
            for (Compromisso c : compromissoManager.getAgenda().listar()) {
                System.out.println(c);
            }
        }
    }
    public void buscarPorData(){
        System.out.print("Digite a data que deseja buscar (dd/MM/yyyy): ");
        String buscaData = scan.nextLine();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate date = LocalDate.parse(buscaData, dateTimeFormatter);
            List<Compromisso> compromissos = compromissoManager.getAgenda().buscarCompromisso(date);

            if(compromissos.isEmpty()){
                System.out.println("Nenhum compromisso foi encontrado para essa data");
            }
            else {
                System.out.println("Compromissos encontrados: ");
                for (Compromisso c : compromissos){
                    System.out.println(c);
                }
            }

        }
        catch (Exception e){
            System.out.println("A data digitada está inválida! Digite no formato (dd/MM/yyyy)");
        }
    }
    public void removerCompromisso (){
        List<Compromisso> lista = compromissoManager.getAgenda().listar();

        if (lista.isEmpty()) {
            System.out.println("Não há compromissos para remover");
            return;
        }

        System.out.println("Compromissos agendados:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i));
        }
        System.out.println();
        System.out.print("Digite o número do compromisso que deseja remover: ");
        int escolha = scan.nextInt();
        scan.nextLine();
        scan.nextLine();
        if (escolha < 1 || escolha > lista.size()) {
            System.out.println("Número inválido!");
            return;
        }

        Compromisso c = lista.get(escolha - 1);
        compromissoManager.getAgenda().remove(c);

        try {

            FileManager.salvar(compromissoManager.getAgenda().listar());
            System.out.println("Compromisso removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }
}

