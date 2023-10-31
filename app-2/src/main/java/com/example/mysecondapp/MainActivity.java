package com.example.mysecondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;





public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionstv;
    TextView questiontv;
    Button ansA,ansB,ansC,ansD;
    Button submitbutton;
    int score=0;
    int totalQuestion=questionanswer.question.length;
    int currentquestionindex=0;
    String selectedanswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionstv=findViewById(R.id.total_question);
        questiontv=findViewById(R.id.question);
        ansA=findViewById(R.id.ans_A);
        ansB=findViewById(R.id.ans_B);
        ansC=findViewById(R.id.ans_C);
        ansD=findViewById(R.id.ans_D);
        submitbutton=findViewById(R.id.submit_button);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitbutton.setOnClickListener(this);
        totalQuestionstv.setText("Total questions: "+totalQuestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
         Button clickedbutton=(Button) view;
         if(clickedbutton.getId()==R.id.submit_button){

             if(selectedanswer.equals(questionanswer.correctAnswers[currentquestionindex])){
                 score++;
             }
             currentquestionindex++;
             loadNewQuestion();

         }
         else{
            selectedanswer=clickedbutton.getText().toString();
            clickedbutton.setBackgroundColor(Color.BLUE);
         }
    }
    void loadNewQuestion(){

        if(currentquestionindex==totalQuestion){
            finishQuiz();
            return;
        }
        questiontv.setText(questionanswer.question[currentquestionindex]);
        ansA.setText(questionanswer.choices[currentquestionindex][0]);
        ansB.setText(questionanswer.choices[currentquestionindex][1]);
        ansC.setText(questionanswer.choices[currentquestionindex][2]);
        ansD.setText(questionanswer.choices[currentquestionindex][3]);
    }
    void finishQuiz() {
        String passstatus="";
        if(score>totalQuestion*0.60){
            passstatus="Passed";
        }
        else{
            passstatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passstatus)
                .setMessage("score is"+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) ->restartQuiz() )
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score=0;
        currentquestionindex=0;
        loadNewQuestion();
    }
}