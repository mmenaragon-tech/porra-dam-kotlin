package org.elCampico.PorraDam.data.model

data class Porra(
    val usuarioId: Int,
    val partidoId: Int,
    val golesLocal: Int,
    val golesVisitante: Int
)

fun List<Porra>.deUsuario(usuarioId: Int): List<Porra> =
    filter { it.usuarioId == usuarioId }

fun List<Porra>.deJornada(jornada: Jornada): List<Porra> {
    val ids = jornada.partidos.map { it.id }.toSet()
    return filter { it.partidoId in ids }
}


