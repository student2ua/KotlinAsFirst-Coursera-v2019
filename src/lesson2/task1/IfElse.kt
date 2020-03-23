@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.*

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String = if (age < 111 || age > 120) {
    if ((age < 10 || age > 20) && age % 10 == 1) "$age год" else if ((age < 5 || age > 21) && age % 10 in 2..4) "$age года" else "$age лет"
} else "$age лет"

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val d1 = t1 * v1
    val d2 = t2 * v2
    val d3 = t3 * v3
    val halfPath = (d1 + d2 + d3) / 2
    println("age%10=$halfPath")
    return if (halfPath <= d1) halfPath / v1
    else if (halfPath <= d1 + d2) (halfPath - d1) / v2 + t1
    else (halfPath - d1 - d2) / v3 + t1 + t2
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    val bool1 = kingX == rookX1 || kingY == rookY1
    val bool2 = kingX == rookX2 || kingY == rookY2
    if (bool1 && bool2) return 3
    else if (bool2) return 2
    else if (bool1) return 1
    else return 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {

    val bool1 = kingX == rookX || kingY == rookY
    val bool2 = abs(kingX - bishopX) == abs(kingY - bishopY)
    if (bool1 && bool2) return 3
    else if (bool2) return 2
    else if (bool1) return 1
    else return 0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
//    println("a = [${a}], b = [${b}], c = [${c}]")
    if (a + b <= c || b + c <= a || c + a <= b) return -1
    val cosA = (a * a + c * c - b * b) / (2 * a * c)
    val cosB = (a * a + b * b - c * c) / (2 * a * b)
    val cosC = (b * b + c * c - a * a) / (2 * c * b)
//    println(acos(cosA) * 180 / PI)
//    println(acos(cosB) * 180 / PI)
//    println(acos(cosC) * 180 / PI)
    if (cosA == 0.0 || cosB == 0.0 || cosC == 0.0) return 1

    var maxY = acos(cosA) * 180 / PI
    maxY = max(maxY, acos(cosB) * 180 / PI)
    maxY = max(maxY, acos(cosC) * 180 / PI)
    if (maxY > 90) return 2
    else return 0

}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int):Int {
    println("a = [${a}], b = [${b}], c = [${c}], d = [${d}]")
    if (b == c) return 0
    val left = max(a, c) // максимум левых концов
    val right = min(b, d)
    val rez= right-left
    println("$right - $left = $rez")
    if (rez>0) return rez else return -1

}
