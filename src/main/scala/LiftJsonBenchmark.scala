package liftbenchmark

import org.openjdk.jmh.annotations._
import java.util.concurrent.TimeUnit
import net.liftweb.json._

case class Person(firstName:String, lastName:String, address:String, age:Int)

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.SECONDS)
class LiftJsonBenchmark  {
  val p = Person("firstName", "lastName", "a long address with a carriage return\n ", 21)
 
  implicit val df = DefaultFormats
  
  val pString = Serialization.write(p)
 
  @Benchmark
  def serializeCaseClass = Serialization.write(p)
  
  @Benchmark
  def readCaseClass = (parse(pString)).extract[Person]
  
}
