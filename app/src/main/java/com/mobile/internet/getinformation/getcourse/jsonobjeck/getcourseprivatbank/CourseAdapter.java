package com.mobile.internet.getinformation.getcourse.jsonobjeck.getcourseprivatbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.RecyclerViewViewHolder> {
    private ArrayList<Course> arrayList;

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyTextView;
        private TextView courseTextView;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyTextView = itemView.findViewById(R.id.currencyTextView);
            courseTextView = itemView.findViewById(R.id.courseTextView);
        }
    }

    public CourseAdapter(ArrayList<Course> arrayList){
        this.arrayList = arrayList;


    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(view);
        return recyclerViewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        Course course = arrayList.get(position);
        holder.currencyTextView.setText(course.getCurrency());
        holder.courseTextView.setText(String.valueOf(course.getCourseVal()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
