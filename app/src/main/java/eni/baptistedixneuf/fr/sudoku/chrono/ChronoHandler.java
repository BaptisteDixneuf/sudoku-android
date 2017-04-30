package eni.baptistedixneuf.fr.sudoku.chrono;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import eni.baptistedixneuf.fr.sudoku.R;
import eni.baptistedixneuf.fr.sudoku.SudokuActivity;

public class ChronoHandler extends Handler {

    private SudokuActivity activity;

    @Override
    public void handleMessage(Message msg) {
        TextView texte = (TextView) activity.findViewById(R.id.chronoText);
        int secondes = msg.what;

        int minutes = (int)(secondes/60);
        int seconde = secondes - (minutes*60);
        String  tminutes = minutes  > 10 ? ""+minutes:"0"+minutes;
        String tseconde  = seconde > 10 ? ""+seconde:"0"+seconde;


        texte.setText("00:"+tminutes +":"+tseconde);

    }

    public SudokuActivity getActivity() {
        return activity;
    }

    public void setActivity(SudokuActivity activity) {
        this.activity = activity;
    }
}
