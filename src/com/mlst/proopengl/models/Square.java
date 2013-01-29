package com.mlst.proopengl.models;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

/**
 * @author I071571 marc.lester.tan@sap.com
 * 
 */
public class Square {
	private FloatBuffer mFVertexBuffer;
	private ByteBuffer mColorBuffer;
	private ByteBuffer mIndicesBuffer;
	private ByteBuffer mTFan1;
	private ByteBuffer mTFan2;

	/**
	 * 
	 */
	public Square() {
		float[] vertices = { 
							-1.0f, 1f, 1f,
							  1f, 1f, 1f, 
							  1f, -1f, 1f, 
							  -1f, -1f, 1f,
							  
							  -1.0f, 1f, -1f,
							  1f, 1f, -1f, 
							  1f, -1f, -1f, 
							  -1f, -1f, -1f,
		};
		
		byte maxColor = (byte) 255;
		
		byte[] colors = {
				maxColor, maxColor, 0, maxColor,
				0, maxColor, maxColor, maxColor,
				0, 0, 0 , maxColor,
				maxColor, 0, maxColor, maxColor,
				
				maxColor, 0, 0, maxColor,
				0, maxColor, 0, maxColor,
				0, 0, maxColor, maxColor,
				0, 0, 0, maxColor
		};
		
		byte[] tfan1 = {
				1,0,3,
				1,3,2,
				1,2,6,
				1,6,5,
				1,5,4,
				1,4,0
		};
		
		byte[] tfan2 = {
				7,4,5,
				7,5,6,
				7,6,2,
				7,2,3,
				7,3,0,
				7,0,4
		};
		
	
		
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		
		mFVertexBuffer = vbb.asFloatBuffer();
		mFVertexBuffer.put(vertices);
		mFVertexBuffer.position(0);
		
		
		mColorBuffer = ByteBuffer.allocateDirect(colors.length);
		mColorBuffer.put(colors);
		mColorBuffer.position(0);
		
		mTFan1 = ByteBuffer.allocateDirect(tfan1.length);
		mTFan1.put(tfan1);
		mTFan1.position(0);
		
		mTFan2 = ByteBuffer.allocateDirect(tfan2.length);
		mTFan2.put(tfan2);
		mTFan2.position(0);
	}
	
	public void draw(GL10 gl){
		gl.glVertexPointer(3,  GL11.GL_FLOAT, 0, mFVertexBuffer);
		gl.glColorPointer(4, GL11.GL_UNSIGNED_BYTE, 0, mColorBuffer);
		gl.glDrawElements(GL11.GL_TRIANGLE_FAN, 6 * 3, GL11.GL_UNSIGNED_BYTE, mTFan1);
		gl.glDrawElements(GL11.GL_TRIANGLE_FAN, 6 * 3, GL11.GL_UNSIGNED_BYTE, mTFan2);
	}
}
