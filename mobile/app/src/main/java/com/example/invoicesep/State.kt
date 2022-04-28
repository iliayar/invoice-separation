package com.example.invoicesep

interface  State<T>

class Loading<T>: State<T>
class Success<T>(val value: T? = null): State<T>
class Failure<T>(val error: Int): State<T>
