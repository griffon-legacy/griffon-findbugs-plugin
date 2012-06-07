/*
 * Copyright 2010-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Andres Almiray
 */
class FindbugsGriffonPlugin {
    // the plugin version
    String version = '0.4'
    // the version or versions of Griffon the plugin is designed for
    String griffonVersion = '1.0.0 > *'
    // the other plugins this plugin depends on
    Map dependsOn = [:]
    // resources that are included in plugin packaging
    List pluginIncludes = []
    // the plugin license
    String license = 'Apache Software License 2.0'
    // Toolkit compatibility. No value means compatible with all
    // Valid values are: swing, javafx, swt, pivot, gtk
    List toolkits = []
    // Platform compatibility. No value means compatible with all
    // Valid values are:
    // linux, linux64, windows, windows64, macosx, macosx64, solaris
    List platforms = []
    // URL where documentation can be found
    String documentation = ''
    // URL where source can be found
    String source = 'https://github.com/griffon/griffon-findbugs-plugin'
    // Install as a framework plugin
    boolean framework = true

    List authors = [
            [
                    name: 'Andres Almiray',
                    email: 'aalmiray@yahoo.com'
            ]
    ]
    String title = 'Enables Spock for testing Griffon projects'
    // accepts Markdown syntax. See http://daringfireball.net/projects/markdown/ for details
    String description = '''
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
'''
}