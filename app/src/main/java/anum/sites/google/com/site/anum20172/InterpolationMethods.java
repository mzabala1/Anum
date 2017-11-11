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
    String pass, gFunction, primeFunction, doublePrimeFunction;
    String rx1, rx2, ry1, ry2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        btnBisection = findViewById(R.id.btnBisection);
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
        btnNewton.setOnClickListener(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolation_methods);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText mEdit   = (EditText)findViewById(R.id.editText);
        final EditText mEdit2 = (EditText)findViewById(R.id.editText2);
        final EditText rangoX1 = (EditText)findViewById(R.id.editText4);
        final EditText rangoX2 = (EditText)findViewById(R.id.editText8);
        final EditText rangoY1 = (EditText)findViewById(R.id.editText7);
        final EditText rangoY2 = (EditText)findViewById(R.id.editText9);
        final EditText gFunct    = (EditText)findViewById(R.id.editText3);
        final EditText primeFunct = (EditText)findViewById(R.id.editText5);
        final EditText doublePrimeFunct = (EditText)findViewById(R.id.editText6);
        final ImageView subie  = (ImageView)findViewById(R.id.zubie);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double a = 0d;
                String num = mEdit2.getText().toString();
                if (!num.isEmpty()) a = Double.parseDouble(num);
                String exp = mEdit.getText().toString();
                String function = gFunct.getText().toString();
                String prime = primeFunct.getText().toString();
                String doublePrime = doublePrimeFunct.getText().toString();

                rx1 = rangoX1.getText().toString();
                rx2 = rangoX2.getText().toString();
                ry1 = rangoY1.getText().toString();
                ry2 = rangoY2.getText().toString();
                pass = exp;
                gFunction = function;
                primeFunction = prime;
                doublePrimeFunction = doublePrime;
                try {
                    Expression e = new ExpressionBuilder(exp)
                            .variables("x")
                            .build()
                            .setVariable("x", a);
                    double res = e.evaluate();
                    String b = Double.toString(res);
                    Snackbar.make(view, b, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } catch (RuntimeException e) {
                    Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.solEcuaciones) {
            return true;
        }

        if(id == R.id.interpolation) {
            Intent a = new Intent(this,Interpolacion.class);
            startActivity(a);
        }

        if(id == R.id.sistemasEcuaciones) {
            Intent a = new Intent(this,SistemasDeEcuaciones.class);
            startActivity(a);
        }

        if(id == R.id.integration) {
            Intent a = new Intent(this,Integracion.class);
            startActivity(a);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Bundle bun = new Bundle();
        bun.putString("expr",pass);
        int id = item.getItemId();
        switch (id){
            case R.id.busqInc:
                Intent a = new Intent(this,BusqInc.class);
                a.putExtras(bun);
                startActivity(a);
                break;
            case R.id.bisec:
                Intent b = new Intent(this,Bisecc.class);
                b.putExtras(bun);
                startActivity(b);
                break;
            case R.id.regFal:
                Intent c = new Intent(this, ReglaFal.class);
                c.putExtras(bun);
                startActivity(c);
                break;
            case R.id.puntFijo:
                Intent d = new Intent(this,PuntoFijo.class);
                bun.putString("funcG", gFunction);
                d.putExtras(bun);
                startActivity(d);
                break;
            case R.id.newT:
                Intent e = new Intent(this,Newto.class);
                bun.putString("funcprima", primeFunction);
                e.putExtras(bun);
                startActivity(e);
                break;
            case R.id.seca:
                Intent f = new Intent(this,Secant.class);
                f.putExtras(bun);
                startActivity(f);
                break;
            case R.id.raicMul:
                Intent g = new Intent(this,RaicesMult.class);
                bun.putString("funcprima", primeFunction);
                bun.putString("funcdprima", doublePrimeFunction);
                g.putExtras(bun);
                startActivity(g);
                break;
            case R.id.grapher:
                Intent h = new Intent(this,Grapher.class);
                bun.putString("ranX1",rx1);
                bun.putString("ranX2",rx2);
                bun.putString("ranY1",ry1);
                bun.putString("ranY2",ry2);
                h.putExtras(bun);
                startActivity(h);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
