 select count(*) from Useraccount u where 
 u.channelid = 1 and u.status between 1 and 2 
 and (select count(1) from MailinglistUseraccount m where m.useraccountid=u.useraccountid and m.mailinglistid in (1))>0 
