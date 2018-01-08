package test

class Foo {

    String emailAddress

    static constraints = {
        emailAddress email: true, unique: true // or false, still fails, only way to fix is to remove unique
    }
}
