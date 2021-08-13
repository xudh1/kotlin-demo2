package net.canway.devops.common.starter.utils.test


class ProxyTestImpl2:ProxyTest {
    override fun test():List<String>{
        println("2222")
        val list = ArrayList<String>()
        list.add("111")
        return list
    }
}


