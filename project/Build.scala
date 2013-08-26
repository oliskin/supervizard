import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "supervizard"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "org.seleniumhq.selenium" % "selenium-java" % "2.32.0" % "test")

  val main = play.Project(appName, appVersion, appDependencies).settings(

    testOptions in Test ~= { args =>
      for {
        arg <- args
        val ta: Tests.Argument = arg.asInstanceOf[Tests.Argument]
        val newArg = if (ta.framework == Some(TestFrameworks.JUnit)) ta.copy(args = List.empty[String]) else ta
      } yield newArg
    })

}
