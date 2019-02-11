[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------------< com.revolut:moneytransfers >---------------------
[INFO] Building moneytransfers 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ moneytransfers ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/Massaq/eclipse-ensa/moneytransfers/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ moneytransfers ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ moneytransfers ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 3 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ moneytransfers ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:3.0.0-M3:test (default-test) @ moneytransfers ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.revolut.moneytransfers.test.MoneyTransfersAPITest
03:47:52.721 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
03:47:53.930 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
03:47:54.620 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:47:54 ERROR App - RevolutJTADS
Feb 11, 2019 3:47:54 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 3:47:55 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 3:47:55 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer] Started.
2019-02-11 03:47:55 INFO  App - Generated json Entity{entity=Beneficiary(phone=9999999999, accounts=[], firstName=Lara, middletName=null, lastName=Craft, address=null), variant=Variant[mediaType=application/json, language=null, encoding=null], annotations=[]}
2019-02-11 03:47:56 INFO  BeneficiaryRest - PUT Entity : Beneficiary(phone=9999999999, accounts=[], firstName=Lara, middletName=null, lastName=Craft, address=null)
03:47:56.763 [grizzly-http-server-0] WARN  org.hibernate.jpa.boot.internal.PersistenceXmlParser - HHH015018: Encountered multiple persistence-unit stanzas defining same name [RevolutUnit1]; persistence-unit names must be unique
03:47:56.764 [grizzly-http-server-0] WARN  org.hibernate.jpa.boot.internal.PersistenceXmlParser - HHH015018: Encountered multiple persistence-unit stanzas defining same name [RevolutJTAUnit]; persistence-unit names must be unique
2019-02-11 03:47:57 INFO  HikariDataSource - HikariPool-1 - Starting...
2019-02-11 03:47:58 INFO  HikariDataSource - HikariPool-1 - Start completed.
Feb 11, 2019 3:48:00 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
03:48:00.654 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:00 ERROR App - RevolutJTADS
03:48:00.929 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:01 ERROR App - RevolutJTADS
Feb 11, 2019 3:48:01 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 3:48:01 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 3:48:01 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-1] Started.
2019-02-11 03:48:01 INFO  AccountRest - PUT Entity : Account(accountID=AccountID(phone=null, currency=EUR), beneficiary=Beneficiary(phone=555555555, accounts=[], firstName=FifthName, middletName=null, lastName=FifthLastName, address=null), country=Spain, label=EUR Spain, iban=IBAN Test 777 000, bic=BIC Test 777 999, balance=0.0)
2019-02-11 03:48:01 INFO  AccountDaoImpl - AccountID(phone=555555555, currency=EUR) persisted
2019-02-11 03:48:01 INFO  AccountRest -  /id POST Entity =  AccountID(phone=555555555, currency=EUR)
2019-02-11 03:48:01 INFO  App - response Entity : InboundJaxrsResponse{context=ClientResponse{method=POST, uri=http://localhost:9998/Account/id, status=200, reason=OK}}
Feb 11, 2019 3:48:01 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
03:48:01.602 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:01 ERROR App - RevolutJTADS
03:48:01.835 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:01 ERROR App - RevolutJTADS
Feb 11, 2019 3:48:01 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 3:48:02 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 3:48:02 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-2] Started.
2019-02-11 03:48:02 INFO  AccountRest - PUT Entity : Account(accountID=AccountID(phone=null, currency=GBP), beneficiary=Beneficiary(phone=21266012346, accounts=[], firstName=Abderrazzak, middletName=null, lastName=Nejeoui, address=null), country=United Kingdom, label=GBP UK, iban=IBAN Test 123 456, bic=BIC Test 789 012, balance=0.0)
2019-02-11 03:48:02 INFO  AccountDaoImpl - AccountID(phone=21266012346, currency=GBP) persisted
2019-02-11 03:48:02 INFO  AccountRest -  /id POST Entity =  AccountID(phone=21266012346, currency=GBP)
2019-02-11 03:48:02 INFO  AccountRest - PUT Entity : Account(accountID=AccountID(phone=null, currency=GBP), beneficiary=Beneficiary(phone=44000000, accounts=[], firstName=Xname, middletName=null, lastName=Henry, address=null), country=United Kingdom, label=GBP UK, iban=IBAN Test 000 000, bic=BIC Test 999 999, balance=0.0)
2019-02-11 03:48:02 INFO  AccountDaoImpl - AccountID(phone=44000000, currency=GBP) persisted
2019-02-11 03:48:02 INFO  AccountRest -  /id POST Entity =  AccountID(phone=44000000, currency=GBP)
Feb 11, 2019 3:48:02 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
03:48:02.397 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:02 ERROR App - RevolutJTADS
03:48:02.563 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:02 ERROR App - RevolutJTADS
Feb 11, 2019 3:48:02 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 3:48:02 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 3:48:02 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-3] Started.
2019-02-11 03:48:02 INFO  AccountRest - PUT Entity : Account(accountID=AccountID(phone=null, currency=EUR), beneficiary=Beneficiary(phone=66666666, accounts=[], firstName=SixthName, middletName=null, lastName=SixthLastName, address=null), country=Italy, label=EUR Italy, iban=IBAN Italy 667 109, bic=BIC Italy 544 000, balance=0.0)
2019-02-11 03:48:02 INFO  AccountDaoImpl - AccountID(phone=66666666, currency=EUR) persisted
2019-02-11 03:48:02 INFO  AccountRest -  topUpParams : TopUpParams(phone=66666666, currency=EUR, amount=7690.96)
2019-02-11 03:48:02 INFO  AccountDaoImpl - AccountID(phone=66666666, currency=EUR) persisted
2019-02-11 03:48:02 INFO  App - topUpResponse.getStatus() = 201
2019-02-11 03:48:02 INFO  AccountRest -  topUpParams : TopUpParams(phone=66666666, currency=EUR, amount=7690.96)
2019-02-11 03:48:02 INFO  AccountDaoImpl - AccountID(phone=66666666, currency=EUR) persisted
2019-02-11 03:48:02 INFO  AccountRest -  /id POST Entity =  AccountID(phone=66666666, currency=EUR)
2019-02-11 03:48:02 INFO  App - getAccountsResponse response Message : org.glassfish.jersey.client.internal.HttpUrlConnector$2@3a988299
Feb 11, 2019 3:48:02 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
03:48:03.029 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:03 ERROR App - RevolutJTADS
03:48:03.179 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:03 ERROR App - RevolutJTADS
Feb 11, 2019 3:48:03 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 3:48:03 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 3:48:03 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-4] Started.
2019-02-11 03:48:03 INFO  App - CDI Test Start
2019-02-11 03:48:03 INFO  AccountDaoImpl - AccountID(phone=000000, currency=GBP) persisted
2019-02-11 03:48:03 INFO  App - CDI Test End
Feb 11, 2019 3:48:03 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
03:48:03.462 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:03 ERROR App - RevolutJTADS
03:48:03.602 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:03 ERROR App - RevolutJTADS
Feb 11, 2019 3:48:03 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 3:48:03 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 3:48:03 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-5] Started.
2019-02-11 03:48:03 INFO  App -  testJTA starting
2019-02-11 03:48:03 ERROR App -  addNewBeneficiaryJTA starting
2019-02-11 03:48:03 INFO  BeneficiaryDaoJTAImpl - JTA Persist 
03:48:03.911 [main] WARN  org.hibernate.jpa.boot.internal.PersistenceXmlParser - HHH015018: Encountered multiple persistence-unit stanzas defining same name [RevolutUnit1]; persistence-unit names must be unique
03:48:03.913 [main] WARN  org.hibernate.jpa.boot.internal.PersistenceXmlParser - HHH015018: Encountered multiple persistence-unit stanzas defining same name [RevolutJTAUnit]; persistence-unit names must be unique
2019-02-11 03:48:03 INFO  TransactionalProvider - connecting to :java:/RevolutJTADS
03:48:04.050 [main] WARN  com.arjuna.ats.jta - ARJUNA016087: TransactionImple.delistResource - unknown resource
2019-02-11 03:48:04 INFO  TransactionalProvider - connecting to :java:/RevolutJTADS
2019-02-11 03:48:04 INFO  TransactionalProvider - connection to :java:/RevolutJTADS closed
2019-02-11 03:48:04 INFO  TransactionalProvider - connecting to :java:/RevolutJTADS
2019-02-11 03:48:04 INFO  TransactionalProvider - connection to :java:/RevolutJTADS closed
2019-02-11 03:48:04 INFO  App - Retrieved from DB using JTA Beneficiary(phone=760978097809, accounts=[], firstName=James, middletName=null, lastName=Gosling, address=null)
2019-02-11 03:48:04 INFO  App -  testJTA End
Feb 11, 2019 3:48:04 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
03:48:04.445 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:04 ERROR App - RevolutJTADS
03:48:04.578 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:04 ERROR App - RevolutJTADS
Feb 11, 2019 3:48:04 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 3:48:04 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 3:48:04 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-6] Started.
2019-02-11 03:48:04 INFO  AccountRest - PUT Entity : Account(accountID=AccountID(phone=null, currency=EUR), beneficiary=Beneficiary(phone=777777, accounts=[], firstName=SeventhName, middletName=null, lastName=SeventhLastName, address=null), country=Germany, label=EUR Germany, iban=IBAN Germany 890 000, bic=BIC Germany 890 999, balance=0.0)
2019-02-11 03:48:04 INFO  AccountDaoImpl - AccountID(phone=777777, currency=EUR) persisted
2019-02-11 03:48:04 INFO  App - response Entity : [{"accountID":{"phone":"777777","currency":"EUR"},"beneficiary":{"phone":"777777","accounts":[],"firstName":"SeventhName","middletName":null,"lastName":"SeventhLastName","address":null},"country":"Germany","label":"EUR Germany","iban":"IBAN Germany 890 000","bic":"BIC Germany 890 999","balance":0.0}]
2019-02-11 03:48:04 INFO  AccountRest - PUT Entity : Account(accountID=AccountID(phone=null, currency=CAD), beneficiary=Beneficiary(phone=888888, accounts=[], firstName=EighthName, middletName=null, lastName=EighthLastName, address=null), country=Canada, label=CAD CANADA, iban=IBAN Canada 773 000, bic=BIC Canada 773 999, balance=0.0)
2019-02-11 03:48:04 INFO  AccountDaoImpl - AccountID(phone=888888, currency=CAD) persisted
2019-02-11 03:48:04 INFO  App - response Entity : [{"accountID":{"phone":"888888","currency":"CAD"},"beneficiary":{"phone":"888888","accounts":[],"firstName":"EighthName","middletName":null,"lastName":"EighthLastName","address":null},"country":"Canada","label":"CAD CANADA","iban":"IBAN Canada 773 000","bic":"BIC Canada 773 999","balance":0.0}]
2019-02-11 03:48:04 INFO  AccountRest -  topUpParams : TopUpParams(phone=777777, currency=EUR, amount=67859.86)
2019-02-11 03:48:04 INFO  AccountDaoImpl - AccountID(phone=777777, currency=EUR) persisted
2019-02-11 03:48:04 INFO  App - Test instance TransferDTO : TransferDTO(debitedAccountID=AccountID(phone=777777, currency=EUR), creditedAccountID=AccountID(phone=888888, currency=CAD), amount=450.0, reference=TR18906RevoT666, comment=Test Transfer)
2019-02-11 03:48:04 INFO  TransferRest - transferDTO: TransferDTO(debitedAccountID=AccountID(phone=777777, currency=EUR), creditedAccountID=AccountID(phone=888888, currency=CAD), amount=450.0, reference=TR18906RevoT666, comment=Test Transfer)
2019-02-11 03:48:04 INFO  TransferRest - PUT TransferDTO : TransferDTO(debitedAccountID=AccountID(phone=777777, currency=EUR), creditedAccountID=AccountID(phone=888888, currency=CAD), amount=450.0, reference=TR18906RevoT666, comment=Test Transfer)
2019-02-11 03:48:04 INFO  App - rsp ::: InboundJaxrsResponse{context=ClientResponse{method=PUT, uri=http://localhost:9998/Transfers/transfer/, status=200, reason=OK}}
2019-02-11 03:48:04 INFO  AccountRest -  /id POST Entity =  AccountID(phone=777777, currency=EUR)
2019-02-11 03:48:04 INFO  AccountRest -  /id POST Entity =  AccountID(phone=888888, currency=CAD)
2019-02-11 03:48:04 INFO  App - account_7 = Account(accountID=AccountID(phone=777777, currency=EUR), beneficiary=Beneficiary(phone=777777, accounts=[], firstName=SeventhName, middletName=null, lastName=SeventhLastName, address=null), country=Germany, label=EUR Germany, iban=IBAN Germany 890 000, bic=BIC Germany 890 999, balance=67409.86)
2019-02-11 03:48:04 INFO  App - account_8 = Account(accountID=AccountID(phone=888888, currency=CAD), beneficiary=Beneficiary(phone=888888, accounts=[], firstName=EighthName, middletName=null, lastName=EighthLastName, address=null), country=Canada, label=CAD CANADA, iban=IBAN Canada 773 000, bic=BIC Canada 773 999, balance=706.5)
Feb 11, 2019 3:48:04 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
03:48:04.989 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:05 ERROR App - RevolutJTADS
03:48:05.122 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:05 ERROR App - RevolutJTADS
Feb 11, 2019 3:48:05 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 3:48:05 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 3:48:05 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-7] Started.
2019-02-11 03:48:05 INFO  App - Generated json Entity{entity=Beneficiary(phone=333333999999, accounts=[], firstName=Albert, middletName=null, lastName=Einstein, address=null), variant=Variant[mediaType=application/json, language=null, encoding=null], annotations=[]}
2019-02-11 03:48:05 INFO  BeneficiaryRest - PUT Entity : Beneficiary(phone=333333999999, accounts=[], firstName=Albert, middletName=null, lastName=Einstein, address=null)
2019-02-11 03:48:05 INFO  BeneficiaryRest - PUT phone : 333333999999
Feb 11, 2019 3:48:05 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
03:48:05.450 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:05 ERROR App - RevolutJTADS
03:48:05.582 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 03:48:05 ERROR App - RevolutJTADS
Feb 11, 2019 3:48:05 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 3:48:05 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 3:48:05 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-8] Started.
2019-02-11 03:48:05 INFO  App - start testJaxRs
2019-02-11 03:48:05 INFO  AccountRest - PUT Entity : Account(accountID=AccountID(phone=null, currency=MAD), beneficiary=Beneficiary(phone=0101010101, accounts=[], firstName=BinaryNAme, middletName=null, lastName=Octal, address=null), country=MOROCCO, label=MAD MOROCCO, iban=IBAN Test 980 000, bic=BIC Test 786 999, balance=0.0)
2019-02-11 03:48:05 INFO  AccountDaoImpl - AccountID(phone=0101010101, currency=MAD) persisted
Feb 11, 2019 3:48:05 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 15.036 s - in com.revolut.moneytransfers.test.MoneyTransfersAPITest
2019-02-11 03:48:05 INFO  HikariDataSource - HikariPool-1 - Shutdown initiated...
2019-02-11 03:48:05 INFO  HikariDataSource - HikariPool-1 - Shutdown completed.
Weld SE container 34f9bccc-5776-40aa-b68b-afdeffd6dec8 shut down by shutdown hook
Weld SE container c60f41f1-865e-4e3b-ac15-e4f903a1cebd shut down by shutdown hook
Weld SE container 3dd2b450-4c9d-4319-8211-60e0b7427d41 shut down by shutdown hook
Weld SE container 04c81195-8305-4eb1-b939-67e48414f059 shut down by shutdown hook
Weld SE container cd4f9699-8498-4628-9d2d-7b6ac5c30164 shut down by shutdown hook
Weld SE container 624e06fa-e61d-4bd8-a287-175c94cf9b18 shut down by shutdown hook
Weld SE container 7534a039-06a3-4fc7-a0e4-fcd73f758596 shut down by shutdown hook
Weld SE container b1c72f09-fd95-4951-8959-0ca3a58409e6 shut down by shutdown hook
Weld SE container 3c6ffbab-f8d3-46fd-8c50-bbd02ff2dab3 shut down by shutdown hook
Weld SE container eaf9e880-3cc2-4ea8-87b8-eff5d936ea49 shut down by shutdown hook
Weld SE container 9fee159a-d0de-4da5-92da-b27f8ace66c0 shut down by shutdown hook
Weld SE container 44ba0f33-78a3-40c7-9713-3db535afaf22 shut down by shutdown hook
Weld SE container 3709b084-a4fa-46f9-8686-af53ec72dc77 shut down by shutdown hook
Weld SE container 1508a41b-efa5-482e-86c1-46007035a08e shut down by shutdown hook
Weld SE container f1513b00-6343-4a07-863e-6f5c679a5e01 shut down by shutdown hook
Weld SE container fb0bf287-503d-473d-bb01-0adce4d11337 shut down by shutdown hook
Weld SE container 7934a7d4-66b6-4354-8e8a-0ad10bcd8fc6 shut down by shutdown hook
Weld SE container c5ce3207-0999-4518-8ace-ea1e9b80b6ea shut down by shutdown hook
Weld SE container 23383225-8eb0-4557-99ed-4071af82bb88 shut down by shutdown hook
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 9, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 19.457 s
[INFO] Finished at: 2019-02-11T03:48:06Z
[INFO] ------------------------------------------------------------------------
