package com.wutsi.mail

data class Merchant(
    val name: String,
    val url: String,
    val logoUrl: String?,
    val category: String? = null,
    val location: String? = null,
)
