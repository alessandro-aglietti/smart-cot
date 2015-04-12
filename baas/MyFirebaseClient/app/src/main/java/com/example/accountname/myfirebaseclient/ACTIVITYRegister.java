package com.example.accountname.myfirebaseclient;

import com.atooma.plugin.IModulePlugin;
import com.atooma.sdk.RegisterService;

/**
 * Created by accountname on 11/04/15.
 */
public class ACTIVITYRegister extends RegisterService {

    @Override
    public IModulePlugin getModuleInstance() {
        return new AtomaModule(this, AtomaModule.MODULE_ID, AtomaModule.MODULE_VERSION);
    }

}