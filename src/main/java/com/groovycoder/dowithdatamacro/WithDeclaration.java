package com.groovycoder.dowithdatamacro;

import org.codehaus.groovy.ast.VariableScope;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.codehaus.groovy.ast.tools.GeneralUtils.*;

class WithDeclaration {
    private Map<String, Object> declarations = new HashMap<>();

    void addAssignment(String variable, Object value) {
        declarations.put(variable, value);
    }

    BlockStatement prependAssignments(List<Statement> statements) {
        BlockStatement block = block(new VariableScope(), createDeclarationStatements());
        block.addStatements(statements);
        return block;
    }

    private List<Statement> createDeclarationStatements() {
        return declarations.entrySet().stream()
                .map(entry -> declS(varX(entry.getKey()), constX(entry.getValue())))
                .collect(Collectors.toList());
    }

}
