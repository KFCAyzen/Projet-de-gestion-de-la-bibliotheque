package main.java.org.example;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueEmprunts {
    private class Noeud{
        String emprunteur;
        Noeud next;
        Noeud(String emprunteur){
            this.emprunteur = emprunteur;
            this.next = null;
        }
    }

    private Noeud head;

    public void ajouterEmprunteur(String emprunteur){
        Noeud nouveauNoeud = new Noeud(emprunteur);
        if(head == null){
            head = nouveauNoeud;
        } else{
            Noeud current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = nouveauNoeud;
        }
    }
    public List<String> getEmprunteur(){
        List<String> emprunteurs = new ArrayList<>();
        Noeud current = head;
        while(head != null){
            emprunteurs.add(head.emprunteur);
            current = current.next;
        }
        return emprunteurs;
    }
}
