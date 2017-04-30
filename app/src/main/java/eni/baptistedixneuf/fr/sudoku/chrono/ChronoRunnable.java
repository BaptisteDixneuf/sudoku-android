package eni.baptistedixneuf.fr.sudoku.chrono;

import android.util.Log;

import eni.baptistedixneuf.fr.sudoku.SudokuActivity;

/**
 * Created by baptiste on 30/04/2017.
 */
public class ChronoRunnable
        implements Runnable {

    private final SudokuActivity activity;
    private boolean isRunning = false;
    private int cpt = 0;
    private long startTime;
    private int acc = 0;

    public ChronoRunnable(SudokuActivity myActivity) {
        this.activity = myActivity;
    }

    @Override
    public void run() {
        startTime  = System.currentTimeMillis() ;

        while (isRunning){
            long currentTime = System.currentTimeMillis();
            /*Log.d("Chrono " , " -- "+(cpt++));*/
            this.activity.getHandler().sendEmptyMessage(acc + (int) ((currentTime - startTime)/1000));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long currentTime = System.currentTimeMillis();
        acc = (int) ((currentTime - startTime)/1000);

    }

    public SudokuActivity getActivity() {
        return activity;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}