package com.xudh.demo.design.abstractFactory

fun main() {
    val context = Context()
    val seriesAFactory = SeriesAFactory(context)
    val seriesBFactory = SeriesBFactory(context)
    val buttonA = seriesAFactory.makeButton()
    val textViewA = seriesAFactory.makeTextView()

    val buttonB = seriesBFactory.makeButton()
    val textViewB = seriesBFactory.makeTextView()
}