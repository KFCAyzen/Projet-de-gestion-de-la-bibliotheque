package main.java.org.example;


import java.util.Scanner;
public class SystemeGestionBibliotheque {


        private static final int CAPACITE_MAX = 100;
        private Livre[] livres = new Livre[CAPACITE_MAX];
        private int nbLivres = 0;
        private PileActivites pileActivites = new PileActivites();

        public static void main(String[] args) {
            new SystemeGestionBibliotheque().demarrer();
        }

        private void demarrer() {
            Scanner scanner = new Scanner(System.in);
            initialiserDonneesExemple();

            while (true) {
                System.out.println("\n Système de Gestion de Bibliothèque ");
                System.out.println("1. Ajouter un livre");
                System.out.println("2. Supprimer un livre (par ISBN)");
                System.out.println("3. Modifier un livre (par ISBN)");
                System.out.println("4. Rechercher un livre");
                System.out.println("5. Trier les livres");
                System.out.println("6. Voir les activités récentes");
                System.out.println("7. Quitter");
                System.out.print("Choisissez une option : ");
                int choix = scanner.nextInt();
                scanner.nextLine(); // consommer le retour chariot

                switch (choix) {
                    case 1 -> ajouterLivre(scanner);
                    case 2 -> supprimerLivre(scanner);
                    case 3 -> modifierLivre(scanner);
                    case 4 -> rechercherLivre(scanner);
                    case 5 -> trierLivres(scanner);
                    case 6 -> pileActivites.afficherActivitesRecentes(5);
                    case 7 -> {
                        System.out.println("Au revoir !");
                        return;
                    }
                    default -> System.out.println("Option invalide.");
                }
            }
        }

        private void initialiserDonneesExemple() {
            ajouterLivreSilent("1984", "George Orwell", "978-0451524935", 1949, "Dystopie");
            ajouterLivreSilent("Ne tirez pas sur l'oiseau moqueur", "Harper Lee", "978-0061120084", 1960, "Roman");
            ajouterLivreSilent("Gatsby le Magnifique", "F. Scott Fitzgerald", "978-0743273565", 1925, "Classique");
            pileActivites.empiler("Données d'exemple initialisées.");
        }

        private void ajouterLivreSilent(String titre, String auteur, String isbn, int annee, String genre) {
            if (nbLivres < CAPACITE_MAX) {
                livres[nbLivres++] = new Livre(titre, auteur, isbn, annee, genre);
            }
        }

        private void ajouterLivre(Scanner scanner) {
            System.out.print("Titre : ");
            String titre = scanner.nextLine();
            System.out.print("Auteur : ");
            String auteur = scanner.nextLine();
            System.out.print("ISBN : ");
            String isbn = scanner.nextLine();
            System.out.print("Année de publication : ");
            int annee = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Genre : ");
            String genre = scanner.nextLine();

            if (nbLivres < CAPACITE_MAX) {
                livres[nbLivres++] = new Livre(titre, auteur, isbn, annee, genre);
                pileActivites.empiler("Ajout : " + titre);
                System.out.println("Livre ajouté.");
            } else {
                System.out.println("La bibliothèque est pleine !");
            }
        }

        private void supprimerLivre(Scanner scanner) {
            System.out.print("ISBN du livre à supprimer : ");
            String isbn = scanner.nextLine();
            for (int i = 0; i < nbLivres; i++) {
                if (livres[i].getIsbn().equals(isbn)) {
                    for (int j = i; j < nbLivres - 1; j++) {
                        livres[j] = livres[j + 1];
                    }
                    livres[--nbLivres] = null;
                    pileActivites.empiler("Suppression : ISBN " + isbn);
                    System.out.println("Livre supprimé.");
                    return;
                }
            }
            System.out.println(" Livre non trouvé.");
        }

