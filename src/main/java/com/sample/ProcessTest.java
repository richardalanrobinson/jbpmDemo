package com.sample;

import java.util.HashMap;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.process.instance.impl.demo.SystemOutWorkItemHandler;
import org.jbpm.process.workitem.wsht.WSHumanTaskHandler;

/**
 * This is a sample file to launch a process.
 */
public class ProcessTest {

	private static final String processId = "orderEntry1";
	
	public static final void main(String[] args) {
		try {
			
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			
			//ksession.getWorkItemManager().registerWorkItemHandler("Log", new SystemOutWorkItemHandler());
			ksession.getWorkItemManager().registerWorkItemHandler("Human Task", new WSHumanTaskHandler());

			int logIntervalInMilliseconds = 500;
			String logName = "orderEntry";
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newThreadedFileLogger(ksession, logName, logIntervalInMilliseconds);
			//KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("customerId", "12345");
			params.put("productId", "ABCD");
			params.put("price", 51);
			params.put("paymentMethod", "account");
			params.put("approved", false);
			
			ksession.startProcess(processId, params);
			
			logger.close();
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(processId + ".bpmn"), ResourceType.BPMN2);
		return kbuilder.newKnowledgeBase();
	}
	
}