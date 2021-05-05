package com.marvelapp.domain.commands

interface Command<out T> {
    suspend fun execute(): T
}