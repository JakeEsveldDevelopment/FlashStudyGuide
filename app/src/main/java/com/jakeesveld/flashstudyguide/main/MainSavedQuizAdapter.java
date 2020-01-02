package com.jakeesveld.flashstudyguide.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.model.Quiz;

import java.util.List;

public class MainSavedQuizAdapter extends RecyclerView.Adapter<MainSavedQuizAdapter.ViewHolder> {

    private List<Quiz> dataList;

    public MainSavedQuizAdapter(List<Quiz> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_quiz_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Quiz data = dataList.get(position);

        holder.textTitle.setText(data.getName());
        holder.textDescription.setText(
                data.getDescription().equals("") ? "No Description Available" : data.getDescription()
        );
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle, textDescription;
        View parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.text_title);
            textDescription = itemView.findViewById(R.id.text_description);
            parent = itemView.findViewById(R.id.parent);

        }
    }
}
