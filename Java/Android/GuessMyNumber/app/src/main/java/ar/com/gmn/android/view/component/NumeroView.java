package ar.com.gmn.android.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

import ar.com.gmn.android.R;
import ar.com.gmn.android.core.Numero;
import ar.com.gmn.android.fragment.DuelFragment;
import ar.com.gmn.android.fragment.SinglePlayerFragment;

public class NumeroView extends LinearLayout {
private Numero numero;
private Typeface type;
private TextView digit1;
private TextView digit2;
private TextView digit3;
private TextView digit4;
private List<TextView> textos;

    public NumeroView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomNumberPicker2);

        if (!this.isInEditMode()) {
            for (int i = 0; i < a.getIndexCount(); ++i) {

                int attr = a.getIndex(i);
                switch (attr) {

                    case R.styleable.NumeroView_styleId:
                        switch (a.getInteger(attr, -1)) {
                            case DuelFragment.STYLE:
                                setTextAppearance(this.getContext(), R.style.ResultadoDuelo);
                                this.setTypeface(Typeface.createFromAsset(context.getAssets(),
                                        "fonts/HandWrite.ttf"));

                                break;
                            case SinglePlayerFragment.STYLE:
                                setTextAppearance(this.getContext(), R.style.ResultadoSinglePLayer);
                                this.setTypeface(Typeface.createFromAsset(context.getAssets(),
                                        "fonts/EraserDust.ttf"));

                                break;

                        }
                        break;


                }
            }

            a.recycle();
        }
    }

	public NumeroView(Context context, Numero n) {
		super(context);

        initView(context);
        setNumero(n);

	}

    private void initView(Context context) {
        final LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.numero_view, this);
        }
        textos = new Vector<>();
        digit1 = (TextView) findViewById(R.id.digit1);
        digit2 = (TextView) findViewById(R.id.digit2);
        digit3 = (TextView) findViewById(R.id.digit3);
        digit4 = (TextView) findViewById(R.id.digit4);
        addText(digit1);
        addText(digit2);
        addText(digit3);
        addText(digit4);


    }

    private void addText(TextView text) {
        this.textos.add(text);

    }


    public void setTypeface(Typeface type2) {
        for (TextView text : textos) {
			text.setTypeface(type2);
		}


    }

    public void setTextSize(int size) {
        for (TextView text : textos) {
            text.setTextSize(size);
		}
		
	}


	public void setTextColor(int color) {
		for (TextView text : textos) {
			text.setTextColor(color);
		}
		
		
	}


	public void setTextAppearance(Context context, int style) {
		for (TextView text : textos) {
			text.setTextAppearance(context, style);
		}
		
	}

    public void setNumero(Numero n) {
        this.digit1.setText(n.get(1));
        this.digit2.setText(n.get(2));
        this.digit3.setText(n.get(3));
        this.digit4.setText(n.get(4));
    }
}
