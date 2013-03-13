Jerver - _A Java-based Server_
==============================

Running tests:

    $ mvn test

_Make sure you have installed Maven._

You will need to have your `JAVA_HOME` environment variable poiting to a
1.7 JDK. In your `~/.bash_profile` or `~/.zshrc` you should have
something to this effect:

    export JAVA_HOME=`/usr/libexec/java_home -v 1.7`

You can also run some test coverage:

    $ mvn cobertura:cobertura

Then open the report at `/target/site/cobertura/index.html`
