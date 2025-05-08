package Entitati;

import java.util.UUID;

public abstract class Utilizator {
    private final UUID id = UUID.randomUUID();
    private String nume;
    private String email;
    private String parola;

    protected Utilizator(String nume, String email, String parola) {
        this.nume = nume;  this.email = email;  this.parola = parola;
    }

    public String getNume() { return nume; }
    public UUID  getId()           { return id; }
    public String getEmail()       { return email; }
    public boolean checkPass(String p){ return parola.equals(p); }
}
