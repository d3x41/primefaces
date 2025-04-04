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
package org.primefaces.component.menu;

import org.primefaces.component.api.MenuItemAware;
import org.primefaces.component.api.RTLAware;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.faces.component.UIPanel;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.FacesEvent;

public abstract class AbstractMenu extends UIPanel implements MenuItemAware, RTLAware {

    public static final String LIST_CLASS = "ui-menu-list ui-helper-reset";
    public static final String MENU_RTL_CLASS = "ui-menu-rtl";
    public static final String MENUITEM_CLASS = "ui-menuitem ui-widget";
    public static final String MENUITEM_LINK_CLASS = "ui-menuitem-link";
    public static final String MENUITEM_TEXT_CLASS = "ui-menuitem-text";
    public static final String MENUITEM_ICON_CLASS = "ui-menuitem-icon ui-icon";
    public static final String TIERED_SUBMENU_CLASS = "ui-widget ui-menuitem ui-menu-parent";
    public static final String TIERED_CHILD_SUBMENU_CLASS = "ui-widget-content ui-menu-list ui-helper-clearfix ui-menu-child ui-shadow";
    public static final String SUBMENU_RIGHT_ICON_CLASS = "ui-icon ui-icon-triangle-1-e";
    public static final String SUBMENU_LEFT_ICON_CLASS = "ui-icon ui-icon-triangle-1-w";
    public static final String SUBMENU_DOWN_ICON_CLASS = "ui-icon ui-icon-triangle-1-s";
    public static final String SUBMENU_LINK_CLASS = "ui-menuitem-link ui-submenu-link";
    public static final String OPTIONS_CLASS = "ui-menuitem ui-menubar-options ui-widget";
    public static final String START_CLASS = "ui-menuitem ui-menubar-start ui-widget";
    public static final String END_CLASS = "ui-menuitem ui-menubar-end ui-widget";

    public enum PropertyKeys {
        tabindex,
        autoDisplay,
        showDelay,
        hideDelay,
        toggleEvent,
        dir;
    }

    public String getTabindex() {
        return (String) getStateHelper().eval(PropertyKeys.tabindex, "0");
    }

    public void setTabindex(String tabindex) {
        getStateHelper().put(PropertyKeys.tabindex, tabindex);
    }

    public boolean isAutoDisplay() {
        return (Boolean) getStateHelper().eval(PropertyKeys.autoDisplay, true);
    }

    public void setAutoDisplay(boolean autoDisplay) {
        getStateHelper().put(PropertyKeys.autoDisplay, autoDisplay);
    }

    public int getShowDelay() {
        return (Integer) getStateHelper().eval(PropertyKeys.showDelay, 0);
    }

    public void setShowDelay(int showDelay) {
        getStateHelper().put(PropertyKeys.showDelay, showDelay);
    }

    public int getHideDelay() {
        return (Integer) getStateHelper().eval(PropertyKeys.hideDelay, 0);
    }

    public void setHideDelay(int hideDelay) {
        getStateHelper().put(PropertyKeys.hideDelay, hideDelay);
    }

    public String getToggleEvent() {
        return (String) getStateHelper().eval(PropertyKeys.toggleEvent, null);
    }

    public void setToggleEvent(String toggleEvent) {
        getStateHelper().put(PropertyKeys.toggleEvent, toggleEvent);
    }

    @Override
    public String getDir() {
        return (String) getStateHelper().eval(PropertyKeys.dir, "ltr");
    }

    public void setDir(String dir) {
        getStateHelper().put(PropertyKeys.dir, dir);
    }

    @Override
    public List<MenuElement> getElements() {
        MenuModel model = getModel();
        if (model != null) {
            return model.getElements();
        }
        else {
            return getChildren().stream()
                        .filter(MenuElement.class::isInstance)
                        .map(MenuElement.class::cast)
                        .collect(Collectors.toList());
        }
    }

    public int getElementsCount() {
        List<MenuElement> elements = getElements();

        return (elements == null) ? 0 : elements.size();
    }

    public abstract MenuModel getModel();

    public boolean isDynamic() {
        return getValueExpression("model") != null;
    }

    @Override
    public void broadcast(FacesEvent event) throws AbortProcessingException {
        broadcastMenuActionEvent(event, getFacesContext(), super::broadcast);
    }
}
