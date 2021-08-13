package net.canway.devops.common.starter.utils.test


class ProxyTestImpl3:ProxyTest {
    override fun test():List<String>{
        println("3333")
        val list = ArrayList<String>()
        list.add("111")
        return list
    }
}

