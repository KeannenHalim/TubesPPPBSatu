package com.example.tubes1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.FragmentManager;

import com.example.tubes1.databinding.ListMenuBinding;

import java.util.ArrayList;

public class MenuListAdapter extends BaseAdapter {
    private ArrayList<String> lst;
    private Activity activity;
    private FragmentManager fm;
    public MenuListAdapter(Activity activity, FragmentManager fm){
        this.lst = new ArrayList<>();
        this.activity = activity;
        this.fm = fm;
    }

    public void addLine(String menu){
        this.lst.add(menu);
        this.notifyDataSetChanged();
    }

    private class ViewHolder{
        private ListMenuBinding binding;
        private int idx;
        public ViewHolder(ListMenuBinding binding, int position){
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
        ListMenuBinding binding = ListMenuBinding.inflate(this.activity.getLayoutInflater());
        viewHolder = new ViewHolder(binding, position);
        View itemview = binding.getRoot();
        viewHolder.setMenu(position);
        return itemview;
    }
}
