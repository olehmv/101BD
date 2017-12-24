name := "101BD"

organization := "com.course.epam"

version := "1"

scalaVersion :="2.11.11"
//sbt update-classifiers
//sbt eclipse
EclipseKeys.withSource := true

scalacOptions := Seq("-target:jvm-1.8")
// adding the tools.jar to the unmanaged-jars seq
unmanagedJars in Compile ~= {uj => 
    Seq(Attributed.blank(file(System.getProperty("java.home").dropRight(3)+"lib/tools.jar"))) ++ uj
}
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-common
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.6.0" % "provided"
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-client
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.6.0" % "provided"
// https://mvnrepository.com/artifact/junit/junit
libraryDependencies += "junit" % "junit" % "4.11" % "test"

// https://mvnrepository.com/artifact/org.apache.mrunit/mrunit
libraryDependencies += "org.apache.mrunit" % "mrunit" % "1.1.0" % Test classifier "hadoop2" 

