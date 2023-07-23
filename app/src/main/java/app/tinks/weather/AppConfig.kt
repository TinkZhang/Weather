package app.tinks.weather

import app.tinks.weather.model.City

object AppConfig {
    val cityList = listOf(
        City("010", "110000", "北京"),
        City("021", "310000", "上海"),
        City("020", "440100", "广州"),
        City("0755", "440300", "深圳"),
        City("0512", "320500", "苏州"),
        City("024", "210100", "沈阳"),
    )
}