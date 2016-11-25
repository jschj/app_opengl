package com.example.joern.blockengine;

import android.opengl.*;
import android.app.Activity;
import android.os.Bundle;
import glWrapper.*;
import gameShared.*;



public class MainActivity extends Activity
{
    private GLSurfaceView oglSurfaceView;
    private OGLScene oglScene;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        oglSurfaceView = new OGLSurfaceView(this);

        setContentView(oglSurfaceView);

        new Thread(new Runnable()
        {
            public void run()
            {
                while (true)
                {
                    oglSurfaceView.requestRender();
                }
            }
        }).start();

        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        //setContentView(R.layout.activity_main);
    }


    @Override
    protected void onPause() {
        super.onPause();
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        oglSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        oglSurfaceView.onResume();
    }
}
