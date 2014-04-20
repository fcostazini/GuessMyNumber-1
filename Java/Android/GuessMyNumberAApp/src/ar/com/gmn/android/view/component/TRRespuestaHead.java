package ar.com.gmn.android.view.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TableRow;
import android.widget.TextView;
import ar.com.gmn.android.core.Numero;
import ar.com.gmn.android.core.Respuesta;

public class TRRespuestaHead extends TableRow {
	private TextView turno;
	private TextView numero;
	private TextView bien;
	private TextView regular;

	public TRRespuestaHead(Context context) {
		super(context);

		turno = new TextView(context);
		turno.setText("T");
		numero = new TextView(context);
		numero.setText("Numero");
		bien = new TextView(context);
		bien.setText("B");
		regular = new TextView(context);
		regular.setText("R");
		
		Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/EraserDust.ttf");
		turno.setTypeface(type);
		turno.setWidth(70);
		turno.setTextSize(20);
		turno.setTextColor(Color.WHITE);
		
		bien.setTypeface(type);
		bien.setTextSize(20);
		bien.setWidth(40);
		bien.setTextColor(Color.WHITE);
		
		regular.setTypeface(type);
		regular.setTextSize(20);
		regular.setWidth(40);
		regular.setTextColor(Color.WHITE);
		
		numero.setTypeface(type);
		numero.setTextSize(20);
		numero.setWidth(200);
		numero.setTextColor(Color.WHITE);
		
		this.addView(turno);
		this.addView(numero);
		this.addView(bien);
		this.addView(regular);
		
		
	}

	public void setBien(String i) {
		this.bien.setText(i.toString());
	}
	
	public void setRegular(String i) {
		this.regular.setText(i.toString());
	}
	
	public void setNumero(String n) {
		this.regular.setText(n);
	}
	
	public void setTurno(String i) {
		this.turno.setText(i.toString());
	}


}
