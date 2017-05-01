package eni.baptistedixneuf.fr.sudoku.dessin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bdixneuf2015 on 28/04/2017.
 */
public class Grid extends View  implements View.OnTouchListener{

    public String initSudoku;

    public static final int GRID_WIDTH = 9;
    public static final int GRID_HEIGHT = 9;
    public static final int HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER = 500;
    public static final int MARGE_BETWEEN_GRID_AND_BOTTOM = 50;

    //Oles dimensions de l'écran
    private int displayWidth;
    private int displayHeight;

    //largeur d'une case
    private int caseWidth;

    //la hauteur d'une case
    private int caseHeight;

    //Object servant au touchEvent
    private int numberSelected;
    private Map<String,Integer> mapNumberSelected;
    private Map<String,Integer> mapDeplacement;

    //Liste des cases présentes dans la grille
    private List<Case> cases;


    public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
        cases = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init(canvas);
        initSudokuNumbers(canvas);
        drawGrid(canvas);
        drawButtons(canvas);
        drawNumbers(canvas);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                Log.d("BDF", "actionDown");

                // On réinitaliase les nombres en mouvement + nombre sélectionné
                mapDeplacement = new HashMap<>();
                numberSelected = 0;
                mapNumberSelected = null;

                // On récupère le nouveau nombre sélectionné
                mapNumberSelected = detectElementClicked(x, y);
                if(mapNumberSelected.get("numberSelected") != null ){
                    numberSelected = mapNumberSelected.get("numberSelected") + 1;
                    Log.d("BDF", "nombre sélectionné " + numberSelected);
                }

                break;
            case MotionEvent.ACTION_MOVE :
                Log.d("BDF", "actionMove");

                //On affiche le déplcement en cours du cchiffre sélectionné
                if(mapNumberSelected != null && numberSelected != 0) {
                    mapDeplacement = new HashMap<>();
                    mapDeplacement.put("x", x);
                    mapDeplacement.put("y", y);
                }

                break;
            case MotionEvent.ACTION_UP :
                Log.d("BDF", "actionUp");

                Map<String, Integer> mapPositionFinal;

                //On enregistre le nombre dans la grille
                if( numberSelected != 0 ) {
                    mapPositionFinal = detectElementClicked(x, y);

                    //On vérifie que l'on a  une position finale où enregistrer le nombre
                    if(mapPositionFinal.get("x") != null && mapPositionFinal.get("y") != null) {
                        Log.d("BDF", "x " + mapPositionFinal.get("x") + ", y" + mapPositionFinal.get("y"));

                        //On initialise la nouvelle case
                        Case newCase = new Case();
                        newCase.setNumber(numberSelected);
                        newCase.setX(mapPositionFinal.get("x"));
                        newCase.setY(mapPositionFinal.get("y"));
                        newCase.setLocked(false);

                        //On vérifie que la case n'est pas déjà remplie
                        boolean isCaseVide = Case.isCaseVide(cases, newCase);

                        //Si la case est vide, on ajoute le nombre à la grille
                        if(isCaseVide) {
                            cases.add(newCase);
                        }
                    }

                }

                // On réinitialiase les déplacements de nombre en cours
                mapDeplacement = new HashMap<>();
                mapPositionFinal = null;
                numberSelected = 0;
                mapNumberSelected= null;

