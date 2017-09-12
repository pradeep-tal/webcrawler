package com.db.mysql

import slick.jdbc.MySQLProfile.api._

object SlickExample extends App {

  class Suppliers(tag: Tag) extends Table[(Int, String, String, String, String, String)](tag, "SUPPLIERS") {
    def id = column[Int]("SUP_ID", O.PrimaryKey)
    def name = column[String]("SUP_NAME")
    def street = column[String]("STREET")
    def city = column[String]("CITY")
    def state = column[String]("STATE")
    def zip = column[String]("ZIP")

    def * = (id, name, street, city, state, zip)
  }

  val suppliers = TableQuery[Suppliers]

  // Definition of the COFFEES table
  class Coffees(tag: Tag) extends Table[(String, Int, Double, Int, Int)](tag, "COFFEES") {
    def name = column[String]("COF_NAME", O.PrimaryKey)
    def supID = column[Int]("SUP_ID")
    def price = column[Double]("PRICE")
    def sales = column[Int]("SALES")
    def total = column[Int]("TOTAL")
    def * = (name, supID, price, sales, total)

    def supplier = foreignKey("SUP_FK", supID, suppliers)(_.id)
  }
  val coffees = TableQuery[Coffees]
  val db = Database.forConfig("mysqlcon")

  try {
    print("In try")

    val setup = DBIO.seq(

      // (suppliers.schema ++ coffees.schema).create,

      suppliers += (101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),

      suppliers += (49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),

      suppliers += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"),

      coffees ++= Seq(
        ("Colombian", 101, 7.99, 0, 0),
        ("French_Roast", 49, 8.99, 0, 0),
        ("Espresso", 150, 9.99, 0, 0),
        ("Colombian_Decaf", 101, 8.99, 0, 0),
        ("French_Roast_Decaf", 49, 9.99, 0, 0)))

    print(" SetUp " + setup)

    val setupFuture = db.run(setup)

  } catch {
    case t: Throwable => {
      print(t.printStackTrace())
      println(t.getMessage)
    }

  } finally {
    db.close()
  }

}
