package com.example.joern.blockengine;

import android.opengl.*;
import android.app.Activity;
import android.os.Bundle;

import EEngine.ESurfaceView;
import EEngine.*;



public class MainActivity extends Activity
{
    private GLSurfaceView surfaceView;
    private EGeometryDynamic geometry;
    private ERenderContext context;
    private EView view;
    private EShaderManager shaderManager;

    private static final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec3 v3Color;" +
            "attribute vec4 v4Position;" +
            "varying vec4 v4FragColor;" +
            "void main()" +
            "{" +
            "    gl_Position = uMVPMatrix * v4Position;" +
            "    v4FragColor = vec4(v3Color.x, v3Color.y, v3Color.z, 1.0);" +
            "}";

    private static final String fragmentShaderCode =
            "precision mediump float;" +
            "varying vec4 v4FragColor;" +
            "void main()" +
            "{" +
            "    gl_FragColor = v4FragColor;" +
            "}";

    private void addCube(float cx, float cy, float cz, float w, float r, float g, float b)
    {
        w /= 2;

        float[][] cv =
                {
                        { cx - w, cy - w, cz - w },     //front left bottom     0
                        { cx - w, cy + w, cz - w },     //front left top        1
                        { cx + w, cy - w, cz - w },     //front right bottom    2
                        { cx + w, cy + w, cz - w },     //front right top       3
                        { cx - w, cy - w, cz + w },     //back left bottom      4
                        { cx - w, cy + w, cz + w },     //back left top         5
                        { cx + w, cy - w, cz + w },     //back right bottom     6
                        { cx + w, cy + w, cz + w }      //back right top        7
                };

        float[] c = { r, g, b };

        //front
        geometry.addFace(cv[0], cv[1], cv[3], c, c, c);
        geometry.addFace(cv[1], cv[2], cv[3], c, c, c);

        //back
        geometry.addFace(cv[4], cv[5], cv[7], c, c, c);
        geometry.addFace(cv[4], cv[6], cv[7], c, c, c);

        //bottom
        geometry.addFace(cv[0], cv[4], cv[6], c, c, c);
        geometry.addFace(cv[0], cv[2], cv[6], c, c, c);

        //top
        geometry.addFace(cv[1], cv[5], cv[7], c, c, c);
        geometry.addFace(cv[1], cv[3], cv[7], c, c, c);

        //left
        geometry.addFace(cv[0], cv[1], cv[5], c, c, c);
        geometry.addFace(cv[0], cv[4], cv[5], c, c, c);

        //right
        geometry.addFace(cv[2], cv[3], cv[7], c, c, c);
        geometry.addFace(cv[2], cv[6], cv[7], c, c, c);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        surfaceView = new ESurfaceView(this);

        shaderManager = new EShaderManager(16, 16);

        int programIndex = shaderManager.addProgram();

        
        shaderManager.getShaderProgram(programIndex).addShader(vertexShaderCode, GLES20.GL_VERTEX_SHADER);

        shaderManager.getShaderProgram(programIndex).addShader(fragmentShaderCode,  GLES20.GL_FRAGMENT_SHADER);

        float[][] triVerts1 =
                {
                        { 0.0f,  0.5f, 0.0f },
                        { -0.5f, -0.5f, 0.0f },
                        { 0.5f, -0.5f, 0.0f }
                };

        float[][] triVerts2 =
                {
                        { 0.0f,  0.5f, 0.0f },
                        { 0.0f, -0.5f, -0.5f },
                        { 0.0f, -0.5f, 0.5f }
                };

        float[][] cubeVerts =
                {
                        { -0.5f, -0.5f, -0.5f },        //left bottom front
                        { -0.5f, 0.5f, -0.5f },         //left top front
                        { 0.5f, -0.5f, -0.5f },         //right bottom front
                        { 0.5f, 0.5f, -0.5f },          //right top front
                        { -0.5f, -0.5f, 0.5f },         //left bottom back
                        { -0.5f, 0.5f, 0.5f },          //left top back
                        { 0.5f, -0.5f, 0.5f },          //right bottom back
                        { 0.5f, 0.5f, 0.5f }            //right top back
                };

        float[] colorRed = { 1, 0, 0 };
        float[] colorGreen = { 0, 1, 0 };
        float[] colorBlue = { 0, 0, 1 };

        geometry = new EGeometryDynamic(4096, 4096);

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                addCube(-5 + i, (float)(Math.random() * 0.1), -5 + j, 1, 0.2f, (float)Math.random(), 0);
            }
        }

        view = new EView();
        view.setViewTarget(10, 10, -10, 0, 0, 0);

        context = new ERenderContext(programIndex, geometry, shaderManager, view);



        setContentView(surfaceView);

        ((ESurfaceView)surfaceView).setRenderContext(context);

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
    protected void onPause()
    {
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
