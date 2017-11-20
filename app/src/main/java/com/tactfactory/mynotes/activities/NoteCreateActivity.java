package com.tactfactory.mynotes.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tactfactory.mynotes.R;
import com.tactfactory.mynotes.dao.NoteDAO;
import com.tactfactory.mynotes.entities.Note;
import com.tactfactory.mynotes.entities.contracts.NoteContract;

public class NoteCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.note_name);
                Note note = new Note();
                note.setName(editText.getText().toString());
                NoteDAO noteDAO = new NoteDAO(NoteCreateActivity.this);
                noteDAO.insert(note);

                Intent intent = new Intent(NoteCreateActivity.this, NoteShowActivity.class);
                intent.putExtra(NoteContract.INTENT_NOTE, note);

                startActivity(intent);
            }
        });
    }
}
