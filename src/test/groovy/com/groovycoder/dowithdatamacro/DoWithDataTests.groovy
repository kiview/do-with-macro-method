package com.groovycoder.dowithdatamacro

class DoWithDataTests extends GroovyTestCase {

    void "test that do with data works for multiple statements and two params"() {

        assertScript '''
        
            doWithData {
              
              println a
              println b
              
              with:
              a | b
              "foo" | "bar"
              "hello" | "world"
              1 | 2
            }

        '''

    }


    void "test that do with data works for multiple statements and three params"() {

        assertScript '''
        
            doWithData {
              
              println a
              println b
              println c
              
              with:
              a | b | c
              "foo" | "bar" | "baz"
              1 | "bar" | 5
            }

        '''

    }

}
