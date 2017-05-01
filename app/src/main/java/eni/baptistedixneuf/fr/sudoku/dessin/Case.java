package eni.baptistedixneuf.fr.sudoku.dessin;

import java.util.List;

/**
 * Created by baptiste on 30/04/2017.
 */

public class Case {

    private int x;
    private int y;
    private int number;

    /**
     * Fonction qui permet de savoir si dans une liste de case, une case en postion x,y est déjà prise par un nombre
     * @param cases liste de cases
     * @param futurCase futurCase que lon doit vérifier
     * @return boolean qui permet de svoir si la case est vide
     */
    public static boolean isCaseVide(List<Case> cases, Case futurCase){
        boolean isCaseVide = true;
        for (Case itemCase: cases) {
            if(itemCase.getX() == futurCase.getX() && itemCase.getY() == futurCase.getY()){
                isCaseVide = false;
            }
        }
        return isCaseVide;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
