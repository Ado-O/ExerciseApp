package com.tech387.wokroutapp.main;

import android.arch.lifecycle.Observer;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.tech387.wokroutapp.Injection;
import com.tech387.wokroutapp.R;
import com.tech387.wokroutapp.ViewModelFactory;
import com.tech387.wokroutapp.data.storage.local.exercise.Exercise;
import com.tech387.wokroutapp.main.exercises.ExerciseFragment;
import com.tech387.wokroutapp.main.exercises.ExerciseViewModel;
import com.tech387.wokroutapp.main.other.FiveFragment;
import com.tech387.wokroutapp.main.other.FourFragment;
import com.tech387.wokroutapp.main.shoppackage.ShopPackageFragment;
import com.tech387.wokroutapp.main.video.VideoActivity;
import com.tech387.wokroutapp.main.wokrouts.WorkoutFragment;
import com.tech387.wokroutapp.main.wokrouts.WorkoutViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainAdapter mAdapter;
    private ExerciseViewModel mExerciseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_atv);

        //add content
        Injection.provideContentRepository(this).getContent();

        //find view
        mToolbar = findViewById(R.id.tb_main);
        mViewPager = findViewById(R.id.vp_main);
        mTabLayout = (TabLayout) findViewById(R.id.tl_main);

        //add view model
        mExerciseViewModel = ViewModelFactory.obtainViewModel(this, ExerciseViewModel.class);

        setupToolbar();
        setupTablistener();
        setupPager();
        setupEvents();
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
        for (int i = 0; i < 5; i++) {
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
        arrayList.add(ShopPackageFragment.newInstance());
        arrayList.add(FourFragment.newInstance());
        arrayList.add(FiveFragment.newInstance());

        mAdapter = new MainAdapter(getSupportFragmentManager(), arrayList);
        mViewPager.setAdapter(mAdapter);

    }

    /**
     * send clickEvent is second activity
     */
    private void setupEvents() {

        //listener
        mExerciseViewModel.getOpenExerciseEvente().observe(this, new Observer<Exercise>() {
            @Override
            public void onChanged(@Nullable Exercise exercise) {
                VideoActivity.startActivity(MainActivity.this, exercise);
            }
        });

    }

}


