package ru.mirea.ikbo1319.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.mirea.ikbo1319.data.RetakeInfo

interface RetakeInfoRepository : JpaRepository<RetakeInfo, Long>