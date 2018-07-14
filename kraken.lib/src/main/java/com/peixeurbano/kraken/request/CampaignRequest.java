package com.peixeurbano.kraken.request;

import java.io.Serializable;
import java.util.List;

import com.peixeurbano.kraken.entity.enums.SelectIdDate;
import com.peixeurbano.kraken.entity.enums.SelectIdValue;
import com.peixeurbano.kraken.entity.enums.Status;
import com.peixeurbano.kraken.entity.imutable.ListBooleanEnum;
import com.peixeurbano.kraken.entity.imutable.ListOperatorText;
import com.peixeurbano.kraken.entity.imutable.ListOperatorValue;
import com.peixeurbano.kraken.entity.imutable.ListSexo;
import com.peixeurbano.kraken.entity.redshift.kraken.State;
import com.peixeurbano.kraken.entity.sqlserver.Campaign;
import com.peixeurbano.kraken.entity.sqlserver.DateSelect;
import com.peixeurbano.kraken.entity.sqlserver.ValueSelect;

public class CampaignRequest implements Serializable {

	private static final long serialVersionUID = -4297643800555047119L;


	/*******PAI***********************************************************************************/
	
	private Campaign campaign;

	private State stateOffer;
	private State stateOfferNav;
	
	/*******SUB-REQUESTS**************************************************************************/
	
	MailinglistSelectRequest mailinglistSelectRequest;// = new MailinglistSelectRequest();

	AgerangeSelectRequest agerangeSelectRequest;// = new AgerangeSelectRequest();

	OfferSelectRequest offerSelectRequest;// = new OfferSelectRequest();
	CategorySelectRequest categorySelectRequest;
	SubcategorySelectRequest subcategorySelectRequest;
	CampaignExcludeRequest campaignExcludeRequest;
	
	OfferSelectNavRequest offerSelectNavRequest;
	CategorySelectNavRequest categorySelectNavRequest;	
	CollectionsSelectNavRequest collectionsSelectNavRequest;
	AttributeSelectNavRequest attributeSelectNavRequest;
	
		
	/*******SELECAO DE VALORES********************************************************************/
	private List<ListOperatorValue> valueSelects = ListOperatorValue.getOperationList();
	/*********************************************************************************************/

	/*******SELECAO DE TEXTO********************************************************************/	
	private final List<ListOperatorText> textSelectOperations = ListOperatorText.getOperationList();
	/*********************************************************************************************/	
	
	/*******SELECAO DE SIM-NAO********************************************************************/	
	private final List<ListBooleanEnum> booleanEnums = ListBooleanEnum.getOperationList();
	/*********************************************************************************************/

	/*******SELECAO DE CLIENTE********************************************************************/
	private final List<ListBooleanEnum> clienteBooleanEnums = ListBooleanEnum.getOperationListCliente();
	/*********************************************************************************************/
	
	/*******SELECAO DE SIM-NAO********************************************************************/	
	private final List<ListSexo> sexos = ListSexo.getOperationList();
	/*********************************************************************************************/
	
	
	/*Datas*/
	private DateSelectRequest dataCadastro;
	private DateSelectRequest dataAniversario;
	private DateSelectRequest dataUltimaCompra;
	private DateSelectRequest dataNavegacao;
	
	
	
	/*NUmeros*/
	private ValueSelect receitaLiquidaCompras;
	private ValueSelect quantidadeCompras;
	private ValueSelect maiorValorCompra;
	private ValueSelect valorUtilizadoValePresente;

	
	public Campaign getCampaign() {
		return this.campaign;
	}

	public void setCampaign(final Campaign campaign) {
		this.campaign = campaign;
	}
	

