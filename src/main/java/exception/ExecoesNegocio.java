package exception;

public class ExecoesNegocio extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExecoesNegocio(String message) {
        super(message);
    }
}
