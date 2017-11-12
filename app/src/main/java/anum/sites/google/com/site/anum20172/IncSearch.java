package anum.sites.google.com.site.anum20172;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class IncSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inc_search);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTable();
            }
        });
    }

    protected void createTable() {
        final TextView IncSearchResultView = (TextView)findViewById(R.id.IncSearch_result);
        final EditText exprEdit = (EditText)findViewById(R.id.expression);
        final EditText xoEdit = (EditText)findViewById(R.id.x0numberSearch);
        final EditText deltaEdit = (EditText)findViewById(R.id.delnumberSearch);
        final EditText iterEdit = (EditText)findViewById(R.id.iternumberSearch);

        Double x0 = Double.parseDouble(xoEdit.getText().toString());
        Double delta = Double.parseDouble(deltaEdit.getText().toString());
        int iter = Integer.parseInt(iterEdit.getText().toString());
        String exp = exprEdit.getText().toString();
        // Creating analyzer
        try {
            Expression e = new ExpressionBuilder(exp)
                    .variables("x").build();

            // Creating table for method
            TableLayout table = (TableLayout)findViewById(R.id.incSearch_table);
            TableRow ttr = new TableRow(this);
            TableRow.LayoutParams tlp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            ttr.setLayoutParams(tlp);
            ttr.setBackgroundColor(Color.parseColor("#CFD8DC"));

            TextView txView = new TextView(this);
            TextView tfxView = new TextView(this);

            txView.setBackgroundResource(R.drawable.table_border_title);
            tfxView.setBackgroundResource(R.drawable.table_border_title);

            txView.setText(" x ");
            tfxView.setText(" f(x) ");

            ttr.addView(txView);
            ttr.addView(tfxView);

            table.addView(ttr,0);

            double x1, y0, y1;
            int cont;

            if (delta != 0) {
                if (iter > 0) {
                    y0 = e.setVariable("x", x0).evaluate();
                    if (y0 != 0) {
                        x1 = x0 + delta;
                        y1 = e.setVariable("x", x1).evaluate();
                        cont = 1;
                        while (((y0 * y1) > 0) && cont < iter) {
                            TableRow tr = new TableRow(this);
                            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                            tr.setLayoutParams(lp);
                            TextView xView = new TextView(this);
                            TextView fxView = new TextView(this);

                            xView.setBackgroundResource(R.drawable.table_border);
                            fxView.setBackgroundResource(R.drawable.table_border);

                            xView.setText(" " + String.valueOf(x0) + " ");
                            fxView.setText(" " + String.valueOf(y0) + " ");

                            tr.addView(xView);
                            tr.addView(fxView);

                            table.addView(tr,cont);

                            x0 = x1;
                            y0 = y1;
                            x1 = x0 + delta;
                            y1 = e.setVariable("x", x1).evaluate();
                            cont++;
                        }
                        if (y0 == 0) {
                            IncSearchResultView.setText(x1 + " is root.");
                            return;
                        } else {
                            String res = ((y0 * y1) < 0) ? "Root between values (" + x0 + "," + x1 + ")" : "Can't find root.";
                            IncSearchResultView.setText(res);
                            return;
                        }
                    } else {
                        IncSearchResultView.setText(x0 + " is root.");
                        return;
                    }
                } else {
                    IncSearchResultView.setText("Number of iterations must be more than 0.");
                    return;
                }
            } else {
                IncSearchResultView.setText("Delta should ve different of 0.");
                return;
            }
        } catch (RuntimeException e) {
            IncSearchResultView.setText(e.getMessage());
        }
    }

}
