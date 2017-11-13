package anum.sites.google.com.site.anum20172;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class SolutionsOfSystemsOfEquations extends AppCompatActivity {

    private int size;
    private ArrayList<ArrayList> data = new ArrayList<>();
    private ArrayList<Double> results = new ArrayList<>();
    private boolean step;
    private ArrayList<Double> x0 = new ArrayList<>();
    private boolean bt1 = false;
    private boolean bt2 = false;
    private int iterations;
    private double tolerance;
    private double relax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions_of_systems_of_equations);

        Button btnCreate = findViewById(R.id.create);
        Button btnSave = findViewById(R.id.save);
        Button btnRelax = findViewById(R.id.relaxation);
        Button btnIter = findViewById(R.id.iteratives);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTable();
            }});

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTable();
            }});

        btnRelax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt1 = true;
                relaxation();
            }});

        btnIter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt2 = true;
                iterations();
            }});

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.systems_equations, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.interpolation){
            Intent a = new Intent(this, InterpolationMethods.class);
            startActivity(a);
        }
        if(id == R.id.oneVarEquations) {
            Intent a = new Intent(this, Main.class);
            startActivity(a);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        Bundle bun = new Bundle();
        bun.putInt("size", size);
        bun.putSerializable("data", data);
        bun.putSerializable("results", results);
        bun.putBoolean("step", step);
        if(bt2){
            bun.putInt("iterations",iterations);
            bun.putDouble("tolerance",tolerance);
            bun.putSerializable("x0",x0);
        }
        if(bt1)bun.putDouble("relax",relax);
        int id = item.getItemId();
        switch (id){
            case R.id.gElim:
                bun.putString("operation", "gElim");
                Intent a = new Intent(this, Matrix.class);
                a.putExtras(bun);
                startActivity(a);
                break;
            case R.id.gppElim:
                bun.putString("operation", "gppElim");
                Intent b = new Intent(this, Matrix.class);
                b.putExtras(bun);
                startActivity(b);
                break;
            case R.id.gptElim:
                bun.putString("operation", "gptElim");
                Intent c = new Intent(this, Matrix.class);
                c.putExtras(bun);
                startActivity(c);
                break;
            case R.id.gaussSeidel:
                bun.putString("operation", "gaussSeidel");
                Intent d = new Intent(this, Matrix.class);
                d.putExtras(bun);
                startActivity(d);
                break;
            case R.id.jacobi:
                bun.putString("operation", "jacobi");
                Intent e = new Intent(this, Matrix.class);
                e.putExtras(bun);
                startActivity(e);
                break;
            case R.id.cholesky:
                bun.putString("operation","cholesky");
                Intent f = new Intent(this, Matrix.class);
                f.putExtras(bun);
                startActivity(f);
                break;
            case R.id.crout:
                bun.putString("operation","crout");
                Intent g = new Intent(this, Matrix.class);
                g.putExtras(bun);
                startActivity(g);
                break;
            case R.id.doolittle:
                bun.putString("operation","doolittle");
                Intent h = new Intent(this, Matrix.class);
                h.putExtras(bun);
                startActivity(h);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected void createTable() {
        final EditText matrixn = (EditText)findViewById(R.id.matrixn);
        size = Integer.parseInt(matrixn.getText().toString());

        try {
            TableLayout table = (TableLayout) findViewById(R.id.data_input);
            table.removeAllViews();
            TableRow.LayoutParams tlp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);

            for (int i = 0; i < size; i++) {
                TableRow ttr = new TableRow(this);
                ttr.setLayoutParams(tlp);
                ttr.setBackgroundColor(Color.parseColor("#CFD8DC"));
                ttr.setMinimumHeight(110);
                for (int j = 0; j <= size; j++) {
                    EditText etxt = new EditText(this);
                    etxt.setKeyListener(new DigitsKeyListener());
                    etxt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    etxt.setHeight(100);
                    etxt.setWidth(100);
                    if(j == size){
                        etxt.setBackgroundResource(R.drawable.edit_border);
                    }else{
                        etxt.setBackgroundResource(R.drawable.table_border);
                    }
                    ttr.addView(etxt);
                }
                table.addView(ttr);
            }
            Button guardar = (Button) findViewById(R.id.save);
            Button rela = (Button) findViewById(R.id.relaxation);
            Button iter = (Button) findViewById(R.id.iteratives);
            guardar.setVisibility(View.VISIBLE);
            rela.setVisibility(View.VISIBLE);
            iter.setVisibility(View.VISIBLE);

        } catch (RuntimeException e) {
            System.out.println("Error");
        }
    }

    protected void saveTable() {
        if(!data.isEmpty()){
            data.clear();
            results.clear();
        }
        TableLayout table = (TableLayout) findViewById(R.id.data_input);
        CheckBox check = (CheckBox) findViewById(R.id.checkP);
        step = check.isChecked();
        for (int i = 0; i < size; i++) {
            ArrayList<Double> row = new ArrayList<>();
            TableRow t = (TableRow) table.getChildAt(i);
            for (int j = 0; j < size; j++) {
                EditText eTxt = (EditText) t.getChildAt(j);
                Double num = Double.parseDouble(eTxt.getText().toString());
                row.add(num);
            }
            data.add(row);
        }
        for (int i = 0; i < size; i++) {
            TableRow t = (TableRow) table.getChildAt(i);
            EditText eTxt = (EditText) t.getChildAt(size);
            Double num = Double.parseDouble(eTxt.getText().toString());
            results.add(num);
        }
        if(bt1) {
            TableLayout relax = (TableLayout) findViewById(R.id.relaxationt);
            TableRow tRelax = (TableRow) relax.getChildAt(0);
            EditText eRelax = (EditText) tRelax.getChildAt(1);
            this.relax = Double.parseDouble(eRelax.getText().toString());
        }
        if(bt2) {
            TableLayout iter = (TableLayout) findViewById(R.id.iterativest);
            TableRow tTol = (TableRow) iter.getChildAt(0);
            EditText tol = (EditText) tTol.getChildAt(1);
            tolerance = Double.parseDouble(tol.getText().toString());

            TableRow tIter = (TableRow) iter.getChildAt(1);
            EditText eIter = (EditText) tIter.getChildAt(1);
            iterations = Integer.parseInt(eIter.getText().toString());

            TableRow tx0 = (TableRow) iter.getChildAt(2);
            for (int i = 0; i < size; i++) {
                EditText ex0 = (EditText) tx0.getChildAt(i);
                Double x = Double.parseDouble(ex0.getText().toString());
                x0.add(x);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        bundle.putSerializable("results", results);
        bundle.putInt("size", size);
        bundle.putBoolean("step", step);
        if(bt2) {
            bundle.putInt("iterations", iterations);
            bundle.putDouble("tolerance", tolerance);
            bundle.putSerializable("x0", x0);
        }
        if(bt1)bundle.putDouble("relax",relax);
        Intent a = new Intent(this,Matrix.class);
    }

    protected void relaxation(){
        TableLayout table = (TableLayout) findViewById(R.id.relaxationt);
        TableRow ttr = new TableRow(this);
        TextView txt = new TextView(this);
        txt.setText("Value of the relaxation");
        EditText eTxt = new EditText(this);
        eTxt.setKeyListener(new DigitsKeyListener());
        eTxt.setHeight(100);
        eTxt.setWidth(100);
        eTxt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ttr.addView(txt);
        ttr.addView(eTxt);
        table.addView(ttr);
    }

    protected void iterations(){
        TableLayout table = (TableLayout) findViewById(R.id.iterativest);
        TableRow ttr = new TableRow(this);
        ttr.setMinimumHeight(110);
        for(int i = 0; i < size; i++){
            EditText eTxt = new EditText(this);
            eTxt.setWidth(100);
            eTxt.setHeight(100);
            eTxt.setKeyListener(new DigitsKeyListener());
            eTxt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            ttr.addView(eTxt);
        }
        TableRow iter = new TableRow(this);
        TextView txt = new TextView(this);
        txt.setText("Number of Iterations");
        EditText eTxt = new EditText(this);
        eTxt.setKeyListener(new DigitsKeyListener());
        eTxt.setWidth(100);
        eTxt.setHeight(100);
        iter.addView(txt);
        iter.addView(eTxt);

        TableRow tol = new TableRow(this);
        TextView txt1 = new TextView(this);
        txt1.setText("Tolerance");
        EditText eTxtL = new EditText(this);
        eTxtL.setKeyListener(new DigitsKeyListener());
        eTxtL.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        eTxtL.setWidth(100);
        eTxtL.setHeight(100);
        tol.addView(txt1);
        tol.addView(eTxtL);

        table.addView(tol);
        table.addView(iter);
        table.addView(ttr);
    }
}
