var prefs = new gadgets.Prefs();

// configure Automation REST call
var NXRequestParams = {
  operationId: 'Document.PageProvider',
  operationParams: {
    providerName: 'user_deleted_documents',
    pageSize: 5,
    queryParams: '$currentUser',
    documentLinkBuilder: prefs.getString("documentLinkBuilder")
  },
  operationContext: {},
  operationDocumentProperties: "common,dublincore",
  entityType: 'documents',
  usePagination: true,
  displayMethod: displayDocumentList,
  displayColumns: [
    {type: 'builtin', field: 'icon'},
    {type: 'builtin', field: 'titleWithLink', label: prefs.getMsg('label.dublincore.title')},
    {type: 'date', field: 'dc:modified', label: prefs.getMsg('label.dublincore.modified')},
    {type: 'text', field: 'dc:creator', label: prefs.getMsg('label.dublincore.creator')}
  ],
  noEntryLabel: prefs.getMsg('label.gadget.no.document')
};

// execute automation request onload
gadgets.util.registerOnLoadHandler(function() {
  doAutomationRequest(NXRequestParams);
});
