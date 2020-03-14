package com.example.quz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity
{
    ProgressBar mProgressBar;
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView;
    TextView mScoreTextView;
    int mIndex;
    int mScore;
    int mQuestion;
    Toast mToastMessage;
// create a data bank using truefalse class each item in the array MVC model view Control
    private Truefalse [] mquestionbank = new Truefalse[]
        {
              new Truefalse(R.string.question1,true),
                new Truefalse(R.string.question2,false),
                new Truefalse(R.string.question3,false)
        };


    int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0/mquestionbank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_Button);
        mQuestionTextView = findViewById(R.id.questio);
        mScoreTextView = findViewById(R.id.marks);
        mProgressBar = findViewById(R.id.prograssbar);
        if(savedInstanceState != null) {
            Log.d("state", "New State");
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
        }
        else {
            mScore = 0;
            mIndex = 0;
        }

        mQuestion = mquestionbank [mIndex].getmQuestionId();
        mQuestionTextView.setText(mQuestion);
        mScoreTextView.setText("Score:" + mScore + "/" + mquestionbank.length);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestion();
            }
        });

    }

    private void checkAnswer(boolean userSelection) {
        boolean actualAnswer = mquestionbank[mIndex].getmAnswer();
        if(userSelection == actualAnswer) {
            mScore = mScore + 1;
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Your answer is Ture " + R.drawable.desgn)
                    .show();        }
        else {
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Your answer is False !")
                    .show();         }
        mToastMessage.show();

    }
    private void updateQuestion() {
        mIndex = (mIndex + 1) % mquestionbank.length;
        if(mIndex == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Quiz App");
            alert.setCancelable(false);
            alert.setMessage("You scored " + mScore + "/" + mquestionbank.length);
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        mQuestion = mquestionbank[mIndex].getmQuestionId();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score:" + mScore + "/" + mquestionbank
                .length);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey", mIndex);
        Log.d("State Changed", "Current Data Saved");

    }

    }
