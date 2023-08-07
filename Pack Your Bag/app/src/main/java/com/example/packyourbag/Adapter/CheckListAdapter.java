package com.example.packyourbag.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.packyourbag.Constants.MyConstants;
import com.example.packyourbag.Database.RoomDB;
import com.example.packyourbag.Model.Items;
import com.example.packyourbag.R;

import java.util.List;

public class CheckListAdapter extends RecyclerView.Adapter<CheckListViewHolder> {

    Context context;
    List<Items> itemsList;
    RoomDB database;
    String show;

    public CheckListAdapter() {
    }

    public CheckListAdapter(Context context, List<Items> itemsList, RoomDB database, String show) {
        this.context = context;
        this.itemsList = itemsList;
        this.database = database;
        this.show = show;

        if (itemsList.size() == 0) {
            Toast.makeText(context, "Nothing to Show", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public CheckListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckListViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.check_list_item, parent, false));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull CheckListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.checkBox.setText(itemsList.get(position).getItemname());
        holder.checkBox.setChecked(itemsList.get(position).isChecked());

        if (MyConstants.FALSE_STRING.equals(show)) {
            holder.btnDelete.setVisibility(View.GONE);
            holder.layout.setBackgroundDrawable(context.getResources()
                    .getDrawable(R.drawable.border_1dp));
        } else {
            if (itemsList.get(position).isChecked()) {
                holder.layout.setBackgroundColor(Color.parseColor("#8e546f"));
            } else {
                holder.layout.setBackgroundDrawable(context.getResources()
                        .getDrawable(R.drawable.border_1dp));
            }
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = holder.checkBox.isChecked();
                database.mainDao().checkUnCheck(itemsList.get(position).getID(), check);
                if (MyConstants.FALSE_STRING.equals(show)) {
                    itemsList = database.mainDao().getAllSelected(true);
                    notifyDataSetChanged();
                } else {
                    itemsList.get(position).setChecked(check);
                    notifyDataSetChanged();

                    Toast toastMessage = null;
                    if (itemsList.get(position).isChecked()) {
                        toastMessage = Toast.makeText(context, "(" + holder.checkBox.getText() + ") Packed", Toast.LENGTH_SHORT);
                    } else {
                        toastMessage = Toast.makeText(context, "(" + holder.checkBox.getText() + ") Un-Packed", Toast.LENGTH_SHORT);
                    }
                    toastMessage.show();
                }
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete (" + itemsList.get(position).getItemname() + ")")
                        .setMessage("Are you sure?")
                        .setIcon(R.drawable.ic_delete)
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                database.mainDao().delete(itemsList.get(position));
                                itemsList.remove(itemsList.get(position));
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}

class CheckListViewHolder extends RecyclerView.ViewHolder {

    LinearLayout layout;
    CheckBox checkBox;
    Button btnDelete;

    public CheckListViewHolder(@NonNull View itemView) {
        super(itemView);

        layout = itemView.findViewById(R.id.linearLayout);
        checkBox = itemView.findViewById(R.id.checkBox);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }
}