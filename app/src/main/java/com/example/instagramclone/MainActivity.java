package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickPower,edtKickSpeed;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName=findViewById(R.id.edtName);
        edtPunchSpeed=findViewById(R.id.edtPunchSpeed);
        edtPunchPower=findViewById(R.id.edtPunchPower);
        edtKickSpeed=findViewById(R.id.edtKickSpeed);
        edtKickPower=findViewById(R.id.edtKickPower);

        btnSubmit=findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(MainActivity.this);

    }

//    public void helloWorldIsTapped(View view){
//        ParseObject boxer=new ParseObject("Boxer");
//        boxer.put("punch_speed",200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e==null){
//                    Toast.makeText(MainActivity.this,"Boxer object is saved successfully.",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }


    @Override
    public void onClick(View v) {
        try {
            final ParseObject kickboxer = new ParseObject("Kickboxer");
            kickboxer.put("name", edtName.getText().toString());
            kickboxer.put("punch_speed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickboxer.put("punch_power", Integer.parseInt(edtPunchPower.getText().toString()));
            kickboxer.put("kick_speed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickboxer.put("kick_power", Integer.parseInt(edtKickPower.getText().toString()));
            kickboxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, kickboxer.get("name") + " is saved successfully.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        //Toast.makeText(MainActivity.this,kickboxer.get("name")+" is saved successfully.",Toast.LENGTH_LONG).show();
                    } else {
                        FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        //Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }catch (Exception e){
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }
    }
}
