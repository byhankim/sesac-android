package com.han.layoutcalculator

import android.app.ListActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.*

class MainActivity : ListActivity() {

    private val treeMaps = TreeMap<String, Intent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addActionMap("1. LinearLayout 계산기", LinearLayoutCalculator::class.java)
        addActionMap("2. LL 입력폼", LinearLayoutForm::class.java)
        addActionMap("3. RL 입력폼", RelativeLayoutForm::class.java)

        val keys = treeMaps.keys
        val keyNames: Array<String> = keys.toTypedArray()
        listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, keyNames)
    }

    private fun addActionMap(keyName: String, className: Class<*>) {
        treeMaps[keyName] = Intent(this, className)
    }

    override fun onListItemClick(listView: ListView, item: View?, position: Int, id: Long) {
        val keyName = listView.getItemAtPosition(position) as String
        startActivity(treeMaps[keyName])
    }
}