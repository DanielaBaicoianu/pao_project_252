package Entitati;

public class Client implements Comparable<Client> {
    private int id;
    private String nume;
    private String email;
    private int numarComenzi;
    private String categorie;

    public Client(int id, String nume, String email) {
        this.id = id;
        this.nume = nume;
        this.email = email;
        this.numarComenzi = 0;
        actualizeazaCategorie();
    }

    public Client(Client other) {
        this.id = other.id;
        this.nume = other.nume;
        this.email = other.email;
        this.numarComenzi = other.numarComenzi;
        this.categorie = other.categorie;
    }

    public void plaseazaComanda() {
        numarComenzi++;
        actualizeazaCategorie();
    }

    private void actualizeazaCategorie() {
        if (numarComenzi >= 15) {
            categorie = "PREMIUM";
        } else if (numarComenzi >= 5) {
            categorie = "SILVER";
        } else {
            categorie = "BASIC";
        }
    }

    public int getId() {
        return id;
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

    public int getNumarComenzi() {
        return numarComenzi;
    }

    public String getCategorie() {
        return categorie;
    }

    @Override
    public int compareTo(Client other) {
        return this.email.compareTo(other.email);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nume + " - " + categorie + " Client (comenzi: " + numarComenzi + ")";
    }
}
