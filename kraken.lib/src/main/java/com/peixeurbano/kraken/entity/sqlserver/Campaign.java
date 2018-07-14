package com.peixeurbano.kraken.entity.sqlserver;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;

import com.peixeurbano.kraken.entity.abstracts.BtnDlgSelecaoQry;
import com.peixeurbano.kraken.entity.enums.BooleanEnum;
import com.peixeurbano.kraken.entity.enums.OperationText;
import com.peixeurbano.kraken.entity.enums.SelectIdValue;
import com.peixeurbano.kraken.entity.enums.Sexo;
import com.peixeurbano.kraken.entity.enums.Status;
import com.peixeurbano.kraken.utils.funcoes;


/**
 * The persistent class for the CAMPAIGN database table.
 * 
 */
@Entity
@Table(name="CAMPAIGN")
@NamedQueries({
	@NamedQuery(name = Campaign.CAMPAIGN_FINDALL, query = "SELECT c FROM Campaign c"),
    @NamedQuery(name = Campaign.CAMPAIGN_FINDALL_LIST, query = "SELECT new Campaign( c.campaignId, c.name, c.usable, c.status, c.scheduleCount, c.campaignExcludeUseraccountCount ) FROM Campaign c ORDER BY c.usable desc, c.name asc "),
    @NamedQuery(name = Campaign.SCHEDULE_FINDALL_LIST, query = "SELECT new Campaign( c.campaignId, c.cronschedule, c.usable, c.gatilho, c.group, c.jobclass, c.tarefaname, c.campaignExcludeUseraccountCount ) FROM Campaign c "),
    @NamedQuery(name = Campaign.CAMPAIGN_BY_ID, query = "SELECT c FROM Campaign c WHERE c.campaignId = :campaignId ")
})
public class Campaign extends BtnDlgSelecaoQry /*implements Serializable */{
	private static final Long serialVersionUID = 1L;
	
	public static final String  CAMPAIGN_FINDALL = "Campaign.findAll";
	public static final String  CAMPAIGN_BY_ID = "campaign.by.id";
	public static final String  CAMPAIGN_FINDALL_LIST = "campaign.findall.list";
	public static final String  SCHEDULE_FINDALL_LIST = "schedule.findall.list";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CAMPAIGN_ID")
	private Integer campaignId;
	
	@ManyToOne
	@JoinColumn(name="USER_ID_INSERT")
	private User userIdInsert=new User(24, "cyborgmg@gmail.com");
	
	@ManyToOne
	@JoinColumn(name="USER_ID_UPDATE")
	private User userIdUpdate;
	
	
	@Column(name="DATE_INSERT")
	private Timestamp dateInsert=new Timestamp(System.currentTimeMillis());
	
	@Column(name="DATE_UPDATE")
	private Timestamp dateUpdate;
	
	
	@Column(name="STATUS")
	private Status status;
	
	@Column(name="SCHEDULE_COUNT")
	private Long scheduleCount;

	@Column(name="SCHEDULE_DATEFIM")
	private Timestamp scheduleDatefim;

	@Column(name="SCHEDULE_DATEINI")
	private Timestamp scheduleDateini;

	@Column(name="QRY")
	private String qry;

	@Column(name="ERRO_LOG")
	private String erroLog;
	
	/*************************************************/
	
	@Column(name="CHECK_APP")
	private BooleanEnum checkApp;
	
	@Transient
	private Boolean checkAppCheck=Boolean.FALSE;

	@Column(name="CHECK_CLIENT")
	private BooleanEnum checkClient;
	
	@Transient
	private Boolean checkClientCheck=Boolean.FALSE;
	
	@Column(name="CHECK_VLPRESENTE")
	private BooleanEnum checkvlpresente;
	
	@Transient
	private Boolean checkvlpresenteCheck=Boolean.FALSE;

	@Column(name="FACEBOOK")
	private BooleanEnum facebook;
	
	@Transient
	private Boolean facebookCheck=Boolean.FALSE;

	@OrderBy
	@Column(name="NAME")
	private String name = "";
	
	@Enumerated(EnumType.STRING)
    @Basic(optional = false)
	@Column(name="SEXO", length = 1, columnDefinition = "char")
	private Sexo sexo;
	
	@Transient
	private Boolean sexoCheck=Boolean.FALSE;
	
	@Column(name="USABLE")
	private BooleanEnum usable=BooleanEnum.VERDADEIRO;
	
	@Transient
	private Boolean usableCheck=Boolean.TRUE;
	
	@Transient
	private Boolean campaignCheck=Boolean.FALSE;
	
	@Transient
	private Boolean nameCheck=Boolean.FALSE;

	/**************************************************************************************/
	
