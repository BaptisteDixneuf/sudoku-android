package eni.baptistedixneuf.fr.sudoku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import eni.baptistedixneuf.fr.sudoku.R;
import eni.baptistedixneuf.fr.sudoku.bo.Level;

/**
 * Created by bdixneuf2015 on 28/04/2017.
 */
public class LevelAdapter extends ArrayAdapter<Level> {

    public LevelAdapter(Context context, List<Level> levels) {
        super(context, 0, levels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.level_list,parent, false);
        }

        LevelViewHolder viewHolder = (LevelViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new LevelViewHolder();
            viewHolder.nom = (TextView) convertView.findViewById(R.id.nom);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Level level = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nom.setText(level.getNom());
        return convertView;
    }

    private class LevelViewHolder {
        public TextView nom;
    }
}
