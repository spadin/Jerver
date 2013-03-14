Jerver - _A Java-based Server_
==============================

Usage
-----

    $ java -jar /path/to/jerver.jar -p 9999 -d /path/to/public

Option flags:

    -p  port
    -d  public directory

Development
-----------

### Prerequisites

You will need to have your `JAVA_HOME` environment variable poiting to a
1.7 JDK.

You can set this up in your `~/.bash_profile` or `~/.zshrc` by adding something to this effect:

    export JAVA_HOME=`/usr/libexec/java_home -v 1.7`

### Running tests

Enter the following to run the JUnit test suite:

    $ mvn test

_Maven 3 required._

### Generating coverage report

You can also run some test coverage:

    $ mvn cobertura:cobertura

Then open the report at `/target/site/cobertura/index.html`

### Packaging

To generate a new package from the project

    $ mvn clean package

You should now find `jerver.jar ` inside the `target` directory.
