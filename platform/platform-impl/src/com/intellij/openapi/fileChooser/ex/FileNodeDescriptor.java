// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.openapi.fileChooser.ex;

import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.openapi.fileChooser.FileElement;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.SimpleTextAttributes;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class FileNodeDescriptor extends NodeDescriptor {

  private FileElement myFileElement;
  private final Icon myOriginalIcon;
  private final String myComment;

  public FileNodeDescriptor(Project project,
                            @NotNull FileElement element,
                            NodeDescriptor parentDescriptor,
                            Icon closedIcon,
                            String name,
                            String comment) {
    super(project, parentDescriptor);
    myOriginalIcon = closedIcon;
    myComment = comment;
    myFileElement = element;
    myName = name;
  }

  @Override
  public boolean update() {
    boolean changed = false;

    // special handling for roots with names (e.g. web roots)
    if (myName == null || myComment == null) {
      final String newName = myFileElement.toString();
      if (!newName.equals(myName)) changed = true;
      myName = newName;
    }

    VirtualFile file = myFileElement.getFile();

    if (file == null) return true;

    setIcon(myOriginalIcon);
    if (myFileElement.isHidden()) {
      setIcon(IconLoader.getTransparentIcon(getIcon()));
    }
    myColor = myFileElement.isHidden() ? SimpleTextAttributes.DARK_TEXT.getFgColor() : null;
    return changed;
  }

  @Override
  @NotNull
  public final FileElement getElement() {
    return myFileElement;
  }

  protected final void setElement(FileElement descriptor) {
    myFileElement = descriptor;
  }

  public @Nls String getComment() {
    return myComment;
  }
}
