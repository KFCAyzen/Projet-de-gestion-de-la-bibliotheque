package main.java.org.example;

public class AlgorithmesRecherche {

    public static Livre rechercheLineaireParTitre(Livre[] livres, String titre) {
        for( Livre livre : livres ) {
            if (livre != null && livre.getTitre().equalsIgnoreCase(titre)) {
                return livre;
            }
        }
        return null;
    }

    public static Livre rechercheBinaireParTitre(Livre[] livres, String titre) {
        int gauche = 0, droite = livres.length;

        while( gauche <= droite ) {
            int milieu = gauche + (droite -gauche)/2;
            int cmp = livres[milieu].getTitre().compareToIgnoreCase(titre);
            if (cmp == 0) return livres[milieu];
            if (cmp < 0) gauche = milieu + 1;
            else droite = milieu - 1;
        }
        return null;
    }

    public static Livre rechercheLineaireParAuteur(Livre[] livres, String auteur) {
        for (Livre livre : livres) {
            if (livre != null && livre.getAuteur().equalsIgnoreCase(auteur)) {
                return livre;
            }
        }
        return null;
    }

    public static Livre rechercheLineaireParIsbn(Livre[] livres, String isbn) {
        for (Livre livre : livres) {
            if (livre != null && livre.getIsbn().equals(isbn)) {
                return livre;
            }
        }
        return null;
    }
}