	public void putCampaign(final Campaign campaign) {
		
		this.setCampaign(campaign);
		
/*		if(this.getCampaign().getStatus()==null){
			this.getCampaign().setStatus(Status.NAO_GERADA);
		}
*/
		if(this.getCampaign().getCampaignId()==null){
			
			Double rand=Math.random()*10000;
			
			this.getCampaign().setStatus(Status.NAO_GERADA);
			
			this.getCampaign().setTarefaname("TCAMPAIGN"+rand.intValue());
			
			this.getCampaign().setGroup("GRCAMPAIGN"+rand.intValue());
		
			this.getCampaign().setGatilho("GCAMPAIGN"+rand.intValue());
		
			this.getCampaign().setCronschedule("0 1 20 1/1 * ? *");
		
			this.getCampaign().setUsableCheck(true);
			
			
		}
		
		
		this.getCampaign().restoreCategoriesSelect();
		this.getCampaign().engagementRestore();
		
		/*Datas*/
		
		for(DateSelect dateselect : this.getCampaign().getDateSelects()) {
			
			switch (dateselect.getSelectId()) {
	    	
	         case DATA_CADASTRO:  this.setDataCadastro(new DateSelectRequest(dateselect));
	        	 		break;	
	         case DATA_ANIVERSARIO: this.setDataAniversario(new DateSelectRequest(dateselect));
			  		  	break;         
	         case DATA_ULTIMA_COMPRA: this.setDataUltimaCompra(new DateSelectRequest(dateselect));
	         		  	break;
	         case DATA_NAVIGATION: this.setDataNavegacao(new DateSelectRequest(dateselect));
  		  		break;		  	
	    	}
			
		}
		
		this.setDataCadastro( this.getDataCadastro()==null?new DateSelectRequest( DateSelectRequest.factory( SelectIdDate.DATA_CADASTRO,this.getCampaign() ) ):this.getDataCadastro() );
		
		this.setDataAniversario( this.getDataAniversario()==null?new DateSelectRequest( DateSelectRequest.factory( SelectIdDate.DATA_ANIVERSARIO,this.getCampaign() ) ):this.getDataAniversario() );
		
		this.setDataUltimaCompra( this.getDataUltimaCompra()==null?new DateSelectRequest( DateSelectRequest.factory( SelectIdDate.DATA_ULTIMA_COMPRA,this.getCampaign() ) ):this.getDataUltimaCompra());
		
		this.setDataNavegacao( this.getDataNavegacao()==null?new DateSelectRequest( DateSelectRequest.factory( SelectIdDate.DATA_NAVIGATION,this.getCampaign() ) ):this.getDataNavegacao());
		
		/*NUmeros*/
		for (ValueSelect valueselect : this.getCampaign().getValueSelects()) {
			
			switch (valueselect.getSelectId()) {
	    	
	         case RECEITA_LIQUIDA_COMPRAS:  this.setReceitaLiquidaCompras(valueselect);
	         		  break;	
	         case QUANTIDADE_COMPRAS:  this.setQuantidadeCompras(valueselect);
			  		  break;         
	         case MAIOR_VALOR_COMPRA:  this.setMaiorValorCompra(valueselect);
	         		  break;
	         case VALOR_UTILIZADO_VALE_PRESENTE:  this.setValorUtilizadoValePresente(valueselect);
	         		  break;		  
	    	}			
		}
		
		this.setReceitaLiquidaCompras( this.getReceitaLiquidaCompras()==null?new ValueSelect(SelectIdValue.RECEITA_LIQUIDA_COMPRAS,this.getCampaign()):this.getReceitaLiquidaCompras() );
		
		this.setQuantidadeCompras( this.getQuantidadeCompras()==null?new ValueSelect(SelectIdValue.QUANTIDADE_COMPRAS,this.getCampaign()):this.getQuantidadeCompras() );
		
		this.setMaiorValorCompra( this.getMaiorValorCompra()==null?new ValueSelect(SelectIdValue.MAIOR_VALOR_COMPRA,this.getCampaign()):this.getMaiorValorCompra() );
		
		this.setValorUtilizadoValePresente( this.getValorUtilizadoValePresente()==null?new ValueSelect(SelectIdValue.VALOR_UTILIZADO_VALE_PRESENTE,this.getCampaign()):this.getValorUtilizadoValePresente() );

		this.setMailinglistSelectRequest( new MailinglistSelectRequest(this.getCampaign()) );

		this.setAgerangeSelectRequest( new AgerangeSelectRequest(this.getCampaign()) );

		this.setOfferSelectRequest( new OfferSelectRequest(this.getCampaign(), this.getCampaign().getOffersSelects()) );
		
		this.setCategorySelectRequest( new CategorySelectRequest(this.getCampaign(), this.getCampaign().getCategorySelects()) );
		
		this.setSubcategorySelectRequest( new SubcategorySelectRequest(this.getCampaign(), this.getCampaign().getSubcategorySelects()) );
		
		this.setOfferSelectNavRequest( new OfferSelectNavRequest(this.getCampaign(), this.getCampaign().getOffersSelectNavs()) );	
		
		this.setCategorySelectNavRequest( new CategorySelectNavRequest(this.getCampaign(), this.getCampaign().getCategorySelectNavs()) );
		
		this.setCollectionsSelectNavRequest( new CollectionsSelectNavRequest(this.getCampaign(), this.getCampaign().getCollectionsSelectNavs()) );
		
		this.setAttributeSelectNavRequest( new AttributeSelectNavRequest(this.getCampaign(), this.getCampaign().getAttributeSelectNavs()) );
		
		this.setCampaignExcludeRequest( new CampaignExcludeRequest(this.getCampaign(), this.getCampaign().getCampaignIdExcExcludes()) );
		
		campaign.setCampaignExcludeUseraccountCount(this.getCampaignExcludeRequest().getTargets().size());
		
	}


	public ValueSelect getReceitaLiquidaCompras() {
		return this.receitaLiquidaCompras;
	}

	public void setReceitaLiquidaCompras(final ValueSelect receitaLiquidaCompras) {
		this.receitaLiquidaCompras = receitaLiquidaCompras;
	}

	public ValueSelect getQuantidadeCompras() {
		return this.quantidadeCompras;
	}

	public void setQuantidadeCompras(final ValueSelect quantidadeCompras) {
		this.quantidadeCompras = quantidadeCompras;
	}

	public ValueSelect getMaiorValorCompra() {
		return this.maiorValorCompra;
	}

