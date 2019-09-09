package com.example.settingspreferenceexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setTitle("Settings");

//        if (findViewById(R.id.fragment_container)!=null){
//
//            if (savedInstanceState !=null)
//                return;

        Fragment fragment = new SettingsScreen();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (savedInstanceState == null){
            fragmentTransaction.add(R.id.relative_layout,fragment, "settings_fragment");
            fragmentTransaction.commit();
        }else{
            fragment = getFragmentManager().findFragmentByTag("settings_fragment");
        }

//            getFragmentManager().beginTransaction().add(R.id.fragment_container, new SettingsFragment()).commit();

//            getFragmentManager().beginTransaction().add(R.id.fragment_container,new SettingsFragment()).commit();
        }




        public static class SettingsScreen extends PreferenceFragment{

//        we create two strings to contain the keynames of the preference setting we wish to listen
        public static final String PREF_SMS_LIMIT = "sms_delete_limit";
        public static final String PREF_MMS_LIMIT = "mms_delete_limit";

//        we create an interface variable of OnSharedPreferenceChangeListener called preferenceChangeListener for easier reference
        private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
//                addPreferencesFromResource(R.xml.preferences);
//                addPreferencesFromResource(R.xml.preferences_group);
//                addPreferencesFromResource(R.xml.preference_subscreen);
                addPreferencesFromResource(R.xml.preferences_def_vals);

//                we then initialize the preferenceChangeListener and override only the onSharedPreferenceChanged method
                preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                    //                    whenever  a change is made in the settings preference, this method is called
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//                        if the key of the selected preference is equal to that of PREF_SMS_LIMIT...
                        if (key.equals(PREF_SMS_LIMIT)){
//                            we find the preference tag with that key in the settings preference
                            Preference smsPref = findPreference(key);
//                            we then set the summary of the found preference tag to be that selected by the user
                            smsPref.setSummary(sharedPreferences.getString(key, "")+ " messages per conversation");
                        }

//                        if the key of the selected preference is equal to that of PREF_MMS_LIMIT...
                        if (key.equals(PREF_MMS_LIMIT)){
//                            we find the preference tag with that key in the settings preference
                            Preference mmsPref = findPreference(key);
//                            we then set the summary of the found preference tag to be that selected by the user
                            mmsPref.setSummary(sharedPreferences.getString(key, "")+ " messages per conversation");
                        }
                    }

                };
            }
//                we then register for this listener from the onResume of this fragment


//            when we leave the settings fragment to another activity and return, the default values override the user's preference
            @Override
            public void onResume() {
                super.onResume();
                getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);

                Preference smsPref = findPreference(PREF_SMS_LIMIT);
                smsPref.setSummary(getPreferenceScreen().getSharedPreferences().getString(PREF_SMS_LIMIT, ""+ " messages per conversation"));


                Preference mmsPref = findPreference(PREF_MMS_LIMIT);
                mmsPref.setSummary(getPreferenceScreen().getSharedPreferences().getString(PREF_MMS_LIMIT, ""+ " messages per conversation"));

            }

//            we then unregister for this listener from the onPause of this of this fragment


            @Override
            public void onPause() {
                super.onPause();
                getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
            }

            @Override
            public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

            }
        }

//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar_layout,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()){
//            case R.id.menu_settings:
//                startActivity(new Intent(this, SettingsActivity.class));
//                return true;
//
//            default: return super.onOptionsItemSelected(item);
//
//
//        }
//    }
}
