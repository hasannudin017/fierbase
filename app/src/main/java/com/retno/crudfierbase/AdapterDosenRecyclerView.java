package com.retno.crudfierbase;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDosenRecyclerView extends RecyclerView.Adapter<AdapterDosenRecyclerView.ViewHolder> {

    private ArrayList<Dosen> daftarDosen;
    private Context context;

    public AdapterDosenRecyclerView(ArrayList<Dosen> dosens, Context ctx) {
/*** Inisiasi data dan variabel yang akan digunakan*/
        daftarDosen = dosens;
        daftarDosen = dosens;
        context = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namadosen);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
/**
 * Inisiasi ViewHolder
 */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dosen, parent, false);
// mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
/**
 * Menampilkan data pada view
 */
        final String name = daftarDosen.get(position).getNik();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/**
 * Read detail data
 */
            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
/**
 * Delete dan update data
 */

//Update
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();
                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);
//aksi tombol edit di klik
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        context.startActivity(DBCreateActivity.getActIntent((Activity)
                                context).putExtra("data", daftarDosen.get(position)));
                    }
                });
//aksi buttondelete di klik
                delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
/**
 * Delete data
 */
                        dialog.dismiss();
                        DBCreateActivity.deleteDosenData((Activity) context, daftarDosen.get(position).getKey());
                    }
                });
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    @Override
    public int getItemCount() {
/**
 * Mengembalikan jumlah item
 */
        return daftarDosen.size();
    }
}
