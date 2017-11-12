package anum.sites.google.com.site.anum20172;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NonLinearMethods extends AppCompatActivity implements View.OnClickListener{

    Button btnIncSearch, btnFixedPoint, btnSecant, btnBisection, btnMultRoots, btnNewton, btnFalseRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_linear_methods);

        btnIncSearch = findViewById(R.id.btnIncSearch);
        btnFixedPoint = findViewById(R.id.btnFixedPoint);
        btnSecant = findViewById(R.id.btnSecant);
        btnBisection = findViewById(R.id.btnBisection);
        btnMultRoots = findViewById(R.id.btnMultRoots);
        btnFalseRule = findViewById(R.id.btnFalseRule);
        btnNewton = findViewById(R.id.btnNewton);

        btnIncSearch.setOnClickListener(this);
        btnFixedPoint.setOnClickListener(this);
        btnSecant.setOnClickListener(this);
        btnBisection.setOnClickListener(this);
        btnMultRoots.setOnClickListener(this);
        btnFalseRule.setOnClickListener(this);
        btnNewton.setOnClickListener(this);
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
            case R.id.btnBisection:
                Intent aBis = new Intent(NonLinearMethods.this, Bisection.class);
                startActivity(aBis);
                finish();
                break;
            case R.id.btnMultRoots:
                Intent aMultR = new Intent(NonLinearMethods.this, MultipleRoots.class);
                startActivity(aMultR);
                finish();
                break;
            case R.id.btnFalseRule:
                Intent aFalR = new Intent(NonLinearMethods.this, FalseRule.class);
                startActivity(aFalR);
                finish();
                break;
            case R.id.btnNewton:
                Intent aNewt = new Intent(NonLinearMethods.this, Newton.class);
                startActivity(aNewt);
                finish();
                break;
        }
    }
}
