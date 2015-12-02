"""
/*
 * COPYRIGHT LICENSE: This information contains sample code provided in source
 * code form. You may copy, modify, and distribute these sample programs in any 
 * form without payment to IBM for the purposes of developing, using, marketing 
 * or distributing application programs conforming to the application programming 
 * interface for the operating platform for which the sample code is written. 
 * 
 * Notwithstanding anything to the contrary, IBM PROVIDES THE SAMPLE SOURCE CODE 
 * ON AN "AS IS" BASIS AND IBM DISCLAIMS ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING, 
 * BUT NOT LIMITED TO, ANY IMPLIED WARRANTIES OR CONDITIONS OF MERCHANTABILITY, 
 * SATISFACTORY QUALITY, FITNESS FOR A PARTICULAR PURPOSE, TITLE, AND ANY WARRANTY OR 
 * CONDITION OF NON-INFRINGEMENT. IBM SHALL NOT BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR
 * OPERATION OF THE SAMPLE SOURCE CODE. IBM HAS NO OBLIGATION TO PROVIDE
 * MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS OR MODIFICATIONS TO THE SAMPLE
 * SOURCE CODE.
 * 
 * (C) Copyright IBM Corp. 2015.
 * 
 * All Rights Reserved. Licensed Materials - Property of IBM.  
 */
 """
 
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