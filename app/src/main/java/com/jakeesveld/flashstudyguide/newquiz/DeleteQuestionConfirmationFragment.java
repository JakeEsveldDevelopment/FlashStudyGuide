package com.jakeesveld.flashstudyguide.newquiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;
import com.jakeesveld.flashstudyguide.quizdetails.DeleteConfirmationFragment;

public class DeleteQuestionConfirmationFragment extends DialogFragment {

    private DeleteConfirmationListener mListener;
    private Question question;
    private Context context;


    public DeleteQuestionConfirmationFragment(Context context, Question question, DeleteConfirmationListener listener) {
        this.context = context;
        this.question = question;
        mListener = listener;
    }

    public static DeleteQuestionConfirmationFragment newInstance
            (Question question, Context context, DeleteConfirmationListener listener) {
        DeleteQuestionConfirmationFragment fragment = new DeleteQuestionConfirmationFragment
                (context, question, listener);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(context)
                .setTitle("Delete Question?")
                .setMessage("Are you sure you want to delete question " + question.getText() + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onConfirmed(question, true);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onConfirmed(question, false);
                    }
                }).create();
    }

    public interface DeleteConfirmationListener {
        void onConfirmed(Question question, boolean confirmed);
    }
}