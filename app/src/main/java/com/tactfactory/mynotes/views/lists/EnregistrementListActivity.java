package com.tactfactory.mynotes.views.lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tactfactory.mynotes.R;
import com.tactfactory.mynotes.entities.Enregistrement;
import com.tactfactory.mynotes.views.activities.NoteCreateActivity;
import com.tactfactory.mynotes.views.fragments.EnregistrementFragment;
import com.tactfactory.mynotes.views.recyclers.MyEnregistrementRecyclerViewAdapter;

import java.util.List;

public class EnregistrementListActivity extends AppCompatActivity implements EnregistrementFragment.OnListFragmentInteractionListener {

    EnregistrementFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragment = (EnregistrementFragment) this.getFragmentManager().findFragmentById(R.id.fragment_list_enregistrement);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            fragment.addOneItem();
            }
        });
    }

    @Override
    public void onListFragmentInteraction(Enregistrement item) {

    }
}
