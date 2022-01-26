package desafio.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import desafio.model.Dados;

public class Arquivo {
	
public String tratarArquivos() throws Exception, FileNotFoundException {
	try {
	
		
		String caminhoEntrada = "dados\\in";
		String caminhoSaida = "dados\\out";

		Dados dados = new Dados();
		//-------------------------------------------------
		// AKI IMPLEMENTAR A LEITURA DE ARQUIVO DO DIRETORIO
		//-------------------------------------------------
		criarPastas(caminhoEntrada,caminhoSaida);
		ArrayList arquivos = new ArrayList();
		File dir = new File(caminhoEntrada + "");
		if (dir.exists()){
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				
				// Get filename of file or directory
				File file = new File(caminhoEntrada + "\\"+children[i]);
				String filename = children[i];
				if( filename.toUpperCase().endsWith(".DAT")){
					try {								
					     FileReader in = new FileReader(file);
					        BufferedReader bufferDetalhe = new BufferedReader(in);        
					        ArrayList NetFileLines = new ArrayList();
					        int cont = 0;
					        String linha = null;
					        double gravaMenorValor = 0;
					        double gravaMaiorValor = 0;
					        double salvaValor = 0;
					        while((linha = bufferDetalhe.readLine()) != null) {  
					        	Map<String,Double> example = new HashMap<String,Double>();
					        	String linhaQuebrada[] = linha.split(";");
					        	if(linhaQuebrada[0].equals("001")){
					        dados.setQuantidadeVendedores(dados.getQuantidadeVendedores() + 1);
					        		}else if(linhaQuebrada[0].equals("002")){
					        			dados.setQuantidadeClientes(dados.getQuantidadeClientes() + 1);
					        				}else if(linhaQuebrada[0].equals("003")){
					        						if((Double.valueOf(linhaQuebrada[3]) * Double.valueOf(linhaQuebrada[4])) > gravaMaiorValor){
					        							dados.setIdVendaMaisAlta( linhaQuebrada[1]);
					        							gravaMaiorValor = (Double.valueOf(linhaQuebrada[3]) * Double.valueOf(linhaQuebrada[4]));
					        						}
					        							if(Double.valueOf(linhaQuebrada[4]) < gravaMenorValor || gravaMenorValor == 0){
					        								
					        								if(!example.containsKey(linhaQuebrada[5]))
					        								        example.put( linhaQuebrada[5], new Double((Double.valueOf(linhaQuebrada[3]) * Double.valueOf(linhaQuebrada[4]))));
					        								        else {
					        								        salvaValor = example.get(linhaQuebrada[5]);
					        								        salvaValor = salvaValor + (Double.valueOf(linhaQuebrada[3]) * Double.valueOf(linhaQuebrada[4]));
					        								        example.replace(linhaQuebrada[5], salvaValor);
					        								        }
					        								        salvaValor = 0;
					        								        for(int h=0; h<example.size(); h++) {
					        								         
					        								            if(gravaMenorValor < example.get(linhaQuebrada[5]))
					        								            	dados.setNomeVendedorMenosVendeu(linhaQuebrada[5]);
					        								            	gravaMenorValor = example.get(linhaQuebrada[5]);
					        								            }
					        								        }
					        							}
					        								
					        						
					        
					        }
					        in.close();
					        criaArquivoSumario(caminhoSaida,dados.getQuantidadeClientes(), dados.getQuantidadeVendedores(), dados.getIdVendaMaisAlta(),dados.getNomeVendedorMenosVendeu(),children[i]);
							
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} 
			}
			
		
		return "Arquivos processados com sucesso.";
	}catch(Exception e) {
		System.out.println(e);
		return "Erro ao processar arquivos.";
	}finally{
	}
}


	 /**************************************************
     * Cria as pastas para entrada e saída de arquivos.
     ***************************************************/
    public void criarPastas(String caminhoEntrada, String caminhoSaida) {
        try {
            if (!Paths.get(caminhoEntrada).toFile().exists()) {
                Files.createDirectories(Paths.get(caminhoEntrada));
                System.out.println(" Diretório de entrada criado com sucesso.");
            }
            if (!Paths.get(caminhoSaida).toFile().exists()) {
                Files.createDirectories(Paths.get(caminhoSaida));
                System.out.println(" Diretório de saída criado com sucesso.");
            }
        } catch (final IOException e) {
           System.out.println(" Não foi possivel criar os diretórios.");
        }

    }
    protected void criaArquivoSumario(String caminho, int quantidadeCLientes, int quantidadeVendedores, String idVendaMaiorValor, String nomeMenorVenda, String nomeArquivo) {
        Path pathArquivoDestino = Paths.get(caminho).resolve(nomeArquivo + ".proc");
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathArquivoDestino.toFile()))) {
                bufferedWriter.write("1. Quantidade de Clientes: " + quantidadeCLientes);
                bufferedWriter.newLine();
                bufferedWriter.write("2. Quantidade de Vendedores: " + quantidadeVendedores);
                bufferedWriter.newLine();
                bufferedWriter.write("3. ID da Venda de valor mais alto: " + idVendaMaiorValor);
                bufferedWriter.newLine();
                bufferedWriter.write("4. Nome do Vendedor que menos vendeu: " + nomeMenorVenda);
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    
}
