package com.example.tubes1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
        viewHolder = new PertemuanListAdapter.ViewHolder(binding, position);
        View itemview = binding.getRoot();
        return itemview;
    }
}

