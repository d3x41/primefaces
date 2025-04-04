/*
 * The MIT License
 *
 * Copyright (c) 2009-2025 PrimeTek Informatics
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.primefaces.visit;

import org.primefaces.component.api.UITree;

import jakarta.faces.component.ContextCallback;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIData;
import jakarta.faces.component.UINamingContainer;
import jakarta.faces.context.FacesContext;

public class UIDataContextCallback implements ContextCallback {

    private String dragId;
    private Object data;

    public UIDataContextCallback(String dragId) {
        this.dragId = dragId;
    }

    @Override
    public void invokeContextCallback(FacesContext fc, UIComponent component) {
        String[] idTokens = dragId.split(String.valueOf(UINamingContainer.getSeparatorChar(fc)));
        String dataId = idTokens[idTokens.length - 2];

        if (component instanceof UITree) {
            UITree uiTree = (UITree) component;
            uiTree.setRowKey(dataId);
            data = uiTree.getRowNode();
            uiTree.setRowKey(null);
        }
        else {
            UIData uiData = (UIData) component;
            uiData.setRowIndex(Integer.parseInt(dataId));
            data = uiData.getRowData();
            uiData.setRowIndex(-1);
        }
    }

    public Object getData() {
        return data;
    }
}
