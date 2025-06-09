package com.example.dashcam

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize

class DashcamViewModelTest : BehaviorSpec({
    Given("a view model using LocalEventService") {
        val viewModel = DashcamViewModel(LocalEventService(), object : SentryService {
            override val isActive = kotlinx.coroutines.flow.MutableStateFlow(false)
            override val events = kotlinx.coroutines.flow.emptyFlow<Event>()
            override fun start() {}
            override fun stop() {}
        })
        When("refresh is called") {
            viewModel.refresh()
            Then("events from the service are exposed") {
                viewModel.events.value.shouldHaveSize(2)
            }
        }
    }
})
