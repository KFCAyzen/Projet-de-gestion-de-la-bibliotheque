package main.java.org.example;

public class Livre {
    private String titre;
    private String auteur;
    private String isbn;
    private int anneePublication;
    private String genre;

    public Livre(String titre, String auteur, String isbn, int anneePublication, String genre) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.anneePublication = anneePublication;
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return String.format("Titre : " + titre + "Auteur : " + auteur + "ISBN : " + isbn + "anneePublication : " + anneePublication + "Genre : " + genre);
    }
}
