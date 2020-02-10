package com.jakeesveld.flashstudyguide.quizdetails;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.model.Quiz;

public class DeleteConfirmationFragment extends DialogFragment {


    private DeleteConfirmationListener mListener;
    private Quiz quiz;
    private Context context;


    public DeleteConfirmationFragment(Context context, Quiz quiz, DeleteConfirmationListener listener) {
        this.context = context;
        this.quiz = quiz;
        mListener = listener;
    }

    public static DeleteConfirmationFragment newInstance(Quiz quiz, Context context, DeleteConfirmationListener listener) {
        DeleteConfirmationFragment fragment = new DeleteConfirmationFragment(context, quiz, listener);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(context)
                .setTitle("Delete Study Guide?")
                .setMessage("Are you sure you want to delete this study guide? (" + quiz.getName() + ")")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onConfirmed(quiz, true);

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onConfirmed(quiz, false);
                    }
                }).create();
    }

    public interface DeleteConfirmationListener {
        void onConfirmed(Quiz quiz, boolean confirmed);
    }
}
