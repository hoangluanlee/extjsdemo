function buildSubjectGrid() {
	var Subject = Ext.data.Record.create([ {
		name : 'id'
	}, {
		name : 'name',
		type : 'string'
	}, {
		name : 'description',
		type : 'string'
	} ]);

	var proxy = new Ext.data.HttpProxy({
		api : {
			read : '../subject/view.action',
			create : '../subject/create.action',
			update : '../subject/update.action',
			destroy : '../subject/delete.action'
		}
	});

	var reader = new Ext.data.JsonReader({
		totalProperty : 'total',
		successProperty : 'success',
		idProperty : 'id',
		root : 'data',
		messageProperty : 'message' // <-- New "messageProperty" meta-data
	}, Subject);

	// The new DataWriter component.
	var writer = new Ext.data.JsonWriter({
		encode : true,
		writeAllFields : true
	});

	// Typical Store collecting the Proxy, Reader and Writer together.
	var store = new Ext.data.Store({
		id : 'user',
		proxy : proxy,
		reader : reader,
		writer : writer, // <-- plug a DataWriter into the store just as you
							// would a Reader
		autoSave : true
	// <-- false would delay executing create, update, destroy requests until
	// specifically told to do so with some [save] buton.
	});

	// read the data from simple array
	store.load();

	Ext.data.DataProxy.addListener('exception', function(proxy, type, action,
			options, res) {
		var errorMsg = res.message;
		if (Ext.isEmpty(errorMsg)) {
			errorMsg = options.reader.jsonData.message;
		}
		Ext.Msg.show({
			title : 'ERROR4',
			msg : errorMsg,
			icon : Ext.MessageBox.ERROR,
			buttons : Ext.Msg.OK
		});
	});

	var editor = new Ext.ux.grid.RowEditor({
		saveText : 'Update'
	});

	// Build menu
	var onDelete = function() {
		editor.stopEditing();
		var grid = Ext.getCmp('subjectGrid');

		var selected = grid.getSelectionModel().getSelectedCell();
		Ext.MessageBox.confirm('Confirm delete', 'Are you sure?',
				function(btn) {
					if (btn == 'yes') {
						var recordToDelete = grid.store.getAt(selected[0]);
						grid.store.remove(recordToDelete);
					}
				});

	};

	var onInsertRecord = function() {
		var newRecord = new Subject({
			name : 'New Subject',
			description : ''
		});
		;

		var grid = Ext.getCmp('subjectGrid');

		editor.stopEditing();
		store.insert(0, newRecord);
		grid.getView().refresh();
		grid.getSelectionModel().select(0);
		editor.startEditing(0);
	};

	var doCellCtxMenu = function(editorGrid, rowIndex, cellIndex, evtObj) {
		evtObj.stopEvent();

		if (!editorGrid.rowCtxMenu) {
			editorGrid.rowCtxMenu = new Ext.menu.Menu({
				items : [ {
					text : 'Insert Record',
					icon : '../images/add.gif',
					handler : onInsertRecord
				}, {
					text : 'Delete Record',
					icon : '../images/delete.gif',
					handler : onDelete
				} ]
			});
		}
		editorGrid.getSelectionModel().select(rowIndex, cellIndex);
		editorGrid.rowCtxMenu.showAt(evtObj.getXY());
	};

	// create grid
	var grid = new Ext.grid.EditorGridPanel({
		id : 'subjectGrid',
		stripeRows : true,
		store : store,
		columns : [ {
			header : "Subject",
			width : 170,
			sortable : true,
			dataIndex : 'name',
			editor : {
				xtype : 'textfield',
				allowBlank : false,
				blankText : 'Subject name field is required field.'
			}
		}, {
			header : "Description",
			width : 160,
			sortable : true,
			dataIndex : 'description',
			editor : {
				xtype : 'textfield',
				allowBlank : true
			}
		} ],
		viewConfig : {
			forcefit : true
		},
		plugins : [ editor ],
		title : 'Subjects list',
		height : 300,
		width : 535,
		frame : true,
		listeners : {
			cellcontextmenu : doCellCtxMenu,
			destroy : function(thisGrid) {
				if (thisGrid.rowCtxMenu) {
					thisGrid.rowCtxMenu.destroy();
				}
			}
		},
		tbar : [ {
			icon : '../images/add.gif',
			text : 'Add Subject',
			handler : onInsertRecord
		}, {
			icon : '../images/delete.gif',
			text : 'Remove Subject',
			handler : onDelete
		} ]
	});

	return grid;
};