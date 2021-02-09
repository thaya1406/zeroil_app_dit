package com.app.zeroil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private String[] sTitles;
    private String[] sContent;
    Adapter(Context context, String[] titles, String[] contents) {
        this.inflater = LayoutInflater.from(context);
        this.sTitles = titles;
        this.sContent = contents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_view,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String title = sTitles[i];
        viewHolder.contentTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return sTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contentTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //implement onClick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), ContentDetails.class);
                    // send page title and contents through recyclerview to detail contents
                    i.putExtra("titleOfPage", sTitles[getAdapterPosition()]);
                    v.getContext().startActivity(i);

                }
            });
            contentTitle = itemView.findViewById(R.id.contentTitle);

        }
    }
}