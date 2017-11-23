package anum.sites.google.com.site.anum20172;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Main extends AppCompatActivity implements View.OnClickListener{

    Button btnNon, btnSysEqu, btnInterpo, btnGrapher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencia a objetos
        btnNon = findViewById(R.id.btnNon);
        btnSysEqu= findViewById(R.id.btnSysEqu);
        btnInterpo = findViewById(R.id.btnInterpo);
        btnGrapher = findViewById(R.id.btnGrapher);

        btnNon.setOnClickListener(this);
        btnSysEqu.setOnClickListener(this);
        btnInterpo.setOnClickListener(this);
        btnGrapher.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNon:
                Intent aNon = new Intent(Main.this, NonLinearMethods.class);
                startActivity(aNon);
                break;
            case R.id.btnSysEqu:
                Intent aSys = new Intent(Main.this, SolutionsOfSystemsOfEquations.class);
                startActivity(aSys);
                break;
            case R.id.btnInterpo:
                Intent aInter = new Intent(Main.this, InterpolationMethods.class);
                startActivity(aInter);
                break;
            case R.id.btnGrapher:
                Intent aGraph = new Intent(Main.this, Grapher.class);
                startActivity(aGraph);
                break;
        }
    }
}
