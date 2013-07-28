package main.scala.com.binomialpricer.dsl

class BSParametersBuilder extends ParametersBuilder {

  var Sigma: Option[Double] = None

  protected override def transform2LatticeParameters: Option[Parameters] = {
    
    val paramsBS = for {
      interestRateBS <- R
      periodBS <- T
      nBS <- N
      sigmaBS <- Sigma
      stockPriceBS <- S0
      strikeBS <- K
    } yield {

      val periodLt = periodBS / nBS
      val iRateLt = Math.exp(interestRateBS * periodLt)
      val up = Math.exp(sigmaBS * Math.sqrt(periodLt))
      val dn = 1.0 / up
      val q = (iRateLt - dn) / (up - dn)

      Parameters(
        interestRate = iRateLt,
        period = periodLt,
        n = nBS,
        up = up,
        dn = dn,
        riskFreeProbability = q,
        stockPrice = stockPriceBS,
        strikePrice = strikeBS)
    }

    paramsBS
  }

}

