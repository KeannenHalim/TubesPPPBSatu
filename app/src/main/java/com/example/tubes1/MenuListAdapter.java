package com.example.tubes1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.example.tubes1.databinding.ItemListMenuBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuListAdapter extends BaseAdapter {
    private List<String> lst;
    private Activity activity;
    private FragmentManager fm;

    public MenuListAdapter(Activity activity, FragmentManager fm){
        this.lst = new ArrayList<>();
        this.activity = activity;
        this.fm = fm;
    }

    public void addLine(String[] menu){
        List<String> temp = Arrays.asList(menu);
        this.lst.addAll(temp);
    }

    private class ViewHolder{
        private ItemListMenuBinding binding;
        private int idx;
        public ViewHolder(ItemListMenuBinding binding, int position){
            this.binding = binding;
            this.binding.lstMenu.setOnClickListener(this::onClick);
            this.idx = position;
        }

        public void setMenu(int position){
            this.binding.tvMenu.setText(lst.get(position));
        }

        private void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putInt("page",this.idx+1);
            fm.setFragmentResult("changePage", bundle);
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
        ViewHolder viewHolder;
        ItemListMenuBinding binding = ItemListMenuBinding.inflate(this.activity.getLayoutInflater());
        viewHolder = new ViewHolder(binding, position);
        View itemview = binding.getRoot();
        viewHolder.setMenu(position);
        return itemview;
    }
}
