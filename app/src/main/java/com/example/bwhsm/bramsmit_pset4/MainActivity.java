package com.example.bwhsm.bramsmit_pset4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> itemArray = new ArrayList<Item>();
    ArrayList<Item> toBeDeleted = new ArrayList<Item>();
    DBHandler dbHandler;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this, null, null, 2);
        getItemList();

        findViewById(R.id.addButton).setOnClickListener(new AddButtonClicked());
        findViewById(R.id.deleteButton).setOnClickListener(new DeleteButtonClicked());
        loadListView();
    }

    private void loadListView() {
        ArrayAdapter arrayAdapter = new CustomAdapter(this, itemArray);
        lvItems = (ListView) findViewById(R.id.taskList);
        lvItems.setAdapter(arrayAdapter);
        for (int i=0;i<itemArray.size();i++) {
            if (itemArray.get(i).getCompleted()) {
                toBeDeleted.add(itemArray.get(i));
            }
        }
        lvItems.setOnItemClickListener(
            new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Item item = (Item) parent.getItemAtPosition(position);
                    if (item.getCompleted()) {
                        item.setCompleted(false);
                        toBeDeleted.remove(item);
                    } else {
                        item.setCompleted(true);
                        toBeDeleted.add(item);
                    }
                    dbHandler.updateItem(item);
                    getItemList();
                    ((CustomAdapter) lvItems.getAdapter()).notifyDataSetChanged();
                }
            }
        );
        lvItems.setOnItemLongClickListener(
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Item item = (Item) parent.getItemAtPosition(position);
                    ArrayList<Item> tempItemList = new ArrayList<Item>();
                    tempItemList.add(item);
                    dbHandler.deleteItems(tempItemList);
                    getItemList();
                    loadListView();
                    return true;
                }
        });

    }

    private void getItemList() {
        itemArray = dbHandler.databaseToArray();
    }


    private class AddButtonClicked implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            goToInput();
        }
    }

    private class DeleteButtonClicked implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            dbHandler.deleteItems(toBeDeleted);
            getItemList();
            loadListView();
        }
    }

    private void goToInput() {
        Intent inputIntent = new Intent(this, InputActivity.class);
        this.startActivity(inputIntent);
        finish();
    }

}
