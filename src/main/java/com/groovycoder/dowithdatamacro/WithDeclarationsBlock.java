package com.groovycoder.dowithdatamacro;

import org.codehaus.groovy.ast.stmt.Statement;

import java.util.List;
import java.util.stream.Collectors;

class WithDeclarationsBlock {
    private List<WithDeclaration> declarations;

    WithDeclarationsBlock(List<WithDeclaration> declarations) {
        this.declarations = declarations;
    }

    List<Statement> wrapStatementsWithAssignments(DoWithDataExpression e) {

        List<Statement> statements = e.getStatements();

        return declarations.stream()
                .map(withDeclaration -> withDeclaration.prependAssignments(statements))
                .collect(Collectors.toList());
    }

}
