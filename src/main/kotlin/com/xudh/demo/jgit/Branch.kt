package com.xudh.demo.jgit

import org.eclipse.jgit.api.CreateBranchCommand
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.internal.storage.file.FileRepository
import java.io.File

fun main() {
    val file = File("C:\\Users\\xudh\\Desktop\\test\\.git")
    val fileRepository = FileRepository(file)
    val git = Git(fileRepository)
    val branchCreate1 = git.branchCreate()
            //c755afae2373430d84a05361cf874f9b924943b5
            //69ded0eeecf43c282git 45820c6490f862f197f4d65
        .setForce(true).setName("dev3").setStartPoint("TEST3").setUpstreamMode(
            CreateBranchCommand.SetupUpstreamMode.TRACK).call()
//    val branchCreate = git.branchCreate()
//        .setForce(false).setName("dev2-derivative").set.setStartPoint().setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).call()
    println(branchCreate1)
    println(branchCreate1.isPeeled)
    println(branchCreate1.isSymbolic)
    println(branchCreate1.leaf)
    println(branchCreate1.name)
    println(branchCreate1.objectId)
    println(branchCreate1.peeledObjectId)
    println(branchCreate1.storage)
    println(branchCreate1.target)
    println(branchCreate1.updateIndex)

//    git.branchDelete().setBranchNames("222").setForce(true).call()
//    git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call()
//    git.branchRename().setNewName("test").call()
}