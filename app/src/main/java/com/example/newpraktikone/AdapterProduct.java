package com.example.newpraktikone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class AdapterProduct extends BaseAdapter {

    private Context mContext;
    public AdapterProduct(Context mContext, List<Products> productsList) {
        this.mContext = mContext;
        this.productsList = productsList;
    }
    List<Products> productsList;
    @Override
    public int getCount() {
        return productsList.size();
    }

    @Override
    public Object getItem(int i) {
        return productsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return productsList.get(i).getID();
    }


    private Bitmap getUserImage(String encodedImg)
    {

        if(encodedImg!=null&& !encodedImg.equals("null")) {
            byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else
            return BitmapFactory.decodeResource(AdapterProduct.this.mContext.getResources(),R.drawable.index);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = View.inflate(mContext,R.layout.activity_products,null);

        TextView Title = v.findViewById(R.id.txtProduct);
        TextView Count = v.findViewById(R.id.txtPrice);
        ImageView Image = v.findViewById(R.id.imageView);

        Products products = productsList.get(position);
        Title.setText(products.getProducts());
        Count.setText(products.getPrice());

        Image.setImageBitmap(getUserImage(products.getImage()));

        return v;
    }
}