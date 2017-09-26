package com.example.bwhsm.bramsmit_pset4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    String inputText;
    TextView txtView;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = (TextView) findViewById(R.id.textView);
        dbHandler = new DBHandler(this,null,null,1);

        findViewById(R.id.addButton).setOnClickListener(new addButtonClicked());
//        findViewById(R.id.deleteButton).setOnClickListener(new deleteButtonClicked());
    }

    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        txtView.setText(dbString);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Task");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputText = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private class addButtonClicked implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            showDialog();
            Item item = new Item(inputText);
            dbHandler.addItem(item);
            printDatabase();
        }
    }

//
//    private class deleteButtonClicked implements View.OnClickListener {
//        @Override
//        public void onClick(View view) {
//            dbHandler.deleteItem(inputText.getText().toString());
//            printDatabase();
//        }
//    }



}
