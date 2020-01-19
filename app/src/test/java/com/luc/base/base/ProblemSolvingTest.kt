package com.luc.base.base

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.math.abs

/**
 * Hackerrank problem solving references
 */

@RunWith(JUnit4::class)
class ProblemSolvingTest {

    /** https://www.hackerrank.com/challenges/diagonal-difference/problem */
    @Test
    fun diagonalDifferenceInput() {//args: Array<String>
        val arr = arrayOf(
            arrayOf(11, 2, 4),
            arrayOf(4, 5, 6),
            arrayOf(10, 8, -12)
        )

        var leftToRight = 0
        var rightToLeft = 0
        for (x in arr.indices)
            for (y in arr[x].indices) {
                if (x == y) leftToRight += arr[x][y]
                if (x + y == arr.size - 1) rightToLeft += arr[x][y]
            }
        println(abs(leftToRight - rightToLeft))
    }

    /** https://www.hackerrank.com/challenges/diagonal-difference/problem */
    @Test
    fun pivot() {//args: Array<String>
        val arr = arrayOf(1, 2, 3, 4, 3, 3)

        var pivot = 0
        var sum = 0
        for (index in arr.indices) {
            val revertIndex = arr.size - 1 - index
            sum += arr[index]

            var subSum = 0
            for (subIndex in revertIndex..(index + 1))
                subSum += arr[subIndex]

            if (sum == subSum) {
                pivot = index + 1
                break
            }
        }
        println(pivot)
    }

    /** https://www.hackerrank.com/challenges/plus-minus/problem */
    @Test
    fun plusMinus() {
        val arr = arrayOf(-4, 3, -9, 0, 4, 1)

        var positives = 0.0
        var negatives = 0.0
        var zeroes = 0.0
        for (value in arr)
            when {
                value > 0.0 -> positives++
                value < 0.0 -> negatives++
                else -> zeroes++
            }
        val positiveRatio = positives / arr.size
        val negativeRatio = negatives / arr.size
        val zeroRatio = zeroes / arr.size
        println("%.6f".format(positiveRatio))
        println("%.6f".format(negativeRatio))
        println("%.6f".format(zeroRatio))
    }

    @Test
    fun error() {
        assert(true)
    }

//    @Throws(Exception::class)
//    internal fun err(): Int {
//        try {
//            throw IOException("..")
//        } catch (e: RuntimeException) {
//            throw RuntimeException(e)
//        } finally {
//            return -1
//        }
//    }

    fun palindrom(text: String): Boolean {
        val pivot = text.length / 2     //3
        val leftText = text.substring(0, pivot)    //tam
        val rightText = text.substring(pivot - 1, text.length - 1)     //mat

        //sort righttext
        var i = 0
        var newRightText = ""
        while (i < rightText.length) {
            newRightText += rightText.substring(i, i + 1)
            i++
        }                           //tam

        return leftText == newRightText
    }

    fun palindrom2(text: String): Boolean {
        val pivot = text.length / 2     //3

        var leftIndex = pivot - 1       //2
        var rightIndex = pivot - 1      //2

        var result = true
        while (leftIndex >= 0) {
            if (text[leftIndex] != text[rightIndex]) {       //m m ,   a  a , t   t
                result = false
                break
            }
            leftIndex--                     //1
            rightIndex++                    //3
        }
        return result
    }


    fun palindrom3(text: String): Boolean {
        var index = 0

        //tammat
        var result = true
        while (index <= text.length / 2) {                       //   0  3,  1
            if (text[index] != text[text.length - 1 - index]) {         //t t , a a
                result = false
                break
            }
            index++
        }

        return result
    }

    @Test
    fun palindrom() {
        println(palindrom3("tamat").toString())
    }

    /** https://www.hackerrank.com/challenges/staircase/problem */
    @Test
    fun staircase() {
        val n = 6

        for (x in 0 until n) {
            var textLine = ""
            for (y in n - 1 downTo 0)
                textLine += if (y <= x) "#" else " "
            println(textLine)
        }
    }

    /** https://www.hackerrank.com/challenges/mini-max-sum/problem */
    @Test
    fun minMaxSum() {
        val arr = arrayOf(9, 2)

        var min = Long.MAX_VALUE
        var max = 0L
        for (x in arr.indices) {
            var temp = 0L
            for (y in arr.indices)
                if (x != y) temp += arr[y]
            if (temp < min) min = temp
            if (temp > max) max = temp
        }
        println("$min $max")
    }

    /** https://www.hackerrank.com/challenges/birthday-cake-candles/problem */
    @Test
    fun birthdayCakeCandles() {
        val arr = arrayOf(3, 1, 2, 3)

        val max = arr.max()
        val total = arr.filter { it == max }.size
        println(total)
    }

    /** https://www.hackerrank.com/challenges/time-conversion/problem */
    @Test
    fun timeConversion() {
        val s = "12:00:00AM"
        var hour = s.substring(0, 2).toInt()
        val minutes = s.substring(2, 8)
        val pm = s.substring(8, 10)

        if (hour == 12) hour = 0
        if (pm == "PM")
            hour = ((hour + 12) % 24)

        var hourText = hour.toString()
        if (hourText.length == 1) hourText = "0$hourText"
        println("$hourText$minutes")
    }

    /** https://www.hackerrank.com/challenges/grading/problem */
    @Test
    fun grading() {
        val grades = arrayOf(73, 67, 38, 33)
        for (i in grades.indices)
            if (grades[i] > 37 && grades[i] % 5 > 2)
                grades[i] = grades[i] - (grades[i] % 5) + 5

        for (resul in grades)
            println(resul)
    }

    /** https://www.hackerrank.com/challenges/apple-and-orange/problem */
    @Test
    fun countApplesAnOranges() {
        val s = 7
        val t = 11
        val a = 5
        val b = 15
        val apples = arrayOf(-2, 2, 1)
        val oranges = arrayOf(5, 6)

        var pickAppleTotal = 0
        var pickOrangeTotal = 0
        for (apple in apples)
            if (a + apple in s..t)
                pickAppleTotal++
        for (orange in oranges)
            if (b + orange in s..t)
                pickOrangeTotal++
        println(pickAppleTotal)
        println(pickOrangeTotal)
    }

    /** https://www.hackerrank.com/challenges/apple-and-orange/problem */

    @Test
    fun kangaroo() {
        val x1 = 0
        val v1 = 2
        val x2 = 5
        val v2 = 3

        //Check if x1 and x2 possible collide
        var result = false
        when {
            v1 == v2 && x1 != x2 -> result = false
            x1 > x2 && v1 > v2 -> result = false
            x2 > x1 && v2 > v1 -> result = false
            else -> {
                var k1 = if (x1 < x2) x1 else x2
                var k2 = if (x1 > x2) x1 else x2
                while (k1 < k2) {
                    k1 += v1
                    k2 += v2
                    if (k1 == k2) {
                        result = true
                        break
                    }
                }
            }
        }

        println(if (result) "YES" else "NO")
    }
}
