package com.jakeesveld.flashstudyguide.newquiz;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.model.Question;


public class NewQuestionFragment extends DialogFragment {
    private static final String ARG_TYPE = "type";

    private String questionType;
    private EditText editQuestion, editHint;
    private RadioGroup radioGroupType, radioGroupMultiple, radioGroupBoolean;
    private RadioButton radioTrue, radioFalse, radioMultipleA, radioMultipleB,
            radioMultipleC, radioMultipleD, radioTypeBoolean, radioTypeMultiple;
    private Button buttonSubmit;

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



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editQuestion = view.findViewById(R.id.edit_question);
        editHint = view.findViewById(R.id.edit_question_hint);
        radioGroupBoolean = view.findViewById(R.id.radio_group_boolean);
        radioGroupType = view.findViewById(R.id.radio_group_type);
        radioGroupMultiple = view.findViewById(R.id.radio_group_multiple);
        radioTrue = view.findViewById(R.id.radio_true);
        radioFalse = view.findViewById(R.id.radio_false);
        radioMultipleA = view.findViewById(R.id.radio_multiple_a);
        radioMultipleB = view.findViewById(R.id.radio_multiple_b);
        radioMultipleC = view.findViewById(R.id.radio_multiple_c);
        radioMultipleD = view.findViewById(R.id.radio_multiple_d);
        radioTypeBoolean = view.findViewById(R.id.radio_type_boolean);
        radioTypeMultiple = view.findViewById(R.id.radio_type_multiple);
        buttonSubmit = view.findViewById(R.id.button_submit);

        setVisibleViews();

        radioGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                toggleVisibleView(i);

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidFields()){
                    // Construct question and return to activity
                }
            }
        });
    }

    private boolean checkValidFields() {
        if(editQuestion.getText().toString().equals("")){
            return false;
        }
        if(questionType.equals(NewQuizActivity.TYPE_MULTIPLE) ||
                radioGroupType.getCheckedRadioButtonId() == R.id.radio_type_multiple){
            if(radioGroupMultiple.getCheckedRadioButtonId() == -1){
                return false;
            }
        }
        if(questionType.equals(NewQuizActivity.TYPE_BOOLEAN)||
                radioGroupType.getCheckedRadioButtonId() == R.id.radio_type_boolean){
            if(radioGroupBoolean.getCheckedRadioButtonId() == -1){
                return false;
            }
        }
        if(questionType.equals("") && radioGroupType.getCheckedRadioButtonId() == -1){
            return false;
        }
        return true;
    }

    private void toggleVisibleView(int i) {
        radioGroupMultiple.setVisibility(View.GONE);
        radioGroupBoolean.setVisibility(View.GONE);
        switch(i){
            case R.id.radio_type_boolean:
                radioGroupBoolean.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_type_multiple:
                radioGroupMultiple.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void setVisibleViews() {
        radioGroupMultiple.setVisibility(View.GONE);
        radioGroupBoolean.setVisibility(View.GONE);
        radioGroupType.setVisibility(View.GONE);
        switch(questionType){
            case NewQuizActivity.TYPE_BOOLEAN:
                radioGroupBoolean.setVisibility(View.VISIBLE);
                break;
            case NewQuizActivity.TYPE_MULTIPLE:
                radioGroupMultiple.setVisibility(View.VISIBLE);
                break;
            case NewQuizActivity.TYPE_BOTH:
                radioGroupType.setVisibility(View.VISIBLE);
                break;
        }
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
