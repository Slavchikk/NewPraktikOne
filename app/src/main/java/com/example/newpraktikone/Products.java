package com.example.newpraktikone;


import android.os.Parcel;
import android.os.Parcelable;


public class Products implements Parcelable{

    private int ID;
    private String Products;
    private String Price;
    private String Image;

    protected Products(Parcel in) {
        ID = in.readInt();
        Products = in.readString();
        Price = in.readString();
        Image = in.readString();
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setProducts(String products) {
        Products = products;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getProducts() {
        return Products;
    }

    public String getPrice() {
        return Price;
    }

    public String getImage() {
        return Image;
    }

    public Products(int ID, String title, String count, String image) {
        this.ID = ID;
        Products = title;
        Price = count;
        Image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeString(Products);
        parcel.writeString(Price);
        parcel.writeString(Image);
    }
}