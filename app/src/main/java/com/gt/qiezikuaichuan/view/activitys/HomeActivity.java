package com.gt.qiezikuaichuan.view.activitys;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gt.qiezikuaichuan.R;
import com.gt.qiezikuaichuan.base.BaseActivity;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_send:
                intent(SelectFileActivity.class);
                break;
            case R.id.button_receive:

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        navigationView.setCheckedItem(item.getItemId());
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        initToolBar();
        navigationView.setNavigationItemSelectedListener(this);
        buttonSend.setOnClickListener(this);
        buttonReceive.setOnClickListener(this);

    }

    private void initToolBar() {
        toolbar.setTitle("茄子快传");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @BindView(R.id.nav)
    NavigationView navigationView;

    @BindView(R.id.drawber)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.button_send)
    Button buttonSend;

    @BindView(R.id.button_receive)
    Button buttonReceive;

}
