package com.example.b2012051_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.view.View
import kotlin.io.println;


class MainActivity : AppCompatActivity() {
    private var lastNumeric: Boolean = false;
    private var lastDot: Boolean = false;
    private var tvInput: TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        if (view is Button) {
            //println(view);
            //println(view.text);
            tvInput?.append(view.text);
            lastNumeric = true;
        }
    }

    fun onDecimalPoint(view: View) {
        if (view is Button && !lastDot) {
            tvInput?.append(view.text)
            lastNumeric = false;
            lastDot = true;
        }
    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(tvInput?.text.toString())) {
            if (view is Button) {
                tvInput?.append(view.text);
                lastNumeric = false
                lastDot = false;
            }
        }
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            val input = tvInput?.text.toString()
            var result: Any

            try {
                result = when {
                    input.contains('+') -> {
                        val parts = input.split('+')
                        val num1 = parts[0].toDouble()
                        val num2 = parts[1].toDouble()
                        num1 + num2
                    }

                    input.contains('-') -> {
                        val parts = input.split('-')
                        val num1 = parts[0].toDouble()
                        val num2 = parts[1].toDouble()
                        num1 - num2
                    }

                    input.contains('*') -> {
                        val parts = input.split('*')
                        val num1 = parts[0].toDouble()
                        val num2 = parts[1].toDouble()
                        num1 * num2
                    }
                    input.contains('/') -> {
                        val parts = input.split('/')
                        val num1 = parts[0].toDouble()
                        val num2 = parts[1].toDouble()
                        if (num2 != 0.0) {
                            num1 / num2
                        } else {
                            "Không thể chia cho 0.";
                        }
                    }
                    else -> "Không thể tính.";
                }
            } catch (e: Exception) {
                result = 0.0;
            }
            tvInput?.text = removeZeroAfterDot(result.toString());
        }
    }

    fun onClear(view: View) {
        tvInput?.text = "";
        lastDot = false;
        lastNumeric = false;
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result;
        if (result.endsWith(".0")) {
            value = result.substring(0, result.length - 2)
        }
        return value;
    }

    private fun isOperatorAdded(value: String): Boolean {
        return value.endsWith("+") || value.endsWith("-") || value.endsWith("*") || value.endsWith("/");
    }
}