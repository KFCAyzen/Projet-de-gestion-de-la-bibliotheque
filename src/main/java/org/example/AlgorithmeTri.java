package main.java.org.example;

public class AlgorithmeTri {

    public static void triBullesParTitre(Livre[] livres, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (livres[j].getTitre().compareToIgnoreCase(livres[j + 1].getTitre()) > 0) {
                    echanger(livres, j, j + 1);
                }
            }
        }
    }

    public static void triSelectionParAuteur(Livre[] livres, int n) {
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (livres[j].getAuteur().compareToIgnoreCase(livres[minIndex].getAuteur()) < 0) {
                    minIndex = j;
                }
            }
            echanger(livres, i, minIndex);
        }
    }

    public static void triRapideParAnnee(Livre[] livres, int debut, int fin) {
        if (debut < fin) {
            int pivotIndex = partitionnerParAnnee(livres, debut, fin);
            triRapideParAnnee(livres, debut, pivotIndex - 1);
            triRapideParAnnee(livres, pivotIndex + 1, fin);
        }
    }

    private static int partitionnerParAnnee(Livre[] livres, int debut, int fin) {
        int pivot = livres[fin].getAnneePublication();
        int i = debut - 1;
        for (int j = debut; j < fin; j++) {
            if (livres[j].getAnneePublication() <= pivot) {
                i++;
                echanger(livres, i, j);
            }
        }
        echanger(livres, i + 1, fin);
        return i + 1;
    }

    private static void echanger(Livre[] livres, int i, int j) {
        Livre temp = livres[i];
        livres[i] = livres[j];
        livres[j] = temp;
    }
}