package com.estech.retrofitsample.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Info2 implements Parcelable {

    private String next, prev;
    private int count, pages;

    public Info2() {}

    public Info2(String next, String prev, int count, int pages) {
        this.next = next;
        this.prev = prev;
        this.count = count;
        this.pages = pages;
    }

    protected Info2(Parcel in) {
        next = in.readString();
        prev = in.readString();
        count = in.readInt();
        pages = in.readInt();
    }

    public static final Creator<Info2> CREATOR = new Creator<Info2>() {
        @Override
        public Info2 createFromParcel(Parcel in) {
            return new Info2(in);
        }

        @Override
        public Info2[] newArray(int size) {
            return new Info2[size];
        }
    };

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(next);
        parcel.writeString(prev);
        parcel.writeInt(count);
        parcel.writeInt(pages);
    }
}
