organization := "com.course.epam"

name := "101BD"

version := "1"

scalaVersion :="2.11.11"

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
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

