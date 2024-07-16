import java.util.List;
import lombok.Data;

@Data
public class Banco {

    private String nome;
    private List<Conta> contas;

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }
}
