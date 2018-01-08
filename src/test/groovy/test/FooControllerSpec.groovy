package test

import grails.buildtestdata.UnitTestDataBuilder
import grails.buildtestdata.mixin.Build
import spock.lang.Specification

@Build(Foo)
class FooControllerSpec extends Specification implements UnitTestDataBuilder {
    void "A domain class that has an email property"() {
        expect: "to build a valid email"
        // requiredMissingPropertyNames for test.Foo = [emailAddress]
        // Creating missing property domain test.Foo, propname emailAddress
        // Building value for test.Foo.emailAddress
        // test.Foo.email constraint, field before adjustment: emailAddress
        // test.Foo.emailAddress field after adjustment for email
        // Property name: emailAddress - Created value: a@b.com
        def foo = Foo.build()
        foo.emailAddress == 'a@b.com'

        and: "this still works, though not unique of course"
        def foo1 = Foo.buildLazy()
        foo1.emailAddress == 'a@b.com'

        and: "Fails: generates value [x] instead of a valid email"
        // requiredMissingPropertyNames for test.Foo = [emailAddress]
        // Creating missing property domain test.Foo, propname emailAddress
        // Building value for test.Foo.emailAddress
        // test.Foo.email constraint, field before adjustment: emailAddress
        // test.Foo.emailAddress field after adjustment for email
        // test.Foo.blank constraint, field before adjustment: a@b.com
        // test.Foo.emailAddress field after adjustment for blank
        // test.Foo.nullable constraint, field before adjustment: x  <--- bad value
        // Unable to find a constraint handler for test.Foo with constraint: nullable
        // test.Foo.unique constraint, field before adjustment: x
        // Unable to find a constraint handler for test.Foo with constraint: unique
        // Failed to generate a valid value for test.Foo.emailAddress
        Foo.build()

        /* Causes Runtime Exception:
        Unable to build valid test.Foo instance, errors:
            [[org.grails.datastore.mapping.validation.ValidationErrors: 1 errors
        Field error in object 'test.Foo' on field 'emailAddress':
            rejected value [x];
            codes [test.Foo.emailAddress.email.error.test.Foo.emailAddress,test.Foo.emailAddress.email.error.emailAddress,test.Foo.emailAddress.email.error.java.lang.String,test.Foo.emailAddress.email.error,foo.emailAddress.email.error.test.Foo.emailAddress,foo.emailAddress.email.error.emailAddress,foo.emailAddress.email.error.java.lang.String,foo.emailAddress.email.error,test.Foo.emailAddress.email.invalid.test.Foo.emailAddress,test.Foo.emailAddress.email.invalid.emailAddress,test.Foo.emailAddress.email.invalid.java.lang.String,test.Foo.emailAddress.email.invalid,foo.emailAddress.email.invalid.test.Foo.emailAddress,foo.emailAddress.email.invalid.emailAddress,foo.emailAddress.email.invalid.java.lang.String,foo.emailAddress.email.invalid,email.invalid.test.Foo.emailAddress,email.invalid.emailAddress,email.invalid.java.lang.String,email.invalid]; arguments [emailAddress,class test.Foo,x];
            default message [Property [{0}] of class [{1}] with value [{2}] is not a valid e-mail address]]]
        */
    }

}

