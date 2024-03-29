package com.example.myapplication;

public class TrueFalse {
    private int mQuestion;
    private  boolean mTrueQuestion;

    private int mTestResId;
    private boolean mAnswerTrue;
    private boolean mAnswered=false;

    public TrueFalse(int question,boolean trueQuestion) {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }





        public int getQuestion(){
            return mQuestion;
        }
        public void setQuestion(int question){
            mQuestion=question;
        }
        public boolean isTrueQuestion(){
            return mTrueQuestion;
        }
        public void setTrueQuestion(boolean trueQuestion){
            mTrueQuestion=trueQuestion;
        }




        public boolean isAnswered(){
            return mAnswered;
        }
        public void setAnswered(boolean answered) {
            mAnswered=answered;
        }







    }




