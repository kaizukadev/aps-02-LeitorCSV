import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;

public class LeitorCSV {
    private static final String SEPARADOR = ",";
    private static final String ARQUIVO = "participantes.csv";

    public static void main(String[] args) {
    	
    	BufferedReader is = null;												// InputStream bufferizado
    	String linha = null;													// Linha lida
        String[] dadosLinha;													// Array com dados da linha lida
        ArrayList<Participante> participante = new ArrayList<Participante>();	// Coleção de objetos que serão lidos

        try {
            is = new BufferedReader(new FileReader(ARQUIVO));					// 'is' (InputStream) receberá leitura bufferizada  
            while ((linha = is.readLine()) != null) {							// Entrada linha a linha
        	 dadosLinha = linha.split(SEPARADOR);								// Array com os dados da linha
        	 participante.add(new Participante(dadosLinha[0],dadosLinha[1]));	// Adicionado novo Participante na coleção (arraylist)
            }
            mostrarObjetosCriados(participante);								// Apresentação dos objetos criados
        }
        catch (IOException e) {
        	errormsg(e,true);
            
        } finally {
            if (is != null) {
                try {
					is.close();													// Fechamento do Stream. Usado finally para garantir
				} catch (IOException e) {										//  o fechamento tanto quando a leitura ocorrer quanto
		        	errormsg(e,true);											//  se houver erro de leitura.
				}
            }
        }
    }

	private static void mostrarObjetosCriados(ArrayList<Participante> participante) {
		msg("*** LEITOR DE ARQUIVO CSV ***\n");
		msg("*** ARQUIVO DE ENTRADA: [" + ARQUIVO + "] ***\n\n");
		msg("Lista de Participantes: \n\n");
		int np = participante.size();
		for ( int k=0 ; k<np ; k++) {
			Participante p = participante.get(k);
			System.out.printf ("%s%02d]\n","[Participante #", k+1);
			msg("  Nome.: " + p.getNome() + "\n");
			msg("  Email: " + p.getEmail()+ "\n\n");
		}
       msg("(Total de Registros: " + np + ")");
}

	public static void msg(String msg) {
		System.out.print(msg);
	}

	private static void errormsg(Exception e, boolean fim ) {
    	msg(">> Mensagem de ERRO!!\n" + e);
    	if (fim) {
        	msg("\n\n***  Programa será finalizado!");
        	System.exit(0);
    	}
	}
}
