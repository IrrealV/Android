package com.estech.notificationssample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.estech.notificationssample.databinding.SecondMainBinding


/**
 * Created by sergi on 02/06/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SecondMainBinding.inflate(layoutInflater).root)
    }
}