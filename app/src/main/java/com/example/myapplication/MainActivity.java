package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreButton;
    private TextView mQuestionTextView;


    private TrueFalse[] mQuestionBank=new TrueFalse[]{
            new TrueFalse(R.string.question_ocean,true),
            new TrueFalse(R.string.question_africa,false),
            new TrueFalse(R.string.question_america,true),
            new TrueFalse(R.string.question_mideast,false),
            new TrueFalse(R.string.question_asia,true),
    };
    private  int mCurrentIndex=0;


    private int userAnsweredCorrect = 0;
   



    private void updateQuestion(){
    int question=mQuestionBank[mCurrentIndex].getQuestion();//get id
    mQuestionTextView.setText(question);//generate text_question
    checkIsAnswered();

}//use updateQuestion




 //add new class
    private void checkAnswer(boolean userPressedTrue){
    boolean answerIsTrue=mQuestionBank[mCurrentIndex].isTrueQuestion();//get current question answer
    int messageResId;
    if (userPressedTrue==answerIsTrue){
        messageResId=R.string.correct_toast;
        userAnsweredCorrect++;
    }else{
        messageResId=R.string.incorrect_toast;

    }
    mQuestionBank[mCurrentIndex].setAnswered(true);
    checkIsAnswered();


        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();//响应监听器，获得messageid，获得toast框
    }






    private void checkIsAnswered(){
    boolean answered=mQuestionBank[mCurrentIndex].isAnswered();
    if(answered){
        mTrueButton.setEnabled((false));
        mFalseButton.setEnabled(false);
    }else{
        mFalseButton.setEnabled(true);
        mTrueButton.setEnabled(true);
    }
    }






    private void showRecord(){
        boolean allAnswered = true;
        String message ;
        double correctMark ;
        int correctAnswerNum = 0;
        for(int i = 0; i < mQuestionBank.length; i++){
            if(mQuestionBank[i].isAnswered() == false) {
                allAnswered = false;
                break;
            }
        }
        if(allAnswered == true){
            correctMark = (double)userAnsweredCorrect/mQuestionBank.length;

            correctMark = (double)((int)(correctMark * 10000)/100.0);
            message = "正确率" + String.valueOf(correctMark) + "%";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }









    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);




        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);//创建textview
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {//设置true监听器
            @Override
            public void onClick(View v) {
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    updateQuestion();

                }//add 监听器 for textview
        });






        mTrueButton=(Button)findViewById(R.id.True_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {//设置true监听器
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                showRecord();
            }
        });







        mFalseButton=(Button)findViewById(R.id.False_button) ;
        mFalseButton.setOnClickListener(new View.OnClickListener() {//设置false监听器
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                showRecord();
            }
        });






        //use new button
        mNextButton=(Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                updateQuestion();
                showRecord();
            }
        });





        mPreButton = (Button) findViewById(R.id.Pre_button);
        mPreButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
               updateQuestion();
               showRecord();
           }
        });









        
        updateQuestion();









        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }











}
