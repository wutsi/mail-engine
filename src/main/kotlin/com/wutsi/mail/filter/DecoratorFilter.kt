package com.wutsi.mail.filter

import com.github.mustachejava.DefaultMustacheFactory
import com.wutsi.mail.MailContext
import com.wutsi.mail.MailFilter
import java.io.InputStreamReader
import java.io.StringWriter

class DecoratorFilter : MailFilter {
    override fun filter(body: String, context: MailContext): String {
        val template = "/decorators/default.html"
        val reader = InputStreamReader(DecoratorFilter::class.java.getResourceAsStream(template))
        reader.use {
            val writer = StringWriter()
            writer.use {
                val mustache = DefaultMustacheFactory().compile(reader, "text")
                mustache.execute(
                    writer,
                    mapOf(
                        "scope" to scope(body, context),
                    ),
                )
                writer.flush()
                return writer.toString()
            }
        }
    }

    private fun scope(body: String, context: MailContext) = mapOf(
        "siteUrl" to context.merchant.url,
        "logoUrl" to context.merchant.logoUrl,
        "siteName" to context.merchant.name,
        "location" to context.merchant.location,
        "category" to context.merchant.category,
        "assetUrl" to context.assetUrl,
        "body" to body,
    )
}
