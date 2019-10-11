package bitw.techexpo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class activity_aboutus extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final objclass StudentData = (objclass) getIntent().getSerializableExtra("USER_DATA");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

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
                                intent = new Intent(activity_aboutus.this, ProfileActivity.class);
                                intent.putExtra("USER_DATA",StudentData);
                                startActivity(intent);
                                break;
                            case R.id.perf :
                                intent = new Intent(activity_aboutus.this, PerformanceActivity.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                            case R.id.study :
                                intent = new Intent(activity_aboutus.this, activity_studysit.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                            case R.id.planner :
                                intent = new Intent(activity_aboutus.this, activity_planner.class);
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
