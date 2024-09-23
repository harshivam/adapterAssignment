package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<String> list;
    //stuffs that the recyclerAdapter will accept
    public RecyclerAdapter(Context context, ArrayList<String> list) {
      this.context = context;
      this.list = list;
    }


    //method is called when the RecyclerView needs a new ViewHolder to represent an item in the list the function returns a viewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflates the single row layput for the text to display from the xml resource in drawable
        View view = LayoutInflater.from(context).inflate(R.layout.textrow,parent, true); // false is used here so it isn't immediately attach to parent view
        //What it does: This creates an instance of the ViewHolder class by passing the inflated view to it.
        // By creating a ViewHolder, you can access and modify those views (e.g., setting text, handling clicks) without inflating them repeatedly.
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //binding the data to the view ,in this case it's the text
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.tv.setText(list.get(position));

    }

    //returns how many items are to be displayed in the list

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public ViewHolder(View itemView){
            super(itemView);

            tv = itemView.findViewById(R.id.TV);

        }

    }
}

// how it actually works

//1. Start
//   |
//   v
//2. RecyclerView requests a new ViewHolder
//   |
//   v
//3. Call `onCreateViewHolder()`
//   |
//   v
//4. Inflate the layout (XML) for the list item and initialize the ViewHolder’s views
//   |
//   v
//5. Pass the new ViewHolder back to RecyclerView
//   |
//   v
//6. RecyclerView calls `onBindViewHolder()` with the ViewHolder and the current item position
//   |
//   v
//7. In `onBindViewHolder()`, bind the data (e.g., set text, image, etc.) to the ViewHolder’s views
//   |
//   v
//8. RecyclerView repeats steps 3–7 for each item in the data list
//   |
//   v
//9. Display the bound items in the RecyclerView
//   |
//   v
//10. End
