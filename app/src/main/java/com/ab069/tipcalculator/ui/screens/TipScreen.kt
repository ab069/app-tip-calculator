package com.ab069.tipcalculator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ab069.tipcalculator.TipViewModel
import java.text.NumberFormat
import java.util.Locale

private val tipPresets = listOf(10, 15, 18, 20, 25)
private val currency = NumberFormat.getCurrencyInstance(Locale.US)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipScreen(viewModel: TipViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tip Calculator") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Bill amount
            OutlinedTextField(
                value = state.billInput,
                onValueChange = viewModel::onBillChanged,
                label = { Text("Bill Amount") },
                prefix = { Text("$") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Tip percentage
            SectionLabel("Tip Percentage")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tipPresets.forEach { pct ->
                    FilterChip(
                        selected = !state.isCustomTip && state.tipPercent == pct,
                        onClick = { viewModel.onTipPresetSelected(pct) },
                        label = { Text("$pct%") },
                        modifier = Modifier.weight(1f)
                    )
                }
                FilterChip(
                    selected = state.isCustomTip,
                    onClick = { viewModel.onCustomTipSelected() },
                    label = { Text("Custom") },
                    modifier = Modifier.weight(1f)
                )
            }

            if (state.isCustomTip) {
                OutlinedTextField(
                    value = state.customTipInput,
                    onValueChange = viewModel::onCustomTipChanged,
                    label = { Text("Custom Tip %") },
                    suffix = { Text("%") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Split
            SectionLabel("Split Between")
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FilledIconButton(
                    onClick = viewModel::onPeopleDecrement,
                    enabled = state.people > 1
                ) { Icon(Icons.Default.Remove, contentDescription = "Decrease") }

                Text(
                    text = "${state.people} ${if (state.people == 1) "person" else "people"}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                FilledIconButton(
                    onClick = viewModel::onPeopleIncrement,
                    enabled = state.people < 99
                ) { Icon(Icons.Default.Add, contentDescription = "Increase") }
            }

            // Round up toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Round up tip", style = MaterialTheme.typography.bodyLarge)
                Switch(
                    checked = state.roundUp,
                    onCheckedChange = viewModel::onRoundUpToggled
                )
            }

            HorizontalDivider()

            // Results card
            if (state.isValid) {
                ResultCard(
                    label = "Tip Amount",
                    value = currency.format(state.tipAmount),
                    subtle = true
                )
                ResultCard(
                    label = "Total Bill",
                    value = currency.format(state.totalBill),
                    subtle = true
                )
                if (state.people > 1) {
                    ResultCard(
                        label = "Each Person Pays",
                        value = currency.format(state.perPerson),
                        subtle = false,
                        highlight = true
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun ResultCard(
    label: String,
    value: String,
    subtle: Boolean,
    highlight: Boolean = false
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        color = if (highlight) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surfaceVariant,
        tonalElevation = if (highlight) 0.dp else 1.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = if (subtle) MaterialTheme.typography.bodyLarge
                        else MaterialTheme.typography.titleMedium,
                color = if (highlight) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = if (highlight) MaterialTheme.typography.headlineSmall
                        else MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = if (highlight) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
