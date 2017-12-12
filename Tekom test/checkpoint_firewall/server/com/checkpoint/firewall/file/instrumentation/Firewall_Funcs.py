# NetBoss imports
from com.harris.netboss.buildit.server.shared.japi import StoreFactory
from com.checkpoint.firewall.file.instrumentation import InstrumentationManager
instanceStore = StoreFactory.getStore(insAgentDomainName)

instrumentationManager = InstrumentationManager(\
    insAgentDomainName, propertySetter, instanceStore.get("SystemName"),\
    agentHome, agentVendor, agentName, agentVersion, customAgentHome, namespacePath)

instrumentationManager.initializeClasses()
instrumentationManager.performCustomInitialization()
instanceStore.put("InstrumentationManager", instrumentationManager)
instanceStore.put("CustomTask", instrumentationManager)
