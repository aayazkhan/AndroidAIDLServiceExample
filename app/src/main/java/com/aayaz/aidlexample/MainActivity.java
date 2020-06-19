package com.aayaz.aidlexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private IAdditionService additionService;


    private EditText editTextNumber1, editTextNumber2;
    private Button buttonCalculate;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        bindService(
                new Intent(this, AdditionService.class),
                serviceConnection,
                Context.BIND_AUTO_CREATE
        );

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int number1 = Integer.parseInt(editTextNumber1.getText().toString());
                int number2 = Integer.parseInt(editTextNumber2.getText().toString());

                int result = 0;
                try {
                    result = additionService.add(number1, number2);

                    textViewResult.setText("" + result);

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            additionService = IAdditionService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            additionService = null;
        }
    };

}