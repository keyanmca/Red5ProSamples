package com.flashvisions.clients.android.red5pro.sample;

import com.flashvisions.clients.android.red5broadcaster.R;
import com.red5pro.streaming.R5Connection;
import com.red5pro.streaming.R5Stream;
import com.red5pro.streaming.R5StreamProtocol;
import com.red5pro.streaming.config.R5Configuration;
import com.red5pro.streaming.source.R5Camera;
import com.red5pro.streaming.source.R5Microphone;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class PublisherFragment extends Fragment implements OnClickListener, SurfaceHolder.Callback 
{
	View root;
	Camera camera;
	R5Stream stream;
	boolean isPublishing = false;
	FragmentHostListener listener;
	LinearLayout layoutDownload;
	public R5Configuration configuration;
	
	public static PublisherFragment newInstance() {
		PublisherFragment fragment = new PublisherFragment();
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
	  if(isPublishing) {
	    onPublishToggle();
	  }
	}
	
	@Override
	public void onResume() 
	{
		super.onResume();
		preview();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		configuration = new R5Configuration(R5StreamProtocol.RTSP, "192.168.1.102",  8554, "live", "live", 1.0f);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.publish_layout, container, false);
		return root;
	}
	
	private void preview() {
		  camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
		  SurfaceView surface = (SurfaceView) getActivity().findViewById(R.id.surfaceView);
		  surface.getHolder().addCallback(this);
		}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		Button publishButton = (Button) getActivity().findViewById(R.id.publishButton);
		  publishButton.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View view) {
		      onPublishToggle();
		    }
		  });
	}
	
	private void onPublishToggle() 
	{
		  Button publishButton = (Button) getActivity().findViewById(R.id.publishButton);
		  if(isPublishing) {
		    stop();
		  }
		  else {
		    start();
		  }
		  isPublishing = !isPublishing;
		  publishButton.setText(isPublishing ? "stop" : "start");
	}
	
	public void start() 
	{
		  camera.stopPreview();

		  stream = new R5Stream(new R5Connection(configuration));
		  stream.setView((SurfaceView) getActivity().findViewById(R.id.surfaceView));

		  R5Camera r5Camera = new R5Camera(camera, 320, 240);
		  R5Microphone r5Microphone = new R5Microphone();

		  stream.attachCamera(r5Camera);
		  stream.attachMic(r5Microphone);
		  stream.publish("red5prostream");
	}
	
	public void stop() 
	{
		 if(stream != null) {
		    stream.stop();
		    camera.startPreview();
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
        listener.setPublisherFragmentTag(getTag());
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentHostListener");
        }
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try {
		      camera.setPreviewDisplay(holder);
		      camera.startPreview();
		    }
		    catch(Exception e) {
		      e.printStackTrace();
		    }
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
}
