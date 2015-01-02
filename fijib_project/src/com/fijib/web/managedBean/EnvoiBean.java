package com.fijib.web.managedBean;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

  import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.fijib.domain.dto.DtoClient;
import com.fijib.domain.dto.DtoEnvoiClient;
import com.fijib.domain.dto.DtoEnvoiNs;
import com.fijib.domain.dto.DtoInfo;
import com.fijib.domain.dto.DtoInscriptionClient;
import com.fijib.domain.dto.DtoRecu;
import com.fijib.impl.domain.service.IUtilisateurMetier;
import com.fijib.impl.persistence.entity.Client;
import com.fijib.impl.persistence.entity.Utilisateur;
import com.fijib.itf.domain.service.CalculFrais;
import com.fijib.itf.domain.service.EnvoiCodeGenerateur;
import com.fijib.itf.domain.service.EnvoiNsMetier;
import com.fijib.itf.domain.service.IClientMetier;
import com.fijib.itf.domain.service.InscriptionMetier;
import com.fijib.itf.domain.service.RechercheService;
import com.liferay.util.servlet.SessionParameters;

@ManagedBean(name = "envoiBean")
@RequestScoped
@Component
public class EnvoiBean implements Serializable {

	@Autowired
	private CalculFrais calculateurFrais;
	@Autowired
	private IUtilisateurMetier iUtilisateurMetier;
	@Autowired
	private EnvoiCodeGenerateur generCodeMetier;
	@Autowired
	private EnvoiNsMetier envoiNsMetier;
	@Autowired
	private InscriptionMetier inscriptionBenefMetier;
	@Autowired
	private RechercheService rechercheService;
	@Autowired
	private IClientMetier clientMetier;
	
//	@ManagedProperty(value = "#{authenticationManagerX}")
//	private AuthenticationManager authenticationManagerX = null;

	private String typeTransfert = "VIDE";
	private String typeEnvoi = "VIDE";
	private DtoEnvoiNs infoEnvoiNs;
	private DtoInscriptionClient infoBenef;
	private String titre;
	private DtoClient client;
	private String inputRecherche;
	private String destination;
	private Client thisClient;
	private DtoRecu recu;
	private int toggle = -1;
	private String sos = "VIDE";
	private DtoEnvoiClient envoiDto;
	private String responsePayPal = "Fail";
	private Map<String, String> RepParam = null;
	
	private DtoInfo infoUser;
	
	
	public void initEnvoi() throws IOException{
		if (infoEnvoiNs == null || infoEnvoiNs.getMontant() == 0
				|| infoEnvoiNs.getCodeSourceEnvoi() == null) {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("accueilClient.xhtml");
		}
	}
	
	public void initType() throws IOException{
		if (typeEnvoi.equalsIgnoreCase("VIDE")) {
			System.out.println(this.typeEnvoi);
			FacesContext.getCurrentInstance().getExternalContext().redirect("/fijib_project/securePages/ClientPages/accueilClient.xhtml");
		}
	}
	
	public String envoiArgent() {
		
		if(toggle == -1){

			infoEnvoiNs.setCinBenef(infoBenef.getCin());
			
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
	
			Utilisateur user = iUtilisateurMetier.findUserByName(auth.getName());
			
			thisClient = clientMetier.getClient(user.getCin());
			
			thisClient.setUtilisateur(user);
	
			infoEnvoiNs.setCinEnv(thisClient.getCin());
			
			String codeEnvoiNs = generCodeMetier.genererCodeEnvoi(
					infoEnvoiNs.getCinBenef(), infoBenef.getFirstName(),
					infoBenef.getLastName());
			
			infoEnvoiNs.setIdEnvoiNs(codeEnvoiNs);
	
			envoiNsMetier.envoiNsVirement(infoEnvoiNs);
	
			inscriptionBenefMetier.inscrire(infoBenef, -1);
			
			Date dateLe = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
			String dateLE = formater.format(dateLe);
			
			SimpleDateFormat formater1 = new SimpleDateFormat("dd/MM/yyyy 'à' hh:mm:ss");
			String dateTransfert = formater1.format(dateLe);
			
			recu = new DtoRecu(infoEnvoiNs.getIdEnvoiNs(), 
					dateLE, dateTransfert, 
					thisClient.getFirstName()+" "+thisClient.getLastName(), infoBenef.getCin(),
					thisClient.getCin(), typeTransfert, infoEnvoiNs.getCodeSourceEnvoi(),
					infoEnvoiNs.getMontant(), infoEnvoiNs.getFrais(), infoEnvoiNs.getMontant()+infoEnvoiNs.getFrais());
			
 
			toggle = 0;
			
			initInfoEnvoiNs();
			initInfoClientNs();
			initEnvoiDto();
			initClient();
			
			return "recuPage";
		}
		
		return null;
	}
	
