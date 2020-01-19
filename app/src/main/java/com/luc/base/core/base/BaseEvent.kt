package com.luc.base.core.base

/**
 * Created by MuhammadLucky on 8/18/2018.
 * For eventbus event
 */
open class BaseEvent(param: Any?) {
    var param: Any? = null

    init {
        this.param = param
    }
}