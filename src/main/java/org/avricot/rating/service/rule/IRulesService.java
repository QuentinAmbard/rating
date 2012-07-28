package org.avricot.rating.service.rule;

public interface IRulesService<O> {

    O executeRules(O result, String resultName, String[] golbalNames, Object[] globalObjects, Object... inputs);

}