	public void initToggle(){
		this.toggle = -1;
	}
	
	
	
	public String envoiArgentInscrit(){
		
		if(toggle == -1){
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
	
			Utilisateur user = iUtilisateurMetier.findUserByName(auth.getName());
			
			thisClient = clientMetier.getClient(user.getCin());
			
			thisClient.setUtilisateur(user);
			
			if(client!=null){
				
				envoiDto.setMontant(infoEnvoiNs.getMontant());
				envoiDto.setFrais(infoEnvoiNs.getFrais());
				envoiDto.setCodeSourceEnvoi(infoEnvoiNs.getCodeSourceEnvoi());
				
				envoiDto.setEnvoyeur(thisClient);
				
				Client beneficiare = new Client(client.getAdresseClient(),
						client.getCodePostale(), client.getDateNaissance(),
						client.getFirstName(), client.getLastName(), 
						client.getNumCompteBanc(), client.getTel(),
						client.getVille());
				beneficiare.setCin(client.getCin());
				
				Utilisateur userBenef = iUtilisateurMetier.findUser(beneficiare.getCin());
				beneficiare.setUtilisateur(userBenef);
				
				envoiDto.setBeneficiare(beneficiare);
				
				if(destination.equalsIgnoreCase("personne")){
					envoiDto.setAunePersonne(true);
				}
				else if(destination.equalsIgnoreCase("compte") && beneficiare.getNumCompteBanc()!=null){
					envoiDto.setAunePersonne(false);
				}
				else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
							"Votre déstinataire n'a pas de compte bancaire !"));
				}
				
				if(typeTransfert.equalsIgnoreCase("VIREMENT")){
					envoiDto.setPaypal(false);
				}
				else if(typeTransfert.equalsIgnoreCase("PAYPAL")){
					envoiDto.setPaypal(true);
				}
				
				
				String codeEnvoi = generCodeMetier.genererCodeEnvoi(
						envoiDto.getBeneficiare().getCin(), 
						envoiDto.getBeneficiare().getFirstName(),
						envoiDto.getBeneficiare().getLastName());
				
				envoiDto.setEnvoiId(codeEnvoi);
				
				envoiNsMetier.envoiArgentBenf(envoiDto);
				
				
				Date dateLe = new Date();
				SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
				String dateLE = formater.format(dateLe);
				
				
				
				SimpleDateFormat formater1 = new SimpleDateFormat("dd/MM/yyyy 'à' hh:mm:ss");
				String dateTransfert = formater1.format(dateLe);
				
				recu = new DtoRecu(envoiDto.getEnvoiId(),
						dateLE, dateTransfert, 
						thisClient.getFirstName()+" "+thisClient.getLastName(), beneficiare.getCin(),
						thisClient.getCin(), typeTransfert, envoiDto.getCodeSourceEnvoi(),
						envoiDto.getMontant(), envoiDto.getFrais(), envoiDto.getMontant()+envoiDto.getFrais());
				
				toggle = 0;
				
				//createRecuPDF();
				
				initInfoEnvoiNs();
				initInfoClientNs();
				initEnvoiDto();
				initClient();
				
