package com.app.zeroil;
// Go Learn Page

import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflatter;
    private List<String> data;
    private String[] sTitle;
    private  String[] sContent;
//    private int[] sImages;


    Adapter() {


//        this.sImages = images;
    }
    //    Adapter(Context context, List<String> data, List<String>data2, int images) {
    Adapter(Context context, String[] data, String[] data2) {

        this.layoutInflatter = LayoutInflater.from(context);
        this.sTitle = data;
        this.sContent = data2;
//        this.sImages = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflatter.inflate(R.layout.custom_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String title = sTitle[position];
        String content = sContent[position];
//        int image = sImages[position];

        holder.contentTitle.setText(title);
        holder.contentDesc.setText(content);
//        holder.contentImage.setImageResource(image);

    }

    @Override
    public int getItemCount() {
        return sTitle.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contentTitle, contentDesc;
        ImageView contentImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Set onClick Listener here
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start new intent
                    Intent i = new Intent(v.getContext(), Details.class);
                    // send story title and contents through recyclerview to detail activity
                    i.putExtra("title_txt",sTitle[getAdapterPosition()]);
                    i.putExtra("content_txt",sContent[getAdapterPosition()]);
//                    i.putExtra("contentImage", sImages[getAdapterPosition()]);
                    v.getContext().startActivity(i);    // Starting next activity from view "v"
                }
            });

            contentTitle = itemView.findViewById(R.id.contentTitle);
            contentDesc = itemView.findViewById(R.id.contentDesc);

        }
    }
}
