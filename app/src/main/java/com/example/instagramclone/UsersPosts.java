package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class UsersPosts extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_posts);

        linearLayout = findViewById(R.id.linearLayout);

        Intent receivedIntentObject=getIntent();
        final String receivedUsername = receivedIntentObject.getStringExtra("username");
        FancyToast.makeText(this,receivedUsername, Toast.LENGTH_SHORT, FancyToast.SUCCESS,true).show();

        setTitle(receivedUsername+"'s posts");

        ParseQuery<ParseObject> parseQuery=new ParseQuery<ParseObject>("Photo");
        parseQuery.whereEqualTo("username",receivedUsername);
        parseQuery.orderByDescending("createdAt");

        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size()>0 && e==null){
                    for(ParseObject post:objects){
                        final TextView postDescriptions=new TextView(UsersPosts.this);
                        postDescriptions.setText(post.get("image_des")+"");
                        ParseFile postPicture=(ParseFile)post.get("picture");
                        postPicture.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if(data!=null && e==null){
                                    Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                                    ImageView postImageView=new ImageView(UsersPosts.this);
                                    LinearLayout.LayoutParams imageView_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    imageView_params.setMargins(5,5,5,5);
                                    postImageView.setLayoutParams(imageView_params);
                                    postImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    postImageView.setImageBitmap(bitmap);

                                    LinearLayout.LayoutParams doc_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    doc_params.setMargins(5,5,5,5);
                                    postDescriptions.setLayoutParams(imageView_params);
                                    postDescriptions.setGravity(Gravity.CENTER);
                                    postDescriptions.setBackgroundColor(Color.RED);
                                    postDescriptions.setTextColor(Color.WHITE);
                                    postDescriptions.setTextSize(30f);

                                    linearLayout.addView(postImageView);
                                    linearLayout.addView(postDescriptions);

                                }
                            }
                        });

                    }
                }else{
                    FancyToast.makeText(UsersPosts.this,receivedUsername + " doesn't have any post ", Toast.LENGTH_SHORT, FancyToast.INFO,true).show();
                    finish();
                }
                dialog.dismiss();
            }

        });
    }
}
