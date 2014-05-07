package ar.com.gmn.android.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
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
import ar.com.gmn.android.view.component.TRRespuesta;

public class SinglePlayerFragment extends Fragment {

	private Evaluador e;
	private CustomNumberPicker digit1;
	private CustomNumberPicker digit2;
	private CustomNumberPicker digit3;
	private CustomNumberPicker digit4;
	private TextView probar;
	private Integer turno = 0;
	private Numero codigo;
	private TableLayout tResultados;
	private Typeface type;
	private View container;

	protected void addRespuesta(Respuesta r) {
		TableLayout tResultados = (TableLayout) this.container.findViewById(R.id.results);
		TRRespuesta trRespuesta = new TRRespuesta(this.container.getContext(), r,
				R.style.ResultadoSinglePLayer);
		trRespuesta.setTextFont(type);
		trRespuesta.setTurno(turno);
		tResultados.addView(trRespuesta);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup c,
			Bundle savedInstanceState) {
		this.container = inflater.inflate(R.layout.single_player_fragment, null);
		// Remove title bar

		codigo = Numero.getRandom(4);
		e = new Evaluador(codigo);
		this.type = Typeface.createFromAsset(this.container.getContext()
				.getAssets(), "fonts/EraserDust.ttf");
		digit1 = CustomNumberPickerFactory.createNumberPiker(
				this.container.getContext(),
				CustomNumberPickerFactory.SINGLE_PLAYER);
		digit2 = CustomNumberPickerFactory.createNumberPiker(
				this.container.getContext(),
				CustomNumberPickerFactory.SINGLE_PLAYER);
		digit3 = CustomNumberPickerFactory.createNumberPiker(
				this.container.getContext(),
				CustomNumberPickerFactory.SINGLE_PLAYER);
		digit4 = CustomNumberPickerFactory.createNumberPiker(
				this.container.getContext(),
				CustomNumberPickerFactory.SINGLE_PLAYER);

		LinearLayout numeroLayout = (LinearLayout) this.container.findViewById(R.id.numeroLayout);
		numeroLayout.addView(digit1);
		numeroLayout.addView(digit2);
		numeroLayout.addView(digit3);
		numeroLayout.addView(digit4);
		probar = new TextView(container.getContext());
		probar.setText("?");

		probar.setTextAppearance(container.getContext(),
				R.style.SP_NumberPiker_Button);
		probar.setTypeface(type);
		probar.setGravity(Gravity.CENTER);

		probar.setBackgroundResource(R.drawable.chalk_frame);
		 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				 LayoutParams.WRAP_CONTENT);
//		probar.getBackground().setColorFilter(0x80000000,
//				PorterDuff.Mode.SRC_ATOP);
		
		 probar.setLayoutParams(params);
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

					CharSequence text = "N�meros repetidos!";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(v.getContext(), text,
							duration);
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
						if (turno >= 7) {
							digit1.setIncorrecto(codigo.get(1));
							digit2.setIncorrecto(codigo.get(2));
							digit3.setIncorrecto(codigo.get(3));
							digit4.setIncorrecto(codigo.get(4));
							probar.setText("X");
							probar.setEnabled(false);
						}
					}

				}
			}

		});
		return this.container;
	}

	public void createTablaResultados() {
		Typeface type = Typeface.createFromAsset(this.container.getContext().getAssets(),
				"fonts/EraserDust.ttf");
		tResultados = (TableLayout) this.container.findViewById(R.id.results);
		((TextView) this.container.findViewById(R.id.textBien)).setTypeface(type);
		((TextView) this.container.findViewById(R.id.textNumero)).setTypeface(type);
		((TextView) this.container.findViewById(R.id.textTurno)).setTypeface(type);
		((TextView) this.container.findViewById(R.id.textRegular)).setTypeface(type);
	}

	
}
