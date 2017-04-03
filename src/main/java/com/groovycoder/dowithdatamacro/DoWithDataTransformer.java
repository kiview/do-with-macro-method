package com.groovycoder.dowithdatamacro;

import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.stmt.Statement;

import java.util.List;

import static org.codehaus.groovy.ast.tools.GeneralUtils.*;

class DoWithDataTransformer {

    Expression transform(ClosureExpression doWithDataCls) {
        DoWithDataExpression doWithDataExpression = new DoWithDataExpressionParser().parse(doWithDataCls);

        WithDeclarationsBlock withDeclarationsBlock = new WithBlockParser().parseWithBlock(doWithDataExpression);
        List<Statement> wrappedStatements = withDeclarationsBlock.wrapStatementsWithAssignments(doWithDataExpression);

        return callX(closureX(block(new VariableScope(), wrappedStatements)), "call");
    }


}
