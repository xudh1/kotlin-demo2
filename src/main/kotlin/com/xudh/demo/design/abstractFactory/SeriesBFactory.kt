package com.xudh.demo.design.abstractFactory

class SeriesBFactory(override val context: Context) : AbstractFactory {

    override fun makeTextView(): TextView {
        return SeriesBTextView(context)
    }

    override fun makeButton(): Button {
        return SeriesBButton(context)
    }
}