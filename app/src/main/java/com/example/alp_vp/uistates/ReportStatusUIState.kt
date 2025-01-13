
package com.example.alp_vp.uistates

import com.example.alp_vp.models.ReportModel

interface ReportStatusUIState {
    data class Success(val data: String): ReportStatusUIState
    object Start: ReportStatusUIState
    object Loading: ReportStatusUIState
    data class Failed(val errorMessage:String): ReportStatusUIState
}
