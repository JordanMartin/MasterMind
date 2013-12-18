package mastermind;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Jordan
 */
public class Combination
{

    /**
     *
     * @param args
     */
    public static void main(String[] args)
      {

        while (true) {
            Combination a = new Combination(4);
            a.askPegsFromConsole();
            Combination c = new Combination(4);
            c.askPegsFromConsole();

            c.compare(a);
            System.out.println(c);
        }
      }
    // Une couleur ne pouvant pas se trouver dans une combinaison
    /**
     *
     */
    public static final Color UNUSABLE_COLOR = Color.lightGray;
    // Toutes les couleurs possibles
    /**
     *
     */
    public static final ArrayList<Color> POSSIBLE_COLORS = new ArrayList(8)
    {
        {
            add(Color.BLUE);
            add(Color.GREEN);
            add(Color.ORANGE);
            add(Color.YELLOW);
            add(Color.GRAY);
            add(Color.RED);
            add(Color.MAGENTA);
            add(Color.PINK);
            add(Color.CYAN);
        }
    };
    int width; // Nombre de pions de la combinaison
    ArrayList<Color> pegs; // Represente la combinaison de width pions
    Integer black = null; // Nombre de pions de bonne couleur mais mal plac�s
    Integer white = null; // Nombre de pions bien plac�s et de bonne couleur

    /**
     *
     * @param width
     */
    public Combination(int width)
      {
        pegs = new ArrayList(width);
        this.width = width;

        for (int i = 0; i < width; i++) {
            pegs.add(null);
        }
      }

    /**
     * D�finit la couleur du pion � la position pos dans la combinaison
     *
     * @param pos   Position du pion dont on veut changer la couleur
     * @param color Nouvelle couleur du pion
     */
    public void setPeg(int pos, Color color)
      {
        if (pos < width) {
            pegs.set(pos, color);
        }
        else {
            System.exit(5);
        }
      }

    /**
     * D�finit la couleur de tous les pions de la combinaison � la m�me couleur
     *
     * @param color nouvelle couleur de tous les pions
     */
    public void setSamePegToAll(Color color)
      {
        // Comme al combinaison est modifi� on remet � z�ro les indicateurs
        black = 0;
        white = 0;
        for (int i = 0; i < width; i++) {
            pegs.set(i, color);
        }
      }

    /**
     *
     */
    public void askPegsFromConsole()
      {

        Scanner s = new Scanner(System.in);
        ArrayList<Integer> combinationInt;

        do {
            System.out.print("Entrez les pions de la combinaison s�par�s par un espace (" + width + " pions) : ");
            String combinationString = s.nextLine();

            combinationInt = new ArrayList(width);

            for (int i = 0; i < combinationString.length(); i++) {
                char tmpC = combinationString.charAt(i);

                if (tmpC == ' ') {
                    continue;
                }
                if (Character.isDigit(tmpC)) {
                    combinationInt.add(Character.getNumericValue(tmpC) - 1);
                }
                else {
                    break;
                }
            }

            if (combinationInt.size() != width) {
                System.out.println("Combinaison entr�e incorrect. Elle doit : \n"
                    + " - Contenir " + width + " entiers s�par�s par un espace\n"
                    + " - Chaque entier doit �tre compris entre 0 et " + (POSSIBLE_COLORS.size() - 1) + "\n"
                    + "R�esayez");
            }
            else {
                break;
            }

        } while (true);


        for (int i = 0; i < combinationInt.size(); i++) {
            setPeg(i, POSSIBLE_COLORS.get(combinationInt.get(i)));
        }
      }

    /**
     * Attribu une couleur choisi al�atoirement � chaque pions de la combinaison
     */
    public void randomCombination()
      {
        for (int i = 0; i < width; i++) {
            pegs.set(i, getRandomColor());
        }

        black = null;
        white = null;
      }

    /**
     * En plus de retourner vrai au faux, les variables white et black de
     * l'objet sont mises � jours
     *
     * @param comb combinaison � comparer
     * @return vrai si elles sont identiques, faut sinon
     */
    public boolean compare(Combination comb)
      {

        black = 0;
        white = 0;

        // Contiendra les positions des pions d�j� contr�l�s
        ArrayList<Integer> pegsAffected = new ArrayList(width);

        for (int i = 0; i < width; i++) {
            // Test si le pions � la position i est bien plac�
            if (pegs.get(i) == comb.pegs.get(i)) {
                white++;
                pegsAffected.add(i);
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                // Test si un pions est de bonne couleur mais mal plac�
                if (!pegsAffected.contains(j) && comb.pegs.get(j) == pegs.get(i)) {
                    black++;
                    pegsAffected.add(j);
                }
            }
        }

        return this.equals(comb);
      }

    /**
     * @param o la combinaison � tester
     * @return vrai si les deux combinaisons sont les m�mes
     */
    @Override
    public boolean equals(Object o)
      {

        if (!(o instanceof Combination)) {
            return false;
        }

        Combination combination = (Combination) o;

        if (this.width != combination.width) {
            return false;
        }

        for (int i = 0; i < width; i++) {
            if (pegs.get(i) == null || !pegs.get(i).equals(combination.pegs.get(i))) {
                return false;
            }
        }

        return true;
      }

    /**
     * @return une couleur parmis celle pr�sente dans POSSIBLE_COLORS
     */
    private Color getRandomColor()
      {
        // Retourne un entier entre 0 et width 
        int randomIndice = (int) (Math.random() * 1000) % (POSSIBLE_COLORS.size());

        return POSSIBLE_COLORS.get(randomIndice);
      }

    /**
     * Retourne la combinaison sans les pions noirs et blancs
     *
     * @return un String cotenant la combinaison
     */
    public String get()
      {
        String ret = "";

        for (Color c : pegs) {
            ret += colorToInt(c);
            ret += " ";
        }

        return ret;
      }

    @Override
    public String toString()
      {
        String ret = "";

        for (Color c : pegs) {
            ret += colorToInt(c);
            ret += " ";
        }

        ret += " | " + white + "W " + black + "B";

        return ret;
      }

    /**
     * Converti une couleur en entier. Pour les tests
     *
     * @param color
     * @return
     */
    public int colorToInt(Color color)
      {

        int i = 1;

        if (color == null) {
            return -1;
        }

        for (Color c : POSSIBLE_COLORS) {
            if (color.equals(c)) {
                return i;
            }
            i++;
        }

        return 0;
      }

    @Override
    public int hashCode()
      {
        int hash = 3;
        hash = 53 * hash + this.width;
        hash = 53 * hash + Objects.hashCode(this.pegs);
        return hash;
      }
}
