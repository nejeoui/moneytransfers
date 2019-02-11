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
04:19:15.881 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
04:19:16.811 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
04:19:17.230 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 04:19:17 ERROR App - RevolutJTADS
Feb 11, 2019 4:19:17 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 4:19:18 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 4:19:18 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer] Started.
2019-02-11 04:19:18 INFO  App - Generated json Entity{entity=Beneficiary(phone=9999999999, accounts=[], firstName=Lara, middletName=null, lastName=Craft, address=null), variant=Variant[mediaType=application/json, language=null, encoding=null], annotations=[]}
2019-02-11 04:19:18 INFO  BeneficiaryRest - PUT Entity : Beneficiary(phone=9999999999, accounts=[], firstName=Lara, middletName=null, lastName=Craft, address=null)
04:19:18.818 [grizzly-http-server-0] WARN  org.hibernate.jpa.boot.internal.PersistenceXmlParser - HHH015018: Encountered multiple persistence-unit stanzas defining same name [RevolutUnit1]; persistence-unit names must be unique
04:19:18.819 [grizzly-http-server-0] WARN  org.hibernate.jpa.boot.internal.PersistenceXmlParser - HHH015018: Encountered multiple persistence-unit stanzas defining same name [RevolutJTAUnit]; persistence-unit names must be unique
2019-02-11 04:19:19 INFO  HikariDataSource - HikariPool-1 - Starting...
2019-02-11 04:19:19 INFO  HikariDataSource - HikariPool-1 - Start completed.
Feb 11, 2019 4:19:21 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
04:19:21.243 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 04:19:21 ERROR App - RevolutJTADS
04:19:21.446 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 04:19:21 ERROR App - RevolutJTADS
Feb 11, 2019 4:19:21 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 4:19:21 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 4:19:21 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-1] Started.
2019-02-11 04:19:21 INFO  AccountRest - PUT Entity : Account(accountID=AccountID(phone=null, currency=EUR), beneficiary=Beneficiary(phone=555555555, accounts=[], firstName=FifthName, middletName=null, lastName=FifthLastName, address=null), country=Spain, label=EUR Spain, iban=IBAN Test 777 000, bic=BIC Test 777 999, balance=0.0)
2019-02-11 04:19:21 INFO  AccountDaoImpl - AccountID(phone=555555555, currency=EUR) persisted
2019-02-11 04:19:21 INFO  AccountRest -  /id POST Entity =  AccountID(phone=555555555, currency=EUR)
2019-02-11 04:19:21 INFO  App - response Entity : InboundJaxrsResponse{context=ClientResponse{method=POST, uri=http://localhost:9998/Account/id, status=200, reason=OK}}
Feb 11, 2019 4:19:21 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
04:19:21.989 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 04:19:22 ERROR App - RevolutJTADS
04:19:22.196 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 04:19:22 ERROR App - RevolutJTADS
Feb 11, 2019 4:19:22 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 4:19:22 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 4:19:22 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-2] Started.
2019-02-11 04:19:22 INFO  AccountRest - PUT Entity : Account(accountID=AccountID(phone=null, currency=GBP), beneficiary=Beneficiary(phone=21266012346, accounts=[], firstName=Abderrazzak, middletName=null, lastName=Nejeoui, address=null), country=United Kingdom, label=GBP UK, iban=IBAN Test 123 456, bic=BIC Test 789 012, balance=0.0)
2019-02-11 04:19:22 INFO  AccountDaoImpl - AccountID(phone=21266012346, currency=GBP) persisted
2019-02-11 04:19:22 INFO  AccountRest -  /id POST Entity =  AccountID(phone=21266012346, currency=GBP)
2019-02-11 04:19:22 INFO  AccountRest - PUT Entity : Account(accountID=AccountID(phone=null, currency=GBP), beneficiary=Beneficiary(phone=44000000, accounts=[], firstName=Xname, middletName=null, lastName=Henry, address=null), country=United Kingdom, label=GBP UK, iban=IBAN Test 000 000, bic=BIC Test 999 999, balance=0.0)
2019-02-11 04:19:22 INFO  AccountDaoImpl - AccountID(phone=44000000, currency=GBP) persisted
2019-02-11 04:19:22 INFO  AccountRest -  /id POST Entity =  AccountID(phone=44000000, currency=GBP)
Feb 11, 2019 4:19:22 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
04:19:22.662 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 04:19:22 ERROR App - RevolutJTADS
04:19:22.805 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 04:19:22 ERROR App - RevolutJTADS
Feb 11, 2019 4:19:22 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 4:19:23 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 4:19:23 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-3] Started.
2019-02-11 04:19:23 INFO  AccountRest - PUT Entity : Account(accountID=AccountID(phone=null, currency=EUR), beneficiary=Beneficiary(phone=66666666, accounts=[], firstName=SixthName, middletName=null, lastName=SixthLastName, address=null), country=Italy, label=EUR Italy, iban=IBAN Italy 667 109, bic=BIC Italy 544 000, balance=0.0)
2019-02-11 04:19:23 INFO  AccountDaoImpl - AccountID(phone=66666666, currency=EUR) persisted
2019-02-11 04:19:23 INFO  AccountRest -  topUpParams : TopUpParams(phone=66666666, currency=EUR, amount=7690.96)
2019-02-11 04:19:23 INFO  AccountDaoImpl - AccountID(phone=66666666, currency=EUR) persisted
2019-02-11 04:19:23 INFO  App - topUpResponse.getStatus() = 201
2019-02-11 04:19:23 INFO  AccountRest -  topUpParams : TopUpParams(phone=66666666, currency=EUR, amount=7690.96)
2019-02-11 04:19:23 INFO  AccountDaoImpl - AccountID(phone=66666666, currency=EUR) persisted
2019-02-11 04:19:23 INFO  AccountRest -  /id POST Entity =  AccountID(phone=66666666, currency=EUR)
2019-02-11 04:19:23 INFO  App - getAccountsResponse response Message : org.glassfish.jersey.client.internal.HttpUrlConnector$2@65a4b9d6
Feb 11, 2019 4:19:23 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
04:19:23.225 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 04:19:23 ERROR App - RevolutJTADS
04:19:23.368 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 04:19:23 ERROR App - RevolutJTADS
Feb 11, 2019 4:19:23 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 4:19:23 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 4:19:23 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-4] Started.
2019-02-11 04:19:23 INFO  App - CDI Test Start
2019-02-11 04:19:23 INFO  AccountDaoImpl - AccountID(phone=000000, currency=GBP) persisted
2019-02-11 04:19:23 INFO  App - CDI Test End
Feb 11, 2019 4:19:23 AM org.glassfish.grizzly.http.server.NetworkListener shutdownNow
INFO: Stopped listener bound to [localhost:9998]
04:19:23.696 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 04:19:23 ERROR App - RevolutJTADS
04:19:23.888 [main] WARN  org.jboss.weld.Bootstrap - WELD-000146: BeforeBeanDiscovery.addAnnotatedType(AnnotatedType<?>) used for class org.glassfish.jersey.ext.cdi1x.internal.CdiComponentProvider$JaxRsParamProducer is deprecated from CDI 1.1!
2019-02-11 04:19:23 ERROR App - RevolutJTADS
Feb 11, 2019 4:19:23 AM org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory$GrizzlyTestContainer <init>
INFO: Creating GrizzlyTestContainer configured at the base URI http://localhost:9998/
Feb 11, 2019 4:19:24 AM org.glassfish.grizzly.http.server.NetworkListener start
INFO: Started listener bound to [localhost:9998]
Feb 11, 2019 4:19:24 AM org.glassfish.grizzly.http.server.HttpServer start
INFO: [HttpServer-5] Started.
2019-02-11 04:19:24 INFO  App -  testJTA starting
2019-02-11 04:19:24 ERROR App -  simulateCrash starting
2019-02-11 04:19:24 INFO  BeneficiaryDaoJTAImpl - JTA Persist 
04:19:24.237 [main] WARN  org.hibernate.jpa.boot.internal.PersistenceXmlParser - HHH015018: Encountered multiple persistence-unit stanzas defining same name [RevolutUnit1]; persistence-unit names must be unique
04:19:24.237 [main] WARN  org.hibernate.jpa.boot.internal.PersistenceXmlParser - HHH015018: Encountered multiple persistence-unit stanzas defining same name [RevolutJTAUnit]; persistence-unit names must be unique
2019-02-11 04:19:24 INFO  TransactionalProvider - connecting to :java:/RevolutJTADS
04:19:24.351 [main] WARN  com.arjuna.ats.jta - ARJUNA016087: TransactionImple.delistResource - unknown resource
2019-02-11 04:19:24 INFO  TransactionalProvider - connecting to :java:/RevolutJTADS
2019-02-11 04:19:24 INFO  TransactionalProvider - connection to :java:/RevolutJTADS closed
2019-02-11 04:19:24 INFO  TransactionalProvider - connecting to :java:/RevolutJTADS
2019-02-11 04:19:24 INFO  TransactionalProvider - connection to :java:/RevolutJTADS closed
2019-02-11 04:19:24 INFO  TransactionalProvider - connecting to :java:/RevolutJTADS
2019-02-11 04:19:24 INFO  App - Beneficiaries in DB at start : [Beneficiary(phone=4444477777, accounts=[], firstName=Haar, middletName=null, lastName=Norving, address=null)]
2019-02-11 04:19:24 INFO  BeneficiaryDaoJTAImpl - JTA Persist 
2019-02-11 04:19:24 INFO  App - Beneficiaries in DB at end : [Beneficiary(phone=4444477777, accounts=[], firstName=Haar, middletName=null, lastName=Norving, address=null), Beneficiary(phone=88886666, accounts=[], firstName=Bool, middletName=null, lastName=James, address=null)]
2019-02-11 04:19:24 INFO  RevolutXAResource - Preparing RevolutXAResource
2019-02-11 04:19:24 INFO  RevolutXAResource - Committing RevolutXAResource
2019-02-11 04:19:24 INFO  RevolutXAResource - Crashing the system
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 0, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 14.122 s
[INFO] Finished at: 2019-02-11T04:19:25Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M3:test (default-test) on project moneytransfers: There are test failures.
[ERROR] 
[ERROR] Please refer to /Users/Massaq/eclipse-ensa/moneytransfers/target/surefire-reports for the individual test results.
[ERROR] Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] The forked VM terminated without properly saying goodbye. VM crash or System.exit called?
[ERROR] Command was /bin/sh -c cd /Users/Massaq/eclipse-ensa/moneytransfers && /Library/Java/JavaVirtualMachines/jdk-10.0.1.jdk/Contents/Home/bin/java -jar /Users/Massaq/eclipse-ensa/moneytransfers/target/surefire/surefirebooter205375833403719966.jar /Users/Massaq/eclipse-ensa/moneytransfers/target/surefire 2019-02-11T04-19-13_428-jvmRun1 surefire18054507885729475569tmp surefire_013836293129325957519tmp
[ERROR] Error occurred in starting fork, check output in log
[ERROR] Process Exit Code: 1
[ERROR] Crashed tests:
[ERROR] com.revolut.moneytransfers.test.MoneyTransfersAPITest
[ERROR] org.apache.maven.surefire.booter.SurefireBooterForkException: The forked VM terminated without properly saying goodbye. VM crash or System.exit called?
[ERROR] Command was /bin/sh -c cd /Users/Massaq/eclipse-ensa/moneytransfers && /Library/Java/JavaVirtualMachines/jdk-10.0.1.jdk/Contents/Home/bin/java -jar /Users/Massaq/eclipse-ensa/moneytransfers/target/surefire/surefirebooter205375833403719966.jar /Users/Massaq/eclipse-ensa/moneytransfers/target/surefire 2019-02-11T04-19-13_428-jvmRun1 surefire18054507885729475569tmp surefire_013836293129325957519tmp
[ERROR] Error occurred in starting fork, check output in log
[ERROR] Process Exit Code: 1
[ERROR] Crashed tests:
[ERROR] com.revolut.moneytransfers.test.MoneyTransfersAPITest
[ERROR] 	at org.apache.maven.plugin.surefire.booterclient.ForkStarter.fork(ForkStarter.java:670)
[ERROR] 	at org.apache.maven.plugin.surefire.booterclient.ForkStarter.run(ForkStarter.java:283)
[ERROR] 	at org.apache.maven.plugin.surefire.booterclient.ForkStarter.run(ForkStarter.java:246)
[ERROR] 	at org.apache.maven.plugin.surefire.AbstractSurefireMojo.executeProvider(AbstractSurefireMojo.java:1161)
[ERROR] 	at org.apache.maven.plugin.surefire.AbstractSurefireMojo.executeAfterPreconditionsChecked(AbstractSurefireMojo.java:1002)
[ERROR] 	at org.apache.maven.plugin.surefire.AbstractSurefireMojo.execute(AbstractSurefireMojo.java:848)
[ERROR] 	at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo(DefaultBuildPluginManager.java:137)
[ERROR] 	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:208)
[ERROR] 	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:154)
[ERROR] 	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:146)
[ERROR] 	at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:117)
[ERROR] 	at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:81)
[ERROR] 	at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build(SingleThreadedBuilder.java:56)
[ERROR] 	at org.apache.maven.lifecycle.internal.LifecycleStarter.execute(LifecycleStarter.java:128)
[ERROR] 	at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:305)
[ERROR] 	at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:192)
[ERROR] 	at org.apache.maven.DefaultMaven.execute(DefaultMaven.java:105)
[ERROR] 	at org.apache.maven.cli.MavenCli.execute(MavenCli.java:956)
[ERROR] 	at org.apache.maven.cli.MavenCli.doMain(MavenCli.java:290)
[ERROR] 	at org.apache.maven.cli.MavenCli.main(MavenCli.java:194)
[ERROR] 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
[ERROR] 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
[ERROR] 	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
[ERROR] 	at java.base/java.lang.reflect.Method.invoke(Method.java:564)
[ERROR] 	at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced(Launcher.java:289)
[ERROR] 	at org.codehaus.plexus.classworlds.launcher.Launcher.launch(Launcher.java:229)
[ERROR] 	at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode(Launcher.java:415)
[ERROR] 	at org.codehaus.plexus.classworlds.launcher.Launcher.main(Launcher.java:356)
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoExecutionException
