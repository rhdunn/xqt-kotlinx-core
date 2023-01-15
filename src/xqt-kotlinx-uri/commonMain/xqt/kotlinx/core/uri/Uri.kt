// Copyright (C) 2023 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package xqt.kotlinx.core.uri

/**
 * A Universal Resource Identifier (URI).
 *
 * @see <a href="https://www.rfc-editor.org/rfc/rfc3986">RFC 3986 Uniform Resource Identifier (URI): Generic Syntax</a>
 */
data class Uri(
    /**
     * The URI scheme.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc3986#section-3.1">RFC 3986 (3.1) Scheme</a>
     */
    val scheme: UriScheme,

    /**
     * The URI authority.
     *
     * For hierarchical URIs (http, https, etc.), the authority is always
     * present. An empty authority has the host set to `""`.
     *
     * For non-hierarchical URIs (urn, mailto, etc.), the authority is
     * always null.
     *
     * Note that in KDE file URIs are not hierarchical, while in other
     * environments they are hierarchical.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc3986#section-3.2">RFC 3986 (3.2) Authority</a>
     */
    val authority: Authority? = null,

    /**
     * The URI path.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc3986#section-3.3">RFC 3986 (3.3) Path</a>
     */
    val path: String,

    /**
     * The URI query.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc3986#section-3.4">RFC 3986 (3.4) Query</a>
     */
    val query: String? = null,

    /**
     * The URI fragment.
     *
     * @see <a href="https://www.rfc-editor.org/rfc/rfc3986#section-3.5">RFC 3986 (3.5) Fragment</a>
     */
    val fragment: String? = null
) {
    override fun toString(): String = when {
        authority != null -> "$scheme://$authority$path"
        else -> "$scheme:$path"
    }

    companion object {
        /**
         * Parse a Universal Resource Identifier (URI).
         *
         * @see <a href="https://www.rfc-editor.org/rfc/rfc3986">RFC 3986 Uniform Resource Identifier (URI): Generic Syntax</a>
         */
        fun parse(uri: String): Uri {
            val (scheme, authorityAndPath) = parseScheme(uri)
            return when {
                authorityAndPath.startsWith("//") -> {
                    val (authority, pathAndQuery) = parseAuthority(authorityAndPath)
                    Uri(
                        scheme = scheme,
                        authority = authority,
                        path = pathAndQuery,
                        query = null,
                        fragment = null
                    )
                }

                else -> Uri(
                    scheme = scheme,
                    authority = null,
                    path = authorityAndPath,
                    query = null,
                    fragment = null
                )
            }
        }

        private fun parseScheme(uri: String): Pair<UriScheme, String> = when (val index = uri.indexOf(':')) {
            -1 -> UriScheme.valueOf(uri) to ""
            else -> {
                val scheme = uri.substring(0, index)
                val path = uri.substring(index + 1)
                UriScheme.valueOf(scheme) to path
            }
        }

        private fun parseAuthority(uri: String): Pair<Authority, String> = when (val index = uri.indexOf('/', 2)) {
            -1 -> Authority.parse(uri.substring(2)) to ""
            else -> {
                val authority = uri.substring(2, index)
                val path = uri.substring(index)
                Authority.parse(authority) to path
            }
        }
    }
}
