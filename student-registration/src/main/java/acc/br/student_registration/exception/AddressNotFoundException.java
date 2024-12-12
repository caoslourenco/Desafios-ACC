package acc.br.student_registration.exception;

/**
 * Exceção personalizada para indicar que um endereço não foi encontrado.
 */
public class AddressNotFoundException extends RuntimeException {

    /**
     * Construtor para criar uma exceção com a mensagem fornecida.
     *
     * @param msg mensagem descritiva do erro
     */
    public AddressNotFoundException(String msg) {
        super(msg);
    }
}
