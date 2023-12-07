package br.com.dias.matheus;

public class ClienteDTO {
	
	// AQ vai ser o seguinte vai validar se o cara é pf ou pj pela flag isFisica
	public boolean validateCPFCNPJ(String documento, int isFisica) {
		if(isFisica)
		{
			if(documento != "" && documento.matches("//{11}//")) {
				
			
			}
		} else {
			if(documento != "" && documento.matches("//{14}//")) {
				
			
			}
		}
		

}
