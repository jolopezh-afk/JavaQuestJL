
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

    val consola = Consola(
        codigo = "CC12CD",
        marca = "Sony",
        modelo = "Playstation 5",
        tipoUsuario = "socio"
    )

    println("Codigo: ${consola.codigo}")
    println("Marca: ${consola.marca}")
    println("Modelo: ${consola.modelo}")
    println("Tipo Usuario: ${consola.tipoUsuario}")
    println()

    val clasica = ConsolaClasica(
        "CC12CD",
        "Sony",
        "PlayStation 5",
        "socio"
    )

    val moderna = ConsolaModerna(
        "CM22TO",
        "Nintendo",
        "Switch",
        "infantil"
    )

    val vr = ConsolaVR(
        "VR44RG",
        "Meta",
        "Quest 3",
        "Educacional",
        true

    )

    val consolas: List<Consola> = listOf(
        ConsolaClasica(
            "CC12CD",
            "Sony",
            "PlayStation 5",
            "socio"
        ),
        ConsolaModerna(
            "CM22TO",
            "Nintendo",
            "Switch",
            "infantil"
        ),
        ConsolaVR(
            "VR44RG",
            "Meta",
            "Quest 3",
            "educacional",
            true
        )
    )

    for (consola in consolas) {
        println("Código: ${consola.codigo}")
        println("Marca: ${consola.marca}")
        println("Modelo: ${consola.modelo}")
        println("Tarifa: ${consola.calcularTarifa(60)}")
        println()
    }

    val puesto = Puesto(1)

    println(puesto.describirEstado(puesto))
    println()

    puesto.estado = EstadoPuesto.EnProceso(
        "registrando entrada")

    println(puesto.describirEstado(puesto))
    println()

    puesto.estado = EstadoPuesto.EnReparacion(
        "mantenimiento preventivo"
    )
    println(puesto.describirEstado(puesto))
    println()

    val puestos: MutableList<Puesto> = mutableListOf()

    for (numero in 1..10){
        puestos.add(Puesto(numero))
    }

    for (puesto in puestos){
        println(
            "Puesto: ${puesto.numero}: " + puesto.describirEstado(puesto)
        )
    }

    val historial: MutableList<Consola> = mutableListOf()

    historial.add(ConsolaClasica("CC12CD", "Sony", "PlayStation 5", "socio"))

    historial.add(ConsolaModerna("CC12CD", "Nintendo", "switch", "infantil"))

    for (consola in historial){
        println(
            "${consola.codigo} -> ${consola.marca} -> ${consola.modelo}"
        )
    }

    var puestoLibre: Puesto? = null

    for (puesto in puestos){
        if (puesto.estado is EstadoPuesto.Libre) {
            puestoLibre = puesto
            break
        }
    }

    if (puestoLibre != null){
        println(
            "Primer puesto libre: ${puestoLibre.numero}"
        )
    } else{
        println("no existen puestos libres")
    }
    println()

    val tickets: List<Ticket> = listOf(
        Ticket(
            1,
            "CC12CD",
            "Clasica",
            75,
            1200.0
        ),
        Ticket(
            2,
            "CM22TO",
            "Moderna",
            18,
            0.0
        ),
        Ticket(
            3,
            "VR44RG",
            "VR",
            120,
            7000.0
        )
    )

    val ticketVR = tickets.filter { ticket -> ticket.tipoConsola == "VR" }

    val codigosAtendidos = tickets.map { ticket -> ticket.codigoConsola }

    codigosAtendidos.forEach { codigo -> println(codigo) }

    val recaudacion = tickets.sumOf { ticket -> ticket.monto }
    println("Recaudacion total: $recaudacion")
    println()

    val disponibles = puestos.count { puesto ->
        puesto.estado is EstadoPuesto.Libre
    }

    val codigos = tickets.map { ticket ->
        ticket.codigoConsola
    }

    val total = tickets.sumOf { ticket ->
        ticket.monto
    }

    val vr1 = tickets.filter { ticket ->
        ticket.tipoConsola == "VR"
    }

    val ingresoVR = tickets
        .filter { ticket ->
            ticket.tipoConsola == "VR"
        }
        .sumOf { ticket ->
            ticket.monto
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