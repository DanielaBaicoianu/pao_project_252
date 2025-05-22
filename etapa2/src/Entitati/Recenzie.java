package Entitati;

import java.util.Date;

public class Recenzie {
    private int id;
    private Client client;
    private Produs produs;
    private String comentariu;
    private int rating; // Rating on a scale from 1 to 5
    private Date dataCreare;

    public Recenzie(Client client, Produs produs, String comentariu, int rating) {
        this.client = client;
        this.produs = produs;
        this.comentariu = comentariu;
        this.rating = rating;
        this.dataCreare = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Produs getProdus() {
        return produs;
    }

    public void setProdus(Produs produs) {
        this.produs = produs;
    }

    public String getComentariu() {
        return comentariu;
    }

    public void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDataCreare() {
        return dataCreare;
    }

    public void setDataCreare(Date dataCreare) {
        this.dataCreare = dataCreare;
    }

    @Override
    public String toString() {
        return "Recenzie de la " + client.getNume() + " pentru " + produs.getNume() + 
               ": " + comentariu + " (Rating: " + rating + ")";
    }
}
