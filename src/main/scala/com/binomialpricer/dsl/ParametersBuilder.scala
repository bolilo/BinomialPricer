package main.scala.com.binomialpricer.dsl

trait ParametersBuilder {
  
  var R: Option[Double] = None
  var T: Option[Double] = None
  var N: Option[Double] = None
  var S0: Option[Double] = None
  var K: Option[Double] = None
  
  def build: Parameters = {

    transform2LatticeParameters match {
      case Some(x) => x
      case None => throw new IllegalArgumentException("bad argument");
    }

  }
  
  protected def transform2LatticeParameters: Option[Parameters]

}