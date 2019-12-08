package aplicacion.vista.html;

import aplicacion.vista.html.especificos.Html;
import aplicacion.vista.html.especificos.Tag;

public class Header {

	private Tag logo;
	private Tag usuario = null;
	private Tag fotoPerfil = null;
	private Tag logout = null;
	private Tag darseDeBaja = null;
	private Tag login = null;
	private Tag registro = null;

	public Header(String nombreUsuario, String rutaFotoPerfil) {
		if (nombreUsuario != null) {
			this.usuario = new Tag(nombreUsuario);
		}
		if (rutaFotoPerfil != null) {
			this.fotoPerfil = new Tag("img", null, false, false);
			this.fotoPerfil.prepararAtributos();
			this.fotoPerfil.addAtributo("src", rutaFotoPerfil);
			this.fotoPerfil.addAtributo("alt", "fotoDePerfil");
		}
		this.logo = new Tag("img", null, false, false);
		this.logo.prepararAtributos();
		this.logo.addAtributo("src", "imgs/logo.png");
	}

	public void addLogout() {
		this.logout = new Tag("a", "Logout", true, true);
		this.logout.prepararAtributos();
		this.logout.addAtributo("href", "Logout");
	}

	public void addLogin() {
		this.login = new Tag("a", "Login", true, true);
		this.login.prepararAtributos();
		this.login.addAtributo("href", "Login");
	}

	public void addRegistro() {
		this.registro = new Tag("a", "Registro", true, true);
		this.registro.prepararAtributos();
		this.registro.addAtributo("href", "Registro");
	}

	public void addDarseDeBaja() {
		this.darseDeBaja = new Tag("a", "Darse de baja", true, true);
		this.darseDeBaja.prepararAtributos();
		this.darseDeBaja.addAtributo("href", "Baja");
	}

	public Html addAPagina(Html pagina) {
		Tag navBar = new Tag("ul", null, true, true);
		navBar.prepararAtributos();
		navBar.addAtributo("id", "navegacion");
		navBar.prepararHijos();
		navBar.addChild(Tag.incrustarEn(this.logo, "li"));
		if (this.usuario != null) {
			navBar.addChild(Tag.incrustarEn(this.usuario, "li"));
			if (this.fotoPerfil != null) {
				navBar.addChild(Tag.incrustarEn(this.fotoPerfil, "li"));
			}
			if (this.logout != null) {
				navBar.addChild(Tag.incrustarEn(this.logout, "li"));
			}
			if (this.darseDeBaja != null) {
				navBar.addChild(Tag.incrustarEn(this.darseDeBaja, "li"));
			}
		} else {
			if (this.login != null) {
				navBar.addChild(Tag.incrustarEn(this.login, "li"));
			}
			if (this.registro != null) {
				navBar.addChild(Tag.incrustarEn(this.registro, "li"));
			}
		}
		pagina.addABody(navBar);
		return pagina;
	}
}
