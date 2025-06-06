# TreeTable

Treetable is is used for displaying hierarchical data in tabular format.

[See this widget in the JavaScript API Docs.](../jsdocs/classes/src_PrimeFaces.PrimeFaces.widget.TreeTable-1.html)

## Info

| Name | Value |
| --- | --- |
| Tag | treeTable
| Component Class | org.primefaces.component.treetable.TreeTable
| Component Type | org.primefaces.component.TreeTable
| Component Family | org.primefaces.component |
| Renderer Type | org.primefaces.component.TreeTableRenderer
| Renderer Class | org.primefaces.component.treetable.TreeTableRenderer

## Attributes

| Name | Default | Type | Description |
| --- | --- | --- | --- |
id | null | String | Unique identifier of the component
allowUnsorting | false | Boolean | Defines whether columns are allowed to be unsorted. Default is false.
binding | null | Object | An el expression that maps to a server side UIComponent instance in a backing bean
cellSeparator | null | String | Separator text to use in output mode of editable cells with multiple components.
currentPageReportTemplate | null | String | Template of the currentPageReport UI.
dataLocale | null | Object | Locale to be used in features such as sorting, defaults to view locale.
disabledTextSelection | true | Boolean | Disables text selection on row click.
editable | false | Boolean | Controls incell editing.
editingRow | false | Boolean | Defines if cell editors of row should be displayed as editable or not. Default is false meaning display mode
editInitEvent | null | String | Defines a client side event to open cell on editable treetable.
editMode | row | String | Defines edit mode, valid values are "row" (default) and "cell".
emptyMessage | No records found | String | Text to display when there is no data to display.
expandMode | children | String | Updates children only when set to “children” or the node itself with children when set to “self” on node expand.
exportTag | null | String | If XML data exporter in use, this allows customization of the document tag in the XML.
exportRowTag | null | String | If XML data exporter in use, this allows customization of the row tag in the XML.
filterBy | null | SortMeta / Collection<SortMeta> | Property to be used for default sorting. Expects a single or a collection of SortMeta.
filterDelay | 300 | Integer | Delay in milliseconds before sending an ajax filter query.
filterEvent | keyup | String | Event triggering filter for input filters. If "enter" it will only filter after ENTER key is pressed.
filteredValue  | null | TreeNode | TreeNode to keep filtered data.
filterNormalize | false | Boolean | Defines if filtering would be done using normalized values (accents will be removed from characters). Default is false.
first | 0 | Integer | Index of the first data to display.
globalFilter | null | String | Value of the global filter to use when filtering by default.
globalFilterFunction | null | MethodExpression | Custom implementation to globally filter a value against a constraint.
globalFilterOnly | false | Boolean | When true this will hide all column filters and allow all columns to be filtered by global filter only.
liveResize | false | Boolean | Columns are resized live in this mode without using a resize helper.
nativeElements | false | Boolean | In native mode, treetable uses native checkboxes.
nodeVar | null | String | Name of the request-scoped variable that'll be used to refer current treenode using EL.
pageLinks | 10 | Integer | Maximum number of page links to display.
paginatorAlwaysVisible | true | Boolean | Defines if paginator should be hidden if total data count is less than number of rows per page.
paginatorPosition | both | String | Position of the paginator.
paginatorTemplate | null | String | Template of the paginator.
propagateSelectionUp | true | Boolean | Defines upwards selection propagation for checkbox mode.
propagateSelectionDown | true | Boolean | Defines downwards selection propagation for checkbox mode.
rendered | true | Boolean | Boolean value to specify the rendering of the component, when set to false component will not be rendered.
required | false | Boolean | Validation constraint for selection.
requiredMessage | null | String | Message for required selection validation.
resizableColumns | false | Boolean | Defines if columns can be resized or not.
resizeMode | null | String | Defines the resize behavior, valid values are "fit" (default) and expand.
rows | 0 | Integer | Number of rows to display per page. Default value is 0 meaning to display all data available.
rowsPerPageTemplate | null | String | Template of the rowsPerPage dropdown.
rowStyleClass | null | String | Style class for each row.
saveOnCellBlur | true | Boolean | Saves the changes in cell editing on blur, when set to false changes are discarded.
scrollable | false | Boolean | Whether or not the data should be scrollable.
scrollHeight | null | Integer | Height of scrollable data.
scrollWidth | null | Integer | Width of scrollable data.
selection | null | Object | Selection reference.
selectionMode | null | String | Type of selection mode.
showGridlines | false | Boolean | When enabled, cell borders are displayed.
showUnselectableCheckbox | false | Boolean | Defines if in checkbox selection mode, a readonly checkbox should be displayed for an unselectable node.
size | regular | String | Size of the table content, valid values are "small" and "large". Leave empty for regular size.
skipChildren | false | Boolean | Ignores processing of children during lifecycle, improves performance if table only has output components.
sortBy | null | FilterMeta / Collection<FilterMeta> | Property to be used for default sorting. Expects a single or a collection of SortMeta.
sortMode | multiple | String | Defines sorting mode, valid values are _single_ and _multiple_.
stickyHeader | false | Boolean | Sticky header stays in window viewport during scrolling.
style | null | String | Inline style of the container element.
styleClass | null | String | Style class of the container element.
tableStyle | null | String | Inline style of the table element.
tableStyleClass | null | String | Style class of the table element.
touchable | null | Boolean | Enable touch support (if the browser supports it). Default is the global primefaces.TOUCHABLE, which can be overwritten on component level.
value | null | Object | A TreeNode instance as the backing model.
var | null | String | Name of the request-scoped variable used to refer each treenode.
widgetVar | null | String | Name of the client side widget.

