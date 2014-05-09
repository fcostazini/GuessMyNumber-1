package ar.com.gmn.android.service;

import android.util.JsonWriter;
import ar.com.gmn.android.core.Evaluador;
import ar.com.gmn.android.core.Numero;
import ar.com.gmn.android.service.observers.IMessageObserver;

public class WebSocketConnector {
	private Evaluador evaluador;
	private IMessageObserver observer;
	
	public WebSocketConnector(Evaluador evaluador) {
		super();
		this.evaluador = evaluador;
	}

	public void sendMessage(String string) {
		System.out.println(string);
		
	}

	public void evaluarNumero(String oponente, Numero numero) {
		Message m = new Message();
		m.setType(Message.RESPUESTA_INTENTO);
		m.setContent(evaluador.evaluar(numero).toString());
		m.setOponente(oponente);
		observer.actulizar(m);
		
	}

}
