package ar.com.gmn.android.view.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import ar.com.gmn.android.R;

public class CustomNumberPicker extends LinearLayout {
private ImageView upArrow;
private ImageView downArrow;
private TextView numero;
private Integer current;
private ImageViewOnTouchListener touchListerner;
	public CustomNumberPicker(Context context) {
		super(context);
		current = 0;
		 touchListerner = new ImageViewOnTouchListener();
		// TODO Auto-generated constructor stub
		upArrow = new ImageView(context);
		downArrow  = new ImageView(context);
		numero = new TextView(context);
		createUpButton();
		createDownButton();
		
		createNumero(context);
		this.setOrientation(LinearLayout.VERTICAL);
		this.setGravity(Gravity.CENTER);
		this.addView(upArrow);
		this.addView(numero);
		this.addView(downArrow);
		
	}

	public void createNumero(Context context) {
		LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(60,60);
		numero.setBackgroundColor(Color.TRANSPARENT);
		Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/Digital.otf");
		numero.setTypeface(type);
		numero.setTextColor(Color.BLACK);
		numero.setTextSize(30);
		numero.setGravity(Gravity.CENTER);
		numero.setText(current.toString());
		numero.setLayoutParams(parms);
		numero.setBackgroundResource(R.drawable.panel);
	}

	public void createUpButton() {
		LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(90,60);
		
		upArrow.setLayoutParams(parms);
		upArrow.setImageResource(R.drawable.up_arrow);
		upArrow.setOnTouchListener(this.touchListerner);
		upArrow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				numero.setText(getNext().toString());
				
			}

		
		});
	}

	public void createDownButton() {
		LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(90,60);
		downArrow.setImageResource(R.drawable.down_arrow);
		downArrow.setLayoutParams(parms);
		downArrow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				numero.setText(getPrevious().toString());
				
			}
	
		});
		downArrow.setOnTouchListener(this.touchListerner);
	}
	
	private Integer getNext() {
		if (current<9){
			current++;
		
		}else{
			current = 0;
		}
		return current;
		
	}
	private Integer getPrevious() {
		if (current>0){
			current--;
		
		}else{
			current = 9;
		}
		return current;
		
	}
	public Integer getValue() {
		// TODO Auto-generated method stub
		return current;
	}
	public void setCorrecto(){
		
		this.numero.setShadowLayer(10, 0, 0, Color.GREEN);
		
	}
	public void setIncorrecto(Integer numero){
		
		this.numero.setText(numero.toString());
		this.numero.setShadowLayer(10, 0, 0, Color.RED);
	}
}
