package IA;

import java.util.*;
import java.lang.*;

public class Reseau
{

	private Neurone[][] reseau = new Neurone[3][];
	private Apprentissage appr;	
	private Double attendu;
	
	public Reseau(Double p_attendu)
	{
		this.attendu = p_attendu;	//résultat attendu
		int index=0;
		double[] poids={0.1};	//tableau de poids pour la couche 1
		reseau[0]=new Neurone[2];
		reseau[1]=new Neurone[5];
		reseau[2]=new Neurone[1];
		
		reseau[0][0] = new Neurone(index, poids, 0);	//création couche 1 neurone 1 
		index++;
		
		reseau[0][1] = new Neurone(index, poids, 0);	//création couche 1 neurone 2
		index++;
		
		double[] poids2={0.1, 0.1};	//tableau de poids pour la couche 2
		
		for(int i=0;i<5;i++)	
		{
			reseau[1][i]=new Neurone(index, poids2, 1); //création couche 2 neurone i
			index++;
		}
		
		double[] poids3={0.1, 0.1, 0.1, 0.1, 0.1};	//tableau de poids pour la couche 3
		
		reseau[2][0] = new Neurone(index, poids3,2); //création couche 3 neurone 1
		

		reseau[0][0].setNeurone(reseau[1]);	//on met a jour les neuronnes suivant de la couche 1
		reseau[0][1].setNeurone(reseau[1]);
		
		for(int i=0;i<5;i++)
		{
			reseau[1][i].setNeurone(reseau[2]);	//on met a jour les neuronnes suivant de la couche 2
		}


		Neurone[] n=new Neurone[1];
		n[0]=null;
		reseau[2][0].setNeurone(n);	//on met a jour les neuronnes suivant de la couche 3
		
		reseau[0][0].setEntree(0.5);//on met à jour les entrées de la premiére couche
		reseau[0][1].setEntree(0.7);

		while (reseau[2][0].getSortie()<this.attendu-0.1 || reseau[2][0].getSortie()>this.attendu+0.1)	//tant que la sortie du dernier neurone n'est pas environ égal au résultat attendu
		{
			appr = new Apprentissage(this.attendu, reseau, 0.1); //initialisation de l'apprentissage
			reseau = appr.batch();	//appel de l'apprentissage batch
			reseau[0][0].setEntree(0.5);	//on met à jour les entrées de la premiére couche
			reseau[0][1].setEntree(0.7);
		}

		//affichage du résultat
		System.out.println("Le resultat attendu est " + this.attendu + " et le resultat trouve est " + reseau[2][0].getSortie() + "\n");

		double p[];

		//affichage des poids
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j < reseau[i].length; j++) {
				System.out.println("\n couche " + i + " neurone " + j);
				p=reseau[i][j].getPoids();
				for(int k = 0; k<p.length; k++){
					System.out.print("| " + p[k] + " |");
				}
				System.out.println("");
			}
		}
	}
		
}
