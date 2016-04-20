CraftMagic
====

A Java sources generator plugin for Gradle. A hand to accomplish tasks kind of generate and merge java source code from simple JSON files.

Install
---


Usage
---

This project will find all JSON files on craftMagic.inputDir and try to convert to a simple class.

JSON sample file: `com.test.provider.ClassOne.json`

````json
{
	"methods":[{
		"name": "executeQuery1",

		"type": "Query",

		"params": [{
			"type": "Map",
			"name": "param"
		}],

		"body": [{
			"plain": "return executeDefaultQuery(1, (HashMap) param);"
		}]
	}]
}
````

this will generate a new javaclass called ````ClassOne```` over ````com.test.provider```` package


````java
package com.test.provider;

public class ClassOne {
	Query executeQuery1(Map param) {
		return executeDefaultQuery(1, (HashMap) param); 
	}
}
````

Configuration
---
````groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.craft.magic:craftMagic:0.2-SNAPSHOT"
  }
}

apply plugin: "craft.magic.craftMagic"

craftMagic {
	from './'
	to './src/'
}
````
--

### author
[brunoguerra](github.com/brunoguerra)
