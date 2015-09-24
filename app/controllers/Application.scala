package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import models.Person
import play.api.libs.json.Json
import play.api.db.slick.HasDatabaseConfig
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import tables.PersonTable
import play.api.libs.concurrent.Execution.Implicits._
import oracle.jdbc.driver.OracleDriver

class Application extends Controller with PersonTable with HasDatabaseConfig[JdbcProfile] {

  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import driver.api._

  //create an instance of the table
  val Persons = TableQuery[Persons]

//  def index = Action {
//    Ok(views.html.index(Nil))
//  }

  def index = Action.async {
    db.run(Persons.result).map(rs => Ok(views.html.index(rs.toList)))
  }

  val personForm : Form[Person] = Form {
    mapping(
      "name" -> text,
      "sex" -> text
    )(Person.apply)(Person.unapply)
  }

  def insert = Action.async { implicit rs =>
    val person = personForm.bindFromRequest.get
    db.run(Persons += person).map(_ => Redirect(routes.Application.index))
  }

}
