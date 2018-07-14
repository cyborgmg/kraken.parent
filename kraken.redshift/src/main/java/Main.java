

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@Transactional
public class Main {

	public static void main(final String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kraken.redshift-pu");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		StringBuilder qry = new StringBuilder();
		/*
		qry.append("select distinct u.useraccountid ");
		qry.append("from Useraccount u  ");
		qry.append("where ");
		qry.append("u.usesfacebooklogin = 0 ");
		qry.append("and (select count(*) from SalesSource s where u.useraccountid=s.useraccountid and s.sourceid = 2)>0 ");
		qry.append("and u.datefirstpurchase is null ");
		qry.append("and u.female = 0 ");
		qry.append("and ( select count(*) from SalesSource s where s.useraccountid=u.useraccountid )=0 ");
		qry.append("and u.engagement in (0,1) ");
		qry.append("and (u.haspurgastronomia = 0 or u.haspurturismo = 1 or u.haspureCommerce = 0 or u.haspurservicoLocal = 1 or u.haspurentretenimento = 1 or u.haspurbemestar = 0) ");
		qry.append("and ( select count(*) from Promotionalcode p  where  p.useraccountid = u.useraccountid and UPPER( p.code ) = UPPER('carro') )>0");
		qry.append("and u.dateregistered between '2017-02-27' and '2017-03-06' ");
		qry.append("and u.anniversary not between '2017-02-06' and '2017-02-20' ");
		qry.append("and u.datelastpurchase between '2017-02-22' and '2017-02-28' ");
		qry.append("and u.netrevenue =6 ");
		qry.append("and u.totalpurchases between 5 and 8 ");
		qry.append("and u.maximumpurchasevalue <=5 ");
		qry.append("and u.countofvalepresente >=6 ");
		qry.append("and u.agerangeid in (1) ");
		*/
		/*
		qry.append("select ");
		qry.append("    distinct p.title, ps.page ");
		qry.append("from ");
		qry.append("     MngPermanentSchedulingDeal psds ");
		qry.append("    ,MngPermanentScheduling ps ");
		qry.append("    ,MngPagesLatest p ");
		qry.append("    ,MngDealsLatest d ");
		qry.append("where ");
		qry.append("    ps.numericId = psds.numericId and ");
		qry.append("    p.pageid = ps.page and ");
		qry.append("    d.dealId = psds.deal ");
		qry.append("order by p.title ");
		
		entityManager.createQuery(qry.toString()).getResultList();
		
		System.out.println("fim");
		*/	
	}

}
