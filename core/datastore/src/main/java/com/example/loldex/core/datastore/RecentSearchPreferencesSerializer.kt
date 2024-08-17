package com.example.loldex.core.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class RecentSearchPreferencesSerializer @Inject constructor(

) : Serializer<RecentSearchPreferences> {
    override val defaultValue: RecentSearchPreferences =
        RecentSearchPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): RecentSearchPreferences =
        try {
            RecentSearchPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: RecentSearchPreferences, output: OutputStream) {
        t.writeTo(output)
    }
}