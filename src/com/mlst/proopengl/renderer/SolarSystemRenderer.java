package com.mlst.proopengl.renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.mlst.proopengl.models.Planet;

import android.opengl.GLSurfaceView.Renderer;

/**
 * @author I071571 marc.lester.tan@sap.com
 * 
 */
public class SolarSystemRenderer implements Renderer {

	private double mTransY;
	private float mAngle;
	private Planet mPlanet;

	/**
	 * 
	 */
	public SolarSystemRenderer() {
		mPlanet = new Planet(100, 100, 1f, 1f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onDrawFrame(javax.microedition.
	 * khronos.opengles.GL10)
	 */
	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glClearColor(0f, 0.5f, 0.5f, 1f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

		gl.glTranslatef(0f, (float) Math.sin(mTransY), -4f);
		gl.glRotatef(mAngle, 0f, 1f, 0f);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		mPlanet.draw(gl);
		mTransY += 0.001f;
		mAngle += 0.01f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.opengl.GLSurfaceView.Renderer#onSurfaceChanged(javax.microedition
	 * .khronos.opengles.GL10, int, int)
	 */
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);

		float aspectRation;
		float zNear = 0.1f;
		float zFar = 1000;
		float fieldOfView = 30.0f / 57.3f;
		float size;

		gl.glEnable(GL10.GL_NORMALIZE);
		aspectRation = (float) width / (float) height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		size = zNear * (float) Math.tan((double) (fieldOfView) / 2f);
		gl.glFrustumf(-size, size, -size / aspectRation, size / aspectRation, zNear, zFar);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glDisable(GL10.GL_DITHER);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

		gl.glClearColor(1, 1, 1, 1);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glEnable(GL10.GL_DEPTH_TEST);

	}

}
