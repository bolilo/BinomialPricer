package main.scala.com.binomialpricer.dsl

  class PriceHelper(val p: Parameters) {

    def with_pricing_formula(f: (Parameters, Double, Double) => Double): PriceHelper = {
 
      this
    }

    def price(): Double = {
      22.0
    }

  }
