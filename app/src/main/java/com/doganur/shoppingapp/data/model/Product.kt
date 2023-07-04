package com.doganur.shoppingapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/*
modeli taşınabiilir hale getirmek için parceliazable veya serializable hale getirmemiz gerekiyor. Yani direkt olduğu gibi taşıyamıyoruz
ya byteArray ya da Stringe dönüştürmemiz lazım. String'e çevirip taşımak serializable, byteArray'e çevirip taşımak parcelilizable bu seçenek 10kat
daha avantajlı
 */

@Parcelize
data class Product(
    val description: String?,
    val id: String?,
    val image: String?,
    val price: String?,
    val title: String?
) : Parcelable