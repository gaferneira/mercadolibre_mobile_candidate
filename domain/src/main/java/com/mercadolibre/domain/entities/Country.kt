package com.mercadolibre.domain.entities

enum class Country(
    val currency: String,
    val id : String,
    val code : String,
    val countryName: String) {
    NONE("", "", "", ""),
    CUBA("CUP", "MCU", "CU", "Cuba"),
    ARGENTINA("ARS", "MLA", "AR", "Argentina"),
    VENEZUELA("VES", "MLV", "VE", "Venezuela"),
    MEXICO("MXN", "MLM", "MX", "Mexico"),
    DOMINICANA("DOP", "MRD", "DO", "Dominicana"),
    PARAGUAY("PYG", "MPY", "PY", "Paraguay"),
    HONDURAS("HNL", "MHN", "HN", "Honduras"),
    ECUADOR("USD", "MEC", "EC", "Ecuador"),
    URUGUAY("UYU", "MLU", "UY", "Uruguay"),
    PANAMA("PAB", "MPA", "PA", "Panamá"),
    NICARAGUA("NIO", "MNI", "NI", "Nicaragua"),
    COLOMBIA("COP", "MCO", "CO", "Colombia"),
    PERU("PEN", "MPE", "PE", "Perú"),
    BRASIL("BRL", "MLB", "BR", "Brasil"),
    BOLIVIA("BOB", "MBO", "BO", "Bolivia"),
    GUATEMALA("GTQ", "MGT", "GT", "Guatemala"),
    CHILE("CLP", "MLC", "CL", "Chile"),
    COSTA_RICA("CRC", "MCR", "CR", "Costa Rica"),
    EL_SALVADOR("USD", "MSV", "SV", "El Salvador");

    companion object {
        fun findByCode(code: String?) : Country? {
            return values().firstOrNull { it.code == code?.uppercase()}
        }
    }
}
