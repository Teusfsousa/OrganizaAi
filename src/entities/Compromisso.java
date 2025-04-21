package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Compromisso {
    DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
    private String nomeCompromisso;
    private String tipoDeCompromisso;
    private LocalDateTime dataEhoraCompromisso;

    public Compromisso(String nomeCompromisso, String tipoDeCompromisso, LocalDateTime dataEhoraCompromisso) {
        this.nomeCompromisso = nomeCompromisso;
        this.tipoDeCompromisso = tipoDeCompromisso;
        this.dataEhoraCompromisso = dataEhoraCompromisso;
    }

    public String getNomeCompromisso() {
        return nomeCompromisso;
    }

    public void setNomeCompromisso(String nomeCompromisso) {
        this.nomeCompromisso = nomeCompromisso;
    }

    public String getTipoDeCompromisso() {
        return tipoDeCompromisso;
    }

    public void setTipoDeCompromisso(String tipoDeCompromisso) {
        this.tipoDeCompromisso = tipoDeCompromisso;
    }

    public LocalDateTime getDataEhoraCompromisso() {
        return dataEhoraCompromisso;
    }

    public void setDataEhoraCompromisso(LocalDateTime dataEhoraCompromisso) {
        this.dataEhoraCompromisso = dataEhoraCompromisso;
    }

    @Override
    public String toString() {
        return
                "Titulo do compromisso agendado: " + nomeCompromisso +
                ", Tipo de compromisso: " + tipoDeCompromisso +
                ", Data e hora agendada para: " + dataEhoraCompromisso.format(dt)
                ;
    }
}
