package com.flashvisions.clients.android.red5pro.sample;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	static int TOTAL = 2;
	String resource = null;
	
	
	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
    public Fragment getItem(int position) {
		
		switch (position) {
        case 0:
        return PublisherFragment.newInstance();
        case 1:
        return SubscriberFragment.newInstance();
        }
		
		return null;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return TOTAL;
	}

}
