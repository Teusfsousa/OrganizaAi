package servicos;

public class Principal {
    public static void main(String[] args) {

        CompromissoManager compromissoManager = new CompromissoManager();
        MenuManager menuManager = new MenuManager(compromissoManager);
        menuManager.exibirMenu();


    }
}
