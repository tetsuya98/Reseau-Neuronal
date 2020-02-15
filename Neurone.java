package IA;

import java.util.*;
import java.lang.*;


public class Neurone
{
	int m_index,m_cpt=0, m_couche;
	double m_entrees[];
	double m_poids[];
	Neurone m_nextNeurone[];
	double sortie=0;
	double err;
	
	public Neurone(int p_index, double[] p_poids, int p_couche)
	{
		this.m_index=p_index;
		this.m_poids=p_poids;
		this.m_entrees = new double[this.m_poids.length];
		this.m_couche = p_couche;
	}
	
	public void setEntree(double p_entree)	//Fonction qui remplit le tableau d'entrée
	{
		if(m_cpt<m_entrees.length)	//Si le tableau d'entrée n'est pas plein on le rempli
		{
			this.m_entrees[m_cpt] = p_entree;
			System.out.println("index " + m_index + " entree " + this.m_entrees[m_cpt] + " poids " + this.m_poids[m_cpt]);
			m_cpt+=1;
		}
		if(m_entrees.length==m_cpt)	//Si il est plein on lance la fonction calcul sortie
		{
			calculSortie();
			m_cpt=0;
		}
	} 
	
	public double[] getEntrees() {	//Fonction qui retourne le tableau d'entrée
		return this.m_entrees;
	}
	
	public double getSortie() {		//Fonction qui retourne la sortie
		return this.sortie;
	}
	
	public void setErr(double p_err) {	//Fonction qui met à jour l'erreur
		this.err = p_err;
	}
	
	public double getErr() {	//Fonction qui retourne l'erreur
		return this.err;
	}

	public double[] getPoids() {	//Fonction qui retourne le tableau de poids
		return this.m_poids;
	}

	public double getPoids(int i) {	//Fonction qui retourne le poids en position i dans le tableau
		return this.m_poids[i];
	}

	public void setPoids(int k, double w) {	//Fonction qui met à jour le poids k dans le tableau avec la valeur w
		this.m_poids[k] = w;
	}
	
	public void setPoids(double[] p_poids)	//Fonction qui met à jour le tableau de poids
	{
		this.m_poids = p_poids;
	}

	public void setNeurone(Neurone[] p_nextNeurone) //Fonction qui met à jour le tableau de neurones suivantes
	{
		this.m_nextNeurone=p_nextNeurone;
	}
	
	public void calculSortie()	//Fonction qui calcul la sortie
	{
		for(int i=0;i<m_entrees.length;i++)	//on fait la somme des entrées * le poids
		{
			this.sortie=this.sortie + this.m_entrees[i] * this.m_poids[i];
		}
		this.sortie=(1/(1+Math.exp(-this.sortie)));	//on calcule la sortie en utilisant la fonction sigmoide
		System.out.println("index " + m_index + " sortie " + this.sortie + "\n");
		try
		{
			for(int i=0;i<m_nextNeurone.length;i++)		
			{
				m_nextNeurone[i].setEntree(this.sortie);	//on met à jour les entrées du neurone suivant avec la sortie calculée
			}
		}catch(Exception e){}
	}
	
}
