function buildStudentGrid() {

	Ext.util.JSON.encodeDate = function(d) {
		return d.dateFormat('Y-m-d');
	};
	var Student = Ext.data.Record.create([ {
		name : 'id'
	}, {
		name : 'name',
		type : 'string'
	}, {
		name : 'surName',
		type : 'string'
	}, {
		name : 'phoneNumber',
		type : 'string'
	}, {
		name : 'birthday',
		type : 'date',
		dateFormat : 'Y-m-d'
	} ]);

	var proxy = new Ext.data.HttpProxy({
		api : {
			read : '../student/view.action',
			create : '../student/create.action',
			update : '../student/update.action',
			destroy : '../student/delete.action'
		}
	});

	var reader = new Ext.data.JsonReader({
		totalProperty : 'total',
		successProperty : 'success',
		idProperty : 'id',
		root : 'data',
		messageProperty : 'message' // <-- New "messageProperty"
	// meta-data
	}, Student);

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
		writer : writer, // <-- plug a DataWriter into the store just
		// as you would a Reader
		autoSave : true
	// <-- false would delay executing create, update, destroy requests
	// until specifically told to do so with some [save] buton.
	});

	// read the data from simple array
	store.load();

	Ext.data.DataProxy.addListener('exception', function(proxy, type, action,
			options, res) {
		Ext.Msg.show({
			title : 'ERROR3',
			msg : res.message,
			icon : Ext.MessageBox.ERROR,
			buttons : Ext.Msg.OK
		});
	});

	

	var editor = new Ext.ux.grid.RowEditor({
		saveText : 'Update',
		onRowClick: function(g, rowIndex, e) {
		    var colIndex = g.getView().findCellIndex(e.getTarget());
		    if (colIndex !== false) {
		      var column = g.getColumnModel().getColumnAt(colIndex);
		      if (column instanceof Ext.grid.ActionColumn) {
		        return false;
		      }
		    }
		    this.constructor.prototype.onRowClick.apply(this, arguments);
		  }
	});

	// Defines phone validator
	Ext.form.VTypes.phoneVal = /^.{0,20}$/;
	Ext.form.VTypes.phoneText = 'Phone length must be between 0 and 20 characters';
	Ext.form.VTypes.phone = function(v) {
		return Ext.form.VTypes.phoneVal.test(v);
	};
	// Build menu
	var onDelete = function() {
		editor.stopEditing();
		var grid = Ext.getCmp('studentGrid');

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
		var newRecord = new Student({
			name : 'New Student',
			phoneNumber : '',
			surName : 'sur name',
			birthday : new Date()
		});

		var grid = Ext.getCmp('studentGrid');

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
		id : 'studentGrid',
		store : store,
		stripeRows : true,
		columns : [ {
			header : "name",
			width : 170,
			sortable : true,
			dataIndex : 'name',
			editor : {
				xtype : 'textfield',
				allowBlank : false,
				blankText : "Student name field is requied"
			}
		}, {
			header : "Sur Name",
			width : 160,
			sortable : true,
			dataIndex : 'surName',
			editor : {
				xtype : 'textfield',
				allowBlank : false,
				blankText : "Sur name field is requied"
			}
		}, {
			header : "Birthday",
			width : 170,
			sortable : true,
			dataIndex : 'birthday',
			renderer : Ext.util.Format.dateRenderer('Y/m/d'),
			editor : new Ext.form.DateField({
				format : 'Y/m/d',
				allowBlank : false
			})
		}, {
			header : "Phone Number",
			width : 170,
			sortable : true,
			dataIndex : 'phoneNumber',
			editor : {
				xtype : 'textfield',
				vtype : 'phone',
				allowBlank : true
			}
		}, {
			header : 'Add Score',
			xtype : 'actioncolumn',
			stopSelection : true,
			width : 100,
			editable : false,
			align : 'center',
			items : [ {
				icon : '../images/score.jpg', // Use a URL in the icon
				// config
				tooltip : 'Add score',
				editable : false,
				handler : showInputScoreDialog
			}, {
				icon : '../images/chart.jpg', // Use a URL in the icon
				// config
				tooltip : 'View chart',
				handler : showScoreChart
			} ]
		} ],
		viewConfig : {
			forcefit : true
		},
		plugins: [editor],
		title : 'Student list',
		layout : 'fit',
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
			text : 'Add Student',
			handler : onInsertRecord
		}, {
			icon : '../images/delete.gif',
			text : 'Remove Student',
			handler : onDelete
		} ]
	});
	// render to DIV
	return grid;

};

