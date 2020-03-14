package com.example.quz;

public class Truefalse
{
   private int mQuestionId;
   private Boolean mAnswer;

    public Truefalse(int mQuestionId, Boolean mAnswer)
    {
        this.mQuestionId = mQuestionId;
        this.mAnswer = mAnswer;
    }

    public int getmQuestionId()
    {
        return mQuestionId;
    }

    public Boolean getmAnswer() {
        return mAnswer;
    }
}
