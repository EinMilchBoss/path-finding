package orientation

import kotlin.math.absoluteValue

data class Vector(val dx: Int, val dy: Int) {
    fun toUnitVector(): Vector {
        val ndx = dx.divideSafely(dx.absoluteValue)
        val ndy = dy.divideSafely(dy.absoluteValue)
        return Vector(ndx, ndy)
    }
}

fun Int.divideSafely(other: Int): Int = try {
    this / other
} catch(e: ArithmeticException) {
    0
}