package com.example.accountname.myfirebaseclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;

import com.atooma.plugin.IntentBasedTrigger;
import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Trigger;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by accountname on 11/04/15.
 */
public class AtomaTrigger extends Trigger {

    private Firebase myFirebaseRef;
    private String ruleId;
    private ParameterBundle parameterBundle;

    public AtomaTrigger(Context context, String id, int version) {
        super(context, id, version);

        Firebase.setAndroidContext(this.getContext());
    }

    @Override
    public void defineUI() {
        setIcon(R.drawable.ic_launcher);
        setTitle(R.string.firebase_trigger);
    }

    @Override
    public void onInvoke(String ruleId, ParameterBundle parameterBundle) {
        this.ruleId = ruleId;
        this.parameterBundle = parameterBundle;

        this.myFirebaseRef = new Firebase("https://fiery-inferno-3651.firebaseio.com/");

        myFirebaseRef.child("key").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                AtomaTrigger.this.trigger(AtomaTrigger.this.ruleId, AtomaTrigger.this.parameterBundle);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }

        });
    }

    @Override
    public void onRevoke(String s) {
    }
}
