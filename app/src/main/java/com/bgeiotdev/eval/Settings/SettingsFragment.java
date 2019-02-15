package com.bgeiotdev.eval.Settings;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bgeiotdev.eval.Classes.User.User;
import com.bgeiotdev.eval.Others.MyApplication;
import com.bgeiotdev.eval.Others.UserModelView;
import com.bgeiotdev.eval.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    private EditTextPreference mEditPreference;
    private UserModelView umv;
    private ListPreference mListPreference;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        umv = ViewModelProviders.of(this).get(UserModelView.class);
        mEditPreference = (EditTextPreference) getPreferenceManager().findPreference("pseudo_settings");
        mListPreference = (ListPreference) getPreferenceManager().findPreference("difficulty_settings");
        mEditPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (preference.getKey().equals("pseudo_settings")) {
                    User user = umv.loadUser().get(0);
                    String value = newValue.toString();
                    user.setPseudo(value);
                    umv.updateUser(user);
                    Toast.makeText(getActivity(), "Paramètre changé avec succès", Toast.LENGTH_LONG).show();
                }

                return true;
            }
        });

    }


}