package com.example.todoapp.presentation.screen.plans

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.local.entity.ExtraPlan
import com.example.todoapp.domain.usecase.ExtraPlanUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExtraPlanViewModel @Inject constructor(
    private val extraPlanUseCases: ExtraPlanUseCases
) : ViewModel() {
    
    private val _plans = MutableStateFlow<List<ExtraPlan>>(emptyList())
    val plans: StateFlow<List<ExtraPlan>> = _plans
    
    private val _randomPlan = MutableStateFlow<ExtraPlan?>(null)
    val randomPlan: StateFlow<ExtraPlan?> = _randomPlan
    
    init {
        loadPlans()
    }
    
    fun loadPlans() {
        viewModelScope.launch {
            extraPlanUseCases.getAllPlans().collectLatest { planList ->
                _plans.value = planList
            }
        }
    }
    
    fun selectRandomPlan() {
        viewModelScope.launch {
            val plan = extraPlanUseCases.getRandomPlan()
            _randomPlan.value = plan
        }
    }
    
    fun completeRandomPlan(plan: ExtraPlan) {
        viewModelScope.launch {
            extraPlanUseCases.deletePlan(plan)
            _randomPlan.value = null
        }
    }
    
    fun deletePlan(plan: ExtraPlan) {
        viewModelScope.launch {
            extraPlanUseCases.deletePlan(plan)
            if (_randomPlan.value?.id == plan.id) {
                _randomPlan.value = null
            }
        }
    }
    
    fun createPlan(title: String, description: String? = null) {
        viewModelScope.launch {
            extraPlanUseCases.createPlan(title, description)
        }
    }
}