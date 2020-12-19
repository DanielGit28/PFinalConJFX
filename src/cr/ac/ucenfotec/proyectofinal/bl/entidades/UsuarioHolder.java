package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class UsuarioHolder {
    private UsuarioFinal usuario;
    public static UsuarioHolder INSTANCE;

    public UsuarioFinal getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioFinal usuario) {
        this.usuario = usuario;
    }

    public UsuarioHolder() {
        UsuarioHolder.INSTANCE = this;
    }

    public static UsuarioHolder getInstance() {
        return INSTANCE;
    }

}
