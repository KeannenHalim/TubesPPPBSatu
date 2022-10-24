package com.example.tubes1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.example.tubes1.databinding.ItemListDokterBinding;
import com.example.tubes1.databinding.ItemListPertemuanBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PertemuanListAdapter extends BaseAdapter {
    private List<Pertemuan> lst;
    private Activity activity;
    private FragmentManager fm;
    private Presenter presenter;

    public PertemuanListAdapter(Activity activity, FragmentManager fm, Presenter presenter) {
        this.lst = new ArrayList<>();
        this.activity = activity;
        this.fm = fm;
        this.presenter = presenter;
    }

    public void update(List<Pertemuan> pertemuans) {
        this.lst.clear();
        this.lst.addAll(pertemuans);
        notifyDataSetChanged();
    }

    private class ViewHolder {
        private ItemListPertemuanBinding binding;
        private int idx;

        public ViewHolder(ItemListPertemuanBinding binding, int position) {
            this.binding = binding;
            this.idx = position;
            this.binding.deleteBtn.setOnClickListener(this::onClick);
            this.binding.btnMore.setOnClickListener(this::onClickMore);
        }

        private void onClickMore(View view) {
            KeteranganKeluhanDialogFragment fragment = KeteranganKeluhanDialogFragment.newInstance(lst.get(idx).getKeluhan());
            fragment.show(fm.beginTransaction(), "dialog");
        }

        private void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setMessage("Do you want to delete ?");

            builder.setTitle("Alert !");


            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                presenter.deletePertemuan(this.idx);
            });

            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        public void updateView(int position){
            this.binding.tvNamaPasien.setText(lst.get(position).getNamaPasien());
            this.binding.tvNamaDokter.setText(lst.get(idx).getNamaDokter());
            this.binding.tvTanggal.setText(lst.get(idx).getTanggal());
            this.binding.tvWaktu.setText(lst.get(idx).getWaktu());
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
        PertemuanListAdapter.ViewHolder viewHolder;
        ItemListPertemuanBinding binding = ItemListPertemuanBinding.inflate(this.activity.getLayoutInflater());
        viewHolder = new ViewHolder(binding, position);
        View itemview = binding.getRoot();
        viewHolder.updateView(position);
        return itemview;
    }
}

