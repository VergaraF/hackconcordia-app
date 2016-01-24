package sjcf.hackconcordia.com.hackconcordia.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

public class User extends SugarRecord implements Parcelable {

    public String email;
    public String password;// store in plaintext for now. no time
    public int points;

    public User() {

    }

    public User(Parcel in) {
        if(getId() != null) {
            id = in.readLong();
        }
        email = in.readString();
        password = in.readString();
        points = in.readInt();
    }

    @Override
    public void save() {
        super.save();
        // TODO: connect to backend code to save to db
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeInt(points);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };
}
