package main.scala.com.binomialpricer.dsl




object Start {

  def main(args: Array[String]): Unit = {

    var x = 99
    val p = Instrument with_BS_parameters { black_scholes =>
      {
        black_scholes R = 33
        black_scholes T = 44.
        black_scholes N = 99
        black_scholes Sigma = 0.56
        black_scholes S0 = 100
        black_scholes K = 77
      }
    } with_pricing_formula { (lattice_constant, Cu, Cd /*To do Node, Node and conversion Node ->Double*/ ) =>
      {
        val r = lattice_constant interestRate
        val q = lattice_constant riskFreeProbability

        val price = 1.0 / r * (q * Cu + (1 - q) * Cd)
        price
      }
    } price

    println(p)


    //println(SomeClass(33) == SomeClass(99))

  }

}