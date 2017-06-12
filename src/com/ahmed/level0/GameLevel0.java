package com.ahmed.level0;

import java.util.ArrayList;
import java.util.Random;

import com.ahmed.mainHome.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class GameLevel0 extends Activity {

	
	
	ImageView one, two, three, four, five, nine, start;
	
	ArrayList<ImageView> buttonList = null;
	int[] arr = {R.drawable.one, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.nine};
	ArrayList<Integer> drawables = null;
	ArrayList<Integer> resRandom = null;
	Handler handler1 = null, handler2 = null;
	
	
	String tag;
	int iTag, jTag;
	int iLeftPos, jLeftPos, iRightPos, jRightPos, iTopPos, jTopPos, iBottomPos, jBottomPos;
	ImageView left = null, right = null, top = null, bottom = null;
	int pos1, pos2, val1, val2, temp;
	int c = 0;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_level0);
		
		//Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
		init();
		
	}

    public void createRandom (){
		
    	handler2 = new Handler();
		resRandom = new ArrayList<Integer>();
		Thread randomThread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < arr.length; i++) {
					final int f = i;
					handler2.post(new Runnable() {
						
						@Override
						public void run() {
							
							Random random = new Random();
							int r = random.nextInt(drawables.size());
							buttonList.get(f).setBackgroundResource(drawables.get(r));
							resRandom.add(drawables.get(r));
							drawables.remove(r);
						}
					});
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
			}
		});
		//randomThread.start();
		for (int i = 0; i < arr.length; i++) {
			Random random = new Random();
			int r = random.nextInt(drawables.size());
			buttonList.get(i).setBackgroundResource(drawables.get(r));
			resRandom.add(drawables.get(r));
			drawables.remove(r);
		}
		start.setBackgroundResource(0);//setVisibility = false

		
		
	}

	public void playGame (){
		
		for (int i = 0; i < buttonList.size(); i++) {
			
			buttonList.get(i).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
//					for (int j = 0; j < buttonList.size(); j++) {
//						if (v.getTag().equals(buttonList.get(j).getTag()) && resRandom.get(j) != R.id.nine) {
//							buttonList.get(j).setBackgroundResource(R.drawable.nine);
//							//nine.setBackgroundResource(resRandom.get(j));
//							for (int k = 0; k < buttonList.size(); k++) {
//								if (resRandom.get(k) == R.drawable.nine) {
//									buttonList.get(k).setBackgroundResource(resRandom.get(j));
//								}
//							}
//							
//						}
//					}
					
					MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.hit);
					mediaPlayer.start();
					
					ivArroundEmpty();
					exchangeImages(v);
					resetTagsForIv();
					//val1 = 0; val2 = 0; pos1 = 0; pos2 = 0;
					checkNormal();
					if (c == 0) {
						startActivity(new Intent(getApplicationContext(), com.ahmed.level1.GameLevel1.class));
					}
					c = 0;
					
				}
			});
		}
		
	}
	
    public void init(){
		
		one = (ImageView) findViewById(R.id.oneLev0);
		one.setTag("00");
		two = (ImageView) findViewById(R.id.twoLev0);
		two.setTag("01");
		three = (ImageView) findViewById(R.id.threeLev0);
		three.setTag("02");
		four = (ImageView) findViewById(R.id.fourLev0);
		four.setTag("10");
		five = (ImageView) findViewById(R.id.fiveLev0);
		five.setTag("11");
		nine = (ImageView) findViewById(R.id.nineLev0);
		nine.setTag("22");
		start = (ImageView) findViewById(R.id.startLev0);
		
		buttonList = new ArrayList<ImageView>();
		buttonList.add(one);
		buttonList.add(two);
		buttonList.add(three);
		buttonList.add(four);
		buttonList.add(five);
		buttonList.add(nine);
		
		drawables = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			drawables.add(arr[i]);
		}
		handler1 = new Handler();
        Thread startThread = new Thread(new Runnable() {
			@Override
			public void run() {
				
				for (int i = 0; i < 3; i++) {
					final int f = i;
					handler1.post(new Runnable() {
						@Override
						public void run() {
							start.setBackgroundResource(R.drawable.button_start);
						}
					});
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					start.setBackgroundResource(0);
				}
				
			}
		});
		Thread createThread = new Thread(new Runnable() {
			@Override
			public void run() {
				
				for (int i = 0; i < arr.length; i++) {
					final int f = i;
					handler1.post(new Runnable() {			
						@Override
						public void run() {
								buttonList.get(f).setBackgroundResource(arr[f]);
						}
					});
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		createThread.start();
//        
//        for (int i = 0; i < arr.length; i++) {
//			buttonList.get(i).setBackgroundResource(arr[i]);
//		}
        
		start.setBackgroundResource(R.drawable.button_start);
		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				createRandom();
				ivArroundEmpty();
				playGame();
			}
		});
	}

	protected void checkNormal() {
		for (int i = 0; i < resRandom.size(); i++) {
			if (resRandom.get(i) != arr[i]) {
				c++;
			}
		}
	}

	public void resetTagsForIv (){
		one.setTag("00");
		two.setTag("01");
		three.setTag("02");
		four.setTag("10");
		five.setTag("11");
		nine.setTag("12");
	}
	
	public void ivArroundEmpty (){
		
		for (int j = 0; j < buttonList.size(); j++) {
			
			if (resRandom.get(j) == R.drawable.nine) {
				
				tag = (String) buttonList.get(j).getTag();
				iTag = Integer.valueOf(tag.substring(0, 1));
				jTag = Integer.valueOf(tag.substring(1, 2));
				iLeftPos = iTag;jLeftPos = jTag-1; 
				iRightPos = iTag; jRightPos = jTag+1; 
				iTopPos = iTag-1;jTopPos = jTag; 
				iBottomPos = iTag+1;jBottomPos = jTag;
				
					for (int k = 0; k < buttonList.size(); k++) {
						
						if (buttonList.get(k).getTag().equals(String.valueOf(iLeftPos)+String.valueOf(jLeftPos))) {
							buttonList.get(k).setTag("left");
							//Toast.makeText(getApplicationContext(), "Left", Toast.LENGTH_SHORT).show();
					    }
						
						if (buttonList.get(k).getTag().equals(String.valueOf(iRightPos)+String.valueOf(jRightPos))) {
							buttonList.get(k).setTag("right");
							//Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();
						}
						
						if (buttonList.get(k).getTag().equals(String.valueOf(iTopPos)+String.valueOf(jTopPos))) {
							buttonList.get(k).setTag("top");
							//Toast.makeText(getApplicationContext(), "Top", Toast.LENGTH_SHORT).show();
						}
						
						if (buttonList.get(k).getTag().equals(String.valueOf(iBottomPos)+String.valueOf(jBottomPos))) {
							buttonList.get(k).setTag("bottom");
							//Toast.makeText(getApplicationContext(), "Bottom", Toast.LENGTH_SHORT).show();
						}
						
						
						
					}
				
			}
			
		}
		
		
		
	}
	
	public void exchangeImages (View v){
		
		if (v.getTag().equals("left")) {
			
			//pos1 = 0; pos2 = 0;
			for (int j = 0; j < buttonList.size(); j++) {
				if (buttonList.get(j).getTag().equals("left")) {
					pos1 = j;
					break;
				}
			}
			for (int i = 0; i < buttonList.size(); i++) {
				if (resRandom.get(i) == R.drawable.nine) {
					pos2 = i;
					buttonList.get(i).setBackgroundResource(resRandom.get(pos1));
					break;
				}
			}
			v.setBackgroundResource(R.drawable.nine);
			//Swapping two positions in resRandom arraylist to be refreshed
			for (int i = 0; i < buttonList.size(); i++) {
				
				if (i == pos1) {
					val1 = resRandom.get(i);
					for (int j = 0; j < buttonList.size(); j++) {
						if (j == pos2) {
							val2 = resRandom.get(j);
//							temp = val1;
//							val2 = temp;
//							val1 = val2;
							resRandom.remove(pos1);
							resRandom.add(pos1, val2);
							resRandom.remove(pos2);
							resRandom.add(pos2, val1);
							
						}
					}
				}
				
			}
			
			
		}
			
	    if (v.getTag().equals("right")) {
			pos1 = 0; pos2 = 0;
			for (int j = 0; j < buttonList.size(); j++) {
				if (buttonList.get(j).getTag().equals("right")) {
					pos1 = j;
					break;
				}
			}
			for (int i = 0; i < buttonList.size(); i++) {
				if (resRandom.get(i) == R.drawable.nine) {
					pos2 = i;
					buttonList.get(i).setBackgroundResource(resRandom.get(pos1));
					break;
				}
			}
			v.setBackgroundResource(R.drawable.nine);
			//Swapping two positions in resRandom arraylist to be refreshed
			for (int i = 0; i < buttonList.size(); i++) {
				if (i == pos1) {
					val1 = resRandom.get(i);
					for (int j = 0; j < buttonList.size(); j++) {
						if (j == pos2) {
							val2 = resRandom.get(j);
//							temp = val1;
//							val2 = temp;
//							val1 = val2;
							resRandom.remove(pos1);
							resRandom.add(pos1, val2);
							resRandom.remove(pos2);
							resRandom.add(pos2, val1);
							
						}
					}
				}
	    }
			
	    }	
				
		if (v.getTag().equals("top")) {
			
			
			pos1 = 0; pos2 = 0;
			for (int j = 0; j < buttonList.size(); j++) {
				if (buttonList.get(j).getTag().equals("top")) {
					pos1 = j;
					break;
				}
			}
			for (int i = 0; i < buttonList.size(); i++) {
				if (resRandom.get(i) == R.drawable.nine) {
					pos2 = i;
					buttonList.get(i).setBackgroundResource(resRandom.get(pos1));
					break;
				}
			}
			v.setBackgroundResource(R.drawable.nine);
			//Swapping two positions in resRandom arraylist to be refreshed
			for (int i = 0; i < buttonList.size(); i++) {
				if (i == pos1) {
					val1 = resRandom.get(i);
					for (int j = 0; j < buttonList.size(); j++) {
						if (j == pos2) {
							val2 = resRandom.get(j);
//							temp = val1;
//							val2 = temp;
//							val1 = val2;
							resRandom.remove(pos1);
							resRandom.add(pos1, val2);
							resRandom.remove(pos2);
							resRandom.add(pos2, val1);
							
						}
					}
				}
				
				
		}
		}
		if (v.getTag().equals("bottom")) {
			
			
			pos1 = 0; pos2 = 0;
			for (int j = 0; j < buttonList.size(); j++) {
				if (buttonList.get(j).getTag().equals("bottom")) {
					pos1 = j;
					break;
				}
			}
			for (int i = 0; i < buttonList.size(); i++) {
				if (resRandom.get(i) == R.drawable.nine) {
					pos2 = i;
					buttonList.get(i).setBackgroundResource(resRandom.get(pos1));
					break;
				}
			}
			
			v.setBackgroundResource(R.drawable.nine);
			
			//Swapping two positions in resRandom arraylist to be refreshed
			for (int i = 0; i < buttonList.size(); i++) {
				if (i == pos1) {
				    val1 = resRandom.get(i);
					for (int j = 0; j < buttonList.size(); j++) {
						if (j == pos2) {
							val2 = resRandom.get(j);
//							temp = val1;
//							val2 = temp;
//							val1 = val2;
							resRandom.remove(pos1);
							resRandom.add(pos1, val2);
							resRandom.remove(pos2);
							resRandom.add(pos2, val1);
							
						}
					}
				}
				
				
		}
			
			
		}
		
		
		
		
	}
	
	
	
	
	
	
}
