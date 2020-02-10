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
    private onAdapterClickListener listener;

    public QuestionsAdapter(List<Question> dataList, onAdapterClickListener listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Question data = dataList.get(position);

        switch(data.getType()){
            case Question.TYPE_BOOLEAN:
                holder.text_type.setText(R.string.TrueFalse);
                break;
            case Question.TYPE_MULTIPLE:
                holder.text_type.setText(R.string.multiple);
                break;
        }

        if(data.getText().length() > 50) {
            holder.text_question.setText(data.getText().substring(0, 50));
        }else{
            holder.text_question.setText(data.getText());
        }

        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onQuestionItemLongClick(data);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_question, text_type;
        View parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_question = itemView.findViewById(R.id.text_question);
            text_type = itemView.findViewById(R.id.text_type);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    public interface onAdapterClickListener{
        void onQuestionItemLongClick(Question question);
    }
}
