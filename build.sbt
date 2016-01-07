name := "play-scala"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(

  "org.postgresql"      %  "postgresql"       % "9.4-1202-jdbc42", // is needed because play-flyway doesn’t use the postgresql-async driver
  "org.flywaydb"        %% "flyway-play"      % "2.2.1",  //use Flyway Luke, as Play evolutions can't let us run db migration before starting sever.

  "org.scalikejdbc"     %% "scalikejdbc-async"             % "0.5.+", //provides non-blocking APIs to talk with PostgreSQL and MySQL in the JDBC way.
  "org.scalikejdbc"     %% "scalikejdbc-async-play-plugin" % "0.5.+", //manages the lifecycle of the connection pool used by scalikejdbc-async
  "com.github.mauricio" %% "postgresql-async"              % "0.2.+", //Reactive (non-blocking), JDBC-ish, Postgres driver.

  jdbc,
  cache,
  ws,
  specs2 % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
