package ru.mirea.ikbo1319.utils

import ru.mirea.ikbo1319.data.RetakeInfo
import ru.mirea.ikbo1319.dto.RetakeInfoDTO


fun createRetakeInfoDTO(retakeInfo: RetakeInfo): RetakeInfoDTO {
    return RetakeInfoDTO(retakeInfo)
}