function showInputScoreDialog(grid, rowIndex, colIndex) {
	var subjectCombo = buildSubjectComboBox();
	var courseCombo = buildCourseComboBox();
	var store = grid.store;
	var rec = store.getAt(rowIndex);
	var studentId = rec.get('id');

	// numeric
	Ext.form.VTypes['numericVal'] = /^[-+]?\d*\.?\d*$/i;
	Ext.form.VTypes['numericText'] = 'Score must be numeric.';
	Ext.form.VTypes['numericMask'] = /[\-\+0-9.]/;
	Ext.form.VTypes['numeric'] = function(v) {
		if (!Ext.form.VTypes['numericVal'].test(v)) {
			Ext.form.VTypes['numericText'] = 'Score must be numeric.';
			return false;
		} else if (v < 0 || v > 10) {
			Ext.form.VTypes['numericText'] = 'Score must be between 0 and 10';
			return false;
		}
		return true;
	};

	// Builds score grid
	var Score = Ext.data.Record.create([ {
		name : 'id'
	}, {
		name : 'courseId'
	}, {
		name : 'subjectId'
	}, {
		name : 'studentId'
	}, {
		name : 'score',
		type : 'number'
	} ]);

	var proxy = new Ext.data.HttpProxy({
		api : {
			read : '../score/view.action?studentId=' + studentId,
			create : '../score/create.action',
			update : '../score/update.action',
			destroy : '../score/delete.action'
		}
	});

	var reader = new Ext.data.JsonReader({
		totalProperty : 'total',
		successProperty : 'success',
		idProperty : 'id',
		root : 'data',
		messageProperty : 'message' // <-- New "messageProperty" meta-data
	}, Score);

	// The new DataWriter component.
	var writer = new Ext.data.JsonWriter({
		encode : true,
		writeAllFields : true
	});

	// Typical Store collecting the Proxy, Reader and Writer together.
	var scoreStore = new Ext.data.Store({
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
	scoreStore.load();

	Ext.data.DataProxy.addListener('exception', function(proxy, type, action,
			options, res) {
		Ext.Msg.show({
			title : 'ERROR1',
			msg : res.message,
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
		var grid = Ext.getCmp('scoreGrid');

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
		var newRecord = new Score({
			subjectName : '',
			studentId : studentId,
			subjectId : subjectCombo.store.getAt(0).get('id'),
			courseId : courseCombo.store.getAt(0).get('id'),
			score : '0'
		});

		var grid = Ext.getCmp('scoreGrid');

		editor.stopEditing();
		grid.store.insert(0, newRecord);
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
	var gridScore = new Ext.grid.EditorGridPanel({
		id : 'scoreGrid',
		stripeRows : true,
		store : scoreStore,
		viewConfig : {
			forcefit : true
		},
		columns : [ {
			header : "Subject",
			width : 170,
			sortable : true,
			dataIndex : 'subjectId',
			editor : subjectCombo, // specify reference to combo instance
			renderer : Ext.util.Format.comboRenderer(subjectCombo)
		}, {
			header : "Course",
			width : 160,
			sortable : true,
			dataIndex : 'courseId',
			editor : courseCombo,
			renderer : Ext.util.Format.comboRenderer(courseCombo)
		}, {
			header : "Score",
			width : 170,
			sortable : true,
			dataIndex : 'score',
			editor : {
				xtype : 'textfield',
				allowBlank : false,
				vtype : 'numeric'
			}
		} ],

		plugins : [ editor ],
		title : 'Score list',
		height : 300,
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
			text : 'Add Score',
			handler : onInsertRecord
		}, {
			icon : '../images/delete.gif',
			text : 'Remove Subject',
			handler : onDelete
		} ]
	});

	var w = new Ext.Window({
		title : 'Input score for student:' + rec.get('name') + ' '
				+ rec.get('surName'),
		layout : 'form',
		viewConfig : {
			forcefit : true
		},
		modal : true,
		items : [ gridScore ]
	});
	w.show();

	grid.plugins[0].stopEditing();
};

// Show chart
function showScoreChart(grid, rowIndex, colIndex) {
	var rec = grid.store.getAt(rowIndex);
	var w = new Ext.Window({
		title : 'Score chart for student:' + rec.get('name')+ ' ' + rec.get('surName'),
		layout : 'form',
		width : 600,
		hight : 600,
		modal : true,
		items : [ {
			xtype : 'panel',
			layout : 'fit',
			frame : true,
			autoLoad : {
				url : '../score/scorechart.action?studentId=' + rec.get('id')
			}
		} ]
	});
	w.show();

	grid.plugins[0].stopEditing();
};

// Builds subject combo box
function buildSubjectComboBox() {
	// Builds subject combo box
	var Subject = Ext.data.Record.create([ {
		name : 'id'
	}, {
		name : 'name',
		type : 'string'
	}, {
		name : 'description',
		type : 'string'
	}, {
		name : 'numberOfClass',
		type : 'number'
	} ]);

	var subjectDataReader = new Ext.data.JsonReader({
		totalProperty : 'total',
		successProperty : 'success',
		idProperty : 'id',
		root : 'data',
		messageProperty : 'message' // <-- New "messageProperty" meta-data
	}, Subject);

	var subjectStore = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : '../subject/view.action'
		}),
		reader : subjectDataReader,
		remoteSort : false
	});

	subjectStore.load();
	var subjectCombo = new Ext.form.ComboBox({
		store : subjectStore,
		valueField : 'id',
		displayField : 'name',
		hiddenName : 'description',
		mode : 'remote',
		minChars : 0,
		allowBlank : false,
		editable : false,
	});

	return subjectCombo;
}

// Builds subject combo box
function buildCourseComboBox() {
	// Builds subject combo box
	var Course = Ext.data.Record.create([ {
		name : 'id'
	}, {
		name : 'name',
		type : 'string'
	}, {
		name : 'description',
		type : 'string'
	} ]);

	var courseDataReader = new Ext.data.JsonReader({
		totalProperty : 'total',
		successProperty : 'success',
		idProperty : 'id',
		root : 'data',
		messageProperty : 'message' // <-- New "messageProperty" meta-data
	}, Course);

	var courseStore = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : '../course/view.action'
		}),
		reader : courseDataReader,
		remoteSort : false
	});

	courseStore.load();
	var courseCombo = new Ext.form.ComboBox({
		store : courseStore,
		valueField : 'id',
		displayField : 'name',
		hiddenName : 'description',
		mode : 'remote',
		minChars : 0,
		allowBlank : false,
		editable : false,
		listeners : {
			'change' : function(value) {
				alert(value);
			}
		}
	});

	return courseCombo;

}

// create reusable renderer
Ext.util.Format.comboRenderer = function(combo) {
	return function(value) {
		var record = combo.findRecord(combo.valueField, value);
		return record ? record.get(combo.displayField) : '';
	};
};