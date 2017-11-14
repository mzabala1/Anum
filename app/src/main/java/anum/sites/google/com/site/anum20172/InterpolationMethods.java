package anum.sites.google.com.site.anum20172;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class InterpolationMethods extends AppCompatActivity implements View.OnClickListener{
    Button btnNewtonI, btnLagrange, btnNeville;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolation_methods);

        btnNewtonI = findViewById(R.id.btnNewtonI);
        btnLagrange = findViewById(R.id.btnLagrange);
        btnNeville = findViewById(R.id.btnNeville);

        btnNewtonI.setOnClickListener(this);
        btnLagrange.setOnClickListener(this);
        btnNeville.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNewtonI:
                Intent aNew = new Intent(InterpolationMethods.this, NewtonI.class);
                startActivity(aNew);
                finish();
                break;
           /* case R.id.btnFixedPoint:
                Intent aL = new Intent(InterpolationMethods.this, Lagrange.class);
                startActivity(aL);
                finish();
                break;
            case R.id.btnSecant:
                Intent aNev = new Intent(InterpolationMethods.this, Neville.class);
                startActivity(aNev);
                finish();
                break;  **/
        }
    }
}