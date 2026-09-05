
class Puesto (val numero: Int, var estado: EstadoPuesto = EstadoPuesto.Libre){

    fun describirEstado(puesto: Puesto): String {
        return when (val estado = puesto.estado) {
            EstadoPuesto.Libre -> "Puesto Libre"

            is EstadoPuesto.EnJuego -> "En Juego: ${estado.consola.codigo}"

            is EstadoPuesto.EnProceso -> "Procesando: ${estado.motivo}"

            is EstadoPuesto.EnReparacion -> "En Reparacion: ${estado.motivo}"
        }
    }
}