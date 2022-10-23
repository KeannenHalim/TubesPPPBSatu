package com.example.tubes1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;


import com.example.tubes1.databinding.ItemListDokterBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class DokterListAdapter extends BaseAdapter {
    private List<Dokter> lst;
    private Activity activity;
    private FragmentManager fm;
    private Presenter presenter;
    public DokterListAdapter(Activity activity, FragmentManager fm, Presenter presenter) {
        this.lst = new ArrayList<>();
        this.activity = activity;
        this.fm = fm;
        this.presenter = presenter;
    }

    public void update(List<Dokter> dokters) {
        this.lst.clear();
        this.lst.addAll(dokters);
        notifyDataSetChanged();
    }

    private class ViewHolder {
        private ItemListDokterBinding binding;
        private int idx;

        public ViewHolder(ItemListDokterBinding binding, int position) {
            this.binding = binding;
            this.idx = position;
            this.binding.deleteBtn.setOnClickListener(this::onClick);
        }

        private void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setMessage("Do you want to delete ?");

            builder.setTitle("Alert !");


            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                presenter.deleteDokter(this.idx);
            });

            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }

        public void updateView(int idx){
            this.binding.tvNama.setText(lst.get(idx).getNama());
            this.binding.tvSpesialis.setText(lst.get(idx).getSpecialist());
            this.binding.tvTelepon.setText(lst.get(idx).getNoTelepon());
        }
    }

    @Override
    public int getCount() {
        return this.lst.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DokterListAdapter.ViewHolder viewHolder;
        ItemListDokterBinding binding = ItemListDokterBinding.inflate(this.activity.getLayoutInflater());
        viewHolder = new DokterListAdapter.ViewHolder(binding, position);
        View itemview = binding.getRoot();
        viewHolder.updateView(position);
        return itemview;
    }
}
