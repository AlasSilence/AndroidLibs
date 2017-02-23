package me.michaeljiang.bottomnavigationbardemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import me.michaeljiang.bottomnavigationbardemo.fragment.DefaultFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationBar bottomNavigationBar ;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private DefaultFragment firstFragment  = null;
    private DefaultFragment secondFragment = null;
    private DefaultFragment thirdFragment  = null;
    private DefaultFragment forthFragment  = null;

    private final String TAG = "BottomNavigationBarDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActivity();
        initfragment();
        initBottomNavigationBar();

    }

    /**
     * 初始化界面相关UI
     * 在本Demo中并不存在
     */
    private void initActivity(){

    }

    /**
     * 底部菜单栏
     */
    private void initBottomNavigationBar(){
        bottomNavigationBar = (BottomNavigationBar)findViewById(R.id.bottom_navigation_bar);
        BottomNavigationItem location = new BottomNavigationItem(R.drawable.ic_location_on_white_24dp, "Location");
        bottomNavigationBar.setAutoHideEnabled(true);

//        默认模式，只显示图标，点击后显示该图标的文字
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);

        //直接显示所有内容，然后将内容显示在屏幕上
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "Books"))
                .addItem(new BottomNavigationItem(R.drawable.ic_location_on_white_24dp, "Location"))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "Movies & TV"))
                .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, "Games"))
                .initialise();
//        badgeItem = new BadgeItem().setBackgroundColor(Color.RED).setText("99").setHideOnSelect(true);//角标

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
                fragmentTransaction = fragmentManager.beginTransaction();
                switch (position){
                    case 0:{
                        if (firstFragment == null) {
                            firstFragment = DefaultFragment.newInstance("Home");
                        }
                        fragmentTransaction.replace(R.id.tb, firstFragment);
                        fragmentTransaction.commit();
                        break;
                    }
                    case 1:{
                        if (secondFragment == null) {
                            secondFragment = DefaultFragment.newInstance("Books");
                        }
                        fragmentTransaction.replace(R.id.tb, secondFragment);
                        fragmentTransaction.commit();
                        break;
                    }
                    case 2:{
                        if (thirdFragment == null) {
                            thirdFragment = DefaultFragment.newInstance("Movies & TV");
                        }
                        fragmentTransaction.replace(R.id.tb, thirdFragment);
                        fragmentTransaction.commit();
                        break;
                    }
                    case 3:{
                        if (forthFragment == null) {
                            forthFragment = DefaultFragment.newInstance("Games");
                        }
                        fragmentTransaction.replace(R.id.tb, forthFragment);
                        fragmentTransaction.commit();
                        break;
                    }
                }

            }
            @Override
            public void onTabUnselected(int position) {
                Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");

            }
            @Override
            public void onTabReselected(int position) {
                Log.d(TAG, "onTabReselected() called with: " + "position = [" + position + "]");

            }
        });

    }

    /**
     * 初始化fragment管理
     */
    private void initfragment(){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        firstFragment = DefaultFragment.newInstance("Home");
        fragmentTransaction.add(R.id.tb, firstFragment);
        fragmentTransaction.commit();
    }

}