            default: return false;
        }

        this.invalidate();

        return true;
    }

    private void init(Canvas canvas){
        //On réucpère les dimensions de l'écran
        displayWidth = canvas.getWidth();
        displayHeight = canvas.getHeight();

        //Calcul de la largeur d'une case
        caseWidth = displayWidth/GRID_WIDTH;

        //Calcul de la hauteur d'une case
        caseHeight = (displayHeight- HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER)/GRID_HEIGHT;
    }

    private void initSudokuNumbers(Canvas canvas){

        //On dessine les chiffres
        for(int i = 0; i < initSudoku.length(); i++){
            String text = ""+initSudoku.charAt(i);
            if(!text.equals("0")) {
                //On initialise la nouvelle case
                Case newCase = new Case();
                newCase.setNumber(Integer.parseInt(text));
                newCase.setX((i%9));
                newCase.setY((int) i/9);
                newCase.setLocked(true);
                cases.add(newCase);
            }
        }
    }

    private void drawGrid(Canvas canvas){

        //On dessine les lignes verticales
        for (int i= 0; i <= GRID_WIDTH; i++){
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            if(i % 3 == 0) {
                paint.setStrokeWidth(8);
            } else{
                paint.setStrokeWidth(3);
            }
            canvas.drawLine(caseWidth * i, 0, caseWidth *i, displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER, paint);
        }

        //On dessine les lignes horizontales
        for (int i= 0; i <= GRID_HEIGHT ; i++){
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            if(i % 3 == 0) {
                paint.setStrokeWidth(8);
            } else{
                paint.setStrokeWidth(3);
            }
            canvas.drawLine(0, caseHeight * i, displayWidth,caseHeight * i , paint);
        }

    }

    private void drawButtons(Canvas canvas){

        //On dessine les lignes verticales
        for (int i= 0; i <= GRID_WIDTH ; i++){
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            if(i % 3 == 0) {
                paint.setStrokeWidth(8);
            } else{
                paint.setStrokeWidth(3);
            }
            canvas.drawLine(caseWidth * i, displayHeight-HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER + MARGE_BETWEEN_GRID_AND_BOTTOM,
                    caseWidth *i, displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER + MARGE_BETWEEN_GRID_AND_BOTTOM + caseHeight, paint);
        }

        //On dessine les lignes horizontales
        for (int i= 0; i < 2 ; i++){
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(5);
            canvas.drawLine(0,  displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER + MARGE_BETWEEN_GRID_AND_BOTTOM + (caseHeight * i),
                    displayWidth, displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER + MARGE_BETWEEN_GRID_AND_BOTTOM + (caseHeight * i ), paint);
        }

        //On dessine les chiffres
        for(int i = 0; i < 9; i++){
            String text = ""+(i+1);
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setTextSize(100);
            canvas.drawText(text,0,text.length(),
                    (int) ((caseWidth*i) + (caseWidth/3)),
                    (int ) (displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER +  MARGE_BETWEEN_GRID_AND_BOTTOM + (caseHeight/1.2)), paint );
        }

    }

    private void drawNumbers(Canvas canvas){

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(100);

        //Déplacement en cours d'un nombre
        if(mapDeplacement != null){
            if(mapDeplacement.get("x") != null && mapDeplacement.get("y") != null){
                String text = ""+numberSelected;
                canvas.drawText(text,0,text.length(),
                        mapDeplacement.get("x"),
                        mapDeplacement.get("y"), paint );
            }
        }

        //Affichage des nombres de la grille
        for (Case itemCase : cases ) {
            String text = ""+itemCase.getNumber();
            if(itemCase.isLocked()){
                paint.setColor(Color.GREEN);
            }else{
                paint.setColor(Color.RED);
            }
            canvas.drawText(text,0,text.length(),
                    (int) ((caseWidth*itemCase.getX()) + (caseWidth/3)),
                    (int ) ((caseHeight*itemCase.getY()) + (caseHeight/1.2)), paint );
        }
    }

    private Map<String,Integer> detectElementClicked(int x, int y){

        Map<String,Integer> map = new HashMap<>();
        Log.d("BDF","Origine x : "  + x + ", Origine y :" + y );

        /* Log.d("BDF"," Partie 1 => y inférieur à "+ (displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER));
        Log.d("BDF"," Partie 2 => y supérieur à "+ (displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER + MARGE_BETWEEN_GRID_AND_BOTTOM) +
                " et inférieur à" + (displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER + MARGE_BETWEEN_GRID_AND_BOTTOM + caseHeight)); */


        //On détecte dans quel partie l'utilisateur clique
        if(y < displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER){
            Log.d("BDF","Partie 1 => Sudoku");

            //On détecte la case
            int caseX = x/caseWidth;
            int caseY = y/caseHeight;
            Log.d("BDF","Case x" + caseX + " Case y" + caseY);
            map.put("x", caseX);
            map.put("y", caseY);

        }else if (y > displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER + MARGE_BETWEEN_GRID_AND_BOTTOM &&
                y < displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER + MARGE_BETWEEN_GRID_AND_BOTTOM + caseHeight){
            Log.d("BDF","Partie 2 => Choix chifre");

            //On détecte la case
            int caseSelectedX = x/caseWidth;
            Log.d("BDF","caseSelectedX : " + caseSelectedX );
            map.put("numberSelected", caseSelectedX);

        }else {
            Log.d("BDF","Partie autre");
        }

        return map;
    }


    public String getInitSudoku() {
        return initSudoku;
    }

    public void setInitSudoku(String initSudoku) {
        this.initSudoku = initSudoku;
    }
}
