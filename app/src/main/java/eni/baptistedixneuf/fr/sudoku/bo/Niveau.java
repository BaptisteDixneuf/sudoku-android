package eni.baptistedixneuf.fr.sudoku.bo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bdixneuf2015 on 28/04/2017.
 */
public class Niveau implements Parcelable {

    private int level;
    private int num ;
    private int done;

    public Niveau() {
    }

    protected Niveau(Parcel in){
        level = in.readInt();
        num = in.readInt();
        done = in.readInt();
    }

    public static final Parcelable.Creator<Niveau> CREATOR =  new Parcelable.Creator<Niveau>(){

        @Override
        public Niveau createFromParcel(Parcel source) {

            return new Niveau(source);
        }

        @Override
        public Niveau[] newArray(int size) {
            return new Niveau[size];
        }
    };

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(level);
        dest.writeInt(num);
        dest.writeInt(done);
    }

    public int describeContents() {
        return 0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }
}
