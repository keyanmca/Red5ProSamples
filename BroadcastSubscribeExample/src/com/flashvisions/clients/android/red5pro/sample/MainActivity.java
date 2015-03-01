package com.flashvisions.clients.android.red5pro.sample;


import com.flashvisions.clients.android.red5broadcaster.R;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements TabListener, FragmentHostListener {

	ViewPager mPager;
	ViewPagerAdapter mPagerAdapter;
	int selectedPosition = 0;
	
	Tab publisherTab;
	Tab subscriberTab;
	ActionBar mActionBar;
	
	String publisherFragmentTag;
	String subscriberFragmentTag;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mActionBar = getSupportActionBar();
		mActionBar.setHomeButtonEnabled(false);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mPager = (ViewPager) findViewById(R.id.pager);
        
        if(savedInstanceState == null)
        {
        	initialize(savedInstanceState);
        } 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    private void initialize(Bundle savedInstanceState)
	{  
		if(savedInstanceState != null){
		selectedPosition = savedInstanceState.getInt("selectedPosition");
		}
		
		publisherTab = getSupportActionBar().newTab().setText(getText(R.string.publish_title));
		publisherTab.setTabListener(this);
		getSupportActionBar().addTab(publisherTab);
		
		subscriberTab = getSupportActionBar().newTab().setText(getText(R.string.subscribe_title));
		subscriberTab.setTabListener(this);
		getSupportActionBar().addTab(subscriberTab);
		
		mPagerAdapter  = new ViewPagerAdapter(getSupportFragmentManager());
		//mPagerAdapter.setHistoryItems(items);
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(selectedPosition);
	    mPager.setOnPageChangeListener(
        new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            getSupportActionBar().setSelectedNavigationItem(position);            
            }            
        });
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		mPager.setCurrentItem(tab.getPosition());
	}


	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setPublisherFragmentTag(String tag) {
		// TODO Auto-generated method stub
		this.publisherFragmentTag = tag;
	}


	@Override
	public void setSubscriberFragmentTag(String tag) {
		// TODO Auto-generated method stub
		this.subscriberFragmentTag = tag;
	}
	
	
}
