package main.scala.com.binomialpricer

package object dsl {

  implicit def double2Option(d: Double) = Some(d)

  implicit def instrument2ParameterHelper(i: Instrument) = new ParameterHelper

  implicit def parameters2PriceHelper(p: Parameters) = new PriceHelper(p)
  
}