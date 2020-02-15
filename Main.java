package IA;

import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		Double att=-1.0;
		Scanner sc = new Scanner(System.in);
		while(att<0.0 || att>1.0)	//on demande à l'utilisateur de saisir le résultat attendu entre 0 et 1
		{
			System.out.println("Saisir un chiffre en 0 et 1");
   			 att = sc.nextDouble();
		}
		Reseau reseau=new Reseau(att);
	}
}
