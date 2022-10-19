package com.example.tubes1;

import android.app.Activity;
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

    public DokterListAdapter(Activity activity, FragmentManager fm) {
        this.lst = new ArrayList<>();
        this.activity = activity;
        this.fm = fm;
    }

    public void addLine(Dokter[] dokters) {
        List<Dokter> temp = Arrays.asList(dokters);
        this.lst.addAll(temp);
    }

    private class ViewHolder {
        private ItemListDokterBinding binding;
        private int idx;

        public ViewHolder(ItemListDokterBinding binding, int position) {
            this.binding = binding;
            this.idx = position;
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
        return itemview;
    }
}
