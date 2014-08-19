package ar.com.gmn.android.core;

import android.app.Application;

/**
 * Created by Fcostazini on 17/05/2014.
 */
public class GameApplication extends Application {
    private Player user;

    public Player getUser() {
        return this.user;
    }

    public void setUser(Player user) {
        this.user = user;
    }
}
