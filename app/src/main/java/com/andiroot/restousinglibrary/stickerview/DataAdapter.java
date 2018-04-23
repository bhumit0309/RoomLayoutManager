package com.andiroot.restousinglibrary.stickerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Realm realm;

    public static RealmResults<ListNames> data;

    public DataAdapter() {
        realm = Realm.getDefaultInstance();
        data = realm.where(ListNames.class).findAll();
        for(int j=0; j < getItemCount(); j++) {
            Log.d("Data in DataAdapter", "" + data.get(j).getName());
        }
    }


    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.disp_name.setText("Name: " + data.get(i).getName());
        //viewHolder.disp_long.setText(data.get(i).getLongitude() + "");

      /*   Picasso.with(MainActivity.main)
                .load("http://eadate.com/images/userInfo/" + data.get(i).getMainImage())
                .into(viewHolder.disp_image);
        */
        //        Glide.with(MainActivity.main).load("http://eadate.com/images/user/" + data.get(i).getMainImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.disp_image);
//        viewHolder.disp_image.setImageURI(Uri.parse("http://eadate.com/images/user/" + data.get(i).getMainImage() + ""));



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView disp_name; //, disp_long;
        private RelativeLayout rel;
        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            disp_name = (TextView)view.findViewById(R.id.disp_name);
            rel = (RelativeLayout)view.findViewById(R.id.rel);

        }

        @Override
        public void onClick(View view) {
            Log.d("Position", getLayoutPosition() + "");
            Toast.makeText(view.getContext(), "Position: " + getLayoutPosition(), Toast.LENGTH_SHORT);
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            intent.putExtra("Position", getLayoutPosition());
            view.getContext().startActivity(intent);
        }
    }

}
