package com.groovycoder.dowithdatamacro;

import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;

import java.util.LinkedList;
import java.util.List;

class WithBlockParser {

    WithDeclarationsBlock parseWithBlock(DoWithDataExpression doWithDataExp) {

        BinaryExpression variableNameExp = doWithDataExp.variableNameExp();
        List<ExpressionStatement> values = doWithDataExp.values();

        List<VariableExpression> variableExpressions = parseVariableNames(variableNameExp);

        List<WithDeclaration> valueAssignments = new LinkedList<>();

        for (ExpressionStatement s : values) {
            BinaryExpression e = (BinaryExpression) s.getExpression();

            List<ConstantExpression> constantExpressions = parseConstants(e);
            assert constantExpressions.size() == variableExpressions.size();

            WithDeclaration assignment = new WithDeclaration();
            for (int i = 0; i < variableExpressions.size(); i++) {
                String name = variableExpressions.get(i).getName();
                Object value = constantExpressions.get(i).getValue();
                assignment.addAssignment(name, value);
            }

            valueAssignments.add(assignment);
        }

        return new WithDeclarationsBlock(valueAssignments);
    }

    private List<VariableExpression> parseVariableNames(BinaryExpression exp) {
        WithBlockLineParser<VariableExpression> variableExpressionWithBlockLineParser = new WithBlockLineParser<>();
        return variableExpressionWithBlockLineParser.parseLine(exp, VariableExpression.class);
    }
    
    private List<ConstantExpression> parseConstants(BinaryExpression exp) {
        WithBlockLineParser<ConstantExpression> constantExpressionWithBlockLineParser = new WithBlockLineParser<>();
        return constantExpressionWithBlockLineParser.parseLine(exp, ConstantExpression.class);
    }



}
