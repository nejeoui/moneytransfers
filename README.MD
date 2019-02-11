# Money Transfers
===========================================================================
## Java lightweight Restful API for Money Transfers
### Money Transfers API run as a standalone application :
Technologies : 
JPA
Hibernate
HickariCP : High performances connections pool manager
JNDI : using embedded jnp.server
JTA  : Narayana implementation 
CDI 2.0 : Weld 
JAX-RS  2.1: Jersey 

## Project structure :
Maven project 

#download the sources :
```
git clone https://github.com/nejeoui/moneytransfers.git
cd moneytransfers
```

## Run the Junit test
the project depends on maven-surefire-plugin and JersyTest to run Junit test
```
mvn test
runs the following tests :
1-createNewBeneficiary
  Service Endpoint : http://localhost:8080/Beneficiary/newBeneficiary
2-findBeneficiary
  Service Endpoint : http://localhost:8080/Beneficiary/{phone}
3-createNewAccount
   Service Endpoint : http://localhost:8080/Account/newAccount
4-getAccountByPhoneCurrency (retrieve account by ID)
5-getAllAccounts of a beneficiary 
  Service Endpoint : http://localhost:8080/Account/{phone}
6-getAllAccounts retrieve all accounts
  Service Endpoint : http://localhost:8080/Account/AllAccounts/
7-TopUp an account
 Service Endpoint : http://localhost:8080/Account/topUpAccount
8-Money Transfer between two accounts 
 Service Endpoint : http://localhost:8080/Transfers/transfer/
9-Add Beneficiary using JTA 
10-Simulate System crash and Recovery mode
```
## Run the Junit Tests
```
mvn test
```
## Run the RESTful API
```
mvn exec:java -Dexec.mainClass="com.revolut.moneytransfers.App"
```
# Future enhancement 
- Add support for distributed transaction and Two Phase Commit Protocol 2PC
- Security 
- Recurrent payment processing using Batch (JSR 352)
# Logs
1. [Test Log](TestLog.MD)
2. [Run Log](excLog.MD)
3. [Init System Crash Log](InitSystemCrashLog.MD)
4. [Transaction Recovery after crash](DBRecoveryAfterCrashLog.MD)
