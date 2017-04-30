package eni.baptistedixneuf.fr.sudoku;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eni.baptistedixneuf.fr.sudoku.adapter.LevelAdapter;
import eni.baptistedixneuf.fr.sudoku.adapter.NiveauAdapter;
import eni.baptistedixneuf.fr.sudoku.bo.Level;
import eni.baptistedixneuf.fr.sudoku.bo.Niveau;

public class ListNiveauActivity extends Activity {

    private List<Niveau> niveaux;
    private ListView mListView;
    private Level levelSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_niveau);

        //On récupère le level sélectionné dans l'activité précédente
        Bundle extras = getIntent().getExtras();
        levelSelected = extras.getParcelable("level");

        Log.w("BDF", levelSelected.getNom());

        // On récupére les levels
        niveaux = new ArrayList<Niveau>();
        for (int i = 0; i <= 100; i++){
            Niveau niveau = new Niveau();
            niveau.setNum(i);
            niveau.setLevel(levelSelected.getNumero());
            niveau.setDone((int) (Math.random() * 100));
            niveaux.add(niveau);
        }

        //On rafraichit la liste
        NiveauAdapter adapter = new NiveauAdapter(ListNiveauActivity.this, niveaux);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(clickItemListener);

    }

    private ListView.OnItemClickListener clickItemListener = new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Niveau niveau = (Niveau) parent.getAdapter().getItem(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(ListNiveauActivity.this);
            builder.setTitle("Information");
            builder.setMessage(niveau.getNum() + " - " + niveau.getDone() + " %")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

            builder.create();
            builder.show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_niveau, menu);
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
}
