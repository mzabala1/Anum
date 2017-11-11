package anum.sites.google.com.site.anum20172;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Main extends AppCompatActivity implements View.OnClickListener{

    Button btnNon, btnSysEqu, btnInterpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referencia a objetos
        btnNon = findViewById(R.id.btnNon);
        btnSysEqu= findViewById(R.id.btnSysEqu);
        btnInterpo = findViewById(R.id.btnInterpo);

        btnNon.setOnClickListener(this);
        btnSysEqu.setOnClickListener(this);
        btnInterpo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNon:
                Intent aNon = new Intent(Main.this, NonLinearMethods.class);
                startActivity(aNon);
                finish();
                break;
            case R.id.btnSysEqu:
                Intent aSys = new Intent(Main.this, SolutionsOfSystemsOfEquations.class);
                startActivity(aSys);
                finish();
                break;
            case R.id.btnInterpo:
                Intent aInter = new Intent(Main.this, InterpolationMethods.class);
                startActivity(aInter);
                finish();
                break;
        }
    }
}
