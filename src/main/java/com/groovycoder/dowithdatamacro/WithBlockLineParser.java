package com.groovycoder.dowithdatamacro;

import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.Expression;

import java.util.LinkedList;
import java.util.List;

/**
 *  Parses a {@link BinaryExpression} inside the with: block into a list of {@link Expression Expressions}.
 *
 * @param <T> The {@link org.codehaus.groovy.ast.expr.Expression} the line is cast to
 */
class WithBlockLineParser<T> {

    List<T> parseLine(BinaryExpression exp, Class<T> cls) {

        List<T> constantExpressions = new LinkedList<>();

        Expression leftExpression = exp.getLeftExpression();

        if (leftExpression.getClass().equals(cls)) {
            T leftConstantExpression = (T) leftExpression;
            constantExpressions.add(leftConstantExpression);
        } else {
            BinaryExpression leftBinaryExpression = (BinaryExpression) leftExpression;
            List<T> leftConstantExpressions = parseLine(leftBinaryExpression, cls);
            constantExpressions.addAll(leftConstantExpressions);
        }

        T rightExpression = (T) exp.getRightExpression();
        constantExpressions.add(rightExpression);

        return constantExpressions;

    }

}
