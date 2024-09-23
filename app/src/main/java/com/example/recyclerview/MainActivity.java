package com.example.recyclerview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView RV;
    RecyclerAdapter adapter;
    ArrayList<String> list;
    Button btnAdd, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView setup
        RV = findViewById(R.id.RV);
        RV.setLayoutManager(new LinearLayoutManager(this));

        // Initialize list and adapter
        list = new ArrayList<>();
        list.add("Item1");
        list.add("Item2");
        list.add("Item3");

        adapter = new RecyclerAdapter(this, list);
        RV.setAdapter(adapter);

        // Find buttons
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);

        // Added button logic
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemDialog();
            }
        });

        // Delete button logic
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty()) {
                    list.remove(list.size() - 1); //to delete the last item
                    adapter.notifyItemRemoved(list.size()); //updating the adapter class so it can update the view
                } else {
                    Toast.makeText(MainActivity.this, "No items to delete", Toast.LENGTH_SHORT).show(); //if the list is empty user can see a toast
                }
            }
        });
    }

    private void showAddItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Item");

        // inflating a custom view with an editText for the dialog box
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialoglayout, null, false);
        EditText input = viewInflated.findViewById(R.id.input);
        builder.setView(viewInflated);

        // Set up the add and delete btn
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String itemText = input.getText().toString();
                if (!itemText.isEmpty()) {
                    list.add(itemText); // Add new item to the list
                    adapter.notifyItemInserted(list.size() - 1); // Notify adapter of new item
                } else {
                    Toast.makeText(MainActivity.this, "Please enter text,the list is empty RN", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