	//bi-directional many-to-one association to AgerangeSelect
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN}) 
	@OneToMany(mappedBy="campaign", cascade = CascadeType.ALL, orphanRemoval=true)//, fetch=FetchType.EAGER
	private List<AgerangeSelect> agerangeSelects = new ArrayList<AgerangeSelect>();

	//bi-directional many-to-one association to DateSelect
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OneToMany(mappedBy="campaign", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<DateSelect> dateSelects = new ArrayList<DateSelect>();

	//bi-directional many-to-one association to MailinglistSelect
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OneToMany(mappedBy="campaign", cascade = CascadeType.ALL, orphanRemoval=true)	
	private List<MailinglistSelect> mailinglistSelects = new ArrayList<MailinglistSelect>();
	
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OneToMany(mappedBy="campaign", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<OffersSelect> offersSelects = new ArrayList<OffersSelect>();
	
	//bi-directional many-to-one association to CategorySelectNav
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OneToMany(mappedBy="campaign", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<CategorySelect> categorySelects = new ArrayList<CategorySelect>();
	
	//bi-directional many-to-one association to SubcategorySelect
	@OneToMany(mappedBy="campaign", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<SubcategorySelect> subcategorySelects= new ArrayList<SubcategorySelect>();

	//bi-directional many-to-one association to ValueSelect
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OneToMany(mappedBy="campaign", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<ValueSelect> valueSelects = new ArrayList<ValueSelect>();
	
	//bi-directional many-to-one association to CampaignUseraccount
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OneToMany(mappedBy="campaign", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<CampaignUseraccount> campaignUseraccounts = new ArrayList<CampaignUseraccount>();
	
	//bi-directional many-to-one association to AttributeSelectNav
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OneToMany(mappedBy="campaign", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<AttributeSelectNav> attributeSelectNavs = new ArrayList<AttributeSelectNav>();

	//bi-directional many-to-one association to CategorySelectNav
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OneToMany(mappedBy="campaign", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<CategorySelectNav> categorySelectNavs = new ArrayList<CategorySelectNav>();

	//bi-directional many-to-one association to CollectionsSelectNav
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OneToMany(mappedBy="campaign", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<CollectionsSelectNav> collectionsSelectNavs = new ArrayList<CollectionsSelectNav>();

	//bi-directional many-to-one association to OffersSelectNav
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OneToMany(mappedBy="campaign", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<OffersSelectNav> offersSelectNavs = new ArrayList<OffersSelectNav>();
	
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@OneToMany(mappedBy="campaign", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
	private List<CampaignExclude> campaignExcludes = new ArrayList<CampaignExclude>();
	
	/*********************************************************************************************/
	
	/*****CATEGORIESSELECT*******************************************************/
	
	@Column(name="CURSO_AULA")
	private BooleanEnum cursoAulaEnum=BooleanEnum.FALSO;

	@Column(name="ENTRETENIMENTO")
	private BooleanEnum entretenimentoEnum=BooleanEnum.FALSO;

	@Column(name="ESTETICA")
	private BooleanEnum esteticaEnum=BooleanEnum.FALSO;

	@Column(name="GASTRONOMIA")
	private BooleanEnum gastronomiaEnum=BooleanEnum.FALSO;

	@Column(name="MODA")
	private BooleanEnum modaEnum=BooleanEnum.FALSO;

	@Column(name="SAUDEBESTAR")
	private BooleanEnum saudebestarEnum=BooleanEnum.FALSO;

	@Column(name="VIAGEN")
	private BooleanEnum viagenEnum=BooleanEnum.FALSO;
	
	@Transient
	private Boolean categoriesSelectCheck = Boolean.FALSE;

	/************************************************************/
	
	@Transient
	private Boolean cursoAula;

	@Transient
	private Boolean entretenimento;

	@Transient
	private Boolean estetica;

	@Transient
	private Boolean gastronomia;

	@Transient
	private Boolean moda;

	@Transient
	private Boolean saudebestar;

	@Transient
	private Boolean viagen;

	@Transient
	private String sqlPart="";
	
	/************************************************************/
	
	/*******ENGAGEMENT******************************************/
	
	@Column(name="CINCO")
	private BooleanEnum cincoEnum=BooleanEnum.FALSO;

	@Column(name="DEZ")
	private BooleanEnum dezEnum=BooleanEnum.FALSO;

	@Column(name="SETE")
	private BooleanEnum seteEnum=BooleanEnum.FALSO;

	@Column(name="UM")
	private BooleanEnum umEnum=BooleanEnum.FALSO;

	@Column(name="ZERO")
	private BooleanEnum zeroEnum=BooleanEnum.FALSO;

	@Transient
	private Boolean engagementCheck = Boolean.FALSE;
			

	/*************************************************/
	@Transient
	private Boolean cinco;

	@Transient
	private Boolean dez;

	@Transient
	private Boolean sete;

	@Transient
	private Boolean um;

	@Transient
	private Boolean zero;
	
	@Transient
	private String engagementSqlPart="";
	/*************************************************/
	
	/******OFFERSELECT******************************************************************/
	
	@Column(name="OFFER_OPERACAO")
	private BooleanEnum offerOperacao = BooleanEnum.VERDADEIRO;

	@Transient
	private Boolean offerSelectCheck = Boolean.FALSE;
	
	/******CATEGORY_NAV******************************************************************/
	
	@Column(name="CATEGORY_NAV_OPERACAO")
	private BooleanEnum categoryNavOperacao = BooleanEnum.VERDADEIRO;

	@Transient
	private Boolean categoryNavSelectCheck = Boolean.FALSE;
	
	public BooleanEnum getCategoryNavOperacao() {
		if(this.getCategorySelectNavs().isEmpty()){
			this.setCategoryNavOperacao(null);
		}else{
			this.setCategoryNavOperacao(BooleanEnum.VERDADEIRO);
		}
		return this.categoryNavOperacao;
	}

	public void setCategoryNavOperacao(final BooleanEnum categoryNavOperacao) {
		this.categoryNavOperacao = categoryNavOperacao;
	}

	public Boolean getCategoryNavSelectCheck() {
		this.categoryNavSelectCheck = !this.getCategorySelectNavs().isEmpty();
		return this.categoryNavSelectCheck;
	}

	public void setCategoryNavSelectCheck(final Boolean categoryNavSelectCheck) {
		this.categoryNavSelectCheck = categoryNavSelectCheck;
		if(!this.categoryNavSelectCheck){
			this.getCategorySelectNavs().clear();
		}
	}
	/******CATEGORY******************************************************************/
	
	@Column(name="CATEGORY_OPERACAO")
	private BooleanEnum categoryOperacao = BooleanEnum.VERDADEIRO;

	@Transient
	private Boolean categorySelectCheck = Boolean.FALSE;

	/***********************************************************************************/
	public BooleanEnum getCategoryOperacao() {
		if(this.getCategorySelects().isEmpty()){
			this.setCategoryOperacao(null);
		}else{
			this.setCategoryOperacao(BooleanEnum.VERDADEIRO);
		}
		return this.categoryOperacao;
	}

	public void setCategoryOperacao(final BooleanEnum categoryOperacao) {
		this.categoryOperacao = categoryOperacao;
	}

	public Boolean getCategorySelectCheck() {
		this.categorySelectCheck = !this.getCategorySelects().isEmpty();
		return this.categorySelectCheck;
	}

	public void setCategorySelectCheck(final Boolean categorySelectCheck) {
		this.categorySelectCheck = categorySelectCheck;
		if(!this.categorySelectCheck){
			this.getCategorySelects().clear();
		}
	}
	
	/******CATEGORY******************************************************************/
	
	@Column(name="SUBCATEGORY_OPERACAO")
	private BooleanEnum subcategoryOperacao = BooleanEnum.VERDADEIRO;

	@Transient
	private Boolean subcategorySelectCheck = Boolean.FALSE;

	/***********************************************************************************/
	public BooleanEnum getSubcategoryOperacao() {
		if(this.getSubcategorySelects().isEmpty()){
			this.setSubcategoryOperacao(null);
		}else{
			this.setSubcategoryOperacao(BooleanEnum.VERDADEIRO);
		}
		return this.subcategoryOperacao;
	}

	public void setSubcategoryOperacao(final BooleanEnum subcategoryOperacao) {
		this.subcategoryOperacao = subcategoryOperacao;
	}

	public Boolean getSubcategorySelectCheck() {
		this.subcategorySelectCheck = !this.getSubcategorySelects().isEmpty();
		return this.subcategorySelectCheck;
	}

	public void setSubcategorySelectCheck(final Boolean subcategorySelectCheck) {
		this.subcategorySelectCheck = subcategorySelectCheck;
		if(!this.subcategorySelectCheck){
			this.getSubcategorySelects().clear();
		}
	}	
	
	/******COLLECTION_NAV******************************************************************/
	
	@Column(name="COLLECTION_NAV_OPERACAO")
	private BooleanEnum collectionNavOperacao = BooleanEnum.VERDADEIRO;

	@Transient
	private Boolean collectionNavSelectCheck = Boolean.FALSE;

	/***********************************************************************************/
	
	public BooleanEnum getCollectionNavOperacao() {
		if(this.getCollectionsSelectNavs().isEmpty()){
			this.setCollectionNavOperacao(null);
		}else{
			this.setCollectionNavOperacao(BooleanEnum.VERDADEIRO);
		}
		return this.collectionNavOperacao;
	}

	public void setCollectionNavOperacao(final BooleanEnum collectionNavOperacao) {
		this.collectionNavOperacao = collectionNavOperacao;
	}

	public Boolean getCollectionNavSelectCheck() {
		this.collectionNavSelectCheck = !this.getCollectionsSelectNavs().isEmpty();
		return this.collectionNavSelectCheck;
	}

	public void setCollectionNavSelectCheck(final Boolean collectionNavSelectCheck) {
		this.collectionNavSelectCheck = collectionNavSelectCheck;
		if(!this.collectionNavSelectCheck){
			this.getCollectionsSelectNavs().clear();
		}
	}
	
	/******ATTRIBUTE_NAV******************************************************************/
	
	@Column(name="ATTRIBUTE_NAV_OPERACAO")
	private BooleanEnum attributeNavOperacao = BooleanEnum.VERDADEIRO;

	@Transient
	private Boolean attributeNavSelectCheck = Boolean.FALSE;

	/***********************************************************************************/
	
	public BooleanEnum getAttributeNavOperacao() {
		if(this.getAttributeSelectNavs().isEmpty()){
			this.setAttributeNavOperacao(null);
		}else{
			this.setAttributeNavOperacao(BooleanEnum.VERDADEIRO);
		}
		return this.attributeNavOperacao;
	}

	public void setAttributeNavOperacao(final BooleanEnum attributeNavOperacao) {
		this.attributeNavOperacao = attributeNavOperacao;
	}

	public Boolean getAttributeNavSelectCheck() {
		this.attributeNavSelectCheck = !this.getAttributeSelectNavs().isEmpty();
		return this.attributeNavSelectCheck;
	}

	public void setAttributeNavSelectCheck(final Boolean attributeNavSelectCheck) {
		this.attributeNavSelectCheck = attributeNavSelectCheck;
		if(!this.attributeNavSelectCheck){
			this.getAttributeSelectNavs().clear();
		}
	}

	/******OFFER_NAV******************************************************************/
	
	@Column(name="OFFER_NAV_OPERACAO")
	private BooleanEnum offerNavOperacao = BooleanEnum.VERDADEIRO;

	@Transient
	private Boolean offerNavSelectCheck = Boolean.FALSE;

	
	/***********************************************************************************/
	
	/******SCHEDULE******************************************************************/
	@Column(name="CRONSCHEDULE")
	private String cronschedule;

	@Column(name="GATILHO")
	private String gatilho;

	@Column(name="[GROUP]")
	private String group;

	@Column(name="JOBCLASS")
	private String jobclass;

	@Column(name="TAREFANAME")
	private String tarefaname;
	/********************************************************************************/
	
	/******TEXTSELECT******************************************************************/
	
	@Column(name="TEXT_OPERACAO")
	private OperationText textOperacao=OperationText.CONTEM;

	//@NotBlank
	@Column(name="TEXT_DESC")
	private String textDesc="";

	@Transient
	private Boolean textSelectCheck = Boolean.FALSE;
	
	@Transient
	private String opetationDesc="";
	
	/**********************************************************************************/
	
	@Formula("( select COUNT(*) FROM dbo.CAMPAIGN_USERACCOUNT C WHERE C.CAMPAIGN_ID=CAMPAIGN_ID )")
	private Integer campaignUseraccountCount;
	
	@Formula("( select COUNT(*) FROM dbo.CAMPAIGN_EXCLUDE C WHERE C.CAMPAIGN_ID=CAMPAIGN_ID )")
	private Integer campaignExcludeUseraccountCount;
	
	/**********************************************************************************/

	
	public Campaign() {		
		super();
	}

	
	
	public Campaign(final Integer campaignId, final String name) {
		super();
		this.campaignId = campaignId;
		this.name = name;
	}

	public Campaign(final Integer campaignId, final String name, final BooleanEnum usable, final Status status, final Long scheduleCount, final Integer campaignExcludeUseraccountCount) {
		super();
		this.campaignId = campaignId;
		this.name = name;
		this.usable = usable;
		this.status=status;
		this.scheduleCount=scheduleCount;
		this.campaignExcludeUseraccountCount=campaignExcludeUseraccountCount;
	}

	public Campaign(final Integer campaignId, final BooleanEnum checkApp, final BooleanEnum checkClient, final BooleanEnum checkvlpresente,
			final BooleanEnum facebook, final String name, final Sexo sexo, final BooleanEnum usable) {
		super();
		this.campaignId = campaignId;
		this.checkApp = checkApp;
		this.checkClient = checkClient;
		this.checkvlpresente = checkvlpresente;
		this.facebook = facebook;
		this.name = name;
		this.sexo = sexo;
		this.usable = usable;
	}
	
	public Campaign(final Integer campaignId, final String cronschedule, final BooleanEnum usable, final String gatilho, final String group,
			final String jobclass, final String tarefaname, final Integer campaignExcludeUseraccountCount) {
		super();
		this.campaignId = campaignId;
		this.cronschedule = cronschedule;
		this.usable = usable;
		this.gatilho = gatilho;
		this.group = group;
		this.jobclass = jobclass;
		this.tarefaname = tarefaname;
		this.campaignExcludeUseraccountCount=campaignExcludeUseraccountCount;
	}

	public Integer getCampaignId() {
		return this.campaignId;
	}

	public void setCampaignId(final Integer campaignId) {
		this.campaignId = campaignId;
	}

	public BooleanEnum getCheckApp() {
		return this.checkApp;
	}

	public void setCheckApp(final BooleanEnum checkApp) {
		this.checkApp = checkApp;
	}

	public BooleanEnum getCheckClient() {
		return this.checkClient;
	}

	public void setCheckClient(final BooleanEnum checkClient) {
		this.checkClient = checkClient;
	}

	public BooleanEnum getFacebook() {
		return this.facebook;
	}

	public void setFacebook(final BooleanEnum facebook) {
		this.facebook = facebook;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	
	public BooleanEnum getUsable() {
		return this.usable;
	}

	public void setUsable(final BooleanEnum usable) {
		this.usable = usable;
	}
	/**************************************************************************************/

	public List<CampaignUseraccount> getCampaignUseraccounts() {
		return this.campaignUseraccounts;
	}

	public void setCampaignUseraccounts(final List<CampaignUseraccount> campaignUseraccounts) {
		this.campaignUseraccounts = campaignUseraccounts;
	}

	public CampaignUseraccount addCampaignUseraccount(final CampaignUseraccount campaignUseraccount) {
		this.getCampaignUseraccounts().add(campaignUseraccount);
		campaignUseraccount.setCampaign(this);

		return campaignUseraccount;
	}

	public CampaignUseraccount removeCampaignUseraccount(final CampaignUseraccount campaignUseraccount) {
		this.getCampaignUseraccounts().remove(campaignUseraccount);
		campaignUseraccount.setCampaign(null);

		return campaignUseraccount;
	}
	
	/**************************************************************************************/
	public List<AgerangeSelect> getAgerangeSelects() {
		return this.agerangeSelects;
	}

	public void setAgerangeSelects(final List<AgerangeSelect> agerangeSelects) {
		this.agerangeSelects = agerangeSelects;
	}

	public AgerangeSelect addAgerangeSelect(final AgerangeSelect agerangeSelect) {
		this.getAgerangeSelects().add(agerangeSelect);
		agerangeSelect.setCampaign(this);

		return agerangeSelect;
	}

	public AgerangeSelect removeAgerangeSelect(final AgerangeSelect agerangeSelect) {
		this.getAgerangeSelects().remove(agerangeSelect);
		agerangeSelect.setCampaign(null);

		return agerangeSelect;
	}

	public List<DateSelect> getDateSelects() {
		return this.dateSelects;
	}

	public void setDateSelects(final List<DateSelect> dateSelects) {
		this.dateSelects = dateSelects;
	}

	public DateSelect addDateSelect(final DateSelect dateSelect) {
	
		this.getDateSelects().add(dateSelect);
		dateSelect.setCampaign(this);

		return dateSelect;
	}

	public DateSelect removeDateSelect(final DateSelect dateSelect) {
		this.getDateSelects().remove(dateSelect);
		dateSelect.setCampaign(null);

		return dateSelect;
	}
/*************************************************************************************************/
	
	public List<MailinglistSelect> getMailinglistSelects() {
		return this.mailinglistSelects;
	}

	public void setMailinglistSelects(final List<MailinglistSelect> mailinglistSelects) {
		this.mailinglistSelects = mailinglistSelects;
	}

	public MailinglistSelect addMailinglistSelect(final MailinglistSelect mailinglistSelect) {
		this.getMailinglistSelects().add(mailinglistSelect);
		mailinglistSelect.setCampaign(this);

		return mailinglistSelect;
	}

	public MailinglistSelect removeMailinglistSelect(final MailinglistSelect mailinglistSelect) {
		this.getMailinglistSelects().remove(mailinglistSelect);
		mailinglistSelect.setCampaign(null);

		return mailinglistSelect;
	}
	
/***************************************************************************************************/
	
	public List<OffersSelect> getOffersSelects() {
		return this.offersSelects;
	}

	public void setOffersSelects(final List<OffersSelect> offersSelects) {
		this.offersSelects = offersSelects;
	}

	public OffersSelect addOffersSelect(final OffersSelect offersSelect) {
		this.getOffersSelects().add(offersSelect);
		offersSelect.setCampaign(this);

		return offersSelect;
	}

	public OffersSelect removeOffersSelect(final OffersSelect offersSelect) {
		this.getOffersSelects().remove(offersSelect);
		offersSelect.setCampaign(null);

		return offersSelect;
	}
	
/**************************************************************************************************/
	
	public List<CategorySelect> getCategorySelects() {
		return this.categorySelects;
	}

	public void setCategorySelects(final List<CategorySelect> categorySelects) {
		this.categorySelects = categorySelects;
	}

	public CategorySelect addCategorySelect(final CategorySelect categorySelect) {
		this.getCategorySelects().add(categorySelect);
		categorySelect.setCampaign(this);

		return categorySelect;
	}

	public CategorySelect removeCategorySelect(final CategorySelect categorySelect) {
		this.getCategorySelects().remove(categorySelect);
		categorySelect.setCampaign(null);

		return categorySelect;
	}
/****************************************************************************************************/
	public List<ValueSelect> getValueSelects() {
		return this.valueSelects;
	}

	public void setValueSelects(final List<ValueSelect> valueSelects) {
		this.valueSelects = valueSelects;
	}

	public ValueSelect addValueSelect(final ValueSelect valueSelect) {

		this.getValueSelects().add(valueSelect);
		valueSelect.setCampaign(this);

		return valueSelect;
	}

	public ValueSelect removeValueSelect(final ValueSelect valueSelect) {
		this.getValueSelects().remove(valueSelect);
		valueSelect.setCampaign(null);

		return valueSelect;
	}

	public Sexo getSexo() {
		return this.sexo;
	}

	public void setSexo(final Sexo sexo) {
		this.sexo = sexo;
	}
	
	public ValueSelect getBySelectId(final SelectIdValue selectId ){
		
		for (ValueSelect valueSelect : this.valueSelects) {
			if(valueSelect.getSelectId()==selectId){
				return valueSelect; 
			}
		}
		return null;
	}

	public BooleanEnum getCheckvlpresente() {
		return this.checkvlpresente;
	}

	public void setCheckvlpresente(final BooleanEnum checkvlpresente) {
		this.checkvlpresente = checkvlpresente;
	}

	/************************************************************************/
	
	public Boolean getCheckAppCheck() {
		this.checkAppCheck = this.getCheckApp()!=null; 
		return this.checkAppCheck;
	}

	public void setCheckAppCheck(final Boolean checkAppCheck) {
		this.checkAppCheck = checkAppCheck;
		if(!this.checkAppCheck){
			this.setCheckApp(null);
		}
	}

	public Boolean getCheckClientCheck() {
		this.checkClientCheck = this.getCheckClient()!=null;
		return this.checkClientCheck;
	}

	public void setCheckClientCheck(final Boolean checkClientCheck) {
		this.checkClientCheck = checkClientCheck;
		if(!this.checkClientCheck){
			this.setCheckClient(null);
		}
	}

	public Boolean getCheckvlpresenteCheck() {
		this.checkvlpresenteCheck = this.getCheckvlpresente()!=null;
		return this.checkvlpresenteCheck;
	}

	public void setCheckvlpresenteCheck(final Boolean checkvlpresenteCheck) {
		this.checkvlpresenteCheck = checkvlpresenteCheck;
		if(!this.checkvlpresenteCheck){
			this.setCheckvlpresente(null);
		}
	}

	public Boolean getFacebookCheck() {
		this.facebookCheck = this.getFacebook()!=null;
		return this.facebookCheck;
	}

	public void setFacebookCheck(final Boolean facebookCheck) {
		this.facebookCheck = facebookCheck;
		if(!this.facebookCheck){
			this.setFacebook(null);
		}
	}
	
	public Boolean getSexoCheck() {
		this.sexoCheck = this.getSexo()!=null;
		return this.sexoCheck;
	}

	public void setSexoCheck(final Boolean sexoCheck) {
		this.sexoCheck = sexoCheck;
		if(!this.sexoCheck){
			this.setSexo(null);
		}
	}

	public Boolean getUsableCheck() {
		this.usableCheck = this.getUsable().getValor()==1;
		return this.usableCheck;
	}

	public void setUsableCheck(final Boolean usableCheck) {
		this.usableCheck = usableCheck;
		this.setUsable(this.usableCheck?BooleanEnum.VERDADEIRO:BooleanEnum.FALSO);
	}

	public Boolean getCampaignCheck() {
		this.campaignCheck = !((this.name==null?"":this.name).trim()!="");
		return this.campaignCheck;
	}

	public void setCampaignCheck(final Boolean campaignCheck) {
		this.campaignCheck = campaignCheck;
	}
	
	
	
	public Boolean getNameCheck() {
		this.nameCheck = this.name.trim().length()==0;
		return this.nameCheck;
	}

	public void setNameCheck(final Boolean nameCheck) {
		this.nameCheck = nameCheck;
	}

	/*******CATEGORIESSELECT*****************************************************/

	public Boolean getCursoAula() {
		return this.cursoAula;
	}

	public void setCursoAula(final Boolean cursoAula) {
		this.cursoAula = cursoAula;
		this.cursoAulaEnum=funcoes.convertTobooleanEnum(this.cursoAula);
	}

	public Boolean getEntretenimento() {
		return this.entretenimento;
	}

	public void setEntretenimento(final Boolean entretenimento) {
		this.entretenimento = entretenimento;
		this.entretenimentoEnum=funcoes.convertTobooleanEnum(this.entretenimento);
	}

	public Boolean getEstetica() {
		return this.estetica;
	}

	public void setEstetica(final Boolean estetica) {
		this.estetica = estetica;
		this.esteticaEnum=funcoes.convertTobooleanEnum(this.estetica);
	}

	public Boolean getGastronomia() {
		return this.gastronomia;
	}

	public void setGastronomia(final Boolean gastronomia) {
		this.gastronomia = gastronomia;
		this.gastronomiaEnum=funcoes.convertTobooleanEnum(this.gastronomia);
	}

	public Boolean getModa() {
		return this.moda;
	}

	public void setModa(final Boolean moda) {
		this.moda = moda;
		this.modaEnum=funcoes.convertTobooleanEnum(this.moda);
	}

	public Boolean getSaudebestar() {
		return this.saudebestar;
	}

	public void setSaudebestar(final Boolean saudebestar) {
		this.saudebestar = saudebestar;
		this.saudebestarEnum=funcoes.convertTobooleanEnum(this.saudebestar);
	}

	public Boolean getViagen() {
		return this.viagen;
	}

	public void setViagen(final Boolean viagen) {
		this.viagen = viagen;
		this.viagenEnum=funcoes.convertTobooleanEnum(this.viagen);
	}
	
	/*******ENUM-GETS************************************************************************/
	
	public BooleanEnum getCursoAulaEnum() {
		return this.cursoAulaEnum;
	}

	public BooleanEnum getEntretenimentoEnum() {
		return this.entretenimentoEnum;
	}

	public BooleanEnum getEsteticaEnum() {
		return this.esteticaEnum;
	}

	public BooleanEnum getGastronomiaEnum() {
		return this.gastronomiaEnum;
	}

	public BooleanEnum getModaEnum() {
		return this.modaEnum;
	}
	
	public BooleanEnum getSaudebestarEnum() {
		return this.saudebestarEnum;
	}

	public BooleanEnum getViagenEnum() {
		return this.viagenEnum;
	}
	
	/****************************************************************************************/

	public Campaign restoreCategoriesSelect(){
		
		this.cursoAula=this.cursoAulaEnum.convertTobollean();
		this.entretenimento=this.entretenimentoEnum.convertTobollean();
		this.estetica=this.esteticaEnum.convertTobollean();
		this.gastronomia=this.gastronomiaEnum.convertTobollean();
		this.moda=this.modaEnum.convertTobollean();
		this.saudebestar=this.saudebestarEnum.convertTobollean();
		this.viagen=this.viagenEnum.convertTobollean();

		
	return this;	
	}
	
	public String getSqlPartCategoriesSelect() {
		this.sqlPart=     "u.haspurgastronomia = "+this.gastronomiaEnum.getValor()
					 +" or u.haspurturismo = "+this.viagenEnum.getValor()
					 +" or u.haspureCommerce = "+this.cursoAulaEnum.getValor()
					 +" or u.haspurservicoLocal = "+this.modaEnum.getValor()
					 +" or u.haspurentretenimento = "+this.entretenimentoEnum.getValor()
					 +" or u.haspurbemestar = "+this.saudebestarEnum.getValor();
		return "("+this.sqlPart+")";
	}

	/**********************************************************************************/

	public Boolean getCategoriesSelectCheck() {
		this.categoriesSelectCheck=(this.cursoAulaEnum.getValor()+this.entretenimentoEnum.getValor()+this.esteticaEnum.getValor()+
				this.gastronomiaEnum.getValor()+this.modaEnum.getValor()+this.saudebestarEnum.getValor()+this.viagenEnum.getValor())>0;
		return this.categoriesSelectCheck;
	}

	public void setCategoriesSelectCheck(final Boolean categoriesSelectCheck) {
		this.categoriesSelectCheck = categoriesSelectCheck;
		if(!this.categoriesSelectCheck){
			this.setCursoAula(Boolean.FALSE);
			this.setEntretenimento(Boolean.FALSE);
			this.setEstetica(Boolean.FALSE);
			this.setGastronomia(Boolean.FALSE);
			this.setModa(Boolean.FALSE);
			this.setSaudebestar(Boolean.FALSE);
			this.setViagen(Boolean.FALSE);			
		}
	}
	
	/*******ENGAGEMENT******************************************/

	public Boolean getCinco() {
		return this.cinco;
	}

	public void setCinco(final Boolean cinco) {
		this.cinco = cinco;
		this.cincoEnum=funcoes.convertTobooleanEnum(this.cinco);
	}

	public Boolean getDez() {
		return this.dez;		
	}

	public void setDez(final Boolean dez) {
		this.dez = dez;
		this.dezEnum=funcoes.convertTobooleanEnum(this.dez);
	}

	public Boolean getSete() {
		return this.sete;
	}

	public void setSete(final Boolean sete) {
		this.sete = sete;
		this.seteEnum=funcoes.convertTobooleanEnum(this.sete);
	}

	public Boolean getUm() {
		return this.um;
	}

	public void setUm(final Boolean um) {
		this.um = um;
		this.umEnum=funcoes.convertTobooleanEnum(this.um);
	}

	public Boolean getZero() {
		return this.zero;		
	}

	public void setZero(final Boolean zero) {
		this.zero = zero;
		this.zeroEnum=funcoes.convertTobooleanEnum(this.zero);
	}
	/************************************************************************/

	public Campaign engagementRestore(){
		
		this.cinco=this.cincoEnum.convertTobollean();
		this.dez=this.dezEnum.convertTobollean();
		this.sete=this.seteEnum.convertTobollean();
		this.um=this.umEnum.convertTobollean();
		this.zero=this.zeroEnum.convertTobollean();
		
	return this;	
	}
	
	public String getEngagementSqlPart() {
		String arr = ((this.getZero()?"0,":"")+(this.getUm()?"1,":"")+(this.getCinco()?"5,":"")+(this.getSete()?"7,":"")+(this.getDez()?"10,":""));
		this.engagementSqlPart = "("+arr.substring(0,arr.length()-1)+")" ;
		return this.engagementSqlPart;
	}
	
	/*******ENUM-GETS************************************************************************/
	public BooleanEnum getCincoEnum() {
		return this.cincoEnum;
	}

	public BooleanEnum getDezEnum() {
		return this.dezEnum;
	}

	public BooleanEnum getSeteEnum() {
		return this.seteEnum;
	}

	public BooleanEnum getUmEnum() {
		return this.umEnum;
	}

	public BooleanEnum getZeroEnum() {
		return this.zeroEnum;
	}
	/************************************************************************/

	public Boolean getEngagementCheck() {
		
		this.engagementCheck = (this.cincoEnum.getValor()+this.dezEnum.getValor()+this.seteEnum.getValor()+this.umEnum.getValor()+this.zeroEnum.getValor())>0;
		
		return this.engagementCheck;
	}

	public void setEngagementCheck(final Boolean engagementCheck) {
		this.engagementCheck = engagementCheck;
		if(!this.engagementCheck){
			this.setCinco(Boolean.FALSE);
			this.setDez(Boolean.FALSE);
			this.setSete(Boolean.FALSE);
			this.setUm(Boolean.FALSE);
			this.setZero(Boolean.FALSE);
		}
	}	
	
	/******OFFER_Nav_SELECT******************************************************************/

	public BooleanEnum getOfferNavOperacao() {
		if(this.getOffersSelectNavs().isEmpty()){
			this.setOfferNavOperacao(null);
		}else{
			this.setOfferNavOperacao(BooleanEnum.VERDADEIRO);
		}
		return this.offerNavOperacao;
	}

	public void setOfferNavOperacao(final BooleanEnum offerNavOperacao) {
		this.offerNavOperacao = offerNavOperacao;
	}

	public Boolean getOfferNavSelectCheck() {
		this.offerNavSelectCheck = !this.getOffersSelectNavs().isEmpty();
		return this.offerNavSelectCheck;
	}

	public void setOfferNavSelectCheck(final Boolean offerNavSelectCheck) {
		this.offerNavSelectCheck = offerNavSelectCheck;
		if(!this.offerNavSelectCheck){
			this.getOffersSelectNavs().clear();
		}
	}
	/******OFFERSELECT******************************************************************/

	public BooleanEnum getOfferOperacao() {
		if(this.getOffersSelects().isEmpty()){
			this.setOfferOperacao(null);
		}else{
			this.setOfferOperacao(BooleanEnum.VERDADEIRO);
		}
		return this.offerOperacao;
	}

	public void setOfferOperacao(final BooleanEnum offerOperacao) {
		this.offerOperacao = offerOperacao;
	}

	public Boolean getOfferSelectCheck() {
		this.offerSelectCheck = !this.getOffersSelects().isEmpty();
		return this.offerSelectCheck;
	}

	public void setOfferSelectCheck(final Boolean offerSelectCheck) {
		this.offerSelectCheck = offerSelectCheck;
		if(!this.offerSelectCheck){
			this.offersSelects.clear();
		}
	}
	
	/******SCHEDULE******************************************************************/
	public String getCronschedule() {
		return this.cronschedule;
	}

	public void setCronschedule(final String cronschedule) {
		this.cronschedule = cronschedule;
	}

	public String getGatilho() {
		return this.gatilho;
	}

	public void setGatilho(final String gatilho) {
		this.gatilho = gatilho;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(final String group) {
		this.group = group;
	}

	public String getJobclass() {
		return this.jobclass;
	}

	public void setJobclass(final String jobclass) {
		this.jobclass = jobclass;
	}

	public String getTarefaname() {
		return this.tarefaname;
	}

	public void setTarefaname(final String tarefaname) {
		this.tarefaname = tarefaname;
	}
	/********************************************************************************/	
	/******TEXTSELECT******************************************************************/


	public OperationText getTextOperacao() {
		return this.textOperacao;
	}

	public void setTextOperacao(final OperationText textOperacao) {
		this.textOperacao = textOperacao;
	}

	public String getTextDesc() {
		return this.textDesc;
	}
	
	public void setTextDesc(final String textDesc) {
		this.textDesc = textDesc.trim();
	}

	public String getOpetationDesc() {
		this.opetationDesc = this.getTextOperacao().getLabel(this.getTextDesc());
		return this.opetationDesc;
	}

	public Boolean getTextSelectCheck() {
		this.textSelectCheck = !"".equals(this.getTextDesc());
		return this.textSelectCheck;
	}

	public void setTextSelectCheck(final Boolean textSelectCheck) {
		this.textSelectCheck = textSelectCheck;
		if(!this.textSelectCheck){
			this.setTextDesc("");
		}
	}

	/**********************************************************************************/

	public Status getStatus() {
		return this.status;
	}
	
	public Integer getCampaignUseraccountCount() {
		return this.campaignUseraccountCount;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	public Long getScheduleCount() {
		return this.scheduleCount==null?0L:this.scheduleCount;
	}
	
	public void setScheduleCount(final Long scheduleCount) {
		this.scheduleCount = scheduleCount;
	}

	public Timestamp getScheduleDatefim() {
		return this.scheduleDatefim;
	}

	public void setScheduleDatefim(final Timestamp scheduleDatefim) {
		this.scheduleDatefim = scheduleDatefim;
	}

	public Timestamp getScheduleDateini() {
		return this.scheduleDateini;
	}

	public void setScheduleDateini(final Timestamp scheduleDateini) {
		this.scheduleDateini = scheduleDateini;
	}

	public String getErroLog() {
		return this.erroLog;
	}

	public void setErroLog(final String erroLog) {
		this.erroLog = erroLog;
	}

	public String getQry() {
		return this.qry;
	}

	public void setQry(final String qry) {
		this.qry = qry;
	}
	
	public List<AttributeSelectNav> getAttributeSelectNavs() {
		return this.attributeSelectNavs;
	}

	public void setAttributeSelectNavs(final List<AttributeSelectNav> attributeSelectNavs) {
		this.attributeSelectNavs = attributeSelectNavs;
	}

	public AttributeSelectNav addAttributeSelectNav(final AttributeSelectNav attributeSelectNav) {
		this.getAttributeSelectNavs().add(attributeSelectNav);
		attributeSelectNav.setCampaign(this);

		return attributeSelectNav;
	}

	public AttributeSelectNav removeAttributeSelectNav(final AttributeSelectNav attributeSelectNav) {
		this.getAttributeSelectNavs().remove(attributeSelectNav);
		attributeSelectNav.setCampaign(null);

		return attributeSelectNav;
	}

	public List<CategorySelectNav> getCategorySelectNavs() {
		return this.categorySelectNavs;
	}

	public void setCategorySelectNavs(final List<CategorySelectNav> categorySelectNavs) {
		this.categorySelectNavs = categorySelectNavs;
	}

	public CategorySelectNav addCategorySelectNav(final CategorySelectNav categorySelectNav) {
		this.getCategorySelectNavs().add(categorySelectNav);
		categorySelectNav.setCampaign(this);

		return categorySelectNav;
	}

	public CategorySelectNav removeCategorySelectNav(final CategorySelectNav categorySelectNav) {
		this.getCategorySelectNavs().remove(categorySelectNav);
		categorySelectNav.setCampaign(null);

		return categorySelectNav;
	}

	public List<CollectionsSelectNav> getCollectionsSelectNavs() {
		return this.collectionsSelectNavs;
	}

	public void setCollectionsSelectNavs(final List<CollectionsSelectNav> collectionsSelectNavs) {
		this.collectionsSelectNavs = collectionsSelectNavs;
	}

	public CollectionsSelectNav addCollectionsSelectNav(final CollectionsSelectNav collectionsSelectNav) {
		this.getCollectionsSelectNavs().add(collectionsSelectNav);
		collectionsSelectNav.setCampaign(this);

		return collectionsSelectNav;
	}

	public CollectionsSelectNav removeCollectionsSelectNav(final CollectionsSelectNav collectionsSelectNav) {
		this.getCollectionsSelectNavs().remove(collectionsSelectNav);
		collectionsSelectNav.setCampaign(null);

		return collectionsSelectNav;
	}

	public List<OffersSelectNav> getOffersSelectNavs() {
		return this.offersSelectNavs;
	}

	public void setOffersSelectNavs(final List<OffersSelectNav> offersSelectNavs) {
		this.offersSelectNavs = offersSelectNavs;
	}

	public OffersSelectNav addOffersSelectNav(final OffersSelectNav offersSelectNav) {
		this.getOffersSelectNavs().add(offersSelectNav);
		offersSelectNav.setCampaign(this);

		return offersSelectNav;
	}

	public OffersSelectNav removeOffersSelectNav(final OffersSelectNav offersSelectNav) {
		this.getOffersSelectNavs().remove(offersSelectNav);
		offersSelectNav.setCampaign(null);

		return offersSelectNav;
	}

	public User getUserIdInsert() {
		return this.userIdInsert;
	}

	public void setUserIdInsert(final User userIdInsert) {
		this.userIdInsert = userIdInsert;
	}

	public User getUserIdUpdate() {
		return this.userIdUpdate;
	}

	public void setUserIdUpdate(final User userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

	public Timestamp getDateInsert() {
		return this.dateInsert;
	}

	public void setDateInsert(final Timestamp dateInsert) {
		this.dateInsert = dateInsert;
	}

	public Timestamp getDateUpdate() {
		return this.dateUpdate;
	}

	public void setDateUpdate(final Timestamp dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public List<SubcategorySelect> getSubcategorySelects() {
		return this.subcategorySelects;
	}

	public void setSubcategorySelects(final List<SubcategorySelect> subcategorySelects) {
		this.subcategorySelects = subcategorySelects;
	}

	public SubcategorySelect addSubcategorySelect(final SubcategorySelect subcategorySelect) {
		this.getSubcategorySelects().add(subcategorySelect);
		subcategorySelect.setCampaign(this);

		return subcategorySelect;
	}

	public SubcategorySelect removeSubcategorySelect(final SubcategorySelect subcategorySelect) {
		this.getSubcategorySelects().remove(subcategorySelect);
		subcategorySelect.setCampaign(null);

		return subcategorySelect;
	}
	
	public List<CampaignExclude> getCampaignIdExcExcludes() {
		return this.campaignExcludes;
	}

	public void setCampaignIdExcExcludes(final List<CampaignExclude> campaignExcludes) {
		this.campaignExcludes = campaignExcludes;
	}

	public CampaignExclude addCampaignExcludes(final CampaignExclude campaignIdExcExcludes) {
		this.getCampaignIdExcExcludes().add(campaignIdExcExcludes);
		campaignIdExcExcludes.setCampaign(this);

		return campaignIdExcExcludes;
	}

	public CampaignExclude removeCampaignExcludes(final CampaignExclude campaignIdExcExcludes) {
		this.getCampaignIdExcExcludes().remove(campaignIdExcExcludes);
		campaignIdExcExcludes.setCampaign(null);

		return campaignIdExcExcludes;
	}
	
	
	public Integer getCampaignExcludeUseraccountCount() {
		return this.campaignExcludeUseraccountCount;
	}

	public void setCampaignExcludeUseraccountCount(final Integer campaignExcludeUseraccountCount) {
		this.campaignExcludeUseraccountCount = campaignExcludeUseraccountCount;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.campaignId.toString();
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(final String nome) {
		// TODO Auto-generated method stub
		this.name = nome;
	}
	

}