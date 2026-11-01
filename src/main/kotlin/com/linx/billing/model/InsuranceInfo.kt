package com.linx.billing.model

data class InsuranceInfo(
    val bin: String,
    val pcn: String,
    val memberId: String,
) {
    init {
        require(bin.isNotBlank()) { "BIN is required" }
        require(pcn.isNotBlank()) { "PCN is required" }
        require(memberId.isNotBlank()) { "Member ID is required" }
    }
}
