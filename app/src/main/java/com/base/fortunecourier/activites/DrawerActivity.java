package com.base.fortunecourier.activites;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.base.fortunecourier.R;
import com.base.fortunecourier.fragments.AccountFragment;
import com.base.fortunecourier.fragments.HelpFragment;
import com.base.fortunecourier.fragments.PaymentInfoFragment;
import com.base.fortunecourier.fragments.RateCalculatorFragment;
import com.base.fortunecourier.fragments.ShipmentFragment;
import com.base.fortunecourier.fragments.ToolsFragment;

public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static Toolbar toolbar;
    DrawerLayout drawer;
    private boolean mToolBarNavigationListenerIsRegistered = false;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleMarginStart(10);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle("Account");
        Fragment fragment = new AccountFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, fragment);
        fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction fragmentTransaction;
        FragmentManager fragmentManager;
        Fragment fragment;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            toolbar.setTitle("Account");
            fragment = new AccountFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainContainer, fragment);
            fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_shipment) {
            enableViews(true);
            toolbar.setTitle("Shipment");
            fragment = new ShipmentFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainContainer, fragment);
            fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_rate_calculator) {
            enableViews(true);
            toolbar.setTitle("Rate Calculator");
            fragment = new RateCalculatorFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainContainer, fragment);
            fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_tools) {
            enableViews(true);
            toolbar.setTitle("Tools");
            fragment = new ToolsFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainContainer, fragment);
            fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_payment) {
            enableViews(true);
            toolbar.setTitle("Payment Info");
            fragment = new PaymentInfoFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainContainer, fragment);
            fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_help) {
            enableViews(true);
            toolbar.setTitle("Help");
            fragment = new HelpFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainContainer, fragment);
            fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_logout) {
            ShowDialog();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ShowDialog() {
        final Dialog dialog = new Dialog(DrawerActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logout);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button btn_submit = (Button) dialog.findViewById(R.id.btn_submit);
        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(DrawerActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void enableViews(boolean enable) {

        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if (enable) {
            // Remove hamburger
            toggle.setDrawerIndicatorEnabled(false);
            // Show back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if (!mToolBarNavigationListenerIsRegistered) {
                toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't have to be onBackPressed
                        //   onBackPressed();
                        enableViews(false);
                        if (getSupportFragmentManager().findFragmentByTag("FragmentC") != null)
                        {
                            // I'm viewing Fragment C
                            getSupportFragmentManager().popBackStack("A_B_TAG", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            toolbar.setTitle("A_B_TAG");
                        }
                        else
                            {
                            toolbar.setTitle("Account");
                            Fragment fragment = new AccountFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.mainContainer, fragment);
                            fragmentManager.beginTransaction().replace(R.id.mainContainer, fragment);
                            fragmentTransaction.commit();
                        }
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            toggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            toggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }

        // So, one may think "Hmm why not simplify to:
        // .....
        // getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        // mDrawer.setDrawerIndicatorEnabled(!enable);
        // ......
        // To re-iterate, the order in which you enable and disable views IS important #dontSimplify.
    }
}
