package com.example.accountname.myfirebaseclient;

import android.content.Context;
import android.content.SharedPreferences;

import com.atooma.plugin.Module;

/**
 * Created by accountname on 11/04/15.
 */
public class AtomaModule extends Module {

    public static final String MODULE_ID = "ACTIVITY";
    public static final int MODULE_VERSION = 2;

    public AtomaModule(Context context, String moduleId, int moduleVersion) {
        super(context, moduleId, moduleVersion);
    }

    @Override
    public void registerComponents() {
        registerTrigger(new AtomaTrigger(getContext(), "GET_STRINGS", 2));
    }

    @Override
    public void defineUI() {
        setIcon(R.drawable.ic_launcher);
        setTitle(R.string.firebase_trigger);
    }

    @Override
    public void defineAuth() {
        setAuthenticated(true, "");
    }

    @Override
    public void clearCredentials() {
    }
}
