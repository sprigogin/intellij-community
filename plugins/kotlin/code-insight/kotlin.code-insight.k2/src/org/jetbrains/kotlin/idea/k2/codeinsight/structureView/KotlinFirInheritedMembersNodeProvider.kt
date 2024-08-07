// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

package org.jetbrains.kotlin.idea.k2.codeinsight.structureView

import com.intellij.ide.util.InheritedMembersNodeProvider
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.psi.NavigatablePsiElement
import org.jetbrains.kotlin.analysis.api.analyze
import org.jetbrains.kotlin.analysis.api.symbols.KaClassSymbol
import org.jetbrains.kotlin.analysis.api.symbols.KaSymbolOrigin
import org.jetbrains.kotlin.psi.KtClassOrObject

class KotlinFirInheritedMembersNodeProvider : InheritedMembersNodeProvider<TreeElement>() {
    override fun provideNodes(node: TreeElement): Collection<TreeElement> {
        if (node !is KotlinFirStructureViewElement) return listOf()

        val ktClassOrObject = node.element as? KtClassOrObject ?: return listOf()

        analyze(ktClassOrObject) {
            
            val children = mutableListOf<TreeElement>()
            val descriptor = ktClassOrObject.symbol as? KaClassSymbol ?: return listOf()

            for (memberSymbol in descriptor.memberScope.declarations) {
                if (memberSymbol.origin == KaSymbolOrigin.INTERSECTION_OVERRIDE) continue
                if (memberSymbol is KaClassSymbol) continue
                val psi = memberSymbol.psi
                if (psi is NavigatablePsiElement) {
                    children.add(KotlinFirStructureViewElement(psi, ktClassOrObject, memberSymbol.createPointer(), isInherited = true))
                }
            }

            return children
        }
    }
}