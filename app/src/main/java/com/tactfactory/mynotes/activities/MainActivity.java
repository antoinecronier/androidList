package com.tactfactory.mynotes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tactfactory.mynotes.R;
import com.tactfactory.mynotes.dao.EnregistrementDAO;
import com.tactfactory.mynotes.dao.NoteDAO;
import com.tactfactory.mynotes.entities.Enregistrement;
import com.tactfactory.mynotes.entities.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //daoTest();

    }

    private void daoTest() {
        NoteDAO daoNote = new NoteDAO(this);
        EnregistrementDAO daoEnr = new EnregistrementDAO(this);

        List<Note> notes = daoNote.get();
        List<Enregistrement> enregistrements = daoEnr.get();

        for (Note note: notes) {
            android.util.Log.i("Note", note.getId() + " " + note.getName());
        }

        for (Enregistrement enr: enregistrements) {
            android.util.Log.i("Enregistrement", enr.getId() + " " + enr.getContenu() + " " + enr.getNote_id());
        }

        for (Note note: notes) {
            daoNote.delete(note);
        }

        for (Enregistrement enr: enregistrements) {
            daoEnr.delete(enr);
        }

        Note note = new Note();
        for(int i = 0; i < 10; i++){
            note = new Note();
            note.setName("note "+i);
            daoNote.insert(note);
            for(int j = 0; j < 10; j++){
                Enregistrement enr = new Enregistrement();
                enr.setContenu("test contenu enregistrement note " + i + " " + j);
                enr.setNote_id(note.getId());
                daoEnr.insert(enr);
            }
        }

        List<Enregistrement> enregistrementList = enregistrementList = daoEnr.get(note.getId());

        for (Enregistrement enr: enregistrementList) {
            android.util.Log.i("Enregistrement extract", enr.getId() + " " + enr.getContenu() + " " + enr.getNote_id());
        }
    }
}
