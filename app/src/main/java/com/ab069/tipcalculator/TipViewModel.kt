package com.ab069.tipcalculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.ceil

data class TipUiState(
    val billInput: String = "",
    val tipPercent: Int = 18,
    val isCustomTip: Boolean = false,
    val customTipInput: String = "",
    val people: Int = 1,
    val roundUp: Boolean = false
) {
    private val bill: Double get() = billInput.toDoubleOrNull() ?: 0.0
    private val tip: Double get() = if (isCustomTip) {
        (customTipInput.toDoubleOrNull() ?: 0.0) / 100.0
    } else {
        tipPercent / 100.0
    }

    val tipAmount: Double get() {
        val raw = bill * tip
        return if (roundUp) ceil(raw) else raw
    }

    val totalBill: Double get() = bill + tipAmount
    val perPerson: Double get() = if (people > 0) totalBill / people else totalBill
    val isValid: Boolean get() = bill > 0
}

class TipViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TipUiState())
    val uiState: StateFlow<TipUiState> = _uiState.asStateFlow()

    fun onBillChanged(value: String) {
        if (value.matches(Regex("^\\d*\\.?\\d{0,2}$"))) {
            _uiState.update { it.copy(billInput = value) }
        }
    }

    fun onTipPresetSelected(percent: Int) {
        _uiState.update { it.copy(tipPercent = percent, isCustomTip = false, customTipInput = "") }
    }

    fun onCustomTipSelected() {
        _uiState.update { it.copy(isCustomTip = true) }
    }

    fun onCustomTipChanged(value: String) {
        if (value.matches(Regex("^\\d{0,3}$"))) {
            _uiState.update { it.copy(customTipInput = value) }
        }
    }

    fun onPeopleIncrement() {
        _uiState.update { it.copy(people = (it.people + 1).coerceAtMost(99)) }
    }

    fun onPeopleDecrement() {
        _uiState.update { it.copy(people = (it.people - 1).coerceAtLeast(1)) }
    }

    fun onRoundUpToggled(checked: Boolean) {
        _uiState.update { it.copy(roundUp = checked) }
    }
}
