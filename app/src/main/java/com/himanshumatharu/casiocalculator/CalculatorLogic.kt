package com.himanshumatharu.casiocalculator

class CalculatorLogic {

    /** Variables and Properties */
    private var number: Double? = null
    private var intermediateCalculation: Pair<Double,String>? = null

    /** Class Methods */
    fun setNumber(number:Double){
        this.number = number
    }

    fun calculate(symbol:String):Pair<Double?,String?>{
        this.number?.let {
            when (symbol) {
                "+/-" -> {
                    return Pair(it * -1, "")
                }
                "C" -> {
                    return Pair(0.0,"")
                }
                "%" -> {
                    return Pair(it * 0.01, "")
                }
                "=" -> {
                    return this.performTwoNumCalculation(it)
                }
                else -> {
                    this.intermediateCalculation = Pair(it,symbol)
                }
            }
        }
        return Pair(null,"")
    }

    private fun performTwoNumCalculation(n2:Double):Pair<Double?,String?>{
        this.intermediateCalculation?.let {
            val n1 = it.first
            when (it.second) {
                "+" -> {
                    return Pair(n1+n2, "${StringUtils.trimTrailingZero(n1.toString())}+${StringUtils.trimTrailingZero(n2.toString())}")
                }
                "-" -> {
                    return Pair(n1-n2, "${StringUtils.trimTrailingZero(n1.toString())}-${StringUtils.trimTrailingZero(n2.toString())}")
                }
                "x" -> {
                    return Pair(n1*n2, "${StringUtils.trimTrailingZero(n1.toString())}x${StringUtils.trimTrailingZero(n2.toString())}")
                }
                "/" -> {
                    return Pair(n1/n2, "${StringUtils.trimTrailingZero(n1.toString())}/${StringUtils.trimTrailingZero(n2.toString())}")
                }
                else -> {
                    throw Exception("The operation passed in does not match any of the cases.")
                }
            }
        }
        return Pair(null,"")
    }

}