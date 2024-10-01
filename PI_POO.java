import java.util.Scanner;

// Classe base Pessoa
public class Pessoa {
    protected String nome;
    protected String cpf;

    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
    }
}

// Classe Cliente herdando de Pessoa
public class Cliente extends Pessoa {
    private String telefone;
    private String email;

    public Cliente(String nome, String cpf, String telefone, String email) {
        super(nome, cpf);
        this.telefone = telefone;
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Telefone: " + this.telefone);
        System.out.println("Email: " + this.email);
    }
}

// Classe Funcionario herdando de Pessoa
public class Funcionario extends Pessoa {
    private String cargo;

    public Funcionario(String nome, String cpf, String cargo) {
        super(nome, cpf);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Cargo: " + this.cargo);
    }
}

// Interface Agendavel para polimorfismo
public interface Agendavel {
    public void agendar();
}

// Classe AgendamentoConsulta implementando Agendavel
public class AgendamentoConsulta implements Agendavel {
    private Cliente cliente;

    public AgendamentoConsulta(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void agendar() {
        System.out.println("Consulta agendada com sucesso para o cliente " + cliente.getNome());
    }
}

// Classe AgendamentoTerapia implementando Agendavel
public class AgendamentoTerapia implements Agendavel {
    private Cliente cliente;

    public AgendamentoTerapia(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void agendar() {
        System.out.println("Sessão de terapia agendada com sucesso para o cliente " + cliente.getNome());
    }
}

// Singleton para conexão com o banco de dados
public class ConexaoBanco {
    private static ConexaoBanco instancia;

    private ConexaoBanco() {
        // Construtor privado
    }

    public static ConexaoBanco getInstancia() {
        if (instancia == null) {
            instancia = new ConexaoBanco();
        }
        return instancia;
    }

    public void conectar() {
        System.out.println("Conectado ao banco de dados.");
    }
}

// Classe principal para testar o sistema com cadastro de clientes
public class SistemaAgendamento {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando instância da conexão com o banco de dados (Singleton)
        ConexaoBanco conexao = ConexaoBanco.getInstancia();
        conexao.conectar();

        // Cadastro de cliente pelo usuário
        System.out.println("== Cadastro de Cliente ==");

        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();

        // Criando cliente com os dados fornecidos
        Cliente cliente = new Cliente(nome, cpf, telefone, email);

        // Exibindo informações do cliente cadastrado
        System.out.println("\nCliente cadastrado com sucesso!");
        cliente.exibirInformacoes();

        // Escolher tipo de agendamento
        System.out.println("\nEscolha o tipo de agendamento:");
        System.out.println("1. Consulta");
        System.out.println("2. Sessão de Terapia");
        int opcao = scanner.nextInt();

        Agendavel agendamento;

        if (opcao == 1) {
            agendamento = new AgendamentoConsulta(cliente);
        } else if (opcao == 2) {
            agendamento = new AgendamentoTerapia(cliente);
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        // Realizar o agendamento
        System.out.println("\nAgendamento:");
        agendamento.agendar();

        scanner.close();
    }
}
