package bitw.techexpo;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class activity_studysit extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    public void firstYear(View view){
        String url = "https://drive.google.com/folderview?id=1rVvGWK0n7nmuj7sCPA5MnXq0x5zSrzAW";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
    public void secondYear(View view){
        String url = "https://drive.google.com/folderview?id=1bXQHF3iRJLaSOMvesYWsFqBepTur8q55";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
    public void thirdYear(View view){
        String url = "https://drive.google.com/folderview?id=1z3rEj75vb3TguwaPUO4OhYLBlfkB3-AU";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
    public void fourthYear(View view){
        String url = "https://drive.google.com/folderview?id=1MN6ZxCQj-S-73jTBxu4I7GWbXUUd9uSf";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studysit);
        final objclass StudentData = (objclass) getIntent().getSerializableExtra("USER_DATA");
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
                                intent = new Intent(activity_studysit.this, ProfileActivity.class);
                                intent.putExtra("USER_DATA",StudentData);
                                startActivity(intent);
                                break;
                            case R.id.perf :
                                intent = new Intent(activity_studysit.this, PerformanceActivity.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                            case R.id.study :
                                intent = new Intent(activity_studysit.this, activity_studysit.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                            case R.id.planner :
                                intent = new Intent(activity_studysit.this, activity_planner.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                            case R.id.aboutus :
                                intent = new Intent(activity_studysit.this, activity_aboutus.class);
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
}
