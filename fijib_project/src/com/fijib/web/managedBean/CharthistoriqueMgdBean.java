 package com.fijib.web.managedBean;
import javax.annotation.PostConstruct;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.persistence.PostUpdate;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fijib.impl.domain.service.EnvoiMetierImpl;
import com.fijib.itf.domain.service.IEnvoiMetier;
import com.fijib.itf.domain.service.IRetraitMetier;
import com.fijib.itf.persistence.dao.IEnvoi;
 
@Scope("session")
@Component 
@ManagedBean
public class CharthistoriqueMgdBean implements Serializable {
  

    @Autowired
    private IEnvoiMetier envoiMetier ;
    @Autowired
    private IRetraitMetier iRetraitMetier;
    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
    private int[] envs;
    private int[] retraits;
    @PostConstruct
    public void init() {
        createBarModels();
    }
 
    public BarChartModel getBarModel() {
        return barModel;
    }
     
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }
 
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        envs=envoiMetier.historiqueEnvoi();
        ChartSeries series1 = new ChartSeries();
        series1.setLabel("Envois");
        series1.set("Janvier", envs[0]);
	      series1.set("février", envs[1]);
	      series1.set("Mars", envs[2]);
	      series1.set("Avril", envs[3]);
	      series1.set("Mai", envs[4]);
	      series1.set("Juin", envs[5]);
	      series1.set("Juillet", envs[6]);
	      series1.set("Aout", envs[7]);
	      series1.set("septembre", envs[8]);
	      series1.set("Octobre", envs[9]);
	      series1.set("Novembre", envs[10]);
	      series1.set("Decembre", envs[11]);
	      series1.setLabel("Envois");
	      
	      ChartSeries series2 = new ChartSeries();
	      series2.setLabel("Retrait");
	      retraits=iRetraitMetier.historiqueRetrait();
	      
	      series2.set("Janvier", retraits[0]);
	      series2.set("février", retraits[1]);
	      series2.set("Mars", retraits[2]);
	      series2.set("Avril", retraits[3]);
	      series2.set("Mai", retraits[4]);
	      series2.set("Juin", retraits[5]);
	      series2.set("Juillet", retraits[6]);
	      series2.set("Aout", retraits[7]);
	      series2.set("septembre", retraits[8]);
	      series2.set("Octobre", retraits[9]);
	      series2.set("Novembre", retraits[10]);
	      series2.set("Decembre", retraits[11]);

        
  
      
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
     
    private void createBarModels() {
        createBarModel();createHorizontalBarModel();
     }
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Bar statistics");
        barModel.setLegendPosition("ne");
        barModel.setZoom(true);
  
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setTickAngle(-50);
        xAxis.setLabel("Les mois");
          
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Valeurs");
        yAxis.setMin(0);
        yAxis.setMax(50);
    }
    
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
 
        envs=envoiMetier.historiqueEnvoi();
        ChartSeries series1 = new ChartSeries();
        series1.setLabel("Envois");
        series1.set("Janvier", envs[0]);
	      series1.set("février", envs[1]);
	      series1.set("Mars", envs[2]);
	      series1.set("Avril", envs[3]);
	      series1.set("Mai", envs[4]);
	      series1.set("Juin", envs[5]);
	      series1.set("Juillet", envs[6]);
	      series1.set("Aout", envs[7]);
	      series1.set("septembre", envs[8]);
	      series1.set("Octobre", envs[9]);
	      series1.set("Novembre", envs[10]);
	      series1.set("Decembre", envs[11]);
	      series1.setLabel("Envois");
 
	      ChartSeries series2 = new ChartSeries();
	      series2.setLabel("Retrait");
	      retraits=iRetraitMetier.historiqueRetrait();
	      
	      series2.set("Janvier", retraits[0]);
	      series2.set("février", retraits[1]);
	      series2.set("Mars", retraits[2]);
	      series2.set("Avril", retraits[3]);
	      series2.set("Mai", retraits[4]);
	      series2.set("Juin", retraits[5]);
	      series2.set("Juillet", retraits[6]);
	      series2.set("Aout", retraits[7]);
	      series2.set("septembre", retraits[8]);
	      series2.set("Octobre", retraits[9]);
	      series2.set("Novembre", retraits[10]);
	      series2.set("Decembre", retraits[11]);
 
        horizontalBarModel.addSeries(series1);
        horizontalBarModel.addSeries(series2);
         
        horizontalBarModel.setTitle("Horizontal statistics");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
        horizontalBarModel.setZoom(true);
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Valeur");
        xAxis.setTickAngle(-50);
        xAxis.setMin(0);
        xAxis.setMax(50);
         
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Les mois");       
    }
 
}