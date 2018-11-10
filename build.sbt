name := "orel-codes-contest-json"
 
version := "1.0" 
      
lazy val `orel-codes-contest-json` = (project in file(".")).enablePlugins(PlayJava)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
scalaVersion := "2.11.11"

      