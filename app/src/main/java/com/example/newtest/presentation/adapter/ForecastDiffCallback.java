package com.example.newtest.presentation.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.newtest.data.api.model.Forecast;

public class ForecastDiffCallback extends DiffUtil.ItemCallback<Forecast> {
    @Override
    public boolean areItemsTheSame(@NonNull Forecast oldItem, @NonNull Forecast newItem) {
        return oldItem.getDate().equals(newItem.getDate());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Forecast oldItem, @NonNull Forecast newItem) {
        return oldItem.equals(newItem);
    }
}
