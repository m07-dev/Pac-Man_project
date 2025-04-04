import java.util.Random;
import javax.swing.JOptionPane;

public class PacMan {

    public static char[][] startPlateau(int taille, int nb_fantome, char[][] plateau) {
        plateau = new char[taille][taille];
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                plateau[i][j] = '.'; // case vide
            }
        }
        for (int i = 0; i < nb_fantome; i++) {
            Random l = new Random();
            Random m = new Random();
            plateau[l.nextInt(taille - 1)][(m.nextInt(taille - 1))] = 'f';
        }
        return plateau;

    }

    public static void afficheplateau(char[][] plateau) {
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                System.out.print(plateau[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {

        int taille = Integer.valueOf(JOptionPane.showInputDialog("Entrez la taille du tableau"));
        int nb_fantome = Integer.valueOf(JOptionPane.showInputDialog("Entrez le nombre de fantomes"));

        char[][] plateau = startPlateau(taille, nb_fantome, null);

        plateau[taille / 2][taille / 2] = 'p';
        int positionX = taille / 2;
        int positionY = taille / 2;

        afficheplateau(plateau);

        boolean fantomes_recu = true;
        while (fantomes_recu) {
            String coup = JOptionPane.showInputDialog(" g pour gauche, d pour droite, h pour haut, b pour bas");

            // Vérifiez si le mouvement est valide avant de modifier la position
            int newX = positionX;
            int newY = positionY;

            if (coup.equals("g")) {
                newY--;
            } else if (coup.equals("d")) {
                newY++;
            } else if (coup.equals("h")) {
                newX--;
            } else if (coup.equals("b")) {
                newX++;
            } else {
                JOptionPane.showMessageDialog(null, "Coup invalide! Entrez g, d, h ou b.");
                continue;
            }
            ;

            if ((newX < 0 || newX >= taille) || (newY < 0 || newY >= taille)) {
                coup = JOptionPane.showInputDialog("Coup Invalide, quel est le prochain le coup du joueur");
            }
            ;
            if ((plateau[newX][newY] == 'f')) {
                System.out.println("VOus avez percuter un fantome vous êtes out of the game");
                fantomes_recu = false;
                break;

            }
            ;
            // Déplacez le joueur
            plateau[positionX][positionY] = '.';
            positionX = newX;
            positionY = newY;
            plateau[positionX][positionY] = 'p';

            // Affichez le plateau
            afficheplateau(plateau);
        }

    }

}
