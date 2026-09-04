
fun main() {
    println("GameZone iniciado")

    val nombreSistema: String = "GameZone"
    val capacidad: Int = 10
    var recaudacionTotal: Double = 0.0

    val codigoConsola: String = "CC12CD"
    val marca: String = "PlayStation"
    val modelo: String = "PlayStation 5"
    val minutosUso: Int = 75
    val tarifaHora: Double = 800.0

    println("Sistema: $nombreSistema")
    println("Capacidad: $capacidad")
    println("Recaudación: $recaudacionTotal")

    recaudacionTotal = 800.0

    println("Nueva recaudación: $recaudacionTotal")



    val costoBase = calcularCostoBase(minutosUso, tarifaHora)

    val totalconIva = aplicarIva(costoBase)

    println("Costo Base: $costoBase")
    println("Total Con Iva: $totalconIva")

    val tipoUsuario = "socio"

    val monto = 10000.0

    println(aplicarBeneficioUsuario(monto, "infantil"))
    println(aplicarBeneficioUsuario(monto, "socio"))
    println(aplicarBeneficioUsuario(monto, "educacional"))

    if (tipoUsuario == "socio") {
        println("Tiene beneficio de socio")
    } else {
        println("No tiene beneficio de socio")
    }
}

fun calcularCostoBase(minutos: Int, tarifaHora: Double ): Double {
    return (minutos / 60.0) * tarifaHora
}

fun aplicarIva(monto: Double): Double {
    return monto + 1.19
}

fun describirTipoUsuario(tipoUsuario: String): String {
    return when (tipoUsuario) {
        "infantil" -> "Usuario infantil"
        "socio" -> "Usuario socio"
        "educacional" -> "Usuario educacional"
        else -> "Tipo de usuario no valido"
    }
}

fun aplicarBeneficioUsuario(monto: Double, tipoUsuario: String): Double {
    return when (tipoUsuario) {
        "socio" -> monto * 0.80
        "educacional" -> monto * 0.50
        "infantil" -> monto
        else -> monto
    }


}