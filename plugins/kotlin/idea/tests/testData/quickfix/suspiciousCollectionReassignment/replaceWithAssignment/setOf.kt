// "Replace with assignment (original is empty)" "false"
// TOOL: org.jetbrains.kotlin.idea.codeInsight.inspections.shared.SuspiciousCollectionReassignmentInspection
// ACTION: Change type to mutable
// ACTION: Replace overloaded operator with function call
// ACTION: Replace with ordinary assignment
// WITH_STDLIB
fun test(otherList: Set<Int>) {
    var list = setOf<Int>(1, 2, 3)
    foo()
    bar()
    list <caret>+= otherList
}

fun foo() {}
fun bar() {}