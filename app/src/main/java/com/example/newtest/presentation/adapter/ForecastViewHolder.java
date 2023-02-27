package com.example.newtest.presentation.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newtest.R;
import com.example.newtest.data.api.model.Forecast;
public class ForecastViewHolder extends RecyclerView.ViewHolder {
    TextView tvTemperatureFeelsLike,tvTemperatureDay,tvTemperatureEvening ,tvDate;
    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTemperatureFeelsLike = itemView.findViewById(R.id.tv_rv_temperature_feels_like);
        tvTemperatureDay = itemView.findViewById(R.id.tv_rv_temperature_day);
        tvTemperatureEvening = itemView.findViewById(R.id.tv_rv_temperature_evening);
        tvDate = itemView.findViewById(R.id.tv_rv_date);
    }
    public void bind(Forecast forecast){
        tvDate.setText(forecast.getDate());
        tvTemperatureDay.setText(itemView.getContext().getString(R.string.temp_day,String.valueOf(forecast.getParts().getDay().getTempAvg())));
        tvTemperatureEvening.setText(itemView.getContext().getString(R.string.temp_evening,String.valueOf(forecast.getParts().getEvening().getTempAvg())));
        tvTemperatureFeelsLike.setText(itemView.getContext().getString(R.string.temp_feels_like, String.valueOf(forecast.getParts().getDay().getTempFeelsLike())));
    }
}