package eni.baptistedixneuf.fr.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import eni.baptistedixneuf.fr.sudoku.chrono.ChronoHandler;
import eni.baptistedixneuf.fr.sudoku.chrono.ChronoRunnable;

public class SudokuActivity extends Activity {

    private Thread t;
    private ChronoRunnable chrono;
    private ChronoHandler handler ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        handler  = new ChronoHandler();
        handler.setActivity(this);
        chrono = new ChronoRunnable(this);
        t = new Thread(chrono);
        chrono.setRunning(true) ;
        t.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sudoku, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public ChronoHandler getHandler() {
        return handler;
    }

    public void setHandler(ChronoHandler handler) {
        this.handler = handler;
    }
}
