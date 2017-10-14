import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;

public class LeitorCSV {
    private static final String SEPARADOR = ",";
    private static final String ARQUIVO = "participantes.csv";

    public static void main(String[] args) {
    	
    	BufferedReader inputStream = null;
    	String linha = null;
        String[] dadosLinha;
        ArrayList<Participante> participante = new ArrayList<Participante>();

        try {
            inputStream = new BufferedReader(new FileReader(ARQUIVO));
            while ((linha = inputStream.readLine()) != null) {
        	 dadosLinha = linha.split(SEPARADOR);
        	 participante.add(new Participante(dadosLinha[0],dadosLinha[1]));
            }
            mostrarObjetosCriados(participante);
        }
        catch (IOException e) {
        	errormsg(e,true);
            
        } finally {
            if (inputStream != null) {
                try {
					inputStream.close();
				} catch (IOException e) {
		        	errormsg(e,true);
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
