package com.example.seminarmanagementsystem.data.model.bookModel

import android.os.Parcelable
import com.example.seminarmanagementsystem.data.model.bookModel.issueBook.IssuedTo
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Book(
    val Accession_Number: Int,
    val Author: String,
    val Author1: String,
    val Author2: String,
    val Author3: String,
    val Book_Status: Boolean,
    val Category1: String,
    val Category2: String,
    val Category3: String,
    val Edition: String,
    val ID: Int,
    val IssuedTo: @RawValue IssuedTo,
    val MAL_ACC_Number: Int,
    val Publisher: String,
    val Publishing_Year: String,
    val Title: String,
    val _id: String,
    val updatedAt: String
) : Parcelable