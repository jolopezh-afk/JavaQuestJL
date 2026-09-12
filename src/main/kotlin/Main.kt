import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
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

    val puestoss = MutableList(10) { indice ->
        Puesto(indice + 1) // Asumiendo que el constructor de Puesto recibe su número
    }

    println("\nRegistrando entrada...")
    try {

        registrarEntrada(puestos, consola)

        println("Entrada completada con éxito.")
    } catch (e: Exception) {
        println("Error al registrar entrada: ${e.message}")
    }
    println("\nRegistrando salida...")
    try {
        registrarSalida(puestos, "CC12CD", 75)
        println("Salida completada y puesto liberado.")
    } catch (e: Exception) {
        println("Error al registrar salida: ${e.message}")
    }

    println("=== INICIANDO PRUEBAS DE ROBUSTEZ ===")

    println("\n--- 1. PRUEBA DE CÓDIGO INVÁLIDO ---")
    val consolaMala = ConsolaClasica("123ABC", "Sony", "PS4", "socio")
    try {
        registrarEntrada(puestos, consolaMala)
    } catch (e: IllegalArgumentException) {
        println("[ERROR CONTROLADO] ${e.message}")
    }

    println("\n--- Llenando todos los puestos... ---")
    for (i in 1..10) {
        val letra = ('A' + (i % 26)).toString()
        val codigoValido = "AA${i.toString().padStart(2, '0')}A$letra"
        val c = ConsolaClasica(codigoValido, "Sony", "PS4", "socio")
        try {
            registrarEntrada(puestos, c)
        } catch (e: IllegalStateException) {
            println("[ERROR CONTROLADO] No se pudo ingresar $codigoValido: ${e.message}")
        }
    }

    println("\n--- 2. PRUEBA DE CAPACIDAD COMPLETA ---")
    val consolaExtra = ConsolaClasica("XX99XX", "Xbox", "Series X", "invitado")
    try {
        registrarEntrada(puestos, consolaExtra)
    } catch (e: IllegalStateException) {
        println("[ERROR CONTROLADO] ${e.message}")
    }

    println("\n--- 3. PRUEBA DE CONSOLA NO ENCONTRADA ---")
    try {
        registrarSalida(puestos, "ZZ00ZZ", 60)
    } catch (e: NoSuchElementException) {
        println("[ERROR CONTROLADO] ${e.message}")
    }

    println("\n--- 4. PRUEBA DE TARIFA INVÁLIDA ---")

    registrarSalida(puestos, "AA01AB", 45)

    val consolaRota = ConsolaAveriada("BB22BB")
    registrarEntrada(puestos, consolaRota)

    try {
        registrarSalida(puestos, "BB22BB", 30)
    } catch (e: IllegalStateException) {
        println("[ERROR CONTROLADO] ${e.message}")
    }

    println("\n=== EL SISTEMA CONTINÚA FUNCIONANDO CORRECTAMENTE ===")

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

suspend fun registrarEntrada(puestos: MutableList<Puesto>, consola: Consola) {

    if (!validarCodigo(consola.codigo)) {
        throw IllegalArgumentException("Código de consola inválido: ${consola.codigo}. Debe tener formato XX99XX.")
    }

    val puesto = puestos.firstOrNull { it.estado is EstadoPuesto.Libre }
        ?: throw IllegalStateException("No existen puestos disponibles en este momento.")

    puesto.estado = EstadoPuesto.EnProceso("registrando entrada")
    delay(3000)
    puesto.estado = EstadoPuesto.EnJuego(consola)

    println(">> [SISTEMA] Entrada registrada: ${consola.codigo} en el puesto ${puesto.numero}.")
}

suspend fun registrarSalida(puestos: MutableList<Puesto>, codigo: String, minutos: Int) {

    val puesto = puestos.firstOrNull {
        val estado = it.estado
        estado is EstadoPuesto.EnJuego && estado.consola.codigo == codigo
    } ?: throw NoSuchElementException("Consola no encontrada: No hay ninguna consola jugando con el código $codigo.")

    val estadoActual = puesto.estado as EstadoPuesto.EnJuego
    val consola = estadoActual.consola

    puesto.estado = EstadoPuesto.EnProceso("calculando tarifa")
    delay(6500)

    val monto = consola.calcularTarifa(minutos)

    if (!validarTarifa(consola, minutos, monto)) {

        puesto.estado = EstadoPuesto.EnJuego(consola)
        throw IllegalStateException("Error de facturación: La tarifa calculada ($$monto) no es válida.")
    }

    println(">> [SISTEMA] Salida completada | Consola: ${consola.codigo} | Total: $$monto")
    puesto.estado = EstadoPuesto.Libre
}

fun validarCodigo(codigo: String): Boolean {
    val regex = Regex("^[A-Za-z]{2}[0-9]{2}[A-Za-z]{2}$")
    return regex.matches(codigo)
}


fun validarTarifa(consola: Consola, minutos: Int, monto: Double): Boolean {
    if (monto < 0.0) {
        return false
    }

    if (monto == 0.0 && !(consola is ConsolaModerna && minutos < 20)) {
        return false
    }

    return true
}

class ConsolaAveriada(codigo: String) : Consola(codigo, "Test", "Test", "invitado") {
    override fun calcularTarifa(minutos: Int): Double = -15.0
}