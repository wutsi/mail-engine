package com.wutsi.mail.filter

import com.wutsi.mail.Fixtures
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DecoratorFilterTest {
    private val context = Fixtures.createMailContext()
    private val filter = DecoratorFilter()

    @Test
    fun decorate() {
        val html = """
            <p>Hello world</p>
        """.trimIndent()

        val result = filter.filter(html, context)
        assertEquals(
            """
                <html>
                <head>
                    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
                    <link href="https://fonts.googleapis.com/css2?family=PT+Sans&display=swap" rel="stylesheet"/>
                </head>
                <body>

                <table cellpadding="0" cellspacing="10" class="body" width="100%">
                    <tr>
                        <td align="center">
                            <table cellpadding="0" cellspacing="0" class="content" width="100%">
                                <tr>
                                    <td class="padding">
                                        <table cellpadding="0" cellspacing="0" width="100%">
                                            <tr>
                                                <td width="80">
                                                    <a href="https://www.com/u/1">
                                                        <img height="64" src="https://ik.imagekit.io/cx8qxsgz4d/user/12/picture/tr:w-64,h-64,fo-face/023bb5c8-7b09-4f2f-be51-29f5c851c2c0-scaled_image_picker1721723356188894418.png"
                                                             style="border-radius: 32px 32px 32px 32px; vertical-align: middle; padding=2px"
                                                             class="border"
                                                             width="64"/></a>
                                                </td>
                                                <td>
                                                    <div class="text-larger">Maison H</div>
                                                    <div class="text-small">Bakery</div>
                                                    <div class="text-small">Yaounde, Cameroon</div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="border-top padding">
                                        <p>Hello world</p>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="border-top padding">
                                        <table border="0" cellpadding="0" cellspacing="5" width="100%">
                                            <tr>
                                                <td align="center" class="text-small">
                                                    Powered by Wutsi.
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>


                </body>
                </html>
            """.trimIndent(),
            result.trimIndent()
        )
    }
}
