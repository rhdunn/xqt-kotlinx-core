// Copyright (C) 2023 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package xqt.kotlinx.core.uri.test

import xqt.kotlinx.core.uri.*
import xqt.kotlinx.test.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("The HTTPS URI scheme")
class TheHttpsUriScheme {
    @Test
    @DisplayName("supports authority only with host")
    fun supports_authority_only_with_host() {
        val uri = Uri.parse("https://localhost")
        assertEquals(UriScheme.HTTPS, uri.scheme)
        assertEquals(Authority(host = "localhost"), uri.authority)
        assertEquals("", uri.path)
        assertEquals(null, uri.query)
        assertEquals(null, uri.fragment)

        assertEquals("https://localhost", uri.toString())
    }

    @Test
    @DisplayName("supports authority only with host and port")
    fun supports_authority_only_with_host_and_port() {
        val uri = Uri.parse("https://localhost:8020")
        assertEquals(UriScheme.HTTPS, uri.scheme)
        assertEquals(Authority(host = "localhost", port = 8020), uri.authority)
        assertEquals("", uri.path)
        assertEquals(null, uri.query)
        assertEquals(null, uri.fragment)

        assertEquals("https://localhost:8020", uri.toString())
    }

    @Test
    @DisplayName("supports authority and path")
    fun supports_authority_and_path() {
        val uri = Uri.parse("https://localhost/lorem/ipsum/dolor")
        assertEquals(UriScheme.HTTPS, uri.scheme)
        assertEquals(Authority(host = "localhost"), uri.authority)
        assertEquals("/lorem/ipsum/dolor", uri.path)
        assertEquals(null, uri.query)
        assertEquals(null, uri.fragment)

        assertEquals("https://localhost/lorem/ipsum/dolor", uri.toString())
    }

    @Test
    @DisplayName("supports authority and query")
    fun supports_authority_and_query() {
        val uri = Uri.parse("https://localhost?key1=alpha&key2=beta")
        assertEquals(UriScheme.HTTPS, uri.scheme)
        assertEquals(Authority(host = "localhost"), uri.authority)
        assertEquals("", uri.path)
        assertEquals("key1=alpha&key2=beta", uri.query)
        assertEquals(null, uri.fragment)

        assertEquals("https://localhost?key1=alpha&key2=beta", uri.toString())
    }

    @Test
    @DisplayName("supports authority, path, and query")
    fun supports_authority_path_and_query() {
        val uri = Uri.parse("https://localhost/lorem/ipsum/dolor?key1=alpha&key2=beta")
        assertEquals(UriScheme.HTTPS, uri.scheme)
        assertEquals(Authority(host = "localhost"), uri.authority)
        assertEquals("/lorem/ipsum/dolor", uri.path)
        assertEquals("key1=alpha&key2=beta", uri.query)
        assertEquals(null, uri.fragment)

        assertEquals("https://localhost/lorem/ipsum/dolor?key1=alpha&key2=beta", uri.toString())
    }

    @Test
    @DisplayName("supports authority, and fragment")
    fun supports_authority_and_fragment() {
        val uri = Uri.parse("https://localhost#lorem-ipsum")
        assertEquals(UriScheme.HTTPS, uri.scheme)
        assertEquals(Authority(host = "localhost"), uri.authority)
        assertEquals("", uri.path)
        assertEquals(null, uri.query)
        assertEquals("lorem-ipsum", uri.fragment)

        assertEquals("https://localhost#lorem-ipsum", uri.toString())
    }

    @Test
    @DisplayName("supports authority, path, and fragment")
    fun supports_authority_path_and_fragment() {
        val uri = Uri.parse("https://localhost/lorem/ipsum/dolor#lorem-ipsum")
        assertEquals(UriScheme.HTTPS, uri.scheme)
        assertEquals(Authority(host = "localhost"), uri.authority)
        assertEquals("/lorem/ipsum/dolor", uri.path)
        assertEquals(null, uri.query)
        assertEquals("lorem-ipsum", uri.fragment)

        assertEquals("https://localhost/lorem/ipsum/dolor#lorem-ipsum", uri.toString())
    }

    @Test
    @DisplayName("supports authority, query, and fragment")
    fun supports_authority_query_and_fragment() {
        val uri = Uri.parse("https://localhost?key1=alpha&key2=beta#lorem-ipsum")
        assertEquals(UriScheme.HTTPS, uri.scheme)
        assertEquals(Authority(host = "localhost"), uri.authority)
        assertEquals("", uri.path)
        assertEquals("key1=alpha&key2=beta", uri.query)
        assertEquals("lorem-ipsum", uri.fragment)

        assertEquals("https://localhost?key1=alpha&key2=beta#lorem-ipsum", uri.toString())
    }

    @Test
    @DisplayName("supports authority, path, query, and fragment")
    fun supports_authority_path_query_and_fragment() {
        val uri = Uri.parse("https://localhost/lorem/ipsum/dolor?key1=alpha&key2=beta#lorem-ipsum")
        assertEquals(UriScheme.HTTPS, uri.scheme)
        assertEquals(Authority(host = "localhost"), uri.authority)
        assertEquals("/lorem/ipsum/dolor", uri.path)
        assertEquals("key1=alpha&key2=beta", uri.query)
        assertEquals("lorem-ipsum", uri.fragment)

        assertEquals("https://localhost/lorem/ipsum/dolor?key1=alpha&key2=beta#lorem-ipsum", uri.toString())
    }
}
