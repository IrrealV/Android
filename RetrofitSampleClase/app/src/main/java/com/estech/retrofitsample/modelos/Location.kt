package com.estech.retrofitsample.modelos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by sergi on 05/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Parcelize
class Location(val name: String, val url: String) : Parcelable {
}