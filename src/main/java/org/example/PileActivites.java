package main.java.org.example;

import java.util.ArrayList;
import java.util.List;

public class PileActivites {
    private List<String> pile = new ArrayList<>();

    public void empiler(String activite) {
        pile.add(activite);
    }

    public String depiler() {
        if (!pile.isEmpty()) {
            return pile.remove(pile.size() - 1);
        }
        return null;
    }

    public String consulterSommet(){
        if (!pile.isEmpty()) {
            return pile.get(pile.size() - 1);
        }
        return null;
    }

    public boolean estVide(){
        return pile.isEmpty();
    }

    public void afficherActivitesRecentes(int n){
        int debut = Math.max(0, pile.size() - n);
        System.out.println("\n " + (pile.size() < n ? pile.size() : n) + "activites recentes.");
        for (int i = pile.size() -1; i >= debut; i--) {
            System.out.println((pile.size() - i) + " . " + pile.get(i));
        }
    }
}
