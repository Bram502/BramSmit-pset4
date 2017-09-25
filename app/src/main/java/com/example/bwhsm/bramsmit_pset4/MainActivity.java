package com.example.bwhsm.bramsmit_pset4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText inputText;
    TextView txtView;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (EditText) findViewById(R.id.textInput);
        txtView = (TextView) findViewById(R.id.textView);
        dbHandler = new DBHandler(this,null,null,1);

        findViewById(R.id.addButton).setOnClickListener(new addButtonClicked());
        findViewById(R.id.deleteButton).setOnClickListener(new deleteButtonClicked());
    }

    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        txtView.setText(dbString);
        inputText.setText("");
    }

    private class addButtonClicked implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Item item = new Item(inputText.getText().toString());
            dbHandler.addItem(item);
            printDatabase();
        }
    }


    private class deleteButtonClicked implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            dbHandler.deleteItem(inputText.getText().toString());
            printDatabase();
        }
    }



}
