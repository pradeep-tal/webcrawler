package com.db.mysql

import scalikejdbc.config._
import scalikejdbc._
import java.util.Date
import java.io.ByteArrayInputStream
import java.sql.PreparedStatement

object TestConnection extends App {

  case class Emp(private val id: Int, private val name: String, private val date: String) {
    override def toString(): String = {

      "id =" + id + ", name = " + name + ", date= " + date

    }
  }

  val nameonly = (rs: WrappedResultSet) => rs.string("name")

  val mapRsToEmp = (rs: WrappedResultSet) => Emp(rs.int("id"), rs.string("name"), rs.string("created_at"))

  val dbName = Symbol("mysql");

  DBsWithEnv("prod").setup();

  parmaBinder()

  def parmaBinder(): Unit = {

    val bytes = scala.Array[Byte](1, 2, 3, 4, 5, 6, 7)

    val in = new ByteArrayInputStream(bytes)

    val bytesBinder = ParameterBinder(value = in, binder = (stmt: PreparedStatement, idx: Int) => stmt.setBinaryStream(idx, in, bytes.length))

    DB localTx { implicit session =>

      //sql"create table blob_example (id bigint,data blob)".execute().apply()

      sql"insert into blob_example(id,data) values (1,${bytesBinder})".update().apply()

    }

  }

  //DBs.setupAll()

  def queryList(): Unit = {

    val ids = DB readOnly { implicit session =>
      sql"select id from members ".map { x => x.long("id") }.list().apply()
    }

    ids.foreach { println(_) }

  }

  def firstOfMatched() = {
    val id = 2

    val name: Option[String] = DB readOnly { implicit session =>

      sql"select name from members where id= ${id} and name is not null".map { nameonly }.first().apply()

    }

    println(name)

  }

  val id1 = 12943

  val name1 = "pradeep"

  def updateApi(): Unit = {

    DB localTx { implicit session =>

      sql"""insert into emp(id,name,created_at) values (${id1},${name1},current_timestamp)""".update.apply()

      sql"""insert into emp(id,name,created_at) values (12945,'REDDY',current_timestamp)""".update.apply()

    }

  }

  def rsToEmp(): Unit = {

    val res = DB readOnly { implicit session =>
      sql"select id,name,created_at from emp".map(mapRsToEmp).collection.apply()

    }

    res.foreach(println)

  }

  DBs.close()

}