package sjcf.hackconcordia.com.hackconcordia.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.List;

public class SnapTreasure extends SugarRecord implements Parcelable {

    User createdByUser;
    User foundByUser;
    String photoUrl;
    double lat;
    double lng;
    String localityName;
    List<String> tags;
    List<User> pendingUsers;

    @Override
    public void save() {
        super.save();
        // TODO: Get user's location name from lat,lng
    }

    protected SnapTreasure(Parcel in) {
        id = in.readLong();
        createdByUser = in.readParcelable(User.class.getClassLoader());
        foundByUser = in.readParcelable(User.class.getClassLoader());
        photoUrl = in.readString();
        lat = in.readLong();
        lng = in.readLong();
        localityName = in.readString();
        in.readStringList(tags);
        in.readTypedList(pendingUsers, User.CREATOR);
    }

    public static final Parcelable.Creator<SnapTreasure> CREATOR = new Parcelable.Creator<SnapTreasure>() {
        public SnapTreasure createFromParcel(Parcel in) {
            return new SnapTreasure(in);
        }

        @Override
        public SnapTreasure[] newArray(int size) {
            return new SnapTreasure[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeParcelable(createdByUser, flags);
        dest.writeParcelable(foundByUser, flags);
        dest.writeString(photoUrl);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(localityName);
        dest.writeStringList(tags);
        dest.writeTypedList(pendingUsers);
    }
}
