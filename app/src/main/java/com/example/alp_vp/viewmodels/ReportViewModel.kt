package com.example.alp_vp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.alp_vp.models.ReportModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID


class ReportViewModel: ViewModel() {
    private val _reports = MutableStateFlow<List<ReportModel>>(emptyList())
    val reports: StateFlow<List<ReportModel>> get() = _reports

    fun addReport(userId: String, title: String, description: String, imageUri: String) {
        viewModelScope.launch {
            val newReport = ReportModel(
                id = UUID.randomUUID().toString(),
                userId = userId,
                title = title,
                description = description,
                imageUri = imageUri
            )
            _reports.value = _reports.value + newReport
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ReportViewModel()
            }
        }
    }
}

