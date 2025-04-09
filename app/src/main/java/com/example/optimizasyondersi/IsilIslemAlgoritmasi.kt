package com.example.optimizasyondersi

import kotlin.math.*
import kotlin.random.Random

class IsilIslemAlgoritmasi(
    var x1: Double,
    var x2: Double,
    val lb: Double,
    val ub: Double,
    var temperature: Double,
    val coolingRate: Double,
    val maxIter: Int
) {

    //Fonksiyon: f(x1, x2) = x1^2 + x2^2
    fun fonksiyon(x1: Double, x2: Double): Double {
        return x1.pow(2) + x2.pow(2)
    }

    //Komsu uretme (x + r(-1,1)*(ub-lb)*0.2)
    fun komsuUret(x: Double): Double {
        val r = Random.nextDouble(-1.0, 1.0)
        return x + r * (ub - lb) * 0.2
    }

    fun minimizeEt(): List<Double> {
        var bestX1 = x1
        var bestX2 = x2
        var bestValue = fonksiyon(x1, x2)

        for (i in 1..maxIter) {
            val newX1 = komsuUret(x1)
            val newX2 = komsuUret(x2)
            val newValue = fonksiyon(newX1, newX2)
            val delta = newValue - bestValue

            if (delta < 0 || Random.nextDouble() < exp(-delta / temperature)) {
                x1 = newX1
                x2 = newX2
                bestValue = newValue
                bestX1 = x1
                bestX2 = x2
            }

            //Sıcaklik dusurme (komşu x kotuyse)
            temperature *= coolingRate

            println("İterasyon $i | Sıcaklık: ${"%.4f".format(temperature)} | x1: ${"%.4f".format(x1)} x2: ${"%.4f".format(x2)} | En İyi: ${"%.4f".format(bestValue)}")
        }

        return listOf(bestX1, bestX2, bestValue)
    }
}
