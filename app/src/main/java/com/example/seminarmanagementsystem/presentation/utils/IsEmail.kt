package com.example.seminarmanagementsystem.presentation.utils

fun isEmail(email: String): Boolean {
    val options = mapOf(
        "allow_display_name" to false,
        "require_display_name" to false,
        "allow_utf8_local_part" to true,
        "require_tld" to true,
        "ignore_max_length" to false,
        "allow_ip_domain" to false,
        "domain_specific_validation" to false,
        "blacklisted_chars" to "",
        "host_blacklist" to emptyList<String>(),
        "host_whitelist" to emptyList<String>()
    )

    val allowDisplayName = options["allow_display_name"] as Boolean
    val requireDisplayName = options["require_display_name"] as Boolean
    val allowUtf8LocalPart = options["allow_utf8_local_part"] as Boolean
    val requireTld = options["require_tld"] as Boolean
    val ignoreMaxLength = options["ignore_max_length"] as Boolean
    val allowIpDomain = options["allow_ip_domain"] as Boolean
    val domainSpecificValidation = options["domain_specific_validation"] as Boolean
    val blacklistedChars = (options["blacklisted_chars"] as String).toSet()
    val hostBlacklist = (options["host_blacklist"] as List<String>).toSet()
    val hostWhitelist = (options["host_whitelist"] as List<String>).toSet()

    // Email validation logic
    val emailRegex = Regex(
        "^" +
                if (allowDisplayName || requireDisplayName) "(.+)@" else "" +
                        "[A-Za-z0-9" + if (allowUtf8LocalPart) "\\p{L}" else "" + "_%+-]+\\.([A-Za-z]{2,})(?:$|" +
                        (if (domainSpecificValidation) "" else "|.*$)")
    )

    if (!emailRegex.matches(email)) {
        return false
    }

    if (!ignoreMaxLength && email.length > 320) {
        return false
    }

    val localPart = email.substringBefore('@')
    val domainPart = email.substringAfter('@')

    if (localPart.any { it in blacklistedChars }) {
        return false
    }

    if (hostBlacklist.any { domainPart.endsWith(it) }) {
        return false
    }

    if (hostWhitelist.isNotEmpty() && hostWhitelist.none { domainPart.endsWith(it) }) {
        return false
    }

    return true
}