				return "recuPage";
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
						"bénéficiaire non sélectionné !"));
			}
		}
		return null;
	}
	
	public void createRecuPDF() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		String url = "http://localhost:8080/fijib_project/recu.xhtml";
		try {

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(url);
			renderer.layout();
			HttpServletResponse response = (HttpServletResponse) externalContext
					.getResponse();
			response.reset();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "C://user//first.pdf");
			OutputStream browserStream = response.getOutputStream();
			renderer.createPDF(browserStream);
			browserStream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		facesContext.responseComplete();
	}
	
	public void benefIsExist(){
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		Utilisateur user = iUtilisateurMetier.findUserByName(auth.getName());
		
		thisClient = clientMetier.getClient(user.getCin());
		
		thisClient.setUtilisateur(user);
		
		List<DtoClient> cke = rechercheService.getListRechercheCin(infoBenef.getCin());
		
		if(cke.size() != 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",
					"Ce Bénéficiare est déja existant !"));
			
			infoBenef.setCin(null);
			
			RequestContext.getCurrentInstance().update("cinBenef");
		}
		else if(infoBenef.getCin().equalsIgnoreCase(thisClient.getCin())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Vous ne pouvez pas envoyer l'argent à vous même !"));
		}
	}
	
	public String retour() {
		if (typeTransfert.equalsIgnoreCase("VIREMENT")) {
			return "/PayPal/virementPage";
		} else if (typeTransfert.equalsIgnoreCase("PAYPAL")) {
			return null;
		} else
			return null;
	}

	public void settTitre() {
		if (typeEnvoi.equalsIgnoreCase("ENVOI_NON_INSCRIT")) {
			titre = "Envoi de l'argent à une personne non inscrite";
		}
		else if(typeEnvoi.equalsIgnoreCase("ENVOI_INSCRIT")){
			titre = "Envoi de l'argent à une personne inscrite";
		}
	}

	public void selectionner() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", false);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 500);
		options.put("contentWidth", 1000);
		
 		RequestContext.getCurrentInstance().openDialog("listePrefere", options,
				null);
	}
	
	public void recherche(){
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		
		Utilisateur user = iUtilisateurMetier.findUserByName(auth.getName());
		
		if(this.inputRecherche.length() != 0 && !this.inputRecherche.equalsIgnoreCase(user.getCin())){
			List<DtoClient> clients = rechercheService.getListRechercheCin(inputRecherche);
			if(clients.size() != 0){
				client = clients.get(0);
				this.inputRecherche = "";
				
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
						"Le client "+this.inputRecherche+" n'existe pas !"));
				this.inputRecherche = "";
			}
		}
	}
	
	public void onClientChosen(SelectEvent event) {
		client = (DtoClient) event.getObject();
		envoiDto.setCin_beneficaire(client.getCin());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Client Selected", "Id:" + client.getCin());
		this.inputRecherche = "";
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void selectType() throws IOException{
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		
		String typePai = params.get("typePai");
		
		if(typePai != null){
			if (typePai.equalsIgnoreCase("PAYPAL")){
				this.typeTransfert = "PAYPAL";
				infoEnvoiNs.setPaypal(true);
			}
			else if (typePai.equalsIgnoreCase("VIREMENT")){
				this.typeTransfert = "VIREMENT";
				infoEnvoiNs.setPaypal(false);
			}
			else{
				FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/fijib_project/securePages/ClientPages/accueilClient.xhtml");
			}
		}
		
		if(params.get("typeEnvoi") != null){
			this.typeEnvoi = params.get("typeEnvoi");
		}
	}
	
	public void setInfoBare(){
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		
		Utilisateur user = iUtilisateurMetier.findUserByName(auth.getName());
		
		if(auth.getAuthorities().toString().equalsIgnoreCase("[ROLE_ADMIN]")){
			infoUser.setCompte("Administrateur");
		}
		else if(auth.getAuthorities().toString().equalsIgnoreCase("[ROLE_CLIENT]")){
			
			infoUser.setCompte("Client");
		}
		else if(auth.getAuthorities().toString().equalsIgnoreCase("[ROLE_AGENT]")){
			infoUser.setCompte("Agent");
		}
		
		Client client = clientMetier.getClient(user.getCin());
		
		infoUser.setNomPrenom(client.getFirstName()+" "+client.getLastName());
	}

	public String setVirement() {
		if(typeEnvoi.equalsIgnoreCase("ENVOI_NON_INSCRIT")){
			initInfoClientNs();
			return "/securePages/ClientPages/envoiNS";
		}
		else if(typeEnvoi.equalsIgnoreCase("ENVOI_INSCRIT")){
			initInfoClientNs();
			return "/securePages/ClientPages/EnvoiInscrit";
		}
		return null;
	}
	
	public String setPayPalL() throws IOException{
		
		
			
			if(RepParam.get("ACK").equalsIgnoreCase("Success")){
				
				if (typeEnvoi.equalsIgnoreCase("ENVOI_NON_INSCRIT")) {
					initInfoClientNs();
					FacesContext.getCurrentInstance().getExternalContext().redirect("/fijib_project/securePages/ClientPages/envoiNS.xhtml");
				} 
				else if (typeEnvoi.equalsIgnoreCase("ENVOI_INSCRIT")) {
					initInfoClientNs();
					FacesContext.getCurrentInstance().getExternalContext().redirect("/fijib_project/securePages/ClientPages/EnvoiInscrit.xhtml");
				}
			}
 
		
		return null;
	}

	public String goToEnvoiInscrit() {
		this.typeEnvoi = "ENVOI_INSCRIT";
		
		initInfoEnvoiNs();
		initInfoClientNs();
		initClient();
		initEnvoiDto();
		initRecu();
		
		return "/securePages/ClientPages/accEnvoi";
	}

	public String goToEnvoiNs() {
		this.typeEnvoi = "ENVOI_NON_INSCRIT";
		
		System.out.println(this.typeEnvoi);
		
		initInfoEnvoiNs();
		initInfoClientNs();
		initClient();
		initEnvoiDto();
		initRecu();
		
		return "/securePages/ClientPages/accEnvoi";
	}

	public String calculFrais() {

		this.infoEnvoiNs.setFrais(calculateurFrais.calculerFrais(
				this.infoEnvoiNs.getMontant(), this.typeTransfert));

		return null;
	}
	
	public void initEnvoiDto(){
		envoiDto.setAgentguichet(null);
		envoiDto.setAunePersonne(null);
		envoiDto.setBeneficiare(null);
		envoiDto.setCin_beneficaire(null);
		envoiDto.setCodeSourceEnvoi(null);
		envoiDto.setDateEnv(null);
		envoiDto.setEnvoiId(null);
		envoiDto.setEnvoyeur(null);
		envoiDto.setFrais(0);
		envoiDto.setMontant(0);
		envoiDto.setPaypal(false);
	}
	
	public void initClient(){
		client.setAdresseClient(null);
		client.setCin(null);
		client.setCodePostale(null);
		client.setDateNaissance(null);
		client.setEmail(null);
		client.setFirstName(null);
		client.setLastName(null);
		client.setNumCompteBanc(null);
		client.setTel(null);
		client.setUtilisateur(null);
		client.setVille(null);
	}
	
	public void initInfoEnvoiNs() {
		infoEnvoiNs.setCinBenef(null);
		infoEnvoiNs.setCinEnv(null);
		infoEnvoiNs.setCodeSourceEnvoi(null);
		infoEnvoiNs.setDateEnv(null);
		infoEnvoiNs.setFrais(0);
		infoEnvoiNs.setIdEnvoiNs(null);
		infoEnvoiNs.setMontant(0);
		infoEnvoiNs.setPaypal(false);
	}

	public void initInfoClientNs() {
		infoBenef.setCin(null);
		infoBenef.setAdresseClient(null);
		infoBenef.setCodePostale(null);
		infoBenef.setDateNaissance(null);
		infoBenef.setEmail(null);
		infoBenef.setFirstName(null);
		infoBenef.setLastName(null);
		infoBenef.setNumCompteBanc(null);
		infoBenef.setPassword(null);
		infoBenef.setTel(null);
		infoBenef.setVille(null);
	}
	
	public void initRecu(){
		recu.setCinBenef(null);
		recu.setCinEnv(null);
		recu.setCodeEnvoi(null);
		recu.setCodeVirement(null);
		recu.setDateLe(null);
		recu.setDateTransfert(null);
		recu.setFrais(0);
		recu.setIntitule(null);
		recu.setMontant(0);
		recu.setTotal(0);
		recu.setTypePaiment(null);
	}

	public EnvoiBean() {
		this.infoEnvoiNs = new DtoEnvoiNs();
		this.infoBenef = new DtoInscriptionClient();
		this.recu = new DtoRecu();
		this.envoiDto = new DtoEnvoiClient();
		this.client = new DtoClient();
		this.infoUser = new DtoInfo();
		initInfoEnvoiNs();
		initInfoClientNs();
		initClient();
		initEnvoiDto();
		initRecu();
	}
	
	
	//PAYPAL
	private String payerId;
	private String token;
	
	public void get() throws IOException {
		
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		
		this.sos = params.get("sos");
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		
		//System.out.println(auth.getName());
		
		this.payerId = params.get("PayerID");

		//System.out.println(this.payerId);

		this.token = params.get("token");

		//System.out.println(this.token);
		
		if(this.sos!= null && params.get("sos").equalsIgnoreCase("true")){
			responsePayPal = completePayment();
			RepParam = getResponseFormat(responsePayPal);
			
			System.out.println(RepParam.get("ACK"));
			
			this.infoEnvoiNs.setCodeSourceEnvoi(RepParam.get("PAYMENTINFO_0_TRANSACTIONID"));
			
			this.infoEnvoiNs.setMontant(Double.parseDouble(params.get("montant")));
			this.infoEnvoiNs.setFrais(Double.parseDouble(params.get("frais")));
			
			this.typeEnvoi = params.get("typeEnvoi");
			this.typeTransfert = params.get("typePai");
		}
	}
	
	public Map<String, String> getResponseFormat(String Resp){
		
		Map<String, String> RespFormate = new HashMap<String, String>();
		
		String[] Rep1 = Resp.split("&");
		
		int i;
		for(i=0; i<Rep1.length; i++){
			String[] Rep2 = Rep1[i].split("=");
			RespFormate.put(Rep2[0], Rep2[1]);
		}
		
		return RespFormate;
	}

	public String completePayment() throws UnsupportedEncodingException {
		String uri = "https://api-3t.sandbox.paypal.com/nvp";
		String params = "&USER="
				+ URLEncoder.encode("fijib.service-facilitator_api1.gmail.com",
						"UTF-8")
				+ "&PWD="
				+ URLEncoder.encode("1408455853", "UTF-8")
				+ "&SIGNATURE="
				+ URLEncoder
						.encode("AiPC9BjkCyDFQXbSkoZcgqH3hpacAxhf5lW9tf.GT0j3yPpoHjuK3rjn",
								"UTF-8") + "&VERSION="
				+ URLEncoder.encode("87.0", "UTF-8") + "&TOKEN="
				+ URLEncoder.encode(this.token, "UTF-8") + "&PAYERID="
				+ URLEncoder.encode(this.payerId, "UTF-8") +

				"&L_PAYMENTREQUEST_0_NAME0="
				+ URLEncoder.encode("Montant du Transfert  :: ", "UTF-8")
				+ "&L_PAYMENTREQUEST_0_NUMBER0="
				+ URLEncoder.encode("001", "UTF-8")
				+ "&L_PAYMENTREQUEST_0_DESC0="
				+ URLEncoder.encode("Les frais sont décrit en bas", "UTF-8")
				+ "&L_PAYMENTREQUEST_0_AMT0="
				+ URLEncoder.encode("10.00", "UTF-8")
				+ "&L_PAYMENTREQUEST_0_QTY0=" + URLEncoder.encode("1", "UTF-8")
				+

				"&PAYMENTREQUEST_0_ITEMAMT="
				+ URLEncoder.encode("10.00", "UTF-8")
				+ "&PAYMENTREQUEST_0_SHIPPINGAMT="
				+ URLEncoder.encode("2.00", "UTF-8") + "&PAYMENTREQUEST_0_AMT="
				+ URLEncoder.encode("12.00", "UTF-8")
				+ "&PAYMENTREQUEST_0_CURRENCYCODE="
				+ URLEncoder.encode("EUR", "UTF-8") +

				"&PAYMENTREQUEST_0_PAYMENTACTION="
				+ URLEncoder.encode("SALE", "UTF-8") + "&METHOD="
				+ URLEncoder.encode("DoExpressCheckoutPayment", "UTF-8");

		String Rep = this.excutePost(uri, params);
		System.out.println(Rep);
		Rep = URLDecoder.decode(Rep);
		return Rep;
	}

 

	public void sentPost() throws UnsupportedEncodingException {
		if (this.infoEnvoiNs.getMontant() != 0
				&& this.infoEnvoiNs.getFrais() != 0) {
			
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			
			String name = auth.getName();
			
			String uri = "https://api-3t.sandbox.paypal.com/nvp";
			String params = "&USER="
					+ URLEncoder.encode("fijib.service-facilitator_api1.gmail.com","UTF-8")
					+ "&PWD="
					+ URLEncoder.encode("1408455853", "UTF-8")
					+ "&SIGNATURE="
					+ URLEncoder
							.encode("AiPC9BjkCyDFQXbSkoZcgqH3hpacAxhf5lW9tf.GT0j3yPpoHjuK3rjn",
									"UTF-8")
					+ "&VERSION="
					+ URLEncoder.encode("87.0", "UTF-8")
					+

					"&L_PAYMENTREQUEST_0_NAME0="
					+ URLEncoder.encode("Montant du Transfert  :: ", "UTF-8")
					+ "&L_PAYMENTREQUEST_0_NUMBER0="
					+ URLEncoder.encode("001", "UTF-8")
					+ "&L_PAYMENTREQUEST_0_DESC0="
					+ URLEncoder
							.encode("Les frais sont décrit en bas", "UTF-8")
					+ "&L_PAYMENTREQUEST_0_AMT0="
					+ URLEncoder.encode("" + this.infoEnvoiNs.getMontant() ,
							"UTF-8")
					+ "&L_PAYMENTREQUEST_0_QTY0=" 
					+ URLEncoder.encode("1", "UTF-8")
					+

					"&PAYMENTREQUEST_0_ITEMAMT="  
					+ URLEncoder.encode(""+this.infoEnvoiNs.getMontant(),
							"UTF-8")
					+ "&PAYMENTREQUEST_0_SHIPPINGAMT="   
					+ URLEncoder.encode(""+  (int) this.infoEnvoiNs.getFrais()
							,"UTF-8")
					+ "&PAYMENTREQUEST_0_AMT=" 
					+ URLEncoder.encode(""+((int)this.infoEnvoiNs.getFrais()
							+ this.infoEnvoiNs.getMontant())
							, "UTF-8")
					+ "&PAYMENTREQUEST_0_CURRENCYCODE="
					+ URLEncoder.encode("EUR", "UTF-8")
					+

					"&PAYMENTREQUEST_0_PAYMENTACTION="
					+ URLEncoder.encode("SALE", "UTF-8")
					+

					"&HDRIMG="
					+ URLEncoder
							.encode("http://localhost:8080/fijib_project/resources/images/headerPaypal.jpg",
									"UTF-8")
					+ "&CARTBORDERCOLOR="
					+ URLEncoder.encode("eaeaea", "UTF-8")
					+ "&PAYFLOWCOLOR="
					+ URLEncoder.encode("eaeaea", "UTF-8")
					+

					"&RETURNURL="
					+ URLEncoder
							.encode("http://localhost:8080/fijib_project/insecurePages/PayPalFilter.xhtml?sos=true&typePai=PAYPAL&typeEnvoi="
									+ this.typeEnvoi+"&montant="+(this.infoEnvoiNs.getMontant()+"&frais="+this.infoEnvoiNs.getFrais())
									+ "&name=" + name, "UTF-8")
					+ "&CANCELURL="
					+ URLEncoder
							.encode("http://localhost:8080/fijib_project/PayPal/payPal.xhtml",
									"UTF-8") + "&METHOD="
					+ URLEncoder.encode("SetExpressCheckout", "UTF-8");
 			String Rep = this.excutePost(uri, params);
			Rep = URLDecoder.decode(Rep);

			String[] decoupe = Rep.split("&");

			String token = decoupe[0].split("=")[1];
            System.out.println(Rep);
			String redirectSandBox = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
					+ token;

			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(redirectSandBox);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Le Montant et les Frais doivent être different de 0 !"));
		}
	}

	public String excutePost(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	
	
	//PAYPAL
	public DtoEnvoiNs getInfoEnvoiNs() {
		return infoEnvoiNs;
	}

	public void setInfoEnvoiNs(DtoEnvoiNs infoEnvoiNs) {
		this.infoEnvoiNs = infoEnvoiNs;
	}

	public void setCalculateurFrais(CalculFrais calculateurFrais) {
		this.calculateurFrais = calculateurFrais;
	}

	public String getTypeEnvoi() {
		return typeEnvoi;
	}

	public void setTypeEnvoi(String typeEnvoi) {
		this.typeEnvoi = typeEnvoi;
	}

	public DtoInscriptionClient getInfoBenef() {
		return infoBenef;
	}

	public void setInfoBenef(DtoInscriptionClient infoBenef) {
		this.infoBenef = infoBenef;
	}

	public String getTypeTransfert() {
		return typeTransfert;
	}

	public void setTypeTransfert(String typeTransfert) {
		this.typeTransfert = typeTransfert;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public DtoClient getClient() {
		return client;
	}

	public void setClient(DtoClient client) {
		this.client = client;
	}

	public DtoEnvoiClient getEnvoiDto() {
		return envoiDto;
	}

	public void setEnvoiDto(DtoEnvoiClient envoiDto) {
		this.envoiDto = envoiDto;
	}

	public String getInputRecherche() {
		return inputRecherche;
	}

	public void setInputRecherche(String inputRecherche) {
		this.inputRecherche = inputRecherche;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public DtoRecu getRecu() {
		return recu;
	}

	public void setRecu(DtoRecu recu) {
		this.recu = recu;
	}

	public DtoInfo getInfoUser() {
		return infoUser;
	}

	public void setInfoUser(DtoInfo infoUser) {
		this.infoUser = infoUser;
	}
	
	//To-To
	
//	public void login(String login, String pwd) {
//
//		try {
//			Authentication request = new UsernamePasswordAuthenticationToken(
//					login, pwd);
//			Authentication result = authenticationManagerX.authenticate(request);
//			SecurityContextHolder.getContext().setAuthentication(result);
//
//			HttpServletRequest req = (HttpServletRequest) FacesContext
//					.getCurrentInstance().getExternalContext().getRequest();
//
////			if (req.isUserInRole("ROLE_ADMIN"))
////				return "/securePages/AdminPages/accueilAdmin";
////			else if (req.isUserInRole("ROLE_CLIENT")) {
////				return "/securePages/ClientPages/accueilClient";
////			} else if (req.isUserInRole("ROLE_AGENT"))
////				return "/securePages/AgentPages/accueilAgent";
//			System.out.println("arrrrr");
//		} catch (Exception e) {
//			System.out.println("ea");
//			e.printStackTrace();
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "compte inexistant !"));
//		}
//	}
//
//	public AuthenticationManager getAuthenticationManager() {
//		return authenticationManagerX;
//	}
//
//	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//		this.authenticationManagerX = authenticationManager;
//	}
}
