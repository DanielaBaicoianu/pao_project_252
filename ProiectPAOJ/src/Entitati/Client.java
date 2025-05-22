package Entitati;

import Repozitorii.RuteRepo;
import Servicii.ServiciuClient;

import java.time.LocalDate;
import java.util.Date;

import static Utile.UtileClient.idCurent;

public class Client {
    private final int idClient;
    private String nume;
    private String email;
    private String parola;
    private String nrTelefon;
    private LocalDate dataNasterii;

    public Client(String nume, String email, String parola, String nrTelefon, LocalDate dataNasterii) {
        this.nume = nume;
        this.email = email;
        this.parola = parola;
        this.nrTelefon = nrTelefon;
        this.dataNasterii = dataNasterii;
        this.idClient = idCurent;
        idCurent++;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }
}
