import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;

public class Carte {
    private char[][] carte;
    private int largeur;
    private int hauteur;
    private int positionX;
    private int positionY;

    public Carte(String cheminFichier) {
        chargerCarteDepuisFichier(cheminFichier);
    }

    private void chargerCarteDepuisFichier(String cheminFichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            StringBuilder carteBuilder = new StringBuilder();

            while ((ligne = br.readLine()) != null) {
                carteBuilder.append(ligne).append("\n");
            }

            initialiserCarte(carteBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialiserCarte(String carteStr) {
        String[] lignes = carteStr.split("\n");
        hauteur = lignes.length;
        largeur = lignes[0].length();
        System.out.println(hauteur + "; "+ largeur);
        carte = new char[largeur][hauteur];

        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                carte[i][j] = lignes[j].charAt(i);
            }
        }
    }

    public void afficherCarte() {
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                System.out.print(carte[i][j]);
            }
            System.out.println();
        }
    }

    public void deplacerPersonnage(int x, int y, String deplacements) {
        positionX = x;
        positionY = y;

        for (char direction : deplacements.toCharArray()) {
            switch (direction) {
                case 'O':
                    if (positionX > 0 && carte[positionX - 1][positionY] == ' ') {
                        carte[positionX][positionY] = ' ';
                        positionX--;
                        System.out.println(positionX
                                +" "+direction);
                    }
                    break;
                case 'E':
                    if (positionX < largeur  && carte[positionX + 1][positionY] == ' ') {
                        carte[positionX][positionY] = ' ';
                        positionX++;

                        System.out.println(positionX
                                +" "+direction);
                    }
                    break;
                case 'S':
                    if (positionY < hauteur  && carte[positionX][positionY + 1] == ' ') {
                        carte[positionX][positionY] = ' ';
                        positionY++;

                        System.out.println(positionY
                                +" "+direction);
                    }
                    break;
                case 'N':
                    if (positionY > 0 && carte[positionX][positionY - 1] == ' ') {
                        carte[positionX][positionY] = ' ';
                        positionY--;

                        System.out.println(positionY
                                +" "+direction);
                    }
                    break;
            }
        }

        // Mettre à jour la position du personnage après les déplacements
        carte[positionX][positionY] = 'P';
    }
    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public static void main(String[] args) {
        // Premier test
        // Premier test
        String cheminFichierTest1 = "C:/Users/pc/downloads/carte v2.txt";
        Carte carteTest1 = new Carte(cheminFichierTest1);
        carteTest1.afficherCarte();

        int departXTest1 = 3; // coordonnées initiales
        int departYTest1 = 0; // coordonnées initiales
        String deplacementsTest1 = "SSSSEEEEEENN"; // déplacements réels

        carteTest1.deplacerPersonnage(departXTest1, departYTest1, deplacementsTest1);
        carteTest1.afficherCarte();
        System.out.println("Coordonnées finales du personnage pour le test 1 : (" + carteTest1.getPositionX() + ", " + carteTest1.getPositionY() + ")");
        System.out.println("Résultat attendu pour le test 1 : (9,2)");
        // Deuxième test

        Carte carteTest2 = new Carte(cheminFichierTest1);
        carteTest2.afficherCarte();

        int departXTest2 = 6; // coordonnées initiales
        int departYTest2 = 7; // coordonnées initiales
        String deplacementsTest2 = "OONOOOSSO"; // déplacements réels

        carteTest2.deplacerPersonnage(departXTest2, departYTest2, deplacementsTest2);
        carteTest2.afficherCarte();

       System.out.println("Coordonnées finales du personnage pour le test 2 : (" + carteTest2.getPositionX() + ", " + carteTest2.getPositionY() + ")");



    }



}