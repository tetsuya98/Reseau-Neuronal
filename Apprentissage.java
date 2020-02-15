package IA;

import java.util.*;
import java.lang.*;

public class Apprentissage
{
	Double attendu;	//résultat attendu
	Neurone[][] reseau;	//réseau
	Double coeff;	//coefficient d'apprentissage
	Double[] h = {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
	
	public Apprentissage(Double p_attendu, Neurone[][] p_reseau, Double p_coeff) {
		this.attendu = p_attendu;
		this.coeff = p_coeff;
		this.reseau = p_reseau;
		
		double[] tmp; int l = 0; double[] tmpPoids;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < reseau[i].length; j++) {	//pour chaque neurone on calcule h : la somme des entrées * le poids
				tmp = reseau[i][j].getEntrees();
				tmpPoids = reseau[i][j].getPoids();
				for (int k = 0; k < tmp.length; k++) {
					h[l] = h[l] + tmp[k] * tmpPoids[k];
				}
				l = l + 1;
			}
		}
	}
	
	public Neurone[][] batch() {
		reseau[2][0].setErr(attendu - reseau[2][0].getSortie());  //calcul de l'erreur sur le dernier neurone
		int k = 6;
		Double sum;
		Neurone next[];

		for (int i = 1; i >= 0; i--) {
			for (int j = reseau[i].length-1; j >= 0; j--) {	//sur chaque neurone sauf le dernier
				sum=0.0;
				next=reseau[i][j].m_nextNeurone;
				for(int n=0; n<reseau[i][j].m_nextNeurone.length; n++)	//pour chaque neurone suivant le neurone actuel
				{
					for(int l=0; l<next[n].getPoids().length; l++)	//on calcule l'erreur du neurone suivant * poids sortie
						sum=sum+next[n].getErr()*next[n].getPoids(l);
				}
				reseau[i][j].setErr((((Math.exp(-h[k]))/Math.pow(1+Math.exp(-h[k]),2)))*sum);	//on met à jour l'erreur du neuronne actuel
				k = k - 1;	
			}
		} 

		double[] entree;

		for (int i = 0; i <= 2; i++) {	
			for (int j = 0; j < reseau[i].length; j++) {	//pour chaque neurone
				entree = reseau[i][j].getEntrees();
				for (int l = 0; l < entree.length; l++){	//pour chaque entrées
					reseau[i][j].setPoids(l, reseau[i][j].getPoids(l)+coeff*reseau[i][j].getErr()*entree[l]); //on met à jour le poids
				}
			}
		}

		return reseau;
	}
}
