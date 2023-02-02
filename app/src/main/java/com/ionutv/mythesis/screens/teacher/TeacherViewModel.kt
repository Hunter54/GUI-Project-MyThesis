package com.ionutv.mythesis.screens.teacher

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ionutv.mythesis.screens.teacher.thesis.ThesisItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeacherViewModel : ViewModel() {

    private val _thesises = MutableStateFlow(listOf<ThesisItem>())
    val thesises: StateFlow<List<ThesisItem>> get() = _thesises.asStateFlow()

    private val _studentProposedThesises = MutableStateFlow(listOf<ThesisItem>())
    val studentProposedThesis: StateFlow<List<ThesisItem>> get() = _studentProposedThesises

    private val _proposedThesises = MutableStateFlow(listOf<ThesisItem>())
    val proposedThesises: StateFlow<List<ThesisItem>> get() = _proposedThesises.asStateFlow()


    private val _revealedThesisesIdsList = MutableStateFlow(listOf<ThesisItem>())
    val revealedThesisesIdsList: StateFlow<List<ThesisItem>> get() = _revealedThesisesIdsList.asStateFlow()

    private val thesisItems = listOf(
        ThesisItem(
            ThesisItem.Owner("Jhon Doe", Color.Cyan.copy(0.2f)),
            "Study of AI use in Crimes",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "10 Jan",
            true
        ),
        ThesisItem(
            ThesisItem.Owner("Jhoana Doeser", Color.Cyan.copy(0.2f)),
            "Study of AI use in Crimes against the city ",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with youLorem ipsum salvador ipsum lore impas amesta von comen into the room with youLorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "10 Jan",
            false
        ),
        ThesisItem(
            ThesisItem.Owner("Jhoana Doeser", Color.Cyan.copy(0.2f)),
            "Study of AI use in Crimes against the city but this text is too long to fit",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "Lorem ipsum salvador ipsum lore impas amesta von comen into the room with youLorem ipsum salvador ipsum lore impas amesta von comen into the room with youLorem ipsum salvador ipsum lore impas amesta von comen into the room with you",
            "10 Jan",
            true
        )
    )

    init {
        getFakeData()
    }

    private fun getFakeData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val testList = arrayListOf<ThesisItem>()
                var n = 1
                repeat(5) {
                    testList += buildList {
                        thesisItems.forEach {
                            add(it.copy(owner = it.owner.copy(name = it.owner.name + n)))
                        }
                    }
                    n++
                }
                _thesises.update {
                    testList
                }
                _studentProposedThesises.update {
                    testList
                }
                _proposedThesises.update {
                    testList
                }
            }
        }
    }

    fun onItemExpanded(cardId: ThesisItem) {
        if (_revealedThesisesIdsList.value.contains(cardId)) return
        _revealedThesisesIdsList.value =
            _revealedThesisesIdsList.value.toMutableList().also { list ->
                list.add(cardId)
            }
    }

    fun onItemCollapsed(cardId: ThesisItem) {
        if (!_revealedThesisesIdsList.value.contains(cardId)) return
        _revealedThesisesIdsList.value =
            _revealedThesisesIdsList.value.toMutableList().also { list ->
                list.remove(cardId)
            }
    }
}