package mastermind;

import java.awt.Color;
import java.util.ArrayList;

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
    
    
    /*public static final ArrayList<Color> POSSIBLE_COLORS =  new ArrayList(8){
        {
            add(Color.BLUE);
            add(Color.GREEN);
            add(Color.ORANGE);
            add(Color.YELLOW);
            add(Color.GRAY);
            add(Color.RED);
            add(Color.MAGENTA);
            add(Color.PINK);
        }
    };*/
    
    public static final ArrayList<Integer> POSSIBLE_COLORS =  new ArrayList(8){
        {
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }
    };
    
    int width; // Nombre de pions de la combinaison
    ArrayList<Integer> pegs; // Represente la combinaison de width pions
    
    Integer black = null; // Nombre de pions de bonne couleur mais mal placés
    Integer white = null; // Nombre de pions bien placés et de bonne couleur
    
    Combination(int width){
        pegs = new ArrayList(width);
        this.width = width;
        
        for(int i = 0; i < width; i++)
            pegs.add(null);
    }   
    
    void setPeg(int pos, int color) throws Exception {
        if(pos < width)
            pegs.set(pos, color);
        else 
            throw new Exception("Dépassement d'indice");
    }
    
    /**
     * Attribu une couleur choisi aléatoirement à chaque pions de la combinaison
     */
    void randomCombination(){
        for(int i = 0; i < pegs.size(); i++)
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
        ArrayList<Integer> pegsAffected = new ArrayList(pegs.size());
        
        for(int i = 0; i < this.pegs.size(); i++){    
            // Le pion est de bonne couleur et bien placé
            if(pegs.get(i) == comb.pegs.get(i))
            {
                pegsAffected.add(i);
                white++;
            }
            // La couleur d'un pion est bonne mais mal placé (on vérifie que le pions n'est pas déjà affecté)
            else if(comb.pegs.contains(pegs.get(i)) && !pegsAffected.contains(i))
            {
                pegsAffected.add(i);
                black++;
            }
        }        
        return this.equalsTo(comb);
    }
    
    /**
     * @param combination la combinaison à tester
     * @return vrai si les deux combinaisons sont les mêmes
     */
    @SuppressWarnings({"null", "ConstantConditions"})
    boolean equalsTo(Combination combination){
        
        if(this.width != combination.width || combination == null)
            return false;
        
        for(int i = 0; i < pegs.size(); i++)
        {
            if(!pegs.get(i).equals(combination.pegs.get(i)))
                return false;
        }
        
        return true;
    }
    
    /**
     * @return une couleur parmis celle présente dans POSSIBLE_COLORS 
     */
    private Integer getRandomColor(){
        int randomIndice = (int)(Math.random()*1000) % (POSSIBLE_COLORS.size()); // Retourne un entier entre 0 et width  
        return POSSIBLE_COLORS.get(randomIndice);
    }    
    
    @Override
    public String toString(){
        String ret = "";
        
        for(Integer c : pegs){
            ret += c; 
            ret += " ";
        }
        return ret;
    }
}