## Getting started with the TreeTable
Similar to the Tree, TreeTable is populated with an _org.primefaces.model.TreeNode_ instance that
corresponds to the root node. TreeNode API has a hierarchical data structure and represents the data
to be populated in tree. For an example, model to be displayed is a collection of documents similar
as in tree section.

```java
public class Document {
    private String name;
    private String size;
    private String type;
    //getters, setters
}
```

```xhtml
<p:treeTable value="#{bean.root}" var="document">
    <p:column>
        <f:facet name="header">
            Name
        </f:facet>
        <h:outputText value="#{document.name}" />
    </p:column>
    //more columns
</p:treeTable>
```
## Selection
Node selection is a built-in feature of tree and it supports two different modes. Selection should be a
TreeNode for single case and an array of TreeNodes for multiple case, tree finds the selected nodes
and assign them to your selection model.

_single_ : Only one at a time can be selected, selection should be a TreeNode reference.
_multiple_ or _checkbox_ : Multiple nodes can be selected, selection should be a TreeNode[] reference.

As checkbox selection have a special hierarchy, use _CheckboxTreeNode_ in checkbox mode.

## Paginator
Node hierarchy can be displayed as paged using paginator. Usage is similar to DataTable, DataList
and DataGrid where paginator is enabled by setting paginator=”true” and defining a rows property
to define the number of nodes to be displayed per page.

## Ajax Behavior Events

TreeTable provides various ajax behavior events to respond user actions.

| Event | Listener Parameter | Fired |
| --- | --- | --- |
expand | org.primefaces.event.NodeExpandEvent | When a node is expanded.
collapse | org.primefaces.event.NodeCollapseEvent | When a node is collapsed.
select | org.primefaces.event.NodeSelectEvent | When a node is selected.
unselect | org.primefaces.event.NodeUnselectEvent | When a node is unselected.
contextMenu | org.primefaces.event.NodeSelectEvent | ContextMenu display.
colResize | org.primefaces.event.ColumnResizeEvent | When a column is resized.
sort | org.primefaces.event.SortEvent | When data is sorted.
filter | org.primefaces.event.FilterEvent | When data is filtered.
rowEdit | org.primefaces.event.RowEditEvent | On row edit.
rowEditInit | org.primefaces.event.RowEditEvent | When row edit is initialized.
rowEditCancel | org.primefaces.event.RowEditEvent | When row edit is cancelled.
cellEdit | org.primefaces.event.CellEditEvent | On cell edit.
page | org.primefaces.event.data.PageEvent | On pagination.

## Client Side API
Widget: _PrimeFaces.widget.TreeTable_

| Method | Params | Return Type | Description |
| --- | --- | --- | --- |
| clearFilters() | - | void | Clears all column filters

## ContextMenu
TreeTable has special integration with context menu, you can even match different context menus
with different tree nodes using _nodeType_ option of context menu that matches the tree node type.

## Scrolling
Scrollable TreeTable implementation is same as DataTable Scrollable, refer to scrolling part in
DataTable section for detailed information.

## Dynamic Columns
TreeTable supports dynamic columns via p:columns component, refer to dynamic columns in
DataTable section for detailed information.

## Column Order / Priorities
Columns can be ordered via _displayPriority_. Lower value means higher priority.

```xhtml
<p:treeTable value="#{bean.root}" var="document">
    <p:column displayPriority="4">
        <h:outputText value="#{document.name}" />
    </p:column>
    <p:column displayPriority="0">
        <h:outputText value="#{document.type}" />
    </p:column>
</p:treeTable>
```

## Responsive TreeTable
In priority mode, responsiveness is based on column _responsivePriority_ that vary between 1 and 6. Lower value means higher priority.

## Sorting
Sorting is enabled by setting _sortBy_ expressions at column level.

```xhtml
<p:treeTable value="#{bean.root}" var="document">
    <p:column sortBy="#{document.name}">
        <h:outputText value="#{document.name}" />
    </p:column>
    //more columns
</p:treeTable>
```
In case you'd like to display treeTable as sorted on page load use sortBy attribute of treeTable,
optional _sortOrder_ and _sortFunction_ attributes are provided to define the default sort order
(ascending or descinding) and a java method to do the actual sorting respectively. Refer to datatable
sorting section for an example usage of _sortFunction_.

## Editing
Similar to DataTable, TreeTable supports row and cell based editing. Refer to datatable for more
information.

## Skinning
TreeTable content resides in a container element which style and styleClass attributes apply.
Following is the list of structural style classes;

| Class | Applies |
| --- | --- |
.ui-treetable | Main container element.
.ui-treetable-header | Header of treetable.
.ui-treetable-data | Body element of the table containing data

As skinning style classes are global, see the main theming section for more information.

