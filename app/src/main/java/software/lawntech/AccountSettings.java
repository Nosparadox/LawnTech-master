package software.lawntech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AccountSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    public void saved(View view) {
        Intent getNameScreenIntent = new Intent(this, UserActivity.class);
        final int result = 1;
        getNameScreenIntent.putExtra("address", "2262 owl canyon lake tx");
        startActivityForResult(getNameScreenIntent, result);
        finish();
    }

}
