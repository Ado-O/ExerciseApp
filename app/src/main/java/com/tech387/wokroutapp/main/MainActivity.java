package com.tech387.wokroutapp.main;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.tech387.wokroutapp.Injection;
import com.tech387.wokroutapp.R;
import com.tech387.wokroutapp.data.Exercise;
import com.tech387.wokroutapp.data.storage.ContentRepository;
import com.tech387.wokroutapp.data.storage.ExerciseRepository;
import com.tech387.wokroutapp.main.exercises.ExerciseFragment;
import com.tech387.wokroutapp.main.other.FiveFragment;
import com.tech387.wokroutapp.main.other.FourFragment;
import com.tech387.wokroutapp.main.other.ThreeFragment;
import com.tech387.wokroutapp.main.wokrouts.WorkoutFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_atv);

        //find view
        mToolbar = findViewById(R.id.tb_main);
        mViewPager = findViewById(R.id.vp_main);
        mTabLayout = (TabLayout) findViewById(R.id.tl_main);


        setupToolbar();
        setupTablistener();
        setupPager();
    }


     /**
     * toolbar fix text Exercises
     */
    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Exercises");

    }

    /**
     * setting overflow menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * setupTablistener add icone and color white
     */
    public void setupTablistener() {
        //add drawable icon in tablayout
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_human));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_round_timer));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_shopping_cart));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_round_today));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_round_show_chart));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //add color to icon
        for(int i = 0; i < 5; i++){
            mTabLayout.getTabAt(i).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        }
//
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //add listener and forse to be onClick
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //onTabSelected and onTabUnselected are called every time there is a change in tabs.
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //onTabReselected is called whenever a tab is clicked again while it is already showing.
            }
        });
    }

    /**
     * Setting up the listView & its adapter
     */
    private void setupPager() {

        //create arrayList and edit fragment
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(ExerciseFragment.newInstance());
        arrayList.add(WorkoutFragment.newInstance());
        arrayList.add(ThreeFragment.newInstance());
        arrayList.add(FourFragment.newInstance());
        arrayList.add(FiveFragment.newInstance());

        mAdapter = new MainAdapter(getSupportFragmentManager(), arrayList);
        mViewPager.setAdapter(mAdapter);

         }

    }


