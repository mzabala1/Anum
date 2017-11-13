package anum.sites.google.com.site.anum20172;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Matrix extends AppCompatActivity {
    private int size;
    private ArrayList<ArrayList<Double>> data = new ArrayList<>();
    private ArrayList<Double> results = new ArrayList<>();
    private String methods;
    private double[][] matrix;
    private double[][] extended;
    private int[] marks;
    private double[][] l;
    private double[][] u;
    private double[] ans;
    private boolean step = false;
    private ArrayList<Double> x0 = new ArrayList<>();
    private double tolerance;
    private int iterations;
    private double relaxation;
    private String method1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        Bundle bundle = getIntent().getExtras();
        size = bundle.getInt("size");
        results = (ArrayList<Double>) bundle.getSerializable("results");
        data = (ArrayList<ArrayList<Double>>) bundle.getSerializable("data");
        step = bundle.getBoolean("step");
        methods = bundle.getString("operation");
        matrix = new double[data.size()][data.size()];
        extended = new double[data.size()][data.size()+1];
        marks = new int[size];
        TextView met = (TextView) findViewById(R.id.method);
        met.setTextSize(20);

        switch(methods) {
            case "gElim":
                method1 = "Gaussean Elimination";
                break;
            case "gppElim":
                method1 = "G.E Partial pivot";
                break;
            case "gptElim":
                method1 = "G.E Total pivot";
                break;
            case "gaussSeidel":
                method1 = "Gauss Seidel";
                break;
            case "jacobi":
                method1 = "Jacobi";
                break;
            case "cholesky":
                method1 = "Cholesky";
                break;
            case "crout":
                method1 = "Crout";
                break;
            case "dolitle":
                method1 = "Dolittle";
                break;
        }
        met.setText(method1);

        for(int i = 0; i < marks.length; i++){
            marks[i] = i;
        }

        for (int i = 0; i < size; i++) {
            ArrayList<Double> temp = data.get(i);
            for (int j = 0; j < size; j++) {
                matrix[i][j] = temp.get(j);
            }
        }
        for (int i = 0; i < size; i++) {
            ArrayList<Double> temp = data.get(i);
            for (int j = 0; j < size; j++) {
                extended[i][j] = temp.get(j);
            }
            extended[i][size] = results.get(i);
        }
        double[] results = new double[this.results.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = this.results.get(i);
        }
        double [][]  res;
        try {
            switch (methods) {
                case "gElim":
                    res = gausseanElimination(extended, 0);
                    if(!step) print(res, false);
                    ans = regresiveSustitution(res);
                    nameMatrix("");
                    String text = "";
                    for (int i = 0; i < marks.length; i++) {
                        text = text + "  " +  "X" +  String.valueOf(marks[i]);
                    }
                    nameMatrix(text);
                    printArray(ans);
                    break;
                case "gppElim":
                    res = gausseanElimination(extended, 1);
                    if(!step) print(res, false);
                    ans = regresiveSustitution(res);
                    nameMatrix("");
                    String text1 = "";
                    for (int i = 0; i < marks.length; i++) {
                        text1 = text1 + "  " + "X" + String.valueOf(marks[i]);
                    }
                    nameMatrix(text1);
                    printArray(ans);
                    break;
                case "gptElim":
                    res = gausseanElimination(extended, 2);
                    if(!step) print(res, false);
                    ans = regresiveSustitution(res);
                    nameMatrix("");
                    String text2 = "";
                    for (int i = 0; i < marks.length; i++) {
                        text2 = text2 + "  " + "X" + String.valueOf(marks[i]);
                    }
                    nameMatrix(text2);
                    printArray(ans);
                    break;
                case "gaussSeidel":
                    iterations = bundle.getInt("iterations");
                    tolerance = bundle.getDouble("tolerance");
                    x0 = (ArrayList<Double>) bundle.getSerializable("x0");
                    relaxation = (double)bundle.getDouble("relax");
                    double[] x0a = new double[x0.size()];
                    for(int i = 0; i < x0a.length; i++) {
                        x0a[i] = x0.get(i);
                    }
                    if(!step) {
                        res = SeidelJacobi(x0a, tolerance, iterations, matrix, results, relaxation, false);
                        printResult();
                        printArray(res[0]);
                        nameMatrix("Value: " + String.valueOf(res[1]));
                    }
                    break;
                case "jacobi":
                    iterations = bundle.getInt("iterations");
                    tolerance = bundle.getDouble("tolerance");
                    x0 = (ArrayList<Double>) bundle.getSerializable("x0");
                    relaxation = (double)bundle.getDouble("relax");
                    double[] x0a1 = new double[x0.size()];
                    for(int i = 0; i < x0a1.length; i++) {
                        x0a1[i] = x0.get(i);
                    }
                    if(!step) {
                        res = SeidelJacobi(x0a1, tolerance, iterations, matrix, results, relaxation, true);
                        printResult();
                        printArray(res[0]);
                        nameMatrix(String.valueOf(res[1]));
                    }
                    break;
                case "cholesky":
                    ans = croutCholeskyDoolittle(matrix, results, 1);
                    if (!step) {
                        nameMatrix("L Matrix");
                        print(l,true);
                        nameMatrix("U Matrix");
                        print(u,true);
                        printResult();
                        printArray(ans);
                    }
                    break;
                case "crout":
                    ans = croutCholeskyDoolittle(matrix, results, 2);
                    if (!step) {
                        nameMatrix("L Matrix");
                        print(l,true);
                        nameMatrix("U Matrix");
                        print(u,true);

                        printResult();
                        printArray(ans);
                    }
                    break;
                case "doolittle":
                    ans = croutCholeskyDoolittle(matrix, results, 3);
                    if (!step) {
                        nameMatrix("L Matrix");
                        print(l,true);
                        nameMatrix("U Matrix");
                        print(u,true);
                        printResult();
                        printArray(ans);
                    }
                    break;
            }
        } catch (Exception ex) {
            Snackbar.make(findViewById(R.id.activity_result),ex.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    public void nameMatrix(String name) {
        TableLayout table = (TableLayout) findViewById(R.id.result_table);
        TableRow ttr = new TableRow(this);
        ttr.setMinimumHeight(120);
        TextView n = new TextView(this);
        n.setText(name);
        ttr.addView(n);
        table.addView(ttr);
    }

    public void printResult() {
        nameMatrix("");
        String text = "";
        for (int i = 0; i < matrix.length; i ++) {
            text = text + "  " + "X" + String.valueOf(i);
        }
        nameMatrix(text);
    }

    public double[][] gausseanElimination(double[][] a, int piv) {
        int n = a.length;
        for (int k = 0; k < n - 1; k++) {
            if(piv == 1){
                a = partialPivot(a,k);
            }
            if(piv == 2) {
                a = totalPivot(a,k);
            }
            for (int i = k + 1; i < n; i++) {
                double mult = a[i][k] / a[k][k];
                for (int j = k; j < n + 1; j++) {
                    a[i][j] = a[i][j] - mult * a[k][j];
                }
            }
            if(step) {
                nameMatrix("Iteration: " + Integer.toString(k));
                print(a, false);
            }
        }
        return a;
    }

    public double[][] partialPivot(double[][] a, int k) {
        double biggest  = Math.abs(a[k][k]);
        int biggestRow = k;
        int n = a.length;
        for (int i = k + 1; i < n; i++) {
            if(Math.abs(a[i][k]) > biggest) {
                biggest = a[i][k];
                biggestRow = i;
            }
        }
        if(biggest == 0) System.out.println("The system doesn't have a unique solution");
        else if (biggestRow != k) {
            swapRows(a,biggestRow,k);
        }
        return a;
    }

    public double[][] totalPivot(double[][] a, int k) {
        double biggest = 0;
        int rowBiggest = k;
        int columnBiggest = k;
        int n = a.length;
        marks = new int[n];

        for (int i = 1; i <= n; i++){
            marks[i-1] = i;
        }
        for (int i = k; i < n; i++) {
            for (int j = k; j < n; j++) {
                if(Math.abs(a[i][j]) > biggest) {
                    biggest = Math.abs(a[i][j]);
                    rowBiggest = i;
                    columnBiggest = j;
                }
            }
        }
        if(biggest == 0) System.out.println("The system doesn't have a unique solution");
        else {
            if(rowBiggest != k) a = swapRows(a,rowBiggest,k);
            if(columnBiggest != k){
                a = swapColumns(a,columnBiggest,k);
                int temp = marks[columnBiggest];
                marks[columnBiggest] = marks[k];
                marks[k] = temp;
            }
        }
        return a;
    }

    public double[][] swapRows(double array[][], int rowA, int rowB) {
        double tmpRow[] = array[rowA];
        array[rowA] = array[rowB];
        array[rowB] = tmpRow;
        return array;
    }

    public double[][] swapColumns(double array[][], int colA, int colB) {
        for (int i = 0; i < array.length; i++) {
            double temp = array[i][(colA)];
            array[i][colA] = array[i][(colB)];
            array[i][colB] = temp;
        }
        return array;
    }

    public double[] regresiveSustitution(double[][] ab) {
        int n = ab.length;
        double[] x = new double[n];
        x[n - 1] = ab[n - 1][n] / ab[n - 1][n - 1];
        for (int i = n - 1; i >= 0; i--) {
            double cont = 0;
            for (int j = i + 1; j < n; j++) {
                cont += (ab[i][j] * x[j]);
            }
            x[i] = (ab[i][n] - cont) / ab[i][i];
        }
        return x;
    }

    public double[] croutCholeskyDoolittle(double[][] matCoef, double[] solCoef, int method) throws Exception {

        int n = matCoef.length;
        double contK;
        double contJ;
        double contI;
        double cont;

        l = new double[n][n];
        u = new double[n][n];
        double[] res = new double[n];
        double[] aux = new double[n];

        for(int k = 0; k < n; k++){
            contK = 0;
            for(int p = 0; p < k;p++){
                contK += (l[k][p] * u[p][k]);
            }
            switch (method){
                case 1:
                    l[k][k] = Math.sqrt(matCoef[k][k] - contK);
                    u[k][k] = l[k][k];
                    break;
                case 2:
                    l[k][k] = matCoef[k][k] - contK;
                    u[k][k] = 1;
                    break;
                case 3:
                    l[k][k] = 1;
                    u[k][k] = matCoef[k][k] - contK;
                    break;
                default:
                    break;
            }

            for(int i = k+1; i < n;i++){
                contI = 0;
                for(int p = 0; p <= k;p++){
                    contI += (l[i][p] * u[p][k]);
                }
                if(l[k][k] != 0){
                    l[i][k] = (matCoef[i][k] - contI)/u[k][k];
                }else{
                    throw new Exception("System has no solution");
                }
            }

            for(int j = k+1; j < n;j++){
                contJ = 0;
                for(int p = 0; p <= k;p++){
                    contJ += (l[k][p] * u[p][j]);
                }

                if(l[k][k] != 0) {
                    u[k][j] = (matCoef[k][j] - contJ) / l[k][k];
                }else{
                    throw new Exception("System has no solution");
                }
            }
            if(step) {
                nameMatrix("L Iteration: " + Integer.toString(k));
                print(l, true);
                nameMatrix("U Iteration: " + Integer.toString(k));
                print(u, true);
            }
        }

        for(int i = 0;i < n; i++){
            cont = 0;
            for(int j = 0; j < i; j++){
                cont += l[i][j] * aux[j];
            }

            aux[i] = (solCoef[i]-cont)/l[i][i];
        }
        for(int i = n-1;i >= 0; i--){
            cont = 0;
            for(int j = n-1; j > i; j--){
                cont += u[i][j] * res[j];
            }
            res[i] = (aux[i]-cont)/u[i][i];
        }
        return res;
    }

    public double[][] SeidelJacobi(double[] x0,double tol, int iter, double[][] matA, double[] vecB, double rel, boolean jac) throws Exception {

        int cont = 0;
        double disp = tol +1;
        double[] x1;
        double[] aux;

        if(iter > 0){
            if(tol > 0){
                while(disp > tol && cont < iter){
                    if(jac){
                        x1 = jacobiXi(x0, matA, vecB);
                    }else{
                        x1 = seidelXi(x0, matA, vecB);
                    }
                    aux = vecSubstraction(x1,x0);
                    disp = normCalculation(aux)/ normCalculation(x1);
                    if(step){
                        nameMatrix("Iteration: " + cont);
                        printArray(x1);
                    }
                    for(int i = 0; i < x0.length; i++){
                        x1[i] = (rel*x1[i])+((1-rel)*x0[1]);
                    }
                    x0 = x1;
                    cont++;
                }
                if(disp < tol){

                    double[][] res = {x0,{disp}};
                    return res;

                }else{
                    throw new Exception("Surpassed the number of iterations");
                }
            }else{
                throw new Exception("Tolerance is less than 0");
            }
        }else{
            throw new Exception("Iterations less or equals to 0");
        }
    }

    public double[] jacobiXi(double[] x0, double[][] matA, double[] vecB) throws Exception {
        int n = x0.length;
        double[] res = new double[n];
        double cont;

        for(int i = 0; i < n; i++){
            cont = 0;
            for(int j = 0; j < n; j++){
                if(i != j){
                    cont += matA[i][j]*x0[j];
                }
            }
            if(matA[i][i] != 0){
                res[i] = (vecB[i] - cont)/matA[i][i];
            }else{
                throw new Exception("The system probably has no solution");
            }
        }
        return res;
    }

    public double[] seidelXi(double[] x0, double[][] matA, double[] vecB) throws Exception {

        int n = x0.length;
        double[] res = new double[n];
        double cont;

        for(int i = 0; i < n; i++){
            res[i] = x0[i];
        }
        for(int i = 0; i < n; i++){
            cont = 0;
            for(int j = 0; j < n; j++){
                if(i != j){
                    cont += matA[i][j]*res[j];
                }
            }
            if(matA[i][i] != 0){
                res[i] = (vecB[i] - cont)/matA[i][i];
            }else{
                throw new Exception("The system probably has no solution");
            }
        }
        return res;
    }

    public double normCalculation(double[] x0) {
        double cont = 0;
        for(int i = 0; i < x0.length;i++){
            cont += Math.abs(x0[i]) * Math.abs(x0[i]);
        }
        return Math.sqrt(cont);
    }

    public double[] vecSubstraction(double[] x0, double[] x1) {
        int n = x0.length;
        double[] res = new double[n];
        for(int i = 0; i < n; i++){
            res[i] = x0[i] - x1[i];
        }
        return res;
    }



    public void print(double[][] a, boolean isSquared) {
        TableLayout table = (TableLayout) findViewById(R.id.result_table);
        for (int i = 0; i < a.length; i++) {
            TableRow ttr = new TableRow(this);
            TableRow.LayoutParams tlp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            DecimalFormat df = new DecimalFormat( "#########0.00" );
            ttr.setLayoutParams(tlp);
            ttr.setBackgroundColor(Color.parseColor("#CFD8DC"));
            for (int j = 0; j < a.length; j++) {
                TextView n = new TextView(this);
                n.setBackgroundResource(R.drawable.table_border_title);
                n.setText(String.valueOf(df.format(a[i][j])));
                n.setHeight(50);
                n.setWidth(120);
                ttr.addView(n);
            }
            if(!isSquared) {
                TextView n = new TextView(this);
                n.setBackgroundResource(R.drawable.edit_border);
                n.setText(String.valueOf(df.format(a[i][size])));
                n.setHeight(50);
                n.setWidth(120);
                ttr.addView(n);
            }
            table.addView(ttr);
        }
    }

    public void printArray(double[] a){
        TableLayout table = (TableLayout) findViewById(R.id.result_table);
        TableRow ttr = new TableRow(this);
        TableRow.LayoutParams tlp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        DecimalFormat df = new DecimalFormat( "#########0.00" );
        ttr.setLayoutParams(tlp);
        for(int i = 0; i < a.length; i++){
            TextView n = new TextView(this);
            n.setText(String.valueOf(df.format(a[i])));
            n.setHeight(50);
            n.setWidth(120);
            n.setBackgroundColor(Color.parseColor("#CFD8DC"));
            ttr.addView(n);
        }
        table.addView(ttr);
    }
}