package com.chargemap_beta.android.preferences.library;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by r4phab on 02/10/2016.
 */

public class SettingFragment extends Fragment {

    RecyclerView recyclerView;

    SettingsBuilder settingsBuilder;

    public static SettingFragment newInstance(SettingsBuilder settingsBuilder) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();

        args.putSerializable("settingsBuilder", settingsBuilder);

        fragment.setArguments(args);
        return fragment;
    }

    /***********************************************
     * Initialisation
     **********************************************/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        settingsBuilder = (SettingsBuilder) getArguments().getSerializable("settingsBuilder");

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        settingsBuilder.setupRecyclerView(recyclerView);

        return view;
    }

}