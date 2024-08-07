// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.psi;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a Java {@code import module} statement.
 */
@ApiStatus.Experimental
public interface PsiImportModuleStatement extends PsiImportStatementBase {
  /**
   * The empty array of PSI module import statements which can be reused to avoid unnecessary allocations.
   */
  PsiImportModuleStatement[] EMPTY_ARRAY = new PsiImportModuleStatement[0];

  /**
   * Resolves the reference to the module from which members are imported.
   *
   * @return the module from which members are imported, or null if the reference resolve failed
   * or the resolve target is not a module.
   */
  @Nullable
  PsiJavaModule resolveTargetModule();

  /**
   * Returns the name of the member imported from the statement.
   *
   * @return the name of the member.
   */
  @Nullable String getReferenceName();

  /**
   * Returns the reference which specifies the imported module.
   *
   * @return the reference element to the module imported, or null if the reference element is not resolvable or missing.
   */
  @Nullable PsiJavaModuleReferenceElement getModuleReference();

  @Override
  default boolean isOnDemand() {
    return true;
  }

  @Override
  @Nullable default PsiJavaCodeReferenceElement getImportReference() {
    return null;
  }

  @Override
  default boolean isForeignFileImport() {
    return false;
  }
}
