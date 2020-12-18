package cr.ac.ucenfotec.proyectofinal.bl.entidades;

public class UsuarioHolder {
    private UsuarioFinal usuario;

    public UsuarioFinal getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioFinal usuario) {
        this.usuario = usuario;
    }

    public UsuarioHolder() {
    }

    private static final UsuarioHolder INSTANCE = new UsuarioHolder();

    public static UsuarioHolder getInstance() {
        return INSTANCE;
    }

}
