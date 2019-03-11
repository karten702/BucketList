package com.example.bucketlist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "bucketitem_table")
public class BucketItem implements Parcelable {

    @PrimaryKey (autoGenerate = true)
    private long id;

    @ColumnInfo (name = "title")
    private String title;

    @ColumnInfo (name = "description")
    private String description;

    @ColumnInfo (name = "completed")
    private Boolean completed;

    public BucketItem(String title, String description, Boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeValue(this.completed);
    }

    protected BucketItem(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.description = in.readString();
        this.completed = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<BucketItem> CREATOR = new Parcelable.Creator<BucketItem>() {
        @Override
        public BucketItem createFromParcel(Parcel source) {
            return new BucketItem(source);
        }

        @Override
        public BucketItem[] newArray(int size) {
            return new BucketItem[size];
        }
    };
}
