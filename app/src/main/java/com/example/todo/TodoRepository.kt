package com.example.todo

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

data class Todo(val id: Long, val title: String, val done: Boolean = false)

/** Saves todos on the phone as JSON in SharedPreferences. */
class TodoRepository(context: Context) {
    private val prefs = context.getSharedPreferences("todos", Context.MODE_PRIVATE)

    fun load(): List<Todo> {
        val json = prefs.getString(KEY, null) ?: return emptyList()
        val array = JSONArray(json)
        return (0 until array.length()).map { i ->
            val o = array.getJSONObject(i)
            Todo(o.getLong("id"), o.getString("title"), o.getBoolean("done"))
        }
    }

    fun save(todos: List<Todo>) {
        val array = JSONArray()
        todos.forEach {
            array.put(JSONObject().put("id", it.id).put("title", it.title).put("done", it.done))
        }
        prefs.edit().putString(KEY, array.toString()).apply()
    }

    private companion object {
        const val KEY = "items"
    }
}
