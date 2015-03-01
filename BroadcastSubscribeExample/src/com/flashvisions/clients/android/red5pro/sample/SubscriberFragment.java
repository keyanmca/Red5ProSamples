package com.flashvisions.clients.android.red5pro.sample;

import com.flashvisions.clients.android.red5broadcaster.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SubscriberFragment extends Fragment implements OnClickListener 
{
	View root;
	
	FragmentHostListener listener;
	LinearLayout layoutDownload;
	
	public static SubscriberFragment newInstance() {
		SubscriberFragment fragment = new SubscriberFragment();
		Bundle args = new Bundle();
	    fragment.setArguments(args);
		return fragment;
	}
	
	public void onDestroy()
	{
		super.onDestroy();
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
	}
	
	@Override
	public void onResume() 
	{
		super.onResume();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.subscriber_layout, container, false);
		return root;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
		//inflater.inflate(R.menu.action_grabber_menu, menu);
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) 
	{
		//savedInstanceState.putString("resourceURL", txtLinkBox.getText().toString());
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        try {
        listener = (FragmentHostListener) activity;
        listener.setSubscriberFragmentTag(getTag());
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentHostListener");
        }
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
