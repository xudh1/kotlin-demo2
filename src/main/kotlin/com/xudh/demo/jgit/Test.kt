package com.xudh.demo.jgit

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import java.io.File

fun main() {
    val file = File("C:\\Users\\xudh\\Desktop\\test\\.git")
    val fileRepository = FileRepository(file)
    val git = Git(fileRepository)
    val add = git.add().setUpdate(true).addFilepattern("C:\\Users\\xudh\\Desktop\\test\\addTest.txt").call()
}