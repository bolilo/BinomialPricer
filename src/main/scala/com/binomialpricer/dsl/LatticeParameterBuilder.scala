package main.scala.com.binomialpricer.dsl

class LatticeParameterBuilder extends ParametersBuilder {

  var UP: Option[Double] = None
  var DN: Option[Double] = None

  protected override def transform2LatticeParameters: Option[Parameters] = {

    val paramsLt = for {
      interestRateLt <- R
      periodLt <- T
      nLt <- N
      stockPriceLt <- S0
      strikeLt <- K
      up <- UP
      dn <- DN
    } yield {

      val q = (interestRateLt - dn) / (up - dn)

      Parameters(
        interestRate = interestRateLt,
        period = periodLt/nLt,
        n = nLt,
        up = up,
        dn = dn,
        riskFreeProbability = q,
        stockPrice = stockPriceLt,
        strikePrice = strikeLt)
    }
    paramsLt
  }

}