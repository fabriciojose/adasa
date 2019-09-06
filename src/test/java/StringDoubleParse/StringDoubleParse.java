package StringDoubleParse;

import java.text.DecimalFormat;
import java.text.ParseException;

import entidades.Subterranea;

public class StringDoubleParse {

	public static void main(String[] args) {


		DecimalFormat df = new DecimalFormat("#,##0.00");  
		
		
		String str [] = {	"5.000,55", "5000,00", ""};
	
	
		for (String s : str ) {
			System.out.println(s);
			
			try {
				System.out.println("parse " + df.parseObject(s).toString());
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			//System.out.println(Double.parseDouble(s));
		}

	}
	
	/*
	
		// formatar 1000.50 para 1.000,50 e retirar zeros irrelevantes como ,00 - 15.00 fica 15
				try{	listQuantidadesCadastradas.add(	df.format(
*
*
*
*/
}
