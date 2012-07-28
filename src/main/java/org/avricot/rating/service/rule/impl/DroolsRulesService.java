package org.avricot.rating.service.rule.impl;

import javax.inject.Inject;

import org.avricot.rating.service.rule.IRulesService;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DroolsRulesService<O> implements IRulesService<O> {

    private final String rulesUrl;

    @Inject
    public DroolsRulesService(@Value("${rating.rules}") final String rulesUrl) {
        this.rulesUrl = rulesUrl;
    }

    @Override
    public O executeRules(final O result, final String resultName, final String[] golbalNames, final Object[] globalObjects, final Object... inputs) {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource(rulesUrl), ResourceType.DRL);
        if (kbuilder.hasErrors()) {
            for (KnowledgeBuilderError error : kbuilder.getErrors()) {
                System.err.println(error.getMessage());
            }
            throw new IllegalStateException("kbuilder has errors");
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        StatefulKnowledgeSession session = kbase.newStatefulKnowledgeSession();
        try {
            for (int i = 0; i < golbalNames.length; i++) {
                session.setGlobal(golbalNames[i], globalObjects[i]);
            }
            for (Object o : inputs) {
                session.insert(o);
            }
            session.insert("test");
            session.setGlobal(resultName, result);
            session.fireAllRules();
        } finally {
            session.dispose();
        }
        return result;
    }
}
