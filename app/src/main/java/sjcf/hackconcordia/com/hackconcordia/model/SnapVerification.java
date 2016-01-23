package sjcf.hackconcordia.com.hackconcordia.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;


public class SnapVerification extends SugarRecord implements Parcelable {
    String photoUrl;
    User takenByUser;

    protected SnapVerification(Parcel in) {
        id = in.readLong();
        photoUrl = in.readString();
        takenByUser = in.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<SnapVerification> CREATOR = new Parcelable.Creator<SnapVerification>() {
        public SnapVerification createFromParcel(Parcel in) {
            return new SnapVerification(in);
        }

        @Override
        public SnapVerification[] newArray(int size) {
            return new SnapVerification[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(photoUrl);
        dest.writeParcelable(takenByUser, flags);
    }
}
