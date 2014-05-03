package ar.com.gmn.android.fragment;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import ar.com.gmn.android.R;
import ar.com.gmn.android.core.Evaluador;
import ar.com.gmn.android.core.Numero;
import ar.com.gmn.android.core.Respuesta;
import ar.com.gmn.android.util.CustomNumberPickerFactory;
import ar.com.gmn.android.view.component.CustomNumberPicker;
import ar.com.gmn.android.view.component.TRRespuestaDuelo;

public class DuelFragment extends Fragment {

	private Evaluador e;
	private CustomNumberPicker digit1;
	private CustomNumberPicker digit2;
	private CustomNumberPicker digit3;
	private CustomNumberPicker digit4;
	private TextView probar;
	private Integer turno = 0;
	private Numero codigo;
	private TableLayout tResultados;
	private View container;
	private Typeface type;
	private TextView me;
	private TextView him;

	protected void addRespuesta(Respuesta r) {
		TableLayout tResultados = (TableLayout) container
				.findViewById(R.id.results_me);
		TRRespuestaDuelo trRespuesta = new TRRespuestaDuelo(container.getContext(), r,
				R.style.ResultadoDuelo);
		trRespuesta.setTextFont(type);
		tResultados.addView(trRespuesta);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup c,
			Bundle savedInstanceState) {

		this.container = inflater.inflate(R.layout.fragment_duel, null);
		this.me = (TextView)this.container.findViewById(R.id.meDuel);
		this.him = (TextView)this.container.findViewById(R.id.himDuel);
		
		this.me.setTypeface(type);
		codigo = Numero.getRandom(4);
		e = new Evaluador(codigo);
		this.type = Typeface.createFromAsset(
				container.getContext().getAssets(), "fonts/HandWrite.ttf");
		digit1 = CustomNumberPickerFactory.createNumberPiker(container.getContext(),CustomNumberPickerFactory.DUELO);
		digit2 = CustomNumberPickerFactory.createNumberPiker(container.getContext(),CustomNumberPickerFactory.DUELO);
		digit3 = CustomNumberPickerFactory.createNumberPiker(container.getContext(),CustomNumberPickerFactory.DUELO);
		digit4 = CustomNumberPickerFactory.createNumberPiker(container.getContext(),CustomNumberPickerFactory.DUELO);

		
		this.me.setTypeface(type);
		this.him.setTypeface(type);
		LinearLayout numeroLayout = (LinearLayout) container
				.findViewById(R.id.numeroLayout);
		numeroLayout.addView(digit1);
		numeroLayout.addView(digit2);
		numeroLayout.addView(digit3);
		numeroLayout.addView(digit4);
		probar = new TextView(container.getContext());
		probar.setText("?");

		probar.setTextAppearance(container.getContext(),
				R.style.DueloNumberPiker_Button);
		probar.setTypeface(type);
		probar.setGravity(Gravity.CENTER);

		probar.setBackgroundResource(R.drawable.border);
		// LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40,
		// 120);
		probar.getBackground().setColorFilter(0x80000000,
				PorterDuff.Mode.SRC_ATOP);
		
		// probar.setLayoutParams(params);
		numeroLayout.addView(probar);

		createTablaResultados();

		probar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Numero n = new Numero();
				n.set(1, digit1.getValue());
				n.set(2, digit2.getValue());
				n.set(3, digit3.getValue());
				n.set(4, digit4.getValue());

				if (n.tieneRepetidos()) {

					CharSequence text = "Números repetidos!";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(v.getContext(), text, duration);
					toast.show();
				} else {
					turno++;
					Respuesta r = e.evaluar(n);
					addRespuesta(r);
					if (r.resuelto()) {
						digit1.setCorrecto();
						digit2.setCorrecto();
						digit3.setCorrecto();
						digit4.setCorrecto();
						probar.setText(":)");
						probar.setEnabled(false);
					} else {
//						if (turno >= 7) {
//							digit1.setIncorrecto(codigo.get(1));
//							digit2.setIncorrecto(codigo.get(2));
//							digit3.setIncorrecto(codigo.get(3));
//							digit4.setIncorrecto(codigo.get(4));
//							probar.setText("X");
//							probar.setEnabled(false);
//						}
					}

				}
			}

		});

		return this.container;
	}

	public void createTablaResultados() {
		tResultados = (TableLayout) container.findViewById(R.id.results);
		((TextView) container.findViewById(R.id.textBienMe)).setTypeface(type);
		((TextView) container.findViewById(R.id.textNumeroMe)).setTypeface(type);
		
		((TextView) container.findViewById(R.id.textRegularMe)).setTypeface(type);
		((TextView) container.findViewById(R.id.textBienHim)).setTypeface(type);
		((TextView) container.findViewById(R.id.textNumeroHim)).setTypeface(type);
		
		((TextView) container.findViewById(R.id.textRegularHim)).setTypeface(type);

	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_duel_fragment, container,
					false);
			return rootView;
		}
	}

}
