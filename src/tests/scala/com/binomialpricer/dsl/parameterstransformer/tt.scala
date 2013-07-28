package tests.scala.com.binomialpricer.dsl.parameterstransformer


                  
import scala.math._

object Start {

  def main(args: Array[String]) {

    val r = 0.02

    val T = 0.25

    val c = 0.0

    val n = 15

    val sigma = 0.3

    val S0 = 100

    val K = 110

    val h = 3 //n+1

    americanOprtionPrice(r, c, sigma, T, n, S0, K)

    val p = putPriceAtT(S0, K, 1.4, 1 / 1.4)_

    println(populatePutPriceList(h, p))

    def f(a: Double, b: Double) = a - b
    print(populateBinomialPrice(List(5, 10), f))
    
    println(americanOprtionPrice(r, c, sigma, T, n, S0, K))
  }

  def americanOprtionPrice(r: Double, c: Double, sigma: Double, T: Double, n: Int, S0: Double, K: Double) =
    {
      val t = T / n

      println("t= "+t)
      
      val RDiv = exp((r - c) * t)

      println(RDiv)
      
      val un = exp(sigma * sqrt(t))

      println(un)
      
      
      val dn = 1. / un

       println(dn)
     
      
      val qn = (RDiv - dn) / (un - dn)

       println(qn)
     
      //val h = n + 1

      val putP = putPriceAtT(S0, K, un, dn)_

      val binomialP = binomialPrice(RDiv, qn, K)_

      def price0(hStart: Int) =
        {
          val pricesT = populatePutPriceList(hStart, putP)

          def prices_t(h: Int, optimalP: List[Double]): Double =
            {
              if (h == 1) optimalP.head else {
                val bP = populateBinomialPrice(optimalP, binomialP)

                val pP = populatePutPriceList(h, putP)

                val opt = optimalPrices(pP, bP)

                prices_t(h - 1, opt)
              }
            }
          
          prices_t(hStart, pricesT)

        }
      
      price0(n+1)

    }

  def binomialPrice(r: Double, qn: Double, K: Double)(cUp: Double, cDn: Double): Double =
    {
      val c0 = 1 / r * (qn * cUp + (1 - qn) * cDn)
      c0
    }

  def putPriceAtT(S0: Double, K: Double, un: Double, dn: Double)(h: Int, i: Int): Double =
    {
      def v(d: Double) = max(0, K - S0 * pow(d, i))

      val condition = 2 * i - 1

      if (condition > h) v(un) else if (condition < h) v(dn) else max(0, K - S0)
    }

  //val callPrice = -putPrice(_)(_)

  def optimalPrices(putPrices: List[Double], bPrices: List[Double]): List[Double] =
    {
      (putPrices zip bPrices) map (e => max(e._1, e._2))
    }

  //TODO check ordering
  def populateBinomialPrice(prices: List[Double], bPrice: (Double, Double) => Double) =
    {
      val pairs = prices zip prices tail

      pairs.map(e => bPrice(e._1, e._2))
    }

  def populatePutPriceList(h: Int, price: (Int, Int) => Double): List[Double] = {

    def populatePriceListRec(i: Int, acc: List[Double]): List[Double] = {
      if (i == 0) acc
      else {
        val x = price(h, i)
        populatePriceListRec(i - 1, x :: acc)
      }
    }
    populatePriceListRec(h, List()) reverse
  }

}
