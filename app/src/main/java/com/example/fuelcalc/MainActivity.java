package com.example.fuelcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    String resultado = "Aguarda resultado!";
    String resulEta = "Valor empate!";
    String resulGas = "Valor empate!";

    EditText editValGas, editValEta, editConGas, editConEta;
    public Button calcular;
    TextView textResultado, textResulEta, textResulGas;
    Button btnSair;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValGas = findViewById(R.id.editvalgas);
        editValEta = findViewById(R.id.editvaleta);
        editConGas = findViewById(R.id.editcongas);
        editConEta = findViewById(R.id.editconeta);
        btnSair = findViewById(R.id.buttonsair);
        textResultado = findViewById(R.id.textResultado);
        calcular = findViewById(R.id.buttoncalc);
        textResulEta = findViewById(R.id.textResulEta);
        textResulGas = findViewById(R.id.textResulGas);

        calcular.setOnClickListener(new EditText.OnClickListener() {
            @Override
            public void onClick(View v){
                boolean ifAllFieldsChecked = CheckAllFields();

                if(ifAllFieldsChecked) {
                    processar();
                    //calcular.setText("Resultado: " + resultado);
                    textResultado.setText("Resultado: " + resultado);
                    textResulEta.setText("Valor Empate Etanol: " + resulEta);
                    textResulGas.setText("Valor Empate Gasolina: " + resulGas);
                    Toast.makeText(getBaseContext(), "Ação Calculada!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean CheckAllFields() {
        if (editValGas.length() == 0) {
            editValGas.setError("Obrigatório preencher");
            return false;
        }

        if (editValEta.length() == 0) {
            editValEta.setError("Obrigatório preencher");
            return false;
        }

        if (editConGas.length() == 0) {
            editConGas.setError("Obrigatório preencher");
            return false;
        }

        if (editConEta.length() == 0) {
            editConEta.setError("Obrigatório preencher");
            return false;
        }
        // after all validation return true.
        return true;
    }
    public void processar(){
        //DecimalFormat format = new DecimalFormat("###.##");

        double mediaEta = Double.parseDouble(editConEta.getText().toString());
        double mediaGas = Double.parseDouble(editConGas.getText().toString());
        double valEta = Double.parseDouble(editValEta.getText().toString());
        double valGas = Double.parseDouble(editValGas.getText().toString());

        double gaso  = valGas/mediaGas;
        double etan = valEta/mediaEta;
        double valuk = etan/gaso;

        String res = "Gasolina";

        if(valuk < 1.0){
            res = "Etanol";
            resulGas = new DecimalFormat("##.00").format(mediaGas * etan);
            resulEta = new DecimalFormat("##.00").format(mediaEta * gaso);
        }else{
            resulGas = new DecimalFormat("##.00").format(mediaGas * etan);
            resulEta = new DecimalFormat("##.00").format(mediaEta * gaso);
        }
        resultado = res;// + " " + String.valueOf(valuk);
    }
    public void btnSair(View view){
        Toast.makeText(getBaseContext(), "Saindo!", Toast.LENGTH_LONG).show();
        finish();
    }
}