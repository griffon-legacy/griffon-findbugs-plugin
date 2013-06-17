
Enables Spock for testing Griffon projects
------------------------------------------

Plugin page: [http://artifacts.griffon-framework.org/plugin/findbugs](http://artifacts.griffon-framework.org/plugin/findbugs)


Runs FindBugs on your Java and Groovy code.

Usage
-----

Just execute the `findbugs` command target.

Configuration
-------------

The plugin requires no customization to run. However you can configure all options available to the [FindBugs Ant Task][2].
Place all configurations in `griffon-app/conf/BuildConfig.groovy` under the _findbugs_ prefix.

The plugin will automatically register all common source paths (`src/main` and `griffon-app/*`) however if you require additional
paths (for example those exposed by the [lang-bridge][3] plugin) then make sure to set the following configuration property in 
`BuildConfig.groovy`

        findbugs.additionalSources = ['src/common']

Scripts
-------

 * **findbugs** - runs Findbugs on the application's source'

[1]: http://findbugs.sourceforge.net/
[2]: http://findbugs.sourceforge.net/manual/anttask.html
[3]: /plugin/lang-bridge

