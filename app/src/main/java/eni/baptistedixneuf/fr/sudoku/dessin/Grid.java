package eni.baptistedixneuf.fr.sudoku.dessin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bdixneuf2015 on 28/04/2017.
 */
public class Grid extends View {

    public static final int GRID_WIDTH = 9;
    public static final int GRID_HEIGHT = 9;
    public static final int HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER = 500;
    public static final int MARGE_BETWEEN_GRID_AND_BOTTOM = 50;

    public Grid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(canvas);
        drawButtons(canvas);
        drawTimer(canvas);
    }


    private void drawGrid(Canvas canvas){

        //On réucpère les dimensions de l'écran
        int displayWidth = canvas.getWidth();
        int displayHeight = canvas.getHeight();


        //Calcul de la largeur d'une case
        int caseWidth = displayWidth/GRID_WIDTH;

        //Calcul de la hauteur d'une case
        int caseHeight = (displayHeight- HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER)/GRID_HEIGHT;

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



        //On réucpère les dimensions de l'écran
        int displayWidth = canvas.getWidth();
        int displayHeight = canvas.getHeight();


        //Calcul de la largeur d'une case
        int caseWidth = displayWidth/GRID_WIDTH;

        //Calcul de la hauteur d'une case
        int caseHeight = (displayHeight- HEIGHT_BOTTOM_FOR_BUTTON_AND_TIMER)/GRID_HEIGHT;

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

    public void drawTimer(Canvas canvas){

    }





}
