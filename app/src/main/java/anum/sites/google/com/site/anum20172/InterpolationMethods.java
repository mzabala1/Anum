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
import android.widget.EditText;
import android.widget.ImageView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class InterpolationMethods extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*btnBisection = findViewById(R.id.btnBisection);
        btnIncSearch = findViewById(R.id.btnIncSearch);
        btnFixedPoint = findViewById(R.id.btnFixedPoint);
        btnMultSQroot = findViewById(R.id.btnMultSQroot);
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolation_methods);
    }
}
