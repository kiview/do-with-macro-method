package com.groovycoder.dowithdatamacro;

import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.Statement;

import java.util.List;

/**
 * Specificly typed AST representation of doWithData macro method.
 */
class DoWithDataExpression {
    private BinaryExpression variableNameExpression;
    private List<ExpressionStatement> variableValueExpressions;
    private List<Statement> statements;

    DoWithDataExpression(BinaryExpression variableNamesExp, List<ExpressionStatement> variableValueExpressions, List<Statement> statements) {
        this.variableNameExpression = variableNamesExp;
        this.variableValueExpressions = variableValueExpressions;
        this.statements = statements;
    }

    BinaryExpression variableNameExp() {
        return variableNameExpression;
    }

    List<ExpressionStatement> values() {
        return variableValueExpressions;
    }

    List<Statement> getStatements() {
        return statements;
    }
}
