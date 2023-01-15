// Copyright (C) 2023 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package xqt.kotlinx.core.uri.test

import xqt.kotlinx.core.uri.*
import xqt.kotlinx.test.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("The file URI scheme")
class TheFileUriScheme {
    @Test
    @DisplayName("supports unix paths without an authority")
    fun supports_unix_paths_without_an_authority() {
        val uri = Uri.parse("file:///lorem/ipsum/dolor")
        assertEquals(UriScheme.File, uri.scheme)
        assertEquals(Authority(host = ""), uri.authority)
        assertEquals("/lorem/ipsum/dolor", uri.path)
        assertEquals(null, uri.query)
        assertEquals(null, uri.fragment)

        assertEquals("file:///lorem/ipsum/dolor", uri.toString())
    }

    @Test
    @DisplayName("supports unix paths with an authority")
    fun supports_unix_paths_with_an_authority() {
        val uri = Uri.parse("file://localhost/lorem/ipsum/dolor")
        assertEquals(UriScheme.File, uri.scheme)
        assertEquals(Authority(host = "localhost"), uri.authority)
        assertEquals("/lorem/ipsum/dolor", uri.path)
        assertEquals(null, uri.query)
        assertEquals(null, uri.fragment)

        assertEquals("file://localhost/lorem/ipsum/dolor", uri.toString())
    }

    @Test
    @DisplayName("supports KDE URIs without an authority")
    fun supports_kde_uris_with_an_authority() {
        val uri = Uri.parse("file:/lorem/ipsum/dolor")
        assertEquals(UriScheme.File, uri.scheme)
        assertEquals(null, uri.authority)
        assertEquals("/lorem/ipsum/dolor", uri.path)
        assertEquals(null, uri.query)
        assertEquals(null, uri.fragment)

        assertEquals("file:/lorem/ipsum/dolor", uri.toString())
    }

    @Test
    @DisplayName("supports windows paths without an authority")
    fun supports_windows_paths_without_an_authority() {
        val uri = Uri.parse("file:///c:/lorem/ipsum/dolor")
        assertEquals(UriScheme.File, uri.scheme)
        assertEquals(Authority(host = ""), uri.authority)
        assertEquals("/c:/lorem/ipsum/dolor", uri.path)
        assertEquals(null, uri.query)
        assertEquals(null, uri.fragment)

        assertEquals("file:///c:/lorem/ipsum/dolor", uri.toString())
    }

    @Test
    @DisplayName("supports windows paths with an authority")
    fun supports_windows_paths_with_an_authority() {
        val uri = Uri.parse("file://localhost/c:/lorem/ipsum/dolor")
        assertEquals(UriScheme.File, uri.scheme)
        assertEquals(Authority(host = "localhost"), uri.authority)
        assertEquals("/c:/lorem/ipsum/dolor", uri.path)
        assertEquals(null, uri.query)
        assertEquals(null, uri.fragment)

        assertEquals("file://localhost/c:/lorem/ipsum/dolor", uri.toString())
    }
}
