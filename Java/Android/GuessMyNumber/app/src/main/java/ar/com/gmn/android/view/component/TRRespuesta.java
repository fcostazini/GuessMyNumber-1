package ar.com.gmn.android.view.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import ar.com.gmn.android.R;
import ar.com.gmn.android.core.Numero;
import ar.com.gmn.android.core.Respuesta;

public class TRRespuesta extends LinearLayout {

    protected NumeroView numero;
    protected TextView bien;
    protected TextView regular;


    public TRRespuesta(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public TRRespuesta(Context context, Respuesta r) {
        super(context);
        initView(context);
        bien.setText(r.getCorrectos().toString());
        regular.setText(r.getRegulares().toString());
        numero.setNumero(r.getNumero());
    }

    protected void initView(Context context) {
        final LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.respuesta_view, this);
        }
        bien = (TextView) findViewById(R.id.textBien);
        regular = (TextView) findViewById(R.id.textRegular);
        numero = (NumeroView) findViewById(R.id.textNumero);


    }


	public void setTextFont(Typeface type) {

        bien.setTypeface(type);
        regular.setTypeface(type);
        numero.setTypeface(type);
	}

    public void setTextAppearence(int style) {
        bien.setTextAppearance(this.getContext(), style);
        regular.setTextAppearance(this.getContext(), style);
        numero.setTextAppearance(this.getContext(), style);
    }

    public void setBien(Integer i) {
        this.bien.setText(i.toString());
    }

	public void setRegular(Integer i) {
		this.regular.setText(i.toString());
	}

	public void setNumero(Numero n) {
		this.numero = new NumeroView(this.getContext(), n);
	}


}
