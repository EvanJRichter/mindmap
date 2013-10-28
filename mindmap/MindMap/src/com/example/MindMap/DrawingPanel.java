package com.example.MindMap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class DrawingPanel extends SurfaceView implements SurfaceHolder.Callback {
	
	 private double ax = -100, ay = -100;
	 private Paint p = new Paint();
	 Canvas canvas;
	 private SurfaceHolder surfaceHolder;
	 

	 public DrawingPanel(Context context) { 
	        super(context); 
	        getHolder().addCallback(this);
			surfaceHolder = getHolder();
    }


    @Override 
    public void onDraw(Canvas c) { 

		p.setColor(Color.WHITE);
		
		c.drawRect(0, 0, c.getWidth(), c.getHeight(), p);
			
		p.setColor(Color.BLACK);
		
		p.setTypeface(Typeface.create("Helvetica", Typeface.NORMAL));
		p.setTextAlign(Align.CENTER);
		p.setTextSize((float) 30);
		String pressAnywhere = "Press anywhere to begin Mindmap!";
		c.drawText(pressAnywhere, c.getWidth()/2, 200, p); 
		
		c.drawCircle((int) ax, (int) ay, 50, p);
    } 

    public void update() {
    	
		canvas = null;
		// try locking the canvas for exclusive pixel editing
		// in the surface
		try 
		{
			canvas = this.surfaceHolder.lockCanvas();
			synchronized (surfaceHolder) 
			{
				// update game state 
				// render state to the screen
				// draws the canvas on the panel
				if (canvas != null) 
				{
					
					this.onDraw(canvas);	
				}
			}
		}
		finally 
		{
			// in case of an exception the surface is not left in 
			// an inconsistent state
			if (canvas != null) 
			{
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}	// end finally
    }

    public boolean onTouchEvent(MotionEvent event) {
        
        if (event.getAction() == MotionEvent.ACTION_DOWN) 
        {
            ax = event.getX();
            ay = event.getY();
        } 
        
        if (event.getAction() == MotionEvent.ACTION_MOVE) 
        {
        	ax = event.getX();
            ay = event.getY();
        }
        
        if (event.getAction() == MotionEvent.ACTION_UP) {}
        
        update();
        
        return true;
    }
    
    @Override 
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    	update();
     //setWillNotDraw(false); //Allows us to use invalidate() to call onDraw()

     	//_thread = new MainThread(getHolder(), this); //Start the thread that
        //_thread.setRunning(true);                     //will make calls to 
        //_thread.start();                              //onDraw()
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
     //try {
           // _thread.setRunning(false);                //Tells thread to stop
           // _thread.join();                           //Removes thread from memory.
    // } 
     //catch (InterruptedException e) {}
    }


}