package com.groovycoder.dowithdatamacro;

import groovy.lang.Closure;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.macro.runtime.Macro;
import org.codehaus.groovy.macro.runtime.MacroContext;

public class DoWithDataMacroMethod {

    public static void doWithData(Closure closure) {
        throw new UnsupportedOperationException("Not allowed to call macro method directly");
    }

    @Macro
    public static Expression doWithData(MacroContext ctx, ClosureExpression doWithDataCls) {
        DoWithDataTransformer transformer = new DoWithDataTransformer();
        return transformer.transform(doWithDataCls);
    }

}
