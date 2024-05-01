package org.example

class Game {

    fun start() {
        println("=====숫자 야구 게임=====")
        println("세자리 숫자를 입력해주세요. 단, 각 자릿수는 서로 다른 숫자이어야 합니다.")

        val answer = makeAnswer()

        while (true) {
            var input:List<Int>
            try {
                input = readln().chunked(1).map { it.toInt() }
                isValidInput(input)
                val(strike, ball) = makeResult(input, answer)

                printResult(strike, ball)
                if(strike == 3) return
            } catch (e:NumberFormatException) {
                println("숫자를 입력해주세요.")
            } catch (e:Exception) {
                println("${e.message} 다시 입력해주세요.")
            }
        }

    }


    private fun makeAnswer(): List<Int> {
        val numList = 1..9
        return numList.shuffled().take(3).toList()
    }

    private fun isValidInput(input: List<Int>): Boolean {
        //세자리 수 이어야함
        if(input.size != 3) throw IllegalArgumentException("세자리 수 이어야 합니다.")

        //각 자리수가 1..9가 아니면 에러
        input.forEach { if (it !in 1..9) throw IllegalArgumentException("0은 쓸 수 없습니다.") }

        //중복된 숫자가 있으면 에러
        if(input.distinct().size != 3) throw IllegalArgumentException("숫자가 중복될 수 없습니다.")

        return true
    }

    private fun makeResult(input: List<Int>, answer: List<Int>): Pair<Int, Int> {

        var strike = 0
        var ball = 0

        answer.forEachIndexed { index, num ->
            if(input[index] == num) strike++
            else if(input.contains(num)) ball ++
        }

        return Pair(strike, ball)
    }

    private fun printResult(strike:Int, ball:Int) {
        var result = ""

        if(strike > 0) result = "${strike}스트라이크 "
        if(ball > 0) result += "${ball}볼"

        if(result.isEmpty()) result = "Nothing"
        if(strike == 3) result = "정답입니다!"

        println(result)
    }

}