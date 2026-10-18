package com.example.todo

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoRepository(application)

    val todos = mutableStateListOf<Todo>().apply { addAll(repository.load()) }

    fun add(title: String) {
        val trimmed = title.trim()
        if (trimmed.isEmpty()) return
        todos.add(0, Todo(id = System.currentTimeMillis(), title = trimmed))
        persist()
    }

    fun toggle(todo: Todo) {
        val index = todos.indexOfFirst { it.id == todo.id }
        if (index == -1) return
        todos[index] = todo.copy(done = !todo.done)
        persist()
    }

    fun delete(todo: Todo) {
        todos.removeAll { it.id == todo.id }
        persist()
    }

    fun clearCompleted() {
        todos.removeAll { it.done }
        persist()
    }

    private fun persist() = repository.save(todos.toList())
}
