package eni.baptistedixneuf.fr.sudoku.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import eni.baptistedixneuf.fr.sudoku.ListNiveauActivity;
import eni.baptistedixneuf.fr.sudoku.R;
import eni.baptistedixneuf.fr.sudoku.SudokuActivity;
import eni.baptistedixneuf.fr.sudoku.bo.Level;
import eni.baptistedixneuf.fr.sudoku.bo.Niveau;

/**
 * Created by bdixneuf2015 on 28/04/2017.
 */
public class NiveauAdapter extends ArrayAdapter<Niveau> {

    public NiveauAdapter(Context context, List<Niveau> levels) {
        super(context, 0, levels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.niveau_list,parent, false);
        }

        NiveauViewHolder viewHolder = (NiveauViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new NiveauViewHolder();
            viewHolder.niveau_list_id = (TextView) convertView.findViewById(R.id.niveau_list_id);
            viewHolder.niveau_list_nom = (TextView) convertView.findViewById(R.id.niveau_list_nom);
            viewHolder.niveau_list_pourcentage = (TextView) convertView.findViewById(R.id.niveau_list_pourcentage);
            viewHolder.niveau_list_pourcentage = (TextView) convertView.findViewById(R.id.niveau_list_pourcentage);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Niveau niveau = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.niveau_list_id.setText(""+niveau.getNum());
        viewHolder.niveau_list_nom.setText("Niveau : "+niveau.getLevel());

        viewHolder.niveau_list_pourcentage.setText("" + niveau.getDone() + "%");
        if(niveau.getDone()>50) {
            viewHolder.niveau_list_pourcentage.setTextColor(Color.GREEN);
        }else{
            viewHolder.niveau_list_pourcentage.setTextColor(Color.RED);
        }

        return convertView;
    }

    private class NiveauViewHolder {
        public TextView niveau_list_id;
        public TextView niveau_list_nom;
        public TextView niveau_list_pourcentage;
    }
}