	public void setMaiorValorCompra(final ValueSelect maiorValorCompra) {
		this.maiorValorCompra = maiorValorCompra;
	}

	public ValueSelect getValorUtilizadoValePresente() {
		return this.valorUtilizadoValePresente;
	}

	public void setValorUtilizadoValePresente(final ValueSelect valorUtilizadoValePresente) {
		this.valorUtilizadoValePresente = valorUtilizadoValePresente;
	}
	
	public List<ListOperatorValue> getValueSelects() {
		return this.valueSelects;
	}

	public void setValueSelects(final List<ListOperatorValue> valueSelects) {
		this.valueSelects = valueSelects;
	}

	public List<ListBooleanEnum> getBooleanEnums() {
		return this.booleanEnums;
	}

	public List<ListBooleanEnum> getClienteBooleanEnums() {
		return this.clienteBooleanEnums;
	}

	public List<ListSexo> getSexos() {
		return this.sexos;
	}

	public MailinglistSelectRequest getMailinglistSelectRequest() {
		return this.mailinglistSelectRequest;
	}

	public void setMailinglistSelectRequest(final MailinglistSelectRequest mailinglistSelectRequest) {
		this.mailinglistSelectRequest = mailinglistSelectRequest;
	}

	public AgerangeSelectRequest getAgerangeSelectRequest() {
		return this.agerangeSelectRequest;
	}

	public void setAgerangeSelectRequest(final AgerangeSelectRequest agerangeSelectRequest) {
		this.agerangeSelectRequest = agerangeSelectRequest;
	}

	public OfferSelectRequest getOfferSelectRequest() {
		return this.offerSelectRequest;
	}

	public void setOfferSelectRequest(final OfferSelectRequest offerSelectRequest) {
		this.offerSelectRequest = offerSelectRequest;
	}

	public DateSelectRequest getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(final DateSelectRequest dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public DateSelectRequest getDataAniversario() {
		return this.dataAniversario;
	}

	public void setDataAniversario(final DateSelectRequest dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	public DateSelectRequest getDataUltimaCompra() {
		return this.dataUltimaCompra;
	}

	public void setDataUltimaCompra(final DateSelectRequest dataUltimaCompra) {
		this.dataUltimaCompra = dataUltimaCompra;
	}

	public DateSelectRequest getDataNavegacao() {
		return this.dataNavegacao;
	}

	public void setDataNavegacao(final DateSelectRequest dataNavegacao) {
		this.dataNavegacao = dataNavegacao;
	}

	public List<ListOperatorText> getTextSelectOperations() {
		return this.textSelectOperations;
	}

	public OfferSelectNavRequest getOfferSelectNavRequest() {
		return this.offerSelectNavRequest;
	}

	public void setOfferSelectNavRequest(final OfferSelectNavRequest offerSelectNavRequest) {
		this.offerSelectNavRequest = offerSelectNavRequest;
	}

	public CategorySelectNavRequest getCategorySelectNavRequest() {
		return this.categorySelectNavRequest;
	}

	public void setCategorySelectNavRequest(final CategorySelectNavRequest categorySelectNavRequest) {
		this.categorySelectNavRequest = categorySelectNavRequest;
	}

	public CollectionsSelectNavRequest getCollectionsSelectNavRequest() {
		return this.collectionsSelectNavRequest;
	}

	public void setCollectionsSelectNavRequest(final CollectionsSelectNavRequest collectionsSelectNavRequest) {
		this.collectionsSelectNavRequest = collectionsSelectNavRequest;
	}

	public AttributeSelectNavRequest getAttributeSelectNavRequest() {
		return this.attributeSelectNavRequest;
	}

	public void setAttributeSelectNavRequest(final AttributeSelectNavRequest attributeSelectNavRequest) {
		this.attributeSelectNavRequest = attributeSelectNavRequest;
	}

	public CategorySelectRequest getCategorySelectRequest() {
		return this.categorySelectRequest;
	}

	public void setCategorySelectRequest(final CategorySelectRequest categorySelectRequest) {
		this.categorySelectRequest = categorySelectRequest;
	}

	public State getStateOffer() {
		return this.stateOffer;
	}

	public void setStateOffer(final State stateOffer) {
		this.stateOffer = stateOffer;
	}

	public State getStateOfferNav() {
		return this.stateOfferNav;
	}

	public void setStateOfferNav(final State stateOfferNav) {
		this.stateOfferNav = stateOfferNav;
	}

	public SubcategorySelectRequest getSubcategorySelectRequest() {
		return this.subcategorySelectRequest;
	}

	public void setSubcategorySelectRequest(final SubcategorySelectRequest subcategorySelectRequest) {
		this.subcategorySelectRequest = subcategorySelectRequest;
	}

	public CampaignExcludeRequest getCampaignExcludeRequest() {
		return this.campaignExcludeRequest;
	}

	public void setCampaignExcludeRequest(final CampaignExcludeRequest campaignExcludeRequest) {
		this.campaignExcludeRequest = campaignExcludeRequest;
	}

}
