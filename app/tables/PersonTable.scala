package tables

/**
 * Created with IntelliJ IDEA.
 * User: lzhou
 * Date: 22/09/2015
 * Time: 3:29 PM
 */

import models.Person
import slick.driver.JdbcProfile

trait PersonTable {
  protected val driver: JdbcProfile
  import driver.api._
  class Persons(tag: Tag) extends Table[Person](tag, "PERSON") {

    def name = column[String]("NAME", O.PrimaryKey)
    def sex = column[String]("SEX")

    def * = (name, sex) <> ((Person.apply _).tupled, Person.unapply _)
  }
}
