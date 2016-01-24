package sjcf.hackconcordia.com.hackconcordia.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;


public class SnapVerification extends SugarRecord implements Parcelable {

    public SnapTreasure snapTreasure;
    public User submittedByUser;
    public User toBeVerifiedByUser;
    public String photoUrl;
    public int isVerified;

   public SnapVerification() {

   }

    public SnapVerification(Parcel in) {
        id = in.readLong();
        snapTreasure = in.readParcelable(SnapTreasure.class.getClassLoader());
        submittedByUser = in.readParcelable(User.class.getClassLoader());
        toBeVerifiedByUser = in.readParcelable(User.class.getClassLoader());
        photoUrl = in.readString();
        isVerified = in.readInt();
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
        dest.writeParcelable(snapTreasure, flags);
        dest.writeParcelable(submittedByUser, flags);
        dest.writeParcelable(toBeVerifiedByUser, flags);
        dest.writeString(photoUrl);
        dest.writeInt(isVerified);
    }
}
