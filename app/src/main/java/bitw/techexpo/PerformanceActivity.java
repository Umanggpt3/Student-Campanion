package bitw.techexpo;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PerformanceActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private static final String TAG = "MainActivity";
    private  LineChart mchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final objclass StudentData = (objclass) getIntent().getSerializableExtra("USER_DATA");
        String[] arraySpinner = new String[]{
                "MAT", "DMS", "CO", "DS", "LD"
        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        android.support.design.widget.NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        int i = menuItem.getItemId();
                        Intent intent;
                        switch (i) {
                            case R.id.profile :
                                intent = new Intent(PerformanceActivity.this, ProfileActivity.class);
                                intent.putExtra("USER_DATA",StudentData);
                                startActivity(intent);
                                break;
                            case R.id.perf :
                                intent = new Intent(PerformanceActivity.this, PerformanceActivity.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                            case R.id.study :
                                intent = new Intent(PerformanceActivity.this, activity_studysit.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                            case R.id.planner :
                                intent = new Intent(PerformanceActivity.this, activity_planner.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                            case R.id.aboutus :
                                intent = new Intent(PerformanceActivity.this, activity_aboutus.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                        }

                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                });
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Profile");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //PieChart Function Calls

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) spinner.getSelectedItem();
                if (selected.compareTo("MAT") == 0)
                    setupPieChart(1,StudentData);
                else if (selected.compareTo("DMS") == 0)
                    setupPieChart(2,StudentData);
                else if (selected.compareTo("CO") == 0)
                    setupPieChart(3,StudentData);
                else if (selected.compareTo("DS") == 0)
                    setupPieChart(4,StudentData);
                else if (selected.compareTo("LD") == 0)
                    setupPieChart(5,StudentData);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //LINE CHART

        mchart = findViewById(R.id.lineChart);
        mchart.setDragEnabled(true);
        mchart.setScaleEnabled(false);

        LimitLine upper_limit = new LimitLine(85f,"Attendance Shortage");
        upper_limit.setLineWidth(3f);
        upper_limit.enableDashedLine(10f,10f,0);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setLineColor(Color.RED);

        YAxis leftAxis = mchart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(50f);
        leftAxis.enableGridDashedLine(10f,10f,0f);
        leftAxis.setDrawLimitLinesBehindData(true);

        Description description = new Description();
        description.setText("");
        mchart.setDescription(description);

        mchart.getAxisRight().setEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0,0f));
        yValues.add(new Entry(1,70f));
        yValues.add(new Entry(2,89f));
        yValues.add(new Entry(3,79f));
        yValues.add(new Entry(4,68f));
        yValues.add(new Entry(5,95f));

        LineDataSet set1 = new LineDataSet(yValues,"Attendance");


        set1.setFillAlpha(110);
        set1.setColor(Color.BLUE);
        set1.setLineWidth(3f);
        set1.setValueTextSize(15f);
        set1.setValueTextColor(Color.DKGRAY);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        mchart.setData(data);

        String[] values = new  String[]{"0","10","20","30","40","50","60"};
        XAxis xAxis = mchart.getXAxis();
        xAxis.setValueFormatter(new MyAxisValueFormatter(values));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    }

    public  class MyAxisValueFormatter implements IAxisValueFormatter{
        private String[] mValues;
        public MyAxisValueFormatter(String[] values ){
            this.mValues = values;
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupPieChart(int k, objclass StudentData) {
        //populating a list of pie entry
        PieChart chart = findViewById(R.id.chart);
        List<PieEntry> pieEntries = new ArrayList<>();
        if (k == 1) {

            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate();
            int lost = 50 - (StudentData.MATquiz + StudentData.MATass + StudentData.MATint1 + StudentData.MATint2);
            int credits[] = {StudentData.MATquiz, StudentData.MATass, StudentData.MATint1, StudentData.MATint2, lost};
            String subjectName[] = { "Quiz", "Assignment", "Test 1", "Test 2", "Lost Credits"};
            for (int i = 0; i < credits.length; i++) {
                pieEntries.add(new PieEntry(credits[i], subjectName[i]));
            }
        }
        if (k == 2) {

            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate();
            int lost = 50 - (StudentData.DMSquiz + StudentData.DMSass + StudentData.DMSint1 + StudentData.DMSint2);
            int credits[] = {StudentData.DMSquiz, StudentData.DMSass, StudentData.DMSint1, StudentData.DMSint2, lost};
            String subjectName[] = { "Quiz", "Assignment", "Test 1", "Test 2", "Lost Credits"};
            for (int i = 0; i < credits.length; i++) {
                pieEntries.add(new PieEntry(credits[i], subjectName[i]));
            }
        }
        if (k == 3) {

            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate();
            int lost = 50 - (StudentData.COquiz + StudentData.COass + StudentData.COint1 + StudentData.COint2);
            int credits[] = {StudentData.COquiz, StudentData.COass, StudentData.COint1, StudentData.COint2, lost};
            String subjectName[] = { "Quiz", "Assignment", "Test 1", "Test 2", "Lost Credits"};
            for (int i = 0; i < credits.length; i++) {
                pieEntries.add(new PieEntry(credits[i], subjectName[i]));
            }
        }
        if (k == 4) {

            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate();
            int lost = 50 - (StudentData.DSquiz + StudentData.DSass + StudentData.DSint1 + StudentData.DSint2);
            int credits[] = {StudentData.DSquiz, StudentData.DSass, StudentData.DSint1, StudentData.DSint2, lost};
            String subjectName[] = { "Quiz", "Assignment", "Test 1", "Test 2", "Lost Credits"};
            for (int i = 0; i < credits.length; i++) {
                pieEntries.add(new PieEntry(credits[i], subjectName[i]));
            }
        }
        if (k == 5) {

            chart.notifyDataSetChanged(); // let the chart know it's data changed
            chart.invalidate();
            int lost = 50 - (StudentData.LDquiz + StudentData.LDass + StudentData.LDint1 + StudentData.LDint2);
            int credits[] = {StudentData.LDquiz, StudentData.LDass, StudentData.LDint1, StudentData.LDint2, lost};
            String subjectName[] = { "Quiz", "Assignment", "Test 1", "Test 2", "Lost Credits"};
            for (int i = 0; i < credits.length; i++) {
                pieEntries.add(new PieEntry(credits[i], subjectName[i]));
            }
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "CIE");
        dataSet.setValueTextSize(15f);
        dataSet.setColors(ColorTemplate.rgb("#ff5722" ),ColorTemplate.rgb("#ffc107" ),ColorTemplate.rgb("#43a047"),ColorTemplate.rgb("#1976d2")
                ,ColorTemplate.rgb("#e53935"));

        PieData data = new PieData(dataSet);

        //formatting
        dataSet.setValueTextColor(Color.DKGRAY);
        dataSet.setValueTextSize(15f);
        //Get the Chart

        // PieChart chart = findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
        Description description = new Description();
        description.setText("");
        chart.setDescription(description);

        Legend legend = chart.getLegend();
        legend.setTextSize(12f);
        legend.setXEntrySpace(15f);
        legend.setYEntrySpace(35f);
        // legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        legend.setFormSize(15f);
    }
}
