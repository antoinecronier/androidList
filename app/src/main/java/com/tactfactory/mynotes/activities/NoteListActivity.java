package com.tactfactory.mynotes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tactfactory.mynotes.R;
import com.tactfactory.mynotes.entities.Note;

public class NoteListActivity extends AppCompatActivity implements NoteFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NoteListActivity.this, NoteCreateActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onListFragmentClickInteraction(Note item) {

    }

    @Override
    public void onListFragmentLongClickInteraction(Note item) {

    }
}
