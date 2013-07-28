package main.scala.com.binomialpricer.dsl

class ParameterHelper {

  def with_BS_parameters(fillParameters: BSParametersBuilder => Unit): Parameters = {

    takeParameters(new BSParametersBuilder, fillParameters)
  }

  def with_binomial_parameters(fillParameters: LatticeParameterBuilder => Unit): Parameters = {

    takeParameters(new LatticeParameterBuilder, fillParameters)
  }

  private def takeParameters[A <: ParametersBuilder](builder: A, fillParameters: A => Unit): Parameters = {

    fillParameters(builder)

    builder.build
  }

}
