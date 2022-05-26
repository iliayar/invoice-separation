package com.example.invoicesep

interface  State

class Loading: State
class Success: State
class Failure(val error: Int): State
