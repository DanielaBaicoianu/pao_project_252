package Entitati;

public class Recenzie {
    private String numeClient;
    private String comentariu;
    private int rating; // Rating on a scale from 1 to 5

    public Recenzie(String numeClient, String comentariu, int rating) {
        this.numeClient = numeClient;
        this.comentariu = comentariu;
        this.rating = rating;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
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

    @Override
    public String toString() {
        return "Recenzie de la " + numeClient + ": " + comentariu + " (Rating: " + rating + ")";
    }
}
