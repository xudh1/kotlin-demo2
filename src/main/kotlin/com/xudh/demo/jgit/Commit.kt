package com.xudh.demo.jgit

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import java.io.File

fun main() {
    val file = File("C:\\Users\\xudh\\Desktop\\test\\.git")
    val fileRepository = FileRepository(file)
    val git = Git(fileRepository)
    git.commit().call()
    git.add().call()

}
