package desafio.model;

public class Dados {

	
	private int quantidadeClientes;
	
	private int quantidadeVendedores;
	
	private String idVendaMaisAlta;
	
	private String nomeVendedorMenosVendeu;


	public Dados(int quantidadeClientes, int quantidadeVendedores,
			String idVendaMaisAlta, String nomeVendedorMenosVendeu) {
		super();
		this.quantidadeClientes = quantidadeClientes;
		this.quantidadeVendedores = quantidadeVendedores;
		this.idVendaMaisAlta = idVendaMaisAlta;
		this.nomeVendedorMenosVendeu = nomeVendedorMenosVendeu;
	}

	public int getQuantidadeClientes() {
		return quantidadeClientes;
	}

	public void setQuantidadeClientes(int quantidadeClientes) {
		this.quantidadeClientes = quantidadeClientes;
	}

	public Dados() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getQuantidadeVendedores() {
		return quantidadeVendedores;
	}

	public void setQuantidadeVendedores(int quantidadeVendedores) {
		this.quantidadeVendedores = quantidadeVendedores;
	}

	public String getIdVendaMaisAlta() {
		return idVendaMaisAlta;
	}

	public void setIdVendaMaisAlta(String idVendaMaisAlta) {
		this.idVendaMaisAlta = idVendaMaisAlta;
	}

	public String getNomeVendedorMenosVendeu() {
		return nomeVendedorMenosVendeu;
	}

	public void setNomeVendedorMenosVendeu(String nomeVendedorMenosVendeu) {
		this.nomeVendedorMenosVendeu = nomeVendedorMenosVendeu;
	}


	
	
	
}
