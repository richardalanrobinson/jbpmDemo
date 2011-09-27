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
public class StartDemo {

	private static final String processId = "startdemo";
	
	public static final void main(String[] args) {
		try {
			
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			KnowledgeRuntimeLogger logger = createLogger(ksession);

			ksession.getWorkItemManager().registerWorkItemHandler("Human Task", new WSHumanTaskHandler());

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("customerId", "12345");
			params.put("productId", "B");
			params.put("shippingMethod", "ground");
			params.put("paymentMethod", "account"); // triggers xor gateway logic

			ksession.startProcess(processId, params);
			
			logger.close();
			
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KnowledgeRuntimeLogger createLogger(
			StatefulKnowledgeSession ksession) {
		int logIntervalInMilliseconds = 500;
		String logName = processId;
		KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newThreadedFileLogger(ksession, logName, logIntervalInMilliseconds);
		return logger;
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(processId + ".bpmn"), ResourceType.BPMN2);
		return kbuilder.newKnowledgeBase();
	}
	
}