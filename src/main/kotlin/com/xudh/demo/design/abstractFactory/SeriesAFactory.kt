package com.xudh.demo.design.abstractFactory

class SeriesAFactory(override val context: Context) : AbstractFactory {

    override fun makeTextView(): TextView {
        return SeriesATextView(context)
    }

    override fun makeButton(): Button {
        return SeriesAButton(context)
    }
}