package aplicacion.vista.html;

import aplicacion.vista.html.especificos.Formulario;
import aplicacion.vista.html.especificos.Html;
import aplicacion.vista.html.especificos.Tabla;
import aplicacion.vista.html.especificos.Tag;

public class Cuerpo {

	private Formulario formulario = null;
	private Tabla historial = null;
	private Tag resultado = null;

	public Cuerpo(Formulario formulario, Tabla tabla, Tag resultado) {
		if (formulario != null) {
			this.formulario = formulario;
		}
		if (tabla != null) {
			this.historial = tabla;
		}
		if (resultado != null) {
			this.resultado = resultado;
		}
	}

	public Html addAPagina(Html pagina) {
		if (this.formulario != null) {
			pagina = this.formulario.addAPagina(pagina);
		}
		if (this.historial != null) {
			pagina = this.historial.addAPagina(pagina);
		}
		pagina.addABody(this.resultado);
		return pagina;
	}
}
