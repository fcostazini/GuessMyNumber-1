package ar.com.gmn.android;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import ar.com.gmn.android.core.Evaluador;
import ar.com.gmn.android.core.Numero;
import ar.com.gmn.android.core.Respuesta;
import ar.com.gmn.android.view.component.CustomNumberPicker;
import ar.com.gmn.android.view.component.TRRespuesta;
import ar.com.gmn.android.view.component.TRRespuestaHead;

public class MainActivity extends ActionBarActivity {
	private Evaluador e;
	private CustomNumberPicker digit1;
	private CustomNumberPicker digit2;
	private CustomNumberPicker digit3;
	private CustomNumberPicker digit4;
	private Button probar;
	private Integer turno = 0;
	private Numero codigo;
	private TableLayout tResultados; 

	protected void addRespuesta(Respuesta r) {
		TableLayout tResultados = (TableLayout) findViewById(R.id.results);
		TRRespuesta trRespuesta = new TRRespuesta(this, r);
		trRespuesta.setTurno(turno);
		tResultados.addView(trRespuesta);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		   //Remove title bar
	 
		setContentView(R.layout.activity_main);
		
		codigo = Numero.getRandom(4);
		e = new Evaluador(codigo);

		digit1 = new CustomNumberPicker(this);
		digit2 = new CustomNumberPicker(this);
		digit3 = new CustomNumberPicker(this);
		digit4 = new CustomNumberPicker(this);
		
		LinearLayout numeroLayout = (LinearLayout)findViewById(R.id.numeroLayout);
		numeroLayout.addView(digit1);
		numeroLayout.addView(digit2);
		numeroLayout.addView(digit3);
		numeroLayout.addView(digit4);
		probar = new Button(this);
		probar.setBackgroundResource(R.drawable.lock_key);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120,120);
		probar.getBackground().setColorFilter(0x80000000,PorterDuff.Mode.SRC_ATOP);
		
		probar.setLayoutParams(params);
		numeroLayout.addView(probar);

		createTablaResultados();
		
		
		probar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				turno++;
				Numero n = new Numero();
				n.set(1, digit1.getValue());
				n.set(2, digit2.getValue());
				n.set(3, digit3.getValue());
				n.set(4, digit4.getValue());
				
				Respuesta r = e.evaluar(n);
				addRespuesta(r);
				if (r.resuelto()) {
					digit1.setCorrecto();
					digit2.setCorrecto();
					digit3.setCorrecto();
					digit4.setCorrecto();
					probar.setBackgroundResource(R.drawable.unlock);
					probar.getBackground().setColorFilter(0x80000000,PorterDuff.Mode.SRC_ATOP);
					probar.setEnabled(false);
				}else{
					if(turno >= 7){
						digit1.setIncorrecto(codigo.get(1));
						digit2.setIncorrecto(codigo.get(2));
						digit3.setIncorrecto(codigo.get(3));
						digit4.setIncorrecto(codigo.get(4));
						probar.setBackgroundResource(R.drawable.lock);
						probar.getBackground().setColorFilter(0x80000000,PorterDuff.Mode.SRC_ATOP);
						probar.setEnabled(false);
					}
				}

			}

		});
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public void createTablaResultados() {
		tResultados = (TableLayout) findViewById(R.id.results);
		tResultados.addView(new TRRespuestaHead(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.new_game) {
			Intent intent = getIntent();
			finish();
			startActivity(intent);
			
		}
		return super.onOptionsItemSelected(item);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
