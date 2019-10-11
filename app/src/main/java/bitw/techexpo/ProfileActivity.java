package bitw.techexpo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import java.util.Objects;


public class ProfileActivity extends AppCompatActivity {

    TextView name,branch,sem,batch,usn,mail,pno;
    ImageView dp;
    private DrawerLayout mDrawerLayout;

    public void logOut(View view){
        Intent i = new Intent(this,MainActivity.class);
        finish();
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final objclass StudentData = (objclass) getIntent().getSerializableExtra("USER_DATA");
        Toast.makeText(ProfileActivity.this, "Logged In", Toast.LENGTH_SHORT).show();

        //findViewByIDs
        name = findViewById(R.id.student_name);
        branch = findViewById(R.id.branch_name);
        sem = findViewById(R.id.sem_name);
        batch = findViewById(R.id.batch_name);
        usn = findViewById(R.id.usn_name);
        mail = findViewById(R.id.email_name);
        pno = findViewById(R.id.phone_name);
        dp = findViewById(R.id.profile_image);
        //setTexts
        name.setText(StudentData.Name);
        branch.setText(StudentData.Branch);
        sem.setText(StudentData.Sem);
        batch.setText(StudentData.Batch);
        usn.setText("USN : " + StudentData.USN);
        mail.setText(StudentData.Email);
        pno.setText(StudentData.Pno);

        //ProfileImage
        if (StudentData.Gender.equals("M")) {
            dp.setImageResource(R.drawable.logo);
        } else {
            dp.setImageResource(R.drawable.logo);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);

        android.support.design.widget.NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
                                intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                                intent.putExtra("USER_DATA",StudentData);
                                startActivity(intent);
                                break;
                            case R.id.perf :
                                intent = new Intent(ProfileActivity.this, PerformanceActivity.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                            case R.id.study :
                                intent = new Intent(ProfileActivity.this, activity_studysit.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                            case R.id.planner :
                                intent = new Intent(ProfileActivity.this, activity_planner.class);
                                intent.putExtra("USER_DATA", StudentData);
                                startActivity(intent);
                                break;
                            case R.id.aboutus :
                                intent = new Intent(ProfileActivity.this, activity_aboutus.class);
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
