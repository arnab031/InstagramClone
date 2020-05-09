package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("hHCPkG6Lmw5axbml2PesU768zMNACChlbeZHibOW")
                // if defined
                .clientKey("teEBjsrO64dhHkfpg3KYVwEKb45JPhCHndoUQdrw")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
