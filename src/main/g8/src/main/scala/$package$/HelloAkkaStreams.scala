package com.github.dnvriend

import akka.NotUsed
import akka.stream.scaladsl.Source
import com.github.dnvriend.lambda.{HttpRequest, HttpResponse, KinesisEvent, SamContext}
import com.github.dnvriend.lambda.annotation.{HttpHandler, KinesisConf}
import com.github.dnvriend.sam.akka.stream._
import play.api.libs.json.{Format, JsValue, Json}
import com.github.dnvriend.lambda.JsonReads._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@HttpHandler(path = "/hello", method = "get")
class HelloAkkaStreams extends JsonApiGatewayHandler[Nothing] {

  override def handle(src: Source[Nothing, NotUsed],
                      pathParams: Map[String, String],
                      requestParams: Map[String, String],
                      request: HttpRequest,
                      ctx: SamContext): Future[HttpResponse] = {

    Source.single("Hello World")
      .map(msg => s"$msg from sam-akka-stream!!")
      .via(mapResponse)
    }
}

object Person {
  implicit val format: Format[Person] = Json.format
}
case class Person(name: String)

object PersonResponse {
  implicit val format: Format[PersonResponse] = Json.format
}
case class PersonResponse(name: String, age: Int)

@HttpHandler(path = "/person", method = "put")
class PutPerson extends JsonApiGatewayHandler[Person] {

  override def handle(src: Source[Person, NotUsed],
                      pathParams: Map[String, String],
                      requestParams: Map[String, String],
                      request: HttpRequest,
                      ctx: SamContext): Future[HttpResponse] = {

    src
      .map(msg => s"${msg.name} from sam-akka-stream!!")
      .map(name => PersonResponse(name, 42))
      .via(mapResponse)
    }
}

//@KinesisConf(stream = "import:order-intake:order-intake-stream", startingPosition = "TRIM_HORIZON")
//class TestProcessor extends KinesisEventHandler {
//  override def outputStreamName(stage: String): String = s"order-master-$stage-order-master-stream"
//  override def handle(src: Source[KinesisEvent, NotUsed], ctx: SamContext): Future[List[(String, JsValue)]] = {
//    src.map(_.dataAsString)
//      .map { e => println(e); e }
//      .map(_ => Json.obj("key" -> "value"))
//      .map(json => ("STATIC_KEY", json))
//  }
//}