package eni.baptistedixneuf.fr.sudoku.dessin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bdixneuf2015 on 28/04/2017.
 */
public class Grid extends View  implements View.OnTouchListener{

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

    public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init(canvas);
        drawGrid(canvas);
        drawButtons(canvas);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                detectElementClicked(x, y);
                break;

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


    private void detectElementClicked(int x, int y){
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


        }else if (y > displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER + MARGE_BETWEEN_GRID_AND_BOTTOM &&
                y < displayHeight - HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER + MARGE_BETWEEN_GRID_AND_BOTTOM + caseHeight){
            Log.d("BDF","Partie 2 => Choix chifre");

            //On détecte la case
            int caseSelectedX = x/caseWidth;
            Log.d("BDF","caseSelectedX : " + caseSelectedX );

        }else {
            Log.d("BDF","Partie autre");
        }


    }

}
