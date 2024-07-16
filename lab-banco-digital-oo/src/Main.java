import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();
        banco.setContas(new ArrayList<>());
        Map<String, Cliente> clientes = new HashMap<>();

        System.out.print("Digite o nome do banco: ");
        banco.setNome(scanner.nextLine());

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Criar Cliente");
            System.out.println("2 - Criar Conta Corrente");
            System.out.println("3 - Criar Conta Poupança");
            System.out.println("4 - Depositar");
            System.out.println("5 - Sacar");
            System.out.println("6 - Transferir");
            System.out.println("7 - Imprimir Extrato");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    if (clientes.containsKey(nomeCliente)) {
                        System.out.println("Cliente já existe!");
                    } else {
                        Cliente cliente = new Cliente();
                        cliente.criarCliente(nomeCliente);
                        clientes.put(nomeCliente, cliente);
                        System.out.println("Cliente criado com sucesso!");
                    }
                    break;
                case 2:
                    System.out.print("Digite o nome do cliente para a conta corrente: ");
                    nomeCliente = scanner.nextLine();
                    Cliente cliente = clientes.get(nomeCliente);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado!");
                    } else {
                        Conta contaCorrente = new ContaCorrente(cliente);
                        banco.adicionarConta(contaCorrente);
                        System.out.println("Conta Corrente criada com sucesso!");
                    }
                    break;
                case 3:
                    System.out.print("Digite o nome do cliente para a conta poupança: ");
                    nomeCliente = scanner.nextLine();
                    cliente = clientes.get(nomeCliente);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado!");
                    } else {
                        Conta contaPoupanca = new ContaPoupanca(cliente);
                        banco.adicionarConta(contaPoupanca);
                        System.out.println("Conta Poupança criada com sucesso!");
                    }
                    break;
                case 4:
                    System.out.print("Digite o nome do cliente: ");
                    nomeCliente = scanner.nextLine();
                    cliente = clientes.get(nomeCliente);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado!");
                    } else {
                        System.out.print("Digite o valor para depositar: ");
                        double valorDeposito = scanner.nextDouble();
                        for (Conta conta : banco.getContas()) {
                            if (conta.getCliente().equals(cliente)) {
                                conta.depositar(valorDeposito);
                                System.out.println("Depósito realizado com sucesso!");
                                break;
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.print("Digite o nome do cliente: ");
                    nomeCliente = scanner.nextLine();
                    cliente = clientes.get(nomeCliente);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado!");
                    } else {
                        System.out.print("Digite o valor para sacar: ");
                        double valorSaque = scanner.nextDouble();
                        for (Conta conta : banco.getContas()) {
                            if (conta.getCliente().equals(cliente)) {
                                conta.sacar(valorSaque);
                                System.out.println("Saque realizado com sucesso!");
                                break;
                            }
                        }
                    }
                    break;
                case 6:
                    System.out.print("Digite o nome do cliente de origem: ");
                    String nomeClienteOrigem = scanner.nextLine();
                    Cliente clienteOrigem = clientes.get(nomeClienteOrigem);
                    if (clienteOrigem == null) {
                        System.out.println("Cliente de origem não encontrado!");
                    } else {
                        System.out.print("Digite o nome do cliente de destino: ");
                        String nomeClienteDestino = scanner.nextLine();
                        Cliente clienteDestino = clientes.get(nomeClienteDestino);
                        if (clienteDestino == null) {
                            System.out.println("Cliente de destino não encontrado!");
                        } else {
                            System.out.print("Digite o valor para transferir: ");
                            double valorTransferencia = scanner.nextDouble();
                            Conta contaOrigem = null, contaDestino = null;
                            for (Conta conta : banco.getContas()) {
                                if (conta.getCliente().equals(clienteOrigem)) {
                                    contaOrigem = conta;
                                }
                                if (conta.getCliente().equals(clienteDestino)) {
                                    contaDestino = conta;
                                }
                            }
                            if (contaOrigem != null && contaDestino != null) {
                                contaOrigem.transferir(valorTransferencia, contaDestino);
                                System.out.println("Transferência realizada com sucesso!");
                            } else {
                                System.out.println("Conta de origem ou destino não encontrada!");
                            }
                        }
                    }
                    break;
                case 7:
                    for (Conta conta : banco.getContas()) {
                        conta.imprimirExtrato();
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
