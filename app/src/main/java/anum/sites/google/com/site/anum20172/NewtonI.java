package anum.sites.google.com.site.anum20172;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;

public class NewtonI extends AppCompatActivity {
    ArrayList<Double> vx = new ArrayList<>();
    ArrayList<Double> vy = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtoni);

        final EditText valX = (EditText) findViewById(R.id.valX);
        final EditText valY = (EditText) findViewById(R.id.valY);
        final EditText value = (EditText) findViewById(R.id.value);
        final TextView textoValor = (TextView) findViewById(R.id.resultText);
        Button addCoord = findViewById(R.id.btnAdd);
        Button clearCoord = findViewById(R.id.btnDel);
        Button calculeX = findViewById(R.id.btnCal);
        addCoord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valX.getText().toString().isEmpty() || valY.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Value of X or Y can't be empty", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    double x = Double.parseDouble(valX.getText().toString());
                    double y = Double.parseDouble(valY.getText().toString());
                    vx.add(x);
                    vy.add(y);
                    Snackbar.make(view, ("coordinates (" + Double.toString(x) + "," + Double.toString(y) + ") added"), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        clearCoord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vx.clear();
                vy.clear();
                Snackbar.make(view, "All coordinates were eliminated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        calculeX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value.getText().toString().isEmpty()) {
                    Snackbar.make(view, "Please enter a value to evaluate", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (vx.isEmpty() || vy.isEmpty()) {
                    Snackbar.make(view, "No coordinates entered", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    double val = Double.parseDouble(value.getText().toString());
                    double result = -1;
                    double[] x = new double[vx.size()];
                    double[] y = new double[vy.size()];
                    for (int i = 0; i < vx.size(); i++) {
                        x[i] = vx.get(i);
                        y[i] = vy.get(i);
                    }
                    result = NewtonI(x, y, val);
                    textoValor.setText("The result of evaluating the value is" + '\n' + Double.toString(result));
                }

            }
        });
    }

    public double NewtonI(double [] coordinatesX, double[] coordinatesY, double val){
        int n = coordinatesX.length;
        double[][] aux = new double[n][n];
        double prod = 1;
        double acum = 0;

        for(int i = 0; i < n; i++){

            aux[i][0] = coordinatesY[i];

            for(int j = 1; j <= i; j++){
                aux[i][j] = (aux[i][j-1] - aux[i-1][j-1])/(coordinatesX[i] - coordinatesX[i-j]);
            }
            if(i > 0){
                prod *= val-coordinatesX[i-1];
            }
            acum += aux[i][i]*prod;
        }
        return acum;
    }
}