        private void modifierLivre(Scanner scanner) {
            System.out.print("ISBN du livre à modifier : ");
            String isbn = scanner.nextLine();
            for (int i = 0; i < nbLivres; i++) {
                if (livres[i].getIsbn().equals(isbn)) {
                    System.out.print("Nouveau titre (laisser vide pour ne pas changer) : ");
                    String titre = scanner.nextLine();
                    if (!titre.isEmpty()) livres[i].setTitre(titre);

                    System.out.print("Nouvel auteur : ");
                    String auteur = scanner.nextLine();
                    if (!auteur.isEmpty()) livres[i].setAuteur(auteur);

                    System.out.print("Nouvelle année : ");
                    String anneeStr = scanner.nextLine();
                    if (!anneeStr.isEmpty()) livres[i].setAnneePublication(Integer.parseInt(anneeStr));

                    System.out.print("Nouveau genre : ");
                    String genre = scanner.nextLine();
                    if (!genre.isEmpty()) livres[i].setGenre(genre);

                    pileActivites.empiler("Modification : ISBN " + isbn);
                    System.out.println(" Livre mis à jour.");
                    return;
                }
            }
            System.out.println(" Livre non trouvé.");
        }

        private void rechercherLivre(Scanner scanner) {
            System.out.println("Rechercher par :");
            System.out.println("1. Titre (recherche linéaire)");
            System.out.println("2. Titre (recherche binaire – nécessite tri par titre)");
            System.out.println("3. Auteur (recherche linéaire)");
            System.out.println("4. ISBN (recherche linéaire)");
            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            Livre resultat = null;
            switch (choix) {
                case 1 -> {
                    System.out.print("Titre : ");
                    resultat = AlgorithmesRecherche.rechercheLineaireParTitre(livres, scanner.nextLine());
                }
                case 2 -> {
                    AlgorithmeTri.triBullesParTitre(livres, nbLivres);
                    System.out.print("Titre : ");
                    resultat = AlgorithmesRecherche.rechercheBinaireParTitre(livres, scanner.nextLine());
                }
                case 3 -> {
                    System.out.print("Auteur : ");
                    resultat = AlgorithmesRecherche.rechercheLineaireParAuteur(livres, scanner.nextLine());
                }
                case 4 -> {
                    System.out.print("ISBN : ");
                    resultat = AlgorithmesRecherche.rechercheLineaireParIsbn(livres, scanner.nextLine());
                }
                default -> {
                    System.out.println("Type de recherche invalide.");
                    return;
                }
            }

            if (resultat != null) {
                System.out.println(" Trouvé : " + resultat);
                pileActivites.empiler("Recherche : " + resultat.getTitre());
            } else {
                System.out.println(" Aucun livre trouvé.");
            }
        }

    private void trierLivres(Scanner scanner) {
        if (nbLivres == 0) {
            System.out.println(" Aucun livre à trier.");
            return;
        }

        System.out.println("Trier par :");
        System.out.println("1. Titre (Tri à bulles)");
        System.out.println("2. Auteur (Tri par sélection)");
        System.out.println("3. Année de publication (Tri rapide)");
        System.out.print("Votre choix : ");
        int choix = scanner.nextInt();

        long debut = System.nanoTime();
        switch (choix) {
            case 1 -> {
                AlgorithmeTri.triBullesParTitre(livres, nbLivres);
                pileActivites.empiler("Tri par titre (à bulles).");
            }
            case 2 -> {
                AlgorithmeTri.triSelectionParAuteur(livres, nbLivres);
                pileActivites.empiler("Tri par auteur (sélection).");
            }
            case 3 -> {
                AlgorithmeTri.triRapideParAnnee(livres, 0, nbLivres - 1);
                pileActivites.empiler("Tri par année (rapide).");
            }
            default -> {
                System.out.println("Option de tri invalide.");
                return;
            }
        }
        long fin = System.nanoTime();
        System.out.printf(" Tri effectué en %.3f ms.\n", (fin - debut) / 1_000_000.0);
    }
}
