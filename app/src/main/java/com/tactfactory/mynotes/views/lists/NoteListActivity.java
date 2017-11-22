package com.tactfactory.mynotes.views.lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tactfactory.mynotes.R;
import com.tactfactory.mynotes.dao.EnregistrementDAO;
import com.tactfactory.mynotes.dao.NoteDAO;
import com.tactfactory.mynotes.entities.Enregistrement;
import com.tactfactory.mynotes.entities.contracts.NoteContract;
import com.tactfactory.mynotes.views.activities.NoteCreateActivity;
import com.tactfactory.mynotes.views.fragments.NoteFragment;
import com.tactfactory.mynotes.entities.Note;

public class NoteListActivity extends AppCompatActivity implements NoteFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //LoadInitialData();

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
        Intent intent = new Intent(NoteListActivity.this, EnregistrementListActivity.class);
        intent.putExtra(NoteContract.INTENT_NOTE,item);
        startActivity(intent);
    }

    @Override
    public void onListFragmentLongClickInteraction(Note item) {

    }

    public void LoadInitialData(){
        EnregistrementDAO enregistrementDAO = new EnregistrementDAO(this);
        NoteDAO noteDAO = new NoteDAO(this);

        // Purje
        enregistrementDAO.delete();
        noteDAO.delete();

        // Create
        for (int i = 0;i < 3;i++){
            Long note_id = (noteDAO.insert(new Note("note " + i))).getId();
            for (int j = 0; j < 5; j++){
                enregistrementDAO.insert(new Enregistrement("enregistrement " + j, note_id));
            }
        }
    }
}
