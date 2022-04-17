package com.elallouch.quizgame;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.rpc.context.AttributeContext;


public class quiz extends AppCompatActivity {
    TextView question;
    ImageView imageurl;
    TextView number;
    Button  btnNext;
    RadioButton op1, op2;
    int total=1;
    public int trueAns=0;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imageurl = (ImageView) findViewById(R.id.image);
        question = (TextView) findViewById(R.id.Question);
        op1 = (RadioButton) findViewById(R.id.btnOp1);
        op2 = (RadioButton) findViewById(R.id.btnOp2);
        btnNext = (Button) findViewById(R.id.btnNext);
        number = (TextView) findViewById(R.id.cmpt);

        UpdateQst();


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(op1.isChecked())
                {
                    if(op1.getText().toString().equals(ans))
                    {
                        trueAns++;
                    }
                }
                else
                {
                    if(op2.getText().toString().equals(ans))
                    {
                        trueAns++;
                    }
                }
                total++;
                UpdateQst();

            }
        });
    }
    private void UpdateQst()
    {
        if(total > 5)
        {
            startActivity(new Intent(quiz.this,Score.class));
            Counter c = new Counter(trueAns);
        }
        else
        {
            Counter c = new Counter(0);
            reference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Questions qst = snapshot.getValue(Questions.class);

                    question.setText(qst.getQst());
                    op1.setText(qst.getOp1());
                    op2.setText(qst.getOp2());
                    ans = qst.getAns();
                    String t = String.valueOf(total) + "/" + String.valueOf(5);
                    number.setText(t);

                    switch(total) {
                        case 1:
                            // code block
                            imageurl.setImageResource(R.drawable.q1);
                            break;
                        case 2:
                            // code block
                            imageurl.setImageResource(R.drawable.q2);
                            break;
                        case 3:
                            // code block
                            imageurl.setImageResource(R.drawable.q3);
                            break;
                        case 4:
                            // code block
                            imageurl.setImageResource(R.drawable.q4);
                            break;
                        case 5:
                            // code block
                            imageurl.setImageResource(R.drawable.q5);
                            break;
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }



}
