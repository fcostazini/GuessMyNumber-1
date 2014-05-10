package ar.com.gmn.android;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.google.android.gms.common.AccountPicker;


public class LoginActivity extends ActionBarActivity {

    //Intents Codes
    private static final int ACCOUNT_PICK_REQUEST_CODE = 1;
    public GraphUser user;
    private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

        setContentView(R.layout.login_fragment);
        ((ImageView) findViewById(R.id.google_login)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"}, false, null, null, null, null);
                startActivityForResult(intent, ACCOUNT_PICK_REQUEST_CODE);
            }
        });
        final LoginButton button = (LoginButton) findViewById(R.id.facebook_login);
        button.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {


            @Override
            public void onUserInfoFetched(GraphUser user) {
                LoginActivity.this.user = user;

            }
        });
        button.setBackgroundResource(R.drawable.facebook_icon_chalk);
        button.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {

            //Toast.makeText(getApplicationContext(), user.getName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MenuActivity.class);
            this.startActivity(intent);
        } else if (state.isClosed()) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    public void onActivityResult(int request, int result, Intent i) {
        switch(request){
            case ACCOUNT_PICK_REQUEST_CODE:
                if (result == RESULT_OK){
                    String accountName = i.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    Toast.makeText(getApplicationContext(), accountName, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MenuActivity.class);
                    this.startActivity(intent);

                }
                break;
        }

        uiHelper.onActivityResult(request, result, i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

}
