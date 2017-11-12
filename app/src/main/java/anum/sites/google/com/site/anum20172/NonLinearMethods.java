package anum.sites.google.com.site.anum20172;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NonLinearMethods extends AppCompatActivity implements View.OnClickListener{

    Button btnIncSearch, btnFixedPoint, btnSecant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_linear_methods);

        btnIncSearch = findViewById(R.id.btnIncSearch);
        btnFixedPoint = findViewById(R.id.btnFixedPoint);
        btnSecant = findViewById(R.id.btnSecant);

        btnIncSearch.setOnClickListener(this);
        btnFixedPoint.setOnClickListener(this);
        btnSecant.setOnClickListener(this);

        //btnBisection = findViewById(R.id.btnBisection);

        /*btnMultSQroot = findViewById(R.id.btnMultSQroot);
        btnFalseRule = findViewById(R.id.btnFalseRule);
        btnSecant = findViewById(R.id.btnSecant);
        btnNewton = findViewById(R.id.btnNewton);

        btnBisection.setOnClickListener(this);
        btnIncSearch.setOnClickListener(this);
        btnFixedPoint.setOnClickListener(this);
        btnMultSQroot.setOnClickListener(this);
        btnFalseRule.setOnClickListener(this);
        btnSecant.setOnClickListener(this);
        btnNewton.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnIncSearch:
                Intent aInc = new Intent(NonLinearMethods.this, IncSearch.class);
                startActivity(aInc);
                finish();
                break;
            case R.id.btnFixedPoint:
                Intent aFp = new Intent(NonLinearMethods.this, FixedPoint.class);
                startActivity(aFp);
                finish();
                break;
            case R.id.btnSecant:
                Intent aSec = new Intent(NonLinearMethods.this, Secant.class);
                startActivity(aSec);
                finish();
                break;
        }
    }
}
