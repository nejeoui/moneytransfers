# Money Transfers
## Java lightweight Restful API for Money Transfers

###TODO List :
Add support for JTA :
			- Add Support for JNDI
			- Define a new EMFProducer for JTA Datasource
			- add AcoountDaoImplJTA
			- add TransferDaoImplJTA
			- add BeneficiaryDaoImplJTA
Add JUinit Test for with 2 phase commit protocol:
			-  AcoountDaoImplJTA
			-  TransferDaoImplJTA
			-  BeneficiaryDaoImplJTA
	Test JTA Recovery mode.
	Adding Support for Distributed Transactions With the 2PC Protocol
