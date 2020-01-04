package com.jakeesveld.flashstudyguide.newquiz;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.model.Question;


public class NewQuestionFragment extends DialogFragment {
    private static final String ARG_TYPE = "type";

    private String questionType;


    private OnFragmentInteractionListener mListener;

    public NewQuestionFragment() {
    }


    public static NewQuestionFragment newInstance(String questionType) {
        NewQuestionFragment fragment = new NewQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, questionType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionType = getArguments().getString(ARG_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_question, container, false);
    }

    public void onButtonPressed(Question question) {
        if (mListener != null) {
            mListener.onQuestionCompleted(question);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onQuestionCompleted(Question question);
    }
}
