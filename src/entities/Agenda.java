package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private List<Compromisso> compromissos = new ArrayList<>();

    public void adiciona ( Compromisso c ){
        compromissos.add(c);

    }

    public void remove ( Compromisso c){
        compromissos.remove(c);
    }

    public List<Compromisso> listar(){
        return compromissos;
    }

    public List<Compromisso> buscarCompromisso (LocalDate date){
        return  compromissos.stream().filter(compromissos -> compromissos.getDataEhoraCompromisso().toLocalDate()
                .equals(date)).toList();
    }
}
