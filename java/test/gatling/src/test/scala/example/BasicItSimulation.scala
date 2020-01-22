package example

/*
Необходимые библиотеки для работы:
io.gatling.core.Predef._ - функции ядра
import io.gatling.http.Predef._ - функции HTTP
import scala.concurrent.duration._ - функции для временных интервалов, чтобы можно было писать `4 minutes`, `15 seconds`
*/

import io.gatling.core.Predef._ // required for Gatling core structure DSL
import io.gatling.jdbc.Predef._ // can be omitted if you don't use jdbcFeeder
import io.gatling.http.Predef._ // required for Gatling HTTP DSL

import scala.concurrent.duration._ // used for specifying duration unit, eg "5 second"

/*
Основной класс теста.
Именно этот класс, расширяемый от Simulation, ищет фреймворк во время запуска.
*/
class BasicSimulation extends Simulation {

  /*
  Настройки для HTTP
  */
  val httpConf = http
    .baseUrls("http://url")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  /*
  Сценарий скриптов. Здесь в виде "цепочки" пишем запросы, таймеры, отчеты (pacing) и все остальное связанное со сценарием скриптов.
  */
  val scn = scenario("BasicSimulation").repeat(10)(
    exec(
      http("request_1")
        .get("/testTime")
        .check(status.is(200))
    )
  )


  /*
  Сценарий нагрузки. Здесь указываем характер генерируемой нагрузки, модель поднятия пользователей, их количество и длительность нагрузки.
  */
  setUp(
//    scn.inject(atOnceUsers(100))
    scn.inject(constantConcurrentUsers(100) during (15 seconds))
  ).protocols(httpConf)
}
