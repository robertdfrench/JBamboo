Korovasoft JBamboo
==================

This is a Java implementation of the highly scalable Bamboo system for Finite Element Analysis. It will serve as an architectural prototype for Bamboo++, which will be focused on hardware-specific optimizations. The original Mathematica implementation is available at [mcknightcasey/Bamboo](https://github.com/mcknightcasey/Bamboo).

Building
--------
JBamboo depends on the [matrix-toolkits-java](http://code.google.com/p/matrix-toolkits-java/) project, specifically version 0.9.14. Download the zip file, and throw

* arpack_combo-0.1.jar
* mtj-0.9.14.jar
* netlib-java-0.9.3.jar

into the lib/ directory in your jbamboo repo (you may need to mkdir it first).