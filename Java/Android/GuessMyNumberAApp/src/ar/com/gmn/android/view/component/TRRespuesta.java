package ar.com.gmn.android.view.component;

import android.content.Context;
import android.widget.TableRow;
import android.widget.TextView;
import ar.com.gmn.android.core.Numero;
import ar.com.gmn.android.core.Respuesta;

public class TRRespuesta extends TableRow {
	private TextView turno;
	private TextView numero;
	private TextView bien;
	private TextView regular;

	public TRRespuesta(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public TRRespuesta(Context context, Respuesta r) {
		super(context);

		turno = new TextView(context);
		numero = new TextView(context);
		numero.setText(r.getNumero().toString());
		bien = new TextView(context);
		bien.setText(r.getCorrectos().toString());
		regular = new TextView(context);
		regular.setText(r.getRegulares().toString());
		this.addView(turno);
		this.addView(numero);
		this.addView(bien);
		this.addView(regular);
		
	}

	public void setBien(Integer i) {
		this.bien.setText(i.toString());
	}
	
	public void setRegular(Integer i) {
		this.regular.setText(i.toString());
	}
	
	public void setNumero(Numero n) {
		this.numero.setText(n.toString());
	}
	
	public void setTurno(Integer i) {
		this.turno.setText(i.toString());
	}

}
