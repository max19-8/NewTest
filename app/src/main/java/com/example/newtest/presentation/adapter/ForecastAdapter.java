package com.example.newtest.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.newtest.R;
import com.example.newtest.data.api.model.Forecast;

public class ForecastAdapter extends ListAdapter<Forecast,ForecastViewHolder> {
    public ForecastAdapter(@NonNull DiffUtil.ItemCallback<Forecast> diffCallback) {
        super(diffCallback);
    }
    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ForecastViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
