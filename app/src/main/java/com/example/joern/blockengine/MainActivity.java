package com.example.joern.blockengine;

import android.opengl.*;
import android.app.Activity;
import android.os.Bundle;

import glEngine2.EGeometry;
import glEngine2.ERenderInstance;
import glEngine2.ESurfaceView;
import glEngine2.*;



public class MainActivity extends Activity
{
    private GLSurfaceView surfaceView;
    private EGeometry geometry;
    private ERenderInstance instance;
    private EView view;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        surfaceView = new ESurfaceView(this);

        ((ESurfaceView)surfaceView).addShader("uniform mat4 uMVPMatrix;" +
                "attribute vec4 vPosition;" +
                "void main() {" +
                "  gl_Position = uMVPMatrix * vPosition;" +
                "}", GLES20.GL_VERTEX_SHADER);

        ((ESurfaceView)surfaceView).addShader("precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" +
                "}", GLES20.GL_FRAGMENT_SHADER);

        setContentView(surfaceView);

        float[][] triVerts1 =
                {
                        { 0.0f,  0.5f, 0.0f },
                        { -0.5f, -0.5f, 0.0f },
                        { 0.5f, -0.5f, 0.0f }
                };
        float[] triColor1 = { 1, 0, 0, 1 };

        float[][] triVerts2 =
                {
                        { 0.0f,  0.5f, 0.0f },
                        { 0.0f, -0.5f, -0.5f },
                        { 0.0f, -0.5f, 0.5f }
                };
        float[] triColor2 = { 1, 1, 0, 1 };

        geometry = new EGeometry(256, 256);
        geometry.addFace(triVerts1[0], triVerts1[1], triVerts1[2], 0, triColor1);
        geometry.addFace(triVerts2[0], triVerts2[1], triVerts2[2], 0, triColor2);

        view = new EView();
        view.setViewTarget(5, 5, -5, 0, 0, 0);

        instance = new ERenderInstance(geometry, view);

        ((ESurfaceView)surfaceView).linkRenderInstance(instance);

        new Thread(new Runnable()
        {
            public void run()
            {
                while (true)
                {
                    surfaceView.requestRender();
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
        surfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        surfaceView.onResume();
    }
}
