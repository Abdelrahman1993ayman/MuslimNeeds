package com.example.abdelrahmanayman.muslimneeds.TaharaContent.TaharaContent;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Wdo2DetailsModel implements Parcelable {
    private String header, tips;
    private Bitmap image;

    public Wdo2DetailsModel(String header, Bitmap image, String tips) {
        this.header = header;
        this.image = image;
        this.tips = tips;
    }


    private Wdo2DetailsModel(Parcel in) {
        header = in.readString();
        tips = in.readString();
        image = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Wdo2DetailsModel> CREATOR = new Creator<Wdo2DetailsModel>() {
        @Override
        public Wdo2DetailsModel createFromParcel(Parcel in) {
            return new Wdo2DetailsModel(in);
        }

        @Override
        public Wdo2DetailsModel[] newArray(int size) {
            return new Wdo2DetailsModel[size];
        }
    };

    public String getHeader() {
        return header;
    }

    public String getTips() {
        return tips;
    }

    public Bitmap getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(header);
        dest.writeString(tips);
        dest.writeString(String.valueOf(image));
    }
}
