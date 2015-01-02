package com.fijib.itf.domain.service;

public interface CalculFrais {
	public double calculerFrais(double montantEuro, String TypePaiement);
	public double calculerFraisPayPal(double montantEuro);
}
