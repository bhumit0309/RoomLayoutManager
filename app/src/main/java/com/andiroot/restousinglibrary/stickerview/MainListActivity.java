package com.andiroot.restousinglibrary.stickerview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainListActivity extends AppCompatActivity {

    Realm nameRealm;
    private RealmResults<Position> data;
    int id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Realm.init(this);
        RealmConfiguration realmConfiguration2 = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("LayoutNames")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration2);
        nameRealm = Realm.getDefaultInstance();
        /* do your operations in realm2 */

        id = (int) nameRealm.where(ListNames.class).count();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainListActivity.this);
                builder.setTitle("Add New Layout");
// I'm using fragment here so I'm using getView() to provide ViewGroup
// but you can provide here any other instance of ViewGroup from your Fragment / Activity
                View viewInflated = LayoutInflater.from(MainListActivity.this).inflate(R.layout.dialog_layout, (ViewGroup) findViewById(android.R.id.content)
, false);
// Set up the input
                final EditText input = viewInflated.findViewById(R.id.input);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                builder.setView(viewInflated);

// Set up the buttons
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String m_Text = input.getText().toString();
                        ListNames names = new ListNames( (int) nameRealm.where(ListNames.class).count() + 1, m_Text);
                        nameRealm.beginTransaction();
                        nameRealm.copyToRealmOrUpdate(names);
                        nameRealm.commitTransaction();

                        Log.d("LIST NAMES", nameRealm.where(ListNames.class).findFirst().getName());

                        Intent intent = new Intent(MainListActivity.this, MainActivity.class);
                        intent.putExtra("Position", id);
                        MainListActivity.this.startActivity(intent);}
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
        initViews();
    }

    private void initViews(){
        RecyclerView recyclerView = findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        if(nameRealm.isEmpty()) {

        }
        else {
            nameRealm.where(ListNames.class).findAll();
            DataAdapter adapter = new DataAdapter();
            recyclerView.setAdapter(adapter);
        }
    }
}
