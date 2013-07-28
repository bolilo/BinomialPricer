package main.scala.com.binomialpricer.dsl

case class Parameters(interestRate: Double, period: Double, n: Double,
  up: Double, dn: Double, riskFreeProbability: Double,
  stockPrice: Double, strikePrice: Double) {

  def ~=(that: Parameters): Boolean = {

    val epsilon = 0.0001

    def lt(d: Double) = d.abs < epsilon

    lt(interestRate - that.interestRate) &&
      lt(period - that.period) &&
      lt(n - that.n) &&
      lt(up - that.up) &&
      lt(dn - that.dn) &&
      lt(riskFreeProbability - that.riskFreeProbability) &&
      lt(stockPrice - that.stockPrice) &&
      lt(strikePrice - that.strikePrice)
  }

}
