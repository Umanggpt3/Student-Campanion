package bitw.techexpo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    objclass ob2 = new objclass();
    EditText usn;
    EditText pass;
    ProgressDialog progressDialog;
    public void LogMeIn(View view){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after .5s = 500ms
                progressDialog.dismiss();
            }
        }, 500);

        if((usn.getText().toString().toLowerCase()).equals(ob2.USN.toLowerCase()) && pass.getText().toString().equals(ob2.Password))
        {
            //Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
            intent.putExtra("USER_DATA",ob2);
            finish();
            startActivity(intent);
        }
        else
        {
            Toast.makeText(MainActivity.this, "Wrong Credentials!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usn = findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ob2 = dataSnapshot.child("Student1").getValue(objclass.class);
                //Toast.makeText(MainActivity.this, ob2.Name, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
