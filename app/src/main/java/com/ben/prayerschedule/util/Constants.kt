package com.ben.prayerschedule.util

import androidx.annotation.IntDef
import androidx.annotation.StringDef


class Constants {

    companion object {

        @StringDef(CLIENT, REFRESH)
        @Retention(AnnotationRetention.SOURCE)
        annotation class GrantType

        const val CLIENT = "client_credentials"
        const val REFRESH = "refresh_token"

        @IntDef(PERMISSION)
        @Retention(AnnotationRetention.SOURCE)
        annotation class RequestCode

        const val PERMISSION = 999

        @IntDef(MIN)
        @Retention(AnnotationRetention.SOURCE)
        annotation class Limit

        const val MIN = 10
        const val FIRST_PAGE = 0

        @IntDef
        @Retention(AnnotationRetention.SOURCE)
        annotation class MyCampaignStatuses

        const val DRAFT = 0
        const val SENT = 1
        const val CANCELED = 2
        const val SCHEDULED = 3
        const val ACTIVE = 4
        const val ARCHIVE = 5

        @StringDef
        @Retention(AnnotationRetention.SOURCE)
        annotation class Expand

        const val CONCEPT_COUNT = "conceptsCount"

        @StringDef(AGENCY, BRAND, LOCATION_OWNER)
        @Retention(AnnotationRetention.SOURCE)
        annotation class Roles

        const val AGENCY = "agency"
        const val BRAND = "brand"
        const val LOCATION_OWNER = "location-owner"
        const val ADMIN = "admin"


        @IntDef
        @Retention(AnnotationRetention.SOURCE)
        annotation class WebViewContent

        const val TERMS_OF_USE = 0
        const val POLICY = 1
        const val BANK_TRANCFER = 2
        const val PAYPAL_TRANCFER = 3
        const val CREDITCARD_TRANCFER = 4

        @IntDef(NON_VERIFIED, VERIFIED, REJECTED)
        @Retention(AnnotationRetention.SOURCE)
        annotation class User

        const val NON_VERIFIED = 0
        const val VERIFIED = 1
        const val REJECTED = 2
    }
}