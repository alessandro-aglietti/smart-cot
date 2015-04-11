package com.example.accountname.myfirebaseclient;

import com.atooma.sdk.AtoomaRegistrationReceiver;

public class ACTIVITYReceiver extends AtoomaRegistrationReceiver {

    @Override
    public Class getRegisterServiceClass() {
        return ACTIVITYRegister.class;
    }

}