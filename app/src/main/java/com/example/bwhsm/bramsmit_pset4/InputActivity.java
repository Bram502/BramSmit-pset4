package com.example.bwhsm.bramsmit_pset4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {

    EditText userInput;
    String input;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        dbHandler = new DBHandler(this,null,null,2);

        userInput = (EditText) findViewById(R.id.userInput);
        findViewById(R.id.enterButton).setOnClickListener(new enterClicked());
    }


    private void newEntry() {
        input = userInput.getText().toString();
        Item item = new Item(input);
        dbHandler.addItem(item);
        goToMain();
    }

    private class enterClicked implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            newEntry();
        }
    }

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain() {
        Intent mainIntent = new Intent(this,MainActivity.class);
        this.startActivity(mainIntent);
        finish();
    }
}
