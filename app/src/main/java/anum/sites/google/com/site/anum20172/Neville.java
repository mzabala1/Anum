package anum.sites.google.com.site.anum20172;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Neville extends AppCompatActivity {
    ArrayList<Double> vx = new ArrayList<>();
    ArrayList<Double> vy = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neville);

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
                    result = NevilleI(x, y, val);
                    textoValor.setText("The result of evaluating the value is" + '\n' + Double.toString(result));
                }

            }
        });
    }

    public double NevilleI(double x[], double[] y, double val){
        int npoints = x.length;
        double values[][] = new double[npoints][npoints];
        for(int i = 0; i < npoints; i++){
            values[i][0] = y[i];
        }
        for(int i = 0; i < npoints; i++){
            for(int j = 1; j < i + 1; j++){
                values[i][j] = ((val - x[i-j]) * values[i][j-1] - ((val - x[i])*values[i-1][j-1]))/(x[i] - x[i-j]);
            }
        }
        return values[npoints-1][npoints-1];
    }
}
