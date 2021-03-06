package com.smag.androidlearning;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smag.androidlearning.helper.RecycleAdapterAccount;
import com.smag.androidlearning.helper.RecycleAdapterHome;
import com.smag.androidlearning.beans.Theme;
import com.smag.androidlearning.helper.RecycleAdapterSettings;
import com.smag.androidlearning.service.ServiceNotification;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import devlight.io.library.ntb.NavigationTabBar;

public class AppViewContainer extends AppCompatActivity {

    private int [] imagesCours =new int[] {R.drawable.theme1 , R.drawable.theme1x ,R.drawable.theme3 , R.drawable.theme4,R.drawable.theme5, R.drawable.locked , R.drawable.best};
    private List<Theme> themes;

    @Override
    public void onBackPressed() {
        //Alerter si il veut quitter l'application
        startService(new Intent(this,ServiceNotification.class));
        alertFunc("Fermeture","Voulez-vous vraiment quitter l'application?");

        //super.onBackPressed();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themes = (List<Theme>) getIntent().getSerializableExtra("listThemesTrans");
        setContentView(R.layout.activity_app_view_container);
        initUI();
    }

    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view ;
                if(position==0){
                    view = LayoutInflater.from(
                            getBaseContext()).inflate(R.layout.fragment_account, null, false);

                    final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager manager =new LinearLayoutManager(
                            getBaseContext(), LinearLayoutManager.VERTICAL, false
                    );
                    recyclerView.setLayoutManager(manager
                    );
                    recyclerView.setAdapter(new RecycleAdapterAccount(getApplicationContext()));
                    container.addView(view);

                }else if(position==1){
                    themes.add(themes.get(1));
                    view= LayoutInflater.from(getBaseContext()).inflate(R.layout.fragment_home, null, false);
                    final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
                    recyclerView.setHasFixedSize(true);
                    GridLayoutManager gridLayoutManager =new GridLayoutManager(getBaseContext(), 2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(new RecycleAdapterHome(getApplicationContext() , imagesCours,themes));
                    container.addView(view);

                }else{
                    view = LayoutInflater.from(
                            getBaseContext()).inflate(R.layout.fragment_settings, null, false);

                    final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(
                                    getBaseContext(), LinearLayoutManager.VERTICAL, false
                            )
                    );
                    recyclerView.setAdapter(new RecycleAdapterSettings(getApplicationContext()));
                    container.addView(view);
                }
                return view;
            }
        });

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_account_box_black_24dp),
                        Color.parseColor(colors[1]))
                        .title("User")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_home_black_24dp),
                        Color.parseColor(colors[1]))
                        .title("Home")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_settings_black_24dp),
                        Color.parseColor(colors[1]))
                        .title("Settings")
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 1);
        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        navigationTabBar.setBehaviorEnabled(true);

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {

            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
                model.hideBadge();
            }
        });
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {

            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

       /* final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.parent);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
                    navigationTabBar.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            final String title = String.valueOf(new Random().nextInt(15));
                            if (!model.isBadgeShowed()) {
                                model.setBadgeTitle(title);
                                model.showBadge();
                            } else model.updateBadgeTitle(title);
                        }
                    }, i * 100);
                }

                coordinatorLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Snackbar snackbar = Snackbar.make(navigationTabBar, "Notification", Snackbar.LENGTH_SHORT);
                        snackbar.getView().setBackgroundColor(Color.parseColor("#38D0FD"));
                        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text))
                                .setTextColor(Color.parseColor(colors[1]));
                        snackbar.show();
                    }
                }, 1000);
            }
        });*/

       /* final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#38D0FD"));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
        */
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this,ServiceNotification.class));
    }

    public void alertFunc(String title, String message){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(AppViewContainer.this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
            .setNegativeButton("Non", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle(title);
        alert.setIcon(R.drawable.ic_launcher_background);
        alert.show();
    }
}
