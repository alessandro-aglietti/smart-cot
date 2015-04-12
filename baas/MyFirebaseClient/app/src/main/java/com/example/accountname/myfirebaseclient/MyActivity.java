package com.example.accountname.myfirebaseclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Firebase.setAndroidContext(this);
        //this.myFirebaseRef = new Firebase("https://fiery-inferno-3651.firebaseio.com/");

        //setContentView(R.layout.activity_my);

       /* myFirebaseRef.child("key").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
               // ((TextView) findViewById(R.id.mytextview)).setText((String)snapshot.getValue());

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, (String)snapshot.getValue());
                sendIntent.setType("text/plain");
                //sendIntent.setPackage("com.example.accountname");
                //startActivity(sendIntent);

            }

            @Override public void onCancelled(FirebaseError error) { }

        });*/
    }
}
