package com.example.notekeep;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.android.material.snackbar.Snackbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    private NoteRecyclerAdapter noteRecyclerAdapter;

    //private ArrayAdapter<NoteInfo> mAdapterNotes;

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
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
             }
        });

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteRecyclerAdapter.notifyDataSetChanged();

    }

    private void initializeDisplayContent() {

        final RecyclerView recyclerNotes= (RecyclerView) findViewById(R.id.list_notes);
        final LinearLayoutManager notesLayoutManager=new LinearLayoutManager(this);
        recyclerNotes.setLayoutManager(notesLayoutManager);

        List<NoteInfo> notes=DataManager.getInstance().getNotes();
        noteRecyclerAdapter = new NoteRecyclerAdapter(this,notes);
        recyclerNotes.setAdapter(noteRecyclerAdapter);
    }

}
