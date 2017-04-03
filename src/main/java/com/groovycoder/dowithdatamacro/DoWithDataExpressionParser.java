package com.groovycoder.dowithdatamacro;

import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.stmt.Statement;

import java.util.LinkedList;
import java.util.List;

/**
 * Parses a strongly typed {@link DoWithDataExpression} from the backing clojure.
 */
class DoWithDataExpressionParser {

    private static final String LABEL_WITH = "with";

    DoWithDataExpression parse(ClosureExpression doWithDataCls) {
        BlockStatement clsBody = (BlockStatement) doWithDataCls.getCode();

        List<Statement> codeStatements = new LinkedList<>();
        List<ExpressionStatement> withStatements = new LinkedList<>();
        BinaryExpression variableNamesExp = null;

        // parse closure body
        boolean insideWithBlock = false;
        for (Statement s : clsBody.getStatements()) {

            String label = s.getStatementLabel();
            if (label != null) {

                if (label.equals(LABEL_WITH)) {
                    insideWithBlock = true;
                    variableNamesExp = (BinaryExpression) ((ExpressionStatement) s).getExpression();
                    continue;
                } else {
                    throw new UnsupportedOperationException("Discovered unexpected label: " + label);
                }

            }

            if (insideWithBlock) {
                withStatements.add((ExpressionStatement) s);
            } else {
                codeStatements.add(s);
            }

        }
        assert variableNamesExp != null;

        return new DoWithDataExpression(variableNamesExp, withStatements, codeStatements);

    }

}
