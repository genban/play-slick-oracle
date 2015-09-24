package models

/**
 * Created with IntelliJ IDEA.
 * User: lzhou
 * Date: 21/09/2015
 * Time: 2:52 PM
 */

import play.api.libs.json.Json

case class Person(name: String, sex: String)

object Person {
  implicit val personFormat = Json.format[Person]
}
