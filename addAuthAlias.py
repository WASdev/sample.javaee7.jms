import sys

AdminTask.setAdminActiveSecuritySettings('[-customProperties["com.ibm.websphere.security.JAASAuthData.removeNodeNameGlobal=true"]]')

userName = sys.argv[0]
password = sys.argv[1]
aliasName = "SIBAlias"
busName = "DEFAULT"
params = "-alias %s -user %s -password %s" % (aliasName, userName, password)

print "Creating auth alias"
AdminTask.createAuthDataEntry(params)

print "Add user to bus connector role"

params = "-user %s -bus %s" %(userName, busName)
AdminTask.addUserToBusConnectorRole(params)

tcf = AdminConfig.getid('/J2CConnectionFactory:jmsTCF/')
AdminTask.modifySIBJMSConnectionFactory(tcf, '[-containerAuthAlias SIBAlias]')

qcf = AdminConfig.getid('/J2CConnectionFactory:jndi_JMS_BASE_QCF/')
AdminTask.modifySIBJMSConnectionFactory(qcf, '[-containerAuthAlias SIBAlias]')

print "Save"

AdminConfig.save()