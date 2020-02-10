package com.jakeesveld.flashstudyguide.quizdetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakeesveld.flashstudyguide.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.newquiz.NewQuizActivity;

import java.util.List;

public class DetailsQuestionAdapter extends RecyclerView.Adapter<DetailsQuestionAdapter.ViewHolder> {

    private List<Question> dataList;

    public DetailsQuestionAdapter(List<Question> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DetailsQuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsQuestionAdapter.ViewHolder holder, int position) {
        Question data = dataList.get(position);

        holder.textTitle.setText(data.getText());
        switch (data.getType()){
            case NewQuizActivity.TYPE_BOOLEAN:
                holder.textType.setText("True/False");
                break;
            case NewQuizActivity.TYPE_MULTIPLE:
                holder.textType.setText("Multiple Choice");
                break;
        }


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle, textType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_question);
            textType = itemView.findViewById(R.id.text_type);
        }
    }
}
