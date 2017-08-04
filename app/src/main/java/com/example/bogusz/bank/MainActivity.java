package com.example.bogusz.bank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    //GUI declaration
    private Button bZaloguj;
    private EditText etLogin;
    private EditText etHaslo;


    private Toast message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Gui init
        bZaloguj = (Button) findViewById(R.id.bZaloguj);
        etLogin = (EditText) findViewById(R.id.etLogin);
        etHaslo = (EditText) findViewById(R.id.etHaslo);


        bZaloguj.setOnClickListener(bZalogujClicked());


        // wstawia w pole odpowiedni login i haslo, żeby szybciej sie logować
        wstaw();
    }

    private View.OnClickListener bZalogujClicked(){
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sprawdzHaslo();
            }
        };

        return clickListener;
    }

    private void sprawdzHaslo(){
        String tapedLogin = etLogin.getText().toString();
        String tapedHaslo = etHaslo.getText().toString();

        String trueLogin = "daniel";
        String trueHaslo = "123456";

        if((trueHaslo.equals(tapedHaslo)) && (trueLogin.equals(tapedLogin))){
            zaloguj();
        }else{

                CharSequence wiadomosc = "Złe Hasło!";
                message.makeText(getApplicationContext(), wiadomosc,Toast.LENGTH_LONG).show();

                wiadomosc = "Spróbuj jesszcze raz!";
                message.makeText(getApplicationContext(), wiadomosc,Toast.LENGTH_LONG).show();
        }

    }

    private void zaloguj(){

//        sprawdzenie czy dane konto istnieje lub tworzenie nowegogo

        creatingAcount();

        Intent intent;
        intent = new Intent(this,ChooseActivity.class);
        startActivity(intent);

        ChooseActivity.setSignUzytkownik(etLogin.getText().toString().toUpperCase());

        finish();
    }

    private void wstaw(){

        etLogin.setText("daniel");
        etHaslo.setText("123456");
    }


    private void creatingAcount(){

        boolean exist;
        File file = new File(getFilesDir()+ File.separator+"Daniel.txt");
        exist = file.exists();

        if(!exist){
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write("Daniel /n");
                bufferedWriter.write(""+2000);
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
