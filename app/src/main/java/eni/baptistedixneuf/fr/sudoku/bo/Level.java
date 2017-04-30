package eni.baptistedixneuf.fr.sudoku.bo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bdixneuf2015 on 28/04/2017.
 */
public class Level implements Parcelable {

    private int id;
    private int numero;
    private String nom;

    public Level(){}

    protected Level(Parcel in){
        id = in.readInt();
        numero = in.readInt();
        nom = in.readString();
    }

    public static final Parcelable.Creator<Level> CREATOR =  new Parcelable.Creator<Level>(){

        @Override
        public Level createFromParcel(Parcel source) {

            return new Level(source);
        }

        @Override
        public Level[] newArray(int size) {
            return new Level[size];
        }
    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(numero);
        dest.writeString(nom);
    }

    public int describeContents() {
        return 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
