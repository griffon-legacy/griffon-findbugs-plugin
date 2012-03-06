/*
 * Copyright 2010-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Andres Almiray
 */

import griffon.util.Environment

includeTargets << griffonScript("Package")

findbugsPluginBase = getPluginDirForName("findbugs").file as String

target(findbugs: "Run FindBugs on the application's sources") {
    depends(prepackage)

    ant.taskdef(name: "findbugs",
                classname: "edu.umd.cs.findbugs.anttask.FindBugsTask")
    
    jardir = ant.antProject.replaceProperties(buildConfig.griffon.jars.destDir)
    def findbugsConfig = buildConfig.findbugs

    Map findBugsOptions = [classpathRef: 'griffon.build.classpath']
    findBugsOptions.output = findbugsConfig.output ?: 'html'
    findBugsOptions.outputFile = findbugsConfig.outputFile ?: "${projectTargetDir}/findbugs.html"
    if(findbugsConfig.quietErrors) findBugsOptions.quietErrors = findbugsConfig.quietErrors
    findBugsOptions.reportLevel = findbugsConfig.reportLevel ?: 'medium'
    findBugsOptions.sort = !findbugsConfig.sort ? false : true
    if(findbugsConfig.debug) findBugsOptions.debug = findbugsConfig.debug
    findBugsOptions.effort = findbugsConfig.effort ?: 'default'
    if(findbugsConfig.styleSheet) findBugsOptions.styleSheet = findbugsConfig.styleSheet
    if(findbugsConfig.visitors) findBugsOptions.visitors = findbugsConfig.visitors
    if(findbugsConfig.omitVisitors) findBugsOptions.omitVisitors = findbugsConfig.omitVisitors
    if(findbugsConfig.excludeFilter) findBugsOptions.excludeFilter = findbugsConfig.excludeFilter
    if(findbugsConfig.includeFilter) findBugsOptions.includeFilter = findbugsConfig.includeFilter
    if(findbugsConfig.jvmargs) findBugsOptions.jvmargs = findbugsConfig.jvmargs
    if(findbugsConfig.timeout) findBugsOptions.timeout = findbugsConfig.timeout
    if(findbugsConfig.failOnError) findBugsOptions.failOnError = findbugsConfig.failOnError
    if(findbugsConfig.errorProperty) findBugsOptions.errorProperty = findbugsConfig.errorProperty
    if(findbugsConfig.warningsProperty) findBugsOptions.warningsProperty = findbugsConfig.warningsProperty
    findbugsConfig.additionalSources = findbugsConfig.additionalSources ?: []
    
    File cacheDir = griffonSettings.dependencyManager.ivySettings.defaultCache
    List pluginList = [] // "$cacheDir/findbugs/findbugs-coreplugin/jars/findbugs-coreplugin-0.8.4.jar"]
    ant.path(id: 'findbugs.plugin.list') {
        for(file in pluginList) {
            pathelement(location: file)
        }
    }
    findBugsOptions.pluginListRef = 'findbugs.plugin.list'

    ant.delete(file: findBugsOptions.outputFile, quiet: true, failOnError: false)
    ant.mkdir(dir: new File(findBugsOptions.outputFile).parentFile)
    ant.findbugs(findBugsOptions) {
        auxClasspath {
            fileset(dir: jardir , includes : '*.jar')
            fileset(dir: projectMainClassesDir, includes: '**/*.class')
        }
        sourcePath(path: "${basedir}/src/main")
        def excludedPaths = ["resources", "i18n", "conf"] // conf gets special handling
        for (dir in new File("${basedir}/griffon-app").listFiles()) {
            if (!excludedPaths.contains(dir.name) && dir.isDirectory())
                sourcePath(path: "$dir")
        }
        for(dir in findbugsConfig.additionalSources) {
            sourcePath(path: "$dir")
        }
        'class'(location: projectMainClassesDir)
        findbugsConfig.systemProperty.each { k, v ->
            systemProperty(name: k, value: v)
        }
    }
}

setDefaultTarget('findbugs')
