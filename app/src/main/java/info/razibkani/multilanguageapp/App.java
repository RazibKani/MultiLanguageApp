package info.razibkani.multilanguageapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by razibkani on 5/6/17.
 */

public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, LocaleHelper.getLanguage(base)));
    }
}