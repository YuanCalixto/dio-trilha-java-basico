import lombok.Data;

@Data
public class Cliente implements ICliente {

    private String nome;

    @Override
    public void criarCliente(String nome) {
        this.nome = nome;
    }
}
