package com.example.optimizasyondersi

import kotlin.math.pow
import kotlin.math.sqrt

class Algoritmalar(var x1: Double, var x2: Double, var x3: Double, var epsilon: Double, var a: Double) {

    //Gradyenti hesapla
    fun gradyentHesapla(x1: Double, x2: Double, x3: Double): List<Double> {
        val kismiTurevx1 = (2 * x1) + (2 * x2)
        val kismiTurevx2 = (4 * x2) + (2 * x1) + (2 * x3)
        val kismiTurevx3 = (4 * x3) + (2 * x2)

        return listOf(kismiTurevx1, kismiTurevx2, kismiTurevx3)
    }

    //Gradyent büyüklüğü kontrol (z^2<E)
    fun gradyentBuyuklukKontrol(gradyentList: List<Double>, epsilon: Double): Boolean {
        val buyukluk = sqrt(gradyentList[0].pow(2) + gradyentList[1].pow(2) + gradyentList[2].pow(2))
        return buyukluk > epsilon
    }

    //Yönü hesapla (En dik iniş için negatif gradyent) (d = -d)
    fun yonHesapla(gradyentList: List<Double>): List<Double> {
        return listOf(-gradyentList[0], -gradyentList[1], -gradyentList[2])
    }

    //Eşlenik Gradyan yönünü hesapla (d = -g(t) + B(t)*a(t-1) )
    fun yonHesaplaEslenik(gradyentList: List<Double>, gradyentOnceki: List<Double>, beta: Double): List<Double> {
        val d1 = -gradyentList[0] + beta * gradyentOnceki[0]
        val d2 = -gradyentList[1] + beta * gradyentOnceki[1]
        val d3 = -gradyentList[2] + beta * gradyentOnceki[2]
        return listOf(d1, d2, d3)
    }

    //Beta değerini hesapla (Eşlenik gradyant için) (B =  [g(t) / g(t-1)]^2 )
    fun betaHesapla(gradyentList: List<Double>, gradyentOnceki: List<Double>): Double {
        val gradyentVektoruDotProduct = gradyentList[0] * gradyentList[0] + gradyentList[1] * gradyentList[1] + gradyentList[2] * gradyentList[2]
        val gradyentOncedenDotProduct = gradyentOnceki[0] * gradyentOnceki[0] + gradyentOnceki[1] * gradyentOnceki[1] + gradyentOnceki[2] * gradyentOnceki[2]
        return gradyentVektoruDotProduct / gradyentOncedenDotProduct
    }

    //EDI algoritması
    fun minimizeEnDikInis(): List<Double> {
        var iterasyon = 0.0
        var gradyent = gradyentHesapla(x1, x2, x3)

        while (true) {
            //Gradyent büyüklüğü kontrol
            if (!gradyentBuyuklukKontrol(gradyent, epsilon)) {
                break
            }

            //Yönü hesapla(-d)
            val d = yonHesapla(gradyent)

            //a ile yeni x`ler hesapla
            x1 += a * d[0]
            x2 += a * d[1]
            x3 += a * d[2]

            iterasyon += 1
            println("Iterasyon: $iterasyon | x1: $x1, x2: $x2, x3: $x3")
            gradyent = gradyentHesapla(x1, x2, x3) //Yeni x değerleri ile yeni gradyant hesaapla
        }
        return listOf(x1, x2, x3, iterasyon) //Minimum noktalar
    }

    // Eşlenik Gradyan algoritmasını uygula
    fun minimizeEslenik(): List<Double> {
        var iterasyon = 0.0
        var gradyent = gradyentHesapla(x1, x2, x3)

        // İlk yönü hesapla (negatif gradyent)
        var d = yonHesapla(gradyent)

        while (true) {
            //Öğrenme oranını (a)
            val alfa = a

            //Yeni x değerlerie
            x1 += alfa * d[0]
            x2 += alfa * d[1]
            x3 += alfa * d[2]

            //Yeni gradyenti hesapla
            val gradyentYeni = gradyentHesapla(x1, x2, x3)

            //Gradyent büyüklüğünü kontrol et, durma şartı sağlanırsa bitir
            if (!gradyentBuyuklukKontrol(gradyentYeni, epsilon)) {
                break
            }

            //Burdan sonra adım büyüklüğü ile yeni gradyant hesaplanıyor (B değeri)
            val beta = betaHesapla(gradyentYeni, gradyent)

            //Yönü güncelle (yeni d için  eski d ile B hesapla)
            d = yonHesaplaEslenik(gradyentYeni, gradyent, beta)

            //Son olarak yeni gradyant elde et
            gradyent = gradyentYeni

            iterasyon += 1 //Sonsuz iterasyon giderse çökme olur
            if (iterasyon>10000){
                break
            }

            println("Eşlenik Gradyan: Iterasyon: $iterasyon | x1: $x1, x2: $x2, x3: $x3")
        }

        println("Eşlenik Gradyan: Iterasyon: $iterasyon | x1: $x1, x2: $x2, x3: $x3")
        return listOf(x1, x2, x3, iterasyon) //Minimum nokta
    }
}
