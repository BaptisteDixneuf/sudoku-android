package eni.baptistedixneuf.fr.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eni.baptistedixneuf.fr.sudoku.adapter.LevelAdapter;
import eni.baptistedixneuf.fr.sudoku.bo.Level;

public class MainActivity extends Activity {

    private List<Level> levels;
    private ListView mListView;
    private Button buttonJouer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On crée des levels
        levels = new ArrayList<Level>();


        for (int i = 0; i < 3; i++){
            Level level = new Level();
            level.setId(i);
            level.setNumero(i);
            level.setNom("Level " +  i );
            levels.add(level);
        }

        //On rafraichit la liste
        LevelAdapter adapter = new LevelAdapter(MainActivity.this, levels);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                levels.get(position);
                Intent detailIntent = new Intent(MainActivity.this, ListNiveauActivity.class);
                detailIntent.putExtra("level", levels.get(position));
                startActivity(detailIntent);
            }
        });

        buttonJouer = (Button) findViewById(R.id.activity_main_button);
        buttonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("BDF", "Jouer");
                Intent detailIntent = new Intent(MainActivity.this, SudokuActivity.class);
                startActivity(detailIntent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
