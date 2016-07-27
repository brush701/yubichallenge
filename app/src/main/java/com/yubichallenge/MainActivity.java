package com.yubichallenge;

import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    private CloseReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);

		if (savedInstanceState == null) {
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            trans.replace(R.id.container,new SettingsFragment());
            trans.commit();
		}

        IntentFilter filter = new IntentFilter(getString(R.string.CloseMainActivity));
        this.receiver = new CloseReceiver();
        registerReceiver(this.receiver, filter);
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_settings).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    class CloseReceiver
            extends BroadcastReceiver
    {
        CloseReceiver() {}

        public void onReceive(Context paramContext, Intent paramIntent)
        {
            if (paramIntent.getAction().equals(MainActivity.this.getString(R.string.CloseMainActivity))) {
                MainActivity.this.finish();
            }
        }
    }

    public static class SettingsFragment
            extends PreferenceFragment
    {
        public void onCreate(Bundle paramBundle)
        {
            super.onCreate(paramBundle);
            addPreferencesFromResource(R.xml.preferences);
        }
    }

}
