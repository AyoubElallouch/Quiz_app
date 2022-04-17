package com.elallouch.quizgame;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainMenu extends AppCompatActivity {
    Button btnNext, btnCam;
    TextView name, pts, rnk;
    DatabaseReference reference;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);

        btnNext = (Button) findViewById(R.id.btnPlay);
        btnCam = (Button) findViewById(R.id.btnCam);
        name = (TextView) findViewById(R.id.nameplayer);
        pts = (TextView) findViewById(R.id.point);
        rnk = (TextView) findViewById(R.id.rank);
        image = (ImageView) findViewById(R.id.image);


        getUserinfo();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this,quiz.class));
            }
        });
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap btmp = (Bitmap) data.getExtras().get("data");
        image.setImageBitmap(btmp);
    }
    public void getUserinfo()
    {
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(String.valueOf(1));
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                name.setText(user.getNom());
                pts.setText(user.getPoint());
                rnk.setText(user.getRank());
                String mail = user.getEmail();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
