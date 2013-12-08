package mastermind;

import exception.CombinationException;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Jordan
 */
public class Combination {    
    
    public static void main(String args[]){
        
        Combination guess = new Combination(4);
        guess.randomCombination();
        
        Combination b = new Combination(4);
        b.randomCombination();
        
        System.out.println(b);
        System.out.println(guess);
        
        b.compare(guess);
        
        System.out.println("WHITE : " + b.white);
        System.out.println("BLACK : " + b.black);
    }
    
    public static final Color UNUSABLE_COLOR = Color.lightGray;
    public static final ArrayList<Color> POSSIBLE_COLORS =  new ArrayList(8){
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
    
    Integer black = null; // Nombre de pions de bonne couleur mais mal placés
    Integer white = null; // Nombre de pions bien placés et de bonne couleur
    
    Combination(int width){
        pegs = new ArrayList(width);
        this.width = width;
        
        for(int i = 0; i < width; i++)
            pegs.add(null);
    }   
    
    void setPeg(int pos, Color color) {
        if(pos < width)
            pegs.set(pos, color);
        else 
            System.exit(5);
    }
    
    void setSamePegToAll(Color color)
    {
        // Comme al combinaison est modifié on remet à zéro les indicateurs
        black = 0;
        white = 0;
        for(int i = 0; i < width; i++)
            pegs.set(i, color);
    }
    
    /**
     * Attribu une couleur choisi aléatoirement à chaque pions de la combinaison
     */
    void randomCombination(){
        for(int i = 0; i < width; i++)
            pegs.set(i, getRandomColor());
        
        black = null;
        white = null;
    }
    
    /**
     * En plus de retourner vrai au faux, les variables white et black de l'objet 
     * sont mises à jours
     * @param comb combinaison à comparer
     * @return vrai si elles sont identiques, faut sinon
     */
    boolean compare(Combination comb){
        
        black = 0;
        white = 0;
        
        // Contiendra les positions des pions déjà contrôlés
        ArrayList<Integer> pegsAffected = new ArrayList(width);
        
        for(int i = 0; i < width; i++){    
            // Le pion est de bonne couleur et bien placé
            
            if(pegs.get(i) == comb.pegs.get(i))
            {
                pegsAffected.add(i);
                white++;
            }
            // La couleur d'un pion est bonne mais mal placé (on vérifie que le pions n'est pas déjà affecté)
            else if(comb.pegs.contains(pegs.get(i)) && !pegsAffected.contains(i))
            {
                int indiceOfPegs = comb.pegs.indexOf(pegs.get(i)); // Indice du pions mal placé mais de bonne couleur dans la solution
                pegsAffected.add(indiceOfPegs);
                black++;
            }
        }   
        
        return this.equals(comb);
    }
    
    /**
     * @param o la combinaison à tester
     * @return vrai si les deux combinaisons sont les mêmes
     */
    @Override
    public boolean equals(Object o){
        
        if(!(o instanceof Combination))
            return false;
        
        Combination combination = (Combination) o;
        
        if(this.width != combination.width)
            return false;
        
        for(int i = 0; i < width; i++)
        {
            if(pegs.get(i) == null || !pegs.get(i).equals(combination.pegs.get(i)))
                return false;
        }
        
        return true;
    }
    
    /**
     * @return une couleur parmis celle présente dans POSSIBLE_COLORS 
     */
    private Color getRandomColor(){
        // Retourne un entier entre 0 et width 
        int randomIndice = (int)(Math.random()*1000) % (POSSIBLE_COLORS.size()); 
        
        return POSSIBLE_COLORS.get(randomIndice);
    }    
    
    /**
     * Retourne la combinaison sans les pions noirs et blancs
     * @return un String cotenant la combinaison
     */
    public String get(){
        String ret = "";
        
        for(Color c : pegs){
            ret += colorToInt(c); 
            ret += " ";
        }
        
        return ret;
    }
    
    @Override
    public String toString(){
        String ret = "";
        
        for(Color c : pegs){
            ret += colorToInt(c); 
            ret += " ";
        }
        
        ret += " | " + white + "W " + black + "B";
        
        return ret;
    }
    
    /**
     * Converti une couleur en entier. Pour les test
     * @param color
     * @return 
     */
    int colorToInt(Color color){
        
        int i = 1;
        
        if(color == null)
            return -1;
        
        for(Color c : POSSIBLE_COLORS){            
            if(color.equals(c))
                return i;            
            i++;
        }
        
        return 0;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.width;
        hash = 53 * hash + Objects.hashCode(this.pegs);
        return hash;
    }
}
