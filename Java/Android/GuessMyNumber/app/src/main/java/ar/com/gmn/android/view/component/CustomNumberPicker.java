package ar.com.gmn.android.view.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomNumberPicker extends LinearLayout {
private TextView upArrow;
private TextView downArrow;
private TextView numero;
private Integer current;

//private ImageViewOnTouchListener touchListerner;
	public CustomNumberPicker(Context context) {
		super(context);
		current = 0;
//		 touchListerner = new ImageViewOnTouchListener();
		// TODO Auto-generated constructor stub
		upArrow = new TextView(context);
		downArrow  = new TextView(context);
		numero = new TextView(context);
		createUpButton(context);
		createDownButton(context);
		
		createNumero(context);
		this.setOrientation(LinearLayout.VERTICAL);
		this.setGravity(Gravity.CENTER);
		this.addView(upArrow);
		this.addView(numero);
		this.addView(downArrow);
		
	}

	public void createNumero(Context context) {
		LayoutParams parms = new LayoutParams(60,60);
		
		numero.setText(current.toString());
		numero.setGravity(Gravity.CENTER);
		numero.setLayoutParams(parms);
	}

	public void createUpButton(Context context) {
		LayoutParams parms = new LayoutParams(90,60);
		
		upArrow.setLayoutParams(parms);
		upArrow.setText("+");
		upArrow.setGravity(Gravity.CENTER);
		upArrow.setLayoutParams(parms);
		upArrow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				numero.setText(getNext().toString());
				
			}

		
		});
	}

	public void createDownButton(Context context) {
		LayoutParams parms = new LayoutParams(90,60);
		downArrow.setText("-");
		downArrow.setGravity(Gravity.CENTER);
		downArrow.setLayoutParams(parms);
		
		downArrow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				numero.setText(getPrevious().toString());
				
			}
	
		});
//		downArrow.setOnTouchListener(this.touchListerner);
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

	public void setButtonStyle(int style) {
		this.upArrow.setTextAppearance(this.getContext(), style);
		this.downArrow.setTextAppearance(this.getContext(), style);
		
	}

	public void setNumberStyle(int style) {
		this.numero.setTextAppearance(this.getContext(), style);
		
	}

	public void setNumberFrame(int border) {
		this.numero.setBackgroundResource(border);
		
	}
	public void setButtonFrame(int border) {
		this.upArrow.setBackgroundResource(border);
		this.downArrow.setBackgroundResource(border);
		
	}

	public void setButtonTypeFace(Typeface tf) {
		this.upArrow.setTypeface(tf);
		this.downArrow.setTypeface(tf);
		
	}

	public void setNumberTypeFace(Typeface tf) {
		this.numero.setTypeface(tf);
		
	}
}
