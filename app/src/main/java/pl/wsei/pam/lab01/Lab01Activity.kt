package pl.wsei.pam.lab01

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toolbar.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var mLayout: LinearLayout
    lateinit var mTitle: TextView
    lateinit var mButton: Button
    lateinit var mProgress: ProgressBar
    var mBoxes: MutableList<CheckBox> = mutableListOf()
    var mButtons: MutableList<Button> = mutableListOf()
    var passedTests = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mLayout = findViewById(R.id.main)

        mTitle = TextView(this)
        mTitle.text = "Laboratorium 1"
        mTitle.textSize = 24f
        val params = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params.setMargins(20, 20, 20, 20)
        mTitle.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        mTitle.layoutParams = params
        mLayout.addView(mTitle)

        for (i in 1..6) {
            val row= LinearLayout(this)
            row.layoutParams = LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            row.orientation = LinearLayout.HORIZONTAL

            val checkBox = CheckBox(this)
            checkBox.text = "Zadanie ${i}"
            checkBox.isEnabled = false

            mButton = Button(this).also {
                it.text = "Testuj"
                it.textSize = 24f
                it.setOnClickListener({
                    when (i) {
                        1 -> {
                            if (
                                task11(4, 6) in 0.666665..0.666667 &&
                                task11(7, -6) in -1.1666667..-1.1666665
                            ) {
                                if (!mBoxes[0].isChecked) {
                                    mBoxes[0].isChecked = true
                                    passedTests++
                                    mProgress.progress = ((passedTests * 100f) / 6).toInt()
                                }
                            }
                        }

                        2 -> {
                            if (
                                task12(2u, 3u) == "2 + 3 = 5"
                            ) {
                                if (!mBoxes[1].isChecked) {
                                    mBoxes[1].isChecked = true
                                    passedTests++
                                    mProgress.progress = ((passedTests * 100f) / 6).toInt()
                                }
                            }
                        }

                        3 -> {
                            if (
                                task13(2.0, 3.0f) &&
                                !task13(-1.0, 3.0f) &&
                                !task13(4.0, 3.0f)
                            ) {
                                if (!mBoxes[2].isChecked) {
                                    mBoxes[2].isChecked = true
                                    passedTests++
                                    mProgress.progress = ((passedTests * 100f) / 6).toInt()
                                }
                            }
                        }

                        4 -> {
                            if (
                                task14(2, 3) == "2 + 3 = 5" &&
                                task14(-2, -5) == "-2 - 5 = -7"
                            ) {
                                if (!mBoxes[3].isChecked) {
                                    mBoxes[3].isChecked = true
                                    passedTests++
                                    mProgress.progress = ((passedTests * 100f) / 6).toInt()
                                }
                            }
                        }

                        5 -> {
                            if (
                                task15("bardzo dobry") == 5 &&
                                task15("dobry") == 4 &&
                                task15("dostateczny") == 3 &&
                                task15("dopuszczający") == 2 &&
                                task15("niedostateczny") == 1 &&
                                task15("DoBrY") == 4 &&
                                task15("xyz") == -1
                            ) {
                                if (!mBoxes[4].isChecked) {
                                    mBoxes[4].isChecked = true
                                    passedTests++
                                    mProgress.progress = ((passedTests * 100f) / 6).toInt()
                                }
                            }
                        }

                        6 -> {
                            val store = mapOf("A" to 3u, "B" to 4u, "C" to 2u)
                            val asset = mapOf("A" to 1u, "B" to 2u)

                            if (task16(store, asset) == 2u) {
                                if (!mBoxes[5].isChecked) {
                                    mBoxes[5].isChecked = true
                                    passedTests++
                                    mProgress.progress = ((passedTests * 100f) / 6).toInt()
                                }
                            }
                        }
                    }
                })
            }

            row.addView(checkBox)
            row.addView(mButton)

            mLayout.addView(row)
            mBoxes.add(checkBox)
        }

        mProgress = ProgressBar(
            this,
            null,
            androidx.appcompat.R.attr.progressBarStyle,
            androidx.appcompat.R.style.Widget_AppCompat_ProgressBar_Horizontal
        )
        mProgress.max = 100
        mProgress.progress = ((passedTests * 100f) / 6).toInt()

        mLayout.addView(mProgress)


    }

    // Wykonaj dzielenie niecałkowite parametru a przez b
    // Wynik zwróć po instrukcji return
    private fun task11(a: Int, b: Int): Double {
        return a.toDouble() / b
    }

    // Zdefiniuj funkcję, która zwraca łańcuch dla argumentów bez znaku (zawsze dodatnie) wg schematu
    // <a> + <b> = <a + b>
    // np. dla parametrów a = 2 i b = 3
    // 2 + 3 = 5
    private fun task12(a: UInt, b: UInt): String {
        return a.toString() + " + " + b.toString() + " = " + (a + b).toString()
    }

    // Zdefiniu funkcję, która zwraca wartość logiczną, jeśli parametr `a` jest nieujemny i mniejszy od `b`
    fun task13(a: Double, b: Float): Boolean {
        return a >= 0 && a < b.toDouble()
    }

    // Zdefiniuj funkcję, która zwraca łańcuch dla argumentów całkowitych ze znakiem wg schematu
    // <a> + <b> = <a + b>
    // np. dla parametrów a = 2 i b = 3
    // 2 + 3 = 5
    // jeśli b jest ujemne należy zmienić znak '+' na '-'
    // np. dla a = -2 i b = -5
    //-2 - 5 = -7
    // Wskazówki:
    // Math.abs(a) - zwraca wartość bezwględną
    fun task14(a: Int, b: Int): String {
        return if (b < 0) {
            "$a - ${Math.abs(b)} = ${a + b}"
        } else {
            "$a + $b = ${a + b}"
        }
    }

    // Zdefiniuj funkcję zwracającą ocenę jako liczbę całkowitą na podstawie łańcucha z opisem słownym oceny.
    // Możliwe przypadki:
    // bardzo dobry 	5
    // dobry 			4
    // dostateczny 		3
    // dopuszczający 	2
    // niedostateczny	1
    // Funkcja nie powinna być wrażliwa na wielkość znaków np. Dobry, DORBRY czy DoBrY to ta sama ocena
    // Wystąpienie innego łańcucha w degree funkcja zwraca wartość -1
    fun task15(degree: String): Int {
        return when (degree.lowercase()) {
            "bardzo dobry" -> 5
            "dobry" -> 4
            "dostateczny" -> 3
            "dopuszczający" -> 2
            "niedostateczny" -> 1
            else -> -1
        }
    }

    // Zdefiniuj funkcję zwracającą liczbę możliwych do zbudowania egzemplarzy, które składają się z elementów umieszczonych w asset
    // Zmienna store jest magazynem wszystkich elementów
    // Przykład
    // store = mapOf("A" to 3, "B" to 4, "C" to 2)
    // asset = mapOf("A" to 1, "B" to 2)
    // var items = task16(store, asset)
    // println(items)	=> 2 ponieważ do zbudowania jednego egzemplarza potrzebne są 2 elementy "B" i jeden "A", a w magazynie mamy 2 "A" i 4 "B",
    // czyli do zbudowania trzeciego egzemplarza zabraknie elementów typu "B"
    fun task16(store: Map<String, UInt>, asset: Map<String, UInt>): UInt {
        var result = UInt.MAX_VALUE

        for ((name, needed) in asset) {
            val available = store[name] ?: 0u
            val possible = available / needed

            if (possible < result) {
                result = possible
            }
        }

        return result
    }
}