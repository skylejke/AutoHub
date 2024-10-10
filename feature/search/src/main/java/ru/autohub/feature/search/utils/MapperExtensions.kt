package ru.autohub.feature.search.utils

import ru.autohub.core.storage.model.SearchHistoryDto
import ru.autohub.feature.search.model.SearchHistoryVo




internal fun SearchHistoryDto.mapToSearchHistoryVo(): SearchHistoryVo =
    SearchHistoryVo(query = query)
