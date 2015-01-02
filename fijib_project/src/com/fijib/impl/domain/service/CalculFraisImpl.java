package com.fijib.impl.domain.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fijib.itf.domain.service.CalculFrais;

@Service("calculFrais")
@Transactional
public class CalculFraisImpl implements CalculFrais, Serializable {

	@Override
	public double calculerFrais(double montantEuro, String TypePaiement) {
		double fraisEuro = -1 ; // -1 Erreur
		
		if(montantEuro > 0){
			if(TypePaiement.equalsIgnoreCase("PAYPAL")){
				fraisEuro = this.calculerFraisPayPal(montantEuro) + calculerfraisFijibEuro() ;
			}
			else if(TypePaiement.equalsIgnoreCase("VIREMENT")){
				fraisEuro = calculerfraisFijibEuro() ;
			}
		}
		return fraisEuro;
	}
	
	public double calculerFraisPayPal(double montantEuro){
		
		double fraisPayPalEuro = 0;
		
		if(montantEuro>0 && montantEuro<=2500){
			fraisPayPalEuro = (montantEuro*3.4)/100 + 0.25 ;
		}
		else if(montantEuro>2500 && montantEuro<=10000){
			fraisPayPalEuro = (montantEuro*2)/100 + 0.25 ;
		}
		else if(montantEuro>10000 && montantEuro<=50000){
			fraisPayPalEuro = (montantEuro*1.8)/100 + 0.25 ;
		}
		else if(montantEuro>50000 && montantEuro<=100000){
			fraisPayPalEuro = (montantEuro*1.6)/100 + 0.25 ;
		}
		else if(montantEuro>100000){
			fraisPayPalEuro = (montantEuro*1.4)/100 + 0.25 ;
		}
		else{
			fraisPayPalEuro = 0 ;
		}
		
		return fraisPayPalEuro;
	}
	
	public double calculerfraisFijibEuro(){
		return 10;
	}
}
