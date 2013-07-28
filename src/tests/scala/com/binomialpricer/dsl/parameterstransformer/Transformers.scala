package tests.scala.com.binomialpricer.dsl.parameterstransformer

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import main.scala.com.binomialpricer.dsl._

@RunWith(classOf[JUnitRunner])
class Transformers extends FunSuite {

  val interestRate = 0.02
  val period = 0.25
  val n = 15
  val up = .56
  val dn = .44
  val stockPrice = 100
  val strikePrice = 110

  test("binomial parameters") {

    val lattice_p = Instrument with_binomial_parameters { lattice_parameters =>
      {
        lattice_parameters R = this.interestRate
        lattice_parameters T = this.period
        lattice_parameters N = this.n
        lattice_parameters UP = this.up
        lattice_parameters DN = this.dn
        lattice_parameters S0 = this.stockPrice
        lattice_parameters K = this.strikePrice
      }
    }

    val q = (interestRate - dn) / (up - dn)

    val p = Parameters(
      interestRate = this.interestRate,
      period = this.period / this.n,
      n = this.n,
      up = this.up,
      dn = this.dn,
      riskFreeProbability = q,
      stockPrice = this.stockPrice,
      strikePrice = this.strikePrice)

    assert(p ~= lattice_p)
  }

  test("BS parameters") {

    val BS_p = Instrument with_BS_parameters { black_scholes =>
      {
        black_scholes R = this.interestRate
        black_scholes T = this.period
        black_scholes N = this.n
        black_scholes Sigma = 0.3
        black_scholes S0 = this.stockPrice
        black_scholes K = this.strikePrice
      }
    }

    val p = Parameters(
      interestRate = 1.0003333888950623,
      period = 0.01666666666666666,
      n = this.n,
      up = 1.0394896104013376,
      dn = 0.9620105771080376,
      riskFreeProbability = 0.49462170806845446,
      stockPrice = this.stockPrice,
      strikePrice = this.strikePrice)

    assert(p ~= BS_p)

  }

}