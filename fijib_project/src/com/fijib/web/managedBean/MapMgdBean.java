package com.fijib.web.managedBean;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
@ManagedBean
public class MapMgdBean {
 		private MapModel model = new DefaultMapModel();
 		
		public  MapMgdBean() {
		 
		model.addOverlay(new Marker(new LatLng(31.61794 ,-8.01318), "agence Marrakech"));
		model.addOverlay(new Marker(new LatLng(30.42775 ,-9.59811), "agence Agadir"));
		model.addOverlay(new Marker(new LatLng(27.12529 ,-13.16250), "agence Layoune"));
		model.addOverlay(new Marker(new LatLng(23.72211 ,-15.93474), "agence Dakhla"));
		model.addOverlay(new Marker(new LatLng(33.53333 ,	-7.58333), "agence casablance"));
		model.addOverlay(new Marker(new LatLng(35.76667 ,	-5.80000), "agence tanger"));
		model.addOverlay(new Marker(new LatLng(32.33944 	,-6.36083), "agence Beni Mellal"));
		model.addOverlay(new Marker(new LatLng(32.88333 ,	-6.90000), "agence Marrakech"));
		model.addOverlay(new Marker(new LatLng(34.03333 ,	-5.00000), "agence Fez"));
		model.addOverlay(new Marker(new LatLng(33.89500 ,	-5.55472), "agence Meknes"));
		model.addOverlay(new Marker(new LatLng(33.97159 ,	-6.84981), "agence Rabat"));
		model.addOverlay(new Marker(new LatLng(33.23333 	,-8.50000), "agence elJadida"));
		model.addOverlay(new Marker(new LatLng(31.50849 	,-9.75950), "agence Saouira"));
		model.addOverlay(new Marker(new LatLng(28.43804 	,-11.09874), "agence Tantan"));
  		}
		public MapModel getModel() { return this.model; }
		
}
