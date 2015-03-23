package com.github.fakemongo.integration

import com.mongodb.BasicDBObject
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import com.github.fakemongo.Fongo

@RunWith(classOf[JUnitRunner])
class FongoScalaTest extends FunSuite with BeforeAndAfter {

  var fongo: Fongo = _

  before {
    fongo = new Fongo("InMemoryMongo")
  }

  test("Fongo should not throw npe") {
    val db = fongo.getDB("myDB")
    val col = db.createCollection("myCollection", new BasicDBObject())
    val result = col.findOne()
    assert(result == null)
  }

  test("Insert should work") {
    val collection = fongo.getDB("myDB").createCollection("myCollection", new BasicDBObject())

    collection.insert(new BasicDBObject("basic", "basic"))

    assert(1 === collection.count())
  }
}
