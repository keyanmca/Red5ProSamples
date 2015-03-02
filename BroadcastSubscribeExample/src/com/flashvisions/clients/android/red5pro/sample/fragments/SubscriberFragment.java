package com.flashvisions.clients.android.red5pro.sample.fragments;

import com.flashvisions.clients.android.red5pro.broadcastsubscribesample.R;
import com.flashvisions.clients.android.red5pro.sample.FragmentHostListener;
import com.red5pro.streaming.R5Connection;
import com.red5pro.streaming.R5Stream;
import com.red5pro.streaming.R5StreamProtocol;
import com.red5pro.streaming.config.R5Configuration;
import com.red5pro.streaming.view.R5VideoView;

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
import android.widget.Button;

public class SubscriberFragment extends Fragment implements OnClickListener 
{
	View root;
	R5Stream stream;
	boolean isSubscribing = false;
	FragmentHostListener listener;
	public R5Configuration configuration;
	
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
	public void onPause() {
	  super.onPause();
	  if(isSubscribing) {
	    onSubscribeToggle();
	  }
	}
	
	@Override
	public void onResume() 
	{
		super.onResume();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		configuration = new R5Configuration(R5StreamProtocol.RTSP, "192.168.1.102",  8554, "live", "live", 1.0f);
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
		
		Button publishButton = (Button) getActivity().findViewById(R.id.subscribeButton);
		  publishButton.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View view) {
		      onSubscribeToggle();
		    }
		  });
	}
	
	
	private void onSubscribeToggle() 
	{
		  Button subscribeButton = (Button) getActivity().findViewById(R.id.subscribeButton);
		  if(isSubscribing) {
		    stop();
		  }
		  else {
		    start();
		  }
		  isSubscribing = !isSubscribing;
		  subscribeButton.setText(isSubscribing ? "stop" : "start");
	}
	
	public void start() 
	{
		  R5VideoView videoView = (R5VideoView) getActivity().findViewById(R.id.subscribeView);

		  stream = new R5Stream(new R5Connection(configuration));
		  videoView.attachStream(stream);
		  stream.play("streamName");
	}

	
	public void stop() 
	{
	  if(stream != null) {
	    stream.stop();
	  }
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
