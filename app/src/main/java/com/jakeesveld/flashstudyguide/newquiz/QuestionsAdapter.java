package com.jakeesveld.flashstudyguide.newquiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.model.Question;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    private List<Question> dataList;

    public QuestionsAdapter(List<Question> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question data = dataList.get(position);

        switch(data.getType()){
            case Question.TYPE_BOOLEAN:
                holder.text_type.setText(R.string.TrueFalse);
                break;
            case Question.TYPE_MULTIPLE:
                holder.text_type.setText(R.string.multiple);
                break;
        }

        holder.text_question.setText(data.getText().substring(0, 50));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_question, text_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_question = itemView.findViewById(R.id.text_question);
            text_type = itemView.findViewById(R.id.text_type);
        }
    }
}
