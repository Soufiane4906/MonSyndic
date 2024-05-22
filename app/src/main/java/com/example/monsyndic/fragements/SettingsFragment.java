package com.example.monsyndic.fragements;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.monsyndic.